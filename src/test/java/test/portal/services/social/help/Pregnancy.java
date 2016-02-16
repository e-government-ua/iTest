package test.portal.services.social.help;

import common.Constants;
import org.testng.annotations.Test;
import pages.service.social.help.PregnancyPage;
import test.TestBase;

import static org.testng.Assert.assertEquals;

public class Pregnancy extends TestBase {

    @Test(priority = 10)
    public void pregnancy(){
        // ------------------- Test data -------------------//
        String service = Constants.Services.Pregnancy.CERTIFICATE_PREGNANCY;
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
        String district = "Амур-Нижньодніпровський район, м.Дніпропетровськ";
        String phone = Constants.TestData.PersonalInfo.PHONE;
        String email = Constants.TestData.PersonalInfo.E_MAIL;
        String address = "Дніпропетровськ (Центральний), вул. Поля, 1";
        String filePath = "src/test/resources/files/test.jpg";
        String answer_yes = "так";
        String answer_no = "ні";
        String exemption = "жінка-військовослужбовець";
        String transferType = "через національного оператора поштового зв";

        // ------------------- Test case -------------------//
        app.mainPage.typeInSearchField(service);
        app.mainPage.clickService(service);
        assertEquals(app.selectAreaPage.serviceName.getText(), service);
        app.selectAreaPage.selectRegion(region);
        app.bankIdPage.loginByPrivatBankBankID();
        assertEquals(app.pregnancyPage.getServiceName(),service);
        app.pregnancyPage
                .selectTown(district)
                .selectZayavka(answer_yes)
                .typeInRegAddr(address)
                .typeInLivAddr(address)
                .enterPhone(phone)
                .enterEmail(email)
                .selectArea_reestr(answer_yes)
                .selectAdoption(answer_no)
                .selectExemption(exemption)
                .selectTransfer_type(transferType)
                .attachFile(app,filePath)
                .clickConfirmButton()
                .verifyServiceSuccessCreated();


        app.navHelper.openStatusesPage();
        app.statusPage.enterReferenceNumber(PregnancyPage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(Constants.Status.SUCCESS_STATUS9);
        app.bankIdPage.logOut();





    }
}
