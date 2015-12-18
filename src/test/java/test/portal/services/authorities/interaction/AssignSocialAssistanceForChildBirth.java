package test.portal.services.authorities.interaction;

import common.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.TestBase;

import java.awt.*;

public class AssignSocialAssistanceForChildBirth extends TestBase {

    @Test(description = "Призначення соціальної допомоги при народженні дитини через національного оператора поштового зв'язку", priority = 1)
    public void successMessagesServicesByPostOffice() throws AWTException {
        //------------------- Тестовые данные -------------------//
        String service = Constants.Settings.InteractionWithPublicAuthorities.ASSIGN_SOCIAL_ASSISTANCE_FOR_CHILD_BIRTH;
        String region = Constants.Settings.Region.DNIPROPETROVSKA;
        String address1 = "проспект карла маркса 22";
        String address2 = "проспект карла маркса 22";
        String phone = "039 123 4567";
        String email = "test@gmail.com";
        String innScanDocument = "src/test/resources/test.jpg";
        String birthScanDocument = "src/test/resources/test.jpg";
        String area = "Самарський, м.Дніпропетровськ";
        String transferType = "через національного оператора поштового зв'язку";
        String numberPostOffice = "12345";
        String status = "Заявка подана";

        //------------------- Тест-кейс -------------------//
        mainPage.typeInSearchField(service);
        mainPage.clickService(service);
        Assert.assertEquals(selectAreaPage.serviceName.getText(), service);
        selectAreaPage.selectRegion(region);
        authorizationPage.privatBankAuthorization();
        Assert.assertEquals(assignSocialAssistanceForChildBirthPage.serviceName.getText(), service);
        assignSocialAssistanceForChildBirthPage
                .typeInAdress1Field(address1)
                .typeInAdress2Field(address2)
                .typeInEmailField(email)
                .typeInPhoneField(phone)
                .attachDocument(assignSocialAssistanceForChildBirthPage.attachInnScanDocument, innScanDocument)
                .attachDocument(assignSocialAssistanceForChildBirthPage.attachBirthScanDocument, birthScanDocument)
                .selectArea(area)
                .selectTransferTypeField(transferType)
                .typeInNumberPostOfficeField(numberPostOffice)
                .clickConfirmButton()
                .verifyServiceSuccessCreated(email)
                .saveReferenceNumber();
        mainPage.goToStatus();
        statusPage.inputReferenceNumberForAssignSocialAssistanceForChildbirth()
                .clickViewStatusButton()
                .verifyStatus(status);
    }

    @Test(description = "Призначення соціальної допомоги при народженні дитини на рахунок у банку", priority = 2)
    public void successMessagesServicesByAccountBank() throws AWTException {
        //------------------- Тестовые данные -------------------//
        String service = Constants.Settings.InteractionWithPublicAuthorities.ASSIGN_SOCIAL_ASSISTANCE_FOR_CHILD_BIRTH;
        String region = Constants.Settings.Region.DNIPROPETROVSKA;
        String address1 = "проспект карла маркса 22";
        String address2 = "проспект карла маркса 22";
        String phone = "039 123 4567";
        String email = "test@gmail.com";
        String innScanDocument = "src/test/resources/test.jpg";
        String birthScanDocument = "src/test/resources/test.jpg";
        String area = "Самарський, м.Дніпропетровськ";
        String transferType = "на рахунок у банку";
        String bankName = Constants.Settings.TestBankDetails.BANK_NAME;
        String bankMFO = Constants.Settings.TestBankDetails.BANK_MFO;
        String bankOKPO = Constants.Settings.TestBankDetails.BANK_OKPO;
        String bankAccount = Constants.Settings.TestBankDetails.BANK_ACCOUNT;
        String bankTicket = "src/test/resources/test.jpg";
        String status = "Заявка подана";

        //------------------- Тест-кейс -------------------//
        mainPage.typeInSearchField(service);
        mainPage.clickService(service);
        Assert.assertEquals(selectAreaPage.serviceName.getText(), service);
        selectAreaPage.selectRegion(region);
        authorizationPage.privatBankAuthorization();
        Assert.assertEquals(assignSocialAssistanceForChildBirthPage.serviceName.getText(), service);
        assignSocialAssistanceForChildBirthPage
                .typeInAdress1Field(address1)
                .typeInAdress2Field(address2)
                .typeInEmailField(email)
                .typeInPhoneField(phone)
                .attachDocument(assignSocialAssistanceForChildBirthPage.attachInnScanDocument, innScanDocument)
                .attachDocument(assignSocialAssistanceForChildBirthPage.attachBirthScanDocument, birthScanDocument)
                .selectArea(area)
                .selectTransferTypeField(transferType)
                .typeInBankNameField(bankName)
                .typeInBankMFOField(bankMFO)
                .typeInBankOKPOField(bankOKPO)
                .typeInBankAccountField(bankAccount)
                .attachDocument(assignSocialAssistanceForChildBirthPage.attachBankTicket, bankTicket)
                .clickConfirmButton()
                .verifyServiceSuccessCreated(email)
                .saveReferenceNumber();
        mainPage.goToStatus();
        statusPage.inputReferenceNumberForAssignSocialAssistanceForChildbirth()
                .clickViewStatusButton()
                .verifyStatus(status);
    }
}