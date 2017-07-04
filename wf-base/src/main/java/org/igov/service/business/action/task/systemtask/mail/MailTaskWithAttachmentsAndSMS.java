package org.igov.service.business.action.task.systemtask.mail;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.form.FormData;
import org.activiti.engine.form.FormProperty;

import org.apache.commons.mail.ByteArrayDataSource;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.igov.io.mail.Mail;

import javax.activation.DataSource;

import static org.igov.util.ToolLuna.getProtectedNumber;
import static org.igov.service.business.action.task.core.AbstractModelTask.getStringFromFieldExpression;
import org.igov.service.business.action.task.core.ActionTaskService;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.igov.service.exception.RecordNotFoundException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author BW
 */
@Component("MailTaskWithAttachmentsAndSMS")
public class MailTaskWithAttachmentsAndSMS extends Abstract_MailTaskCustom {

    private Expression saAttachmentsForSend;
    protected Expression sPhone_SMS;
    protected Expression sText_SMS;

    private final static Logger LOG = LoggerFactory.getLogger(MailTaskWithoutAttachment.class);

    @Autowired
    ActionTaskService oActionTaskService;

    @Override
    public void execute(DelegateExecution oExecution) throws Exception {

        try {
            ExecutionEntity oExecutionEntity = (ExecutionEntity) oExecution;
            String sTaskId = oExecutionEntity.getActivityId();
            LOG.info("sCurrentActivityId={}", sTaskId);
            List<String> asnTaskId = oActionTaskService
                    .findTaskIDsByActiveAndHistoryProcessInstanceID(
                            Long.valueOf(oExecution.getProcessInstanceId()));
            Map<String, Object> mOnlyDateVariables = new HashMap<>();
            asnTaskId.forEach(snTaskId -> {
                FormData oFormData = oExecution.getEngineServices()
                        .getFormService().getTaskFormData(snTaskId);

                if (oFormData != null) {
                    List<FormProperty> aoFormProperties = oFormData.getFormProperties();
                    aoFormProperties.forEach(oFormProperty -> {
                        String sFormPropertyTypeName = oFormProperty.getType().getName();
                        String sFormPropertyId = oFormProperty.getId();
                        String sFormPropertyValue = oFormProperty.getValue();

                        if (sFormPropertyTypeName.equals("date")) {
                            LOG.info("Date catched. id={}, value={}",
                                    sFormPropertyId, sFormPropertyValue);

                            DateTime oDateTime = DateTime.parse(sFormPropertyValue,
                                    DateTimeFormat.forPattern("dd/MM/yyyy"));
                            String sDate = oDateTime.toString("dd MMMM yyyy", new Locale("uk", "UA"));
                            LOG.info("sDate formated={}", sDate);
                            mOnlyDateVariables.put(sFormPropertyId, sDate);

                        } else if (sFormPropertyTypeName.equals("queueData")) {
                            LOG.info("queueData catched.id={}, value={}",
                                    sFormPropertyId, sFormPropertyValue);
                        }
                    });
                }
            });
            LOG.info("mOnlyDateVariables={}", mOnlyDateVariables);
            oExecution.setVariables(mOnlyDateVariables);
        } catch (RecordNotFoundException oRNFException) {
            LOG.error("Error: {}, occured while looking for a tasks ProcessInstanceId={}",
                    oRNFException.getMessage(), oExecution.getProcessInstanceId());
        } catch (Exception oException) {
            LOG.error("Error: {}, date not formated", oException.getMessage());
        }

        Mail oMail = Mail_BaseFromTask(oExecution);

        String sAttachmentsForSend = getStringFromFieldExpression(this.saAttachmentsForSend, oExecution);

        LOG.info("sAttachmentsForSend after arriving in MailTaskWithAttachmentsAndSMS {}", sAttachmentsForSend);
        LOG.info("Process id is {}", oExecution.getProcessInstanceId());

        try {
            if (sAttachmentsForSend.trim().equals("") || sAttachmentsForSend.equals(" ")
                    || !sAttachmentsForSend.contains("sKey")) {
                LOG.info("Sleeping started..");
                Thread.sleep(2000);
                LOG.info("Variables names {}", oExecution.getVariables() != null
                        ? oExecution.getVariables().keySet() : "null");
                Object oAttachmentsForSendSelected = oExecution.getVariable("result");
                LOG.info("oAttachmentsForSendSelected {}", oAttachmentsForSendSelected != null
                        ? (String) oAttachmentsForSendSelected : "null");
                if (oAttachmentsForSendSelected != null && !((String) oAttachmentsForSendSelected).trim().equals("")) {
                    LOG.info("some sleep always help! {}", oAttachmentsForSendSelected.toString());
                    sAttachmentsForSend = (String) oAttachmentsForSendSelected;
                }
            }
        } catch (Exception ex) {
            LOG.info("Error during sleeping thread in mail {}", ex);
        }

        try {

            LOG.info("sOldAttachmentsForSend: in MailTaskWithAttachmentsAndSMS: " + sAttachmentsForSend.trim());

            String sOldAttachmentsForSend = sAttachmentsForSend.replaceAll("\\{(.*?)\\}\\,", "").replaceAll("\\{(.*?)\\}", "")
                    .replaceAll("^\"|\"$", "").trim();
            LOG.info("MailTaskWithAttachmentsAndSMS: " + sOldAttachmentsForSend);

            if (!sOldAttachmentsForSend.trim().equals("")) {
                List<Attachment> aAttachment = findAddedAttachments(sOldAttachmentsForSend, oExecution.getId());
                if (!aAttachment.isEmpty()) {
                    InputStream oInputStream_Attachment;
                    String sFileName;
                    String sFileExt;
                    String sDescription;
                    for (Attachment oAttachment : aAttachment) {
                        sFileName = oAttachment.getName();
                        String sExt = "";
                        int nAt = sFileName.lastIndexOf(".");
                        if (nAt >= 0) {
                            sExt = sFileName.substring(nAt);
                        }

                        if (!sFileName.toLowerCase().endsWith(".xml") && !sFileName.toLowerCase().endsWith(".rpl")) {
                            sFileName = "Attach_" + oAttachment.getId() + sExt; //
                        }

                        sFileExt = oAttachment.getType().split(";")[0];
                        sDescription = oAttachment.getDescription();
                        if (sDescription == null || "".equals(sDescription.trim())) {
                            sDescription = "(no description)";
                        }
                        LOG.info("Old attach whith name: " + sFileName + " and with id: " + oAttachment.getId());

                        LOG.info("(oAttachment.getId()={}, sFileName={}, sFileExt={}, sDescription={})",
                                oAttachment.getId(), sFileName, sFileExt, sDescription);
                        oInputStream_Attachment = oExecution.getEngineServices().getTaskService()
                                .getAttachmentContent(oAttachment.getId());
                        if (oInputStream_Attachment == null) {
                            LOG.error("Attachment with (id={}) doesn't have content associated with it.", oAttachment.getId());
                            throw new ActivitiObjectNotFoundException(
                                    "Attachment with id '" + oAttachment.getId() + "' doesn't have content associated with it.",
                                    Attachment.class);
                        }
                        DataSource oDataSource = new ByteArrayDataSource(oInputStream_Attachment, sFileExt);
                        oMail._Attach(oDataSource, sFileName, sDescription);
                        LOG.info("oMultiPartEmail.attach: Ok!");
                    }
                }
            }
        } catch (Exception ex) {
            LOG.info("Error during old file mail processing ", ex);
        }

        try {
            LOG.info("sAttachmentsForSend after parsing: " + sAttachmentsForSend);
            JSONObject oJsonTaskAttachVO;
            JSONParser parser = new JSONParser();

            Pattern pattern = Pattern.compile("\\{(.*?)\\}");
            Matcher match = pattern.matcher(sAttachmentsForSend);

            while (match.find()) {
                String sJsonAttach = match.group(0);
                LOG.info("match.group : " + sJsonAttach);
                try {
                    oJsonTaskAttachVO = (JSONObject) parser.parse(sJsonAttach);
                    MultipartFile oMultipartFile;

                    if (oJsonTaskAttachVO != null && oJsonTaskAttachVO.get("sID_StorageType") != null) {
                        String sFileName = (String) oJsonTaskAttachVO.get("sFileNameAndExt");

                        oMultipartFile = oAttachmetService
                                .getAttachment(null, null, (String) oJsonTaskAttachVO.get("sKey"), (String) oJsonTaskAttachVO.get("sID_StorageType"));

                        InputStream oInputStream_Attachment = oMultipartFile.getInputStream();

                        DataSource oDataSource = new ByteArrayDataSource(oInputStream_Attachment, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                        oMail._Attach(oDataSource, sFileName, "(no description)");
                        LOG.info("oMultiPartEmail.attach was sending by new schema!");

                    } else {
                        LOG.error("aAttachment has nothing!");
                        throw new ActivitiObjectNotFoundException("add the file to send");
                    }

                } catch (Exception ex) {
                    LOG.info("There aren't TaskAttachVO objects in mail - JSON parsing error: ", ex);
                }
            }
        } catch (Exception ex) {
            LOG.info("Error during new file mail processing ", ex);
        }

        sendMailOfTask(oMail, oExecution);

        try {
            System.setProperty("mail.mime.address.strict", "false");
            String sPhone_SMS_Value = getStringFromFieldExpression(this.sPhone_SMS, oExecution);
            if (sPhone_SMS_Value != null) {
                String sText_SMS_Value = getStringFromFieldExpression(this.sText_SMS, oExecution);
                if (sText_SMS_Value != null) {
                    sText_SMS_Value = replaceTags(sText_SMS_Value, oExecution);
                    String sReturn;
                    sPhone_SMS_Value = sPhone_SMS_Value.replaceAll("\\ ", "");
                    sReturn = ManagerSMS.sendSms(sPhone_SMS_Value, sText_SMS_Value,
                            generalConfig.getOrderId_ByOrder(getProtectedNumber(Long.valueOf(oExecution.getProcessInstanceId()))), generalConfig.isTest_LiqPay());
                    LOG.info("(sReturn={})", sReturn);
                }
            }
        } catch (Exception ex) {
            LOG.error("Eror during sms sending in MailTaskWithAttachmentsAndSMS:", ex);
            throw ex;
        }
    }

}
