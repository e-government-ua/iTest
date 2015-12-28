package test.portal.services.authorities.interaction;

import common.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.service.authorities.interaction.AssignSocialAssistanceForChildBirthPage;
import test.TestBase;

import java.awt.*;

public class AssignSocialAssistanceForChildBirth extends TestBase {

    @Test(description = "Призначення соціальної допомоги при народженні дитини через національного оператора поштового зв'язку", priority = 10)
    public void successMessagesServicesByPostOffice() {
        //------------------- Тестовые данные -------------------//
        String service = Constants.Services.InteractionWithPublicAuthorities.ASSIGN_SOCIAL_ASSISTANCE_FOR_CHILD_BIRTH;
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
        String address1 = "проспект карла маркса 22";
        String address2 = "проспект карла маркса 22";
        String phone = "039 123 4567";
        String email = "test@gmail.com";
        String innScanDocument = "src/test/resources/test.jpg";
        String birthScanDocument = "src/test/resources/test.jpg";
        String area = "Самарський, м.Дніпропетровськ";
        String transferType = "через національного оператора поштового зв'язку";
        String numberPostOffice = "12345";

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
                .typeInPhoneField(phone);
        app.attachDocument(assignSocialAssistanceForChildBirthPage.attachInnScanDocument, innScanDocument);
        app.attachDocument(assignSocialAssistanceForChildBirthPage.attachBirthScanDocument, birthScanDocument);
        assignSocialAssistanceForChildBirthPage
                .selectArea(area)
                .selectTransferTypeField(transferType)
                .typeInNumberPostOfficeField(numberPostOffice)
                .clickConfirmButton()
                .verifyServiceSuccessCreated(email)
                .saveReferenceNumber();
        mainPage.goToStatus();
        statusPage.enterReferenceNumber(AssignSocialAssistanceForChildBirthPage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(Constants.Status.SUCCESS_STATUS);
    }

    @Test(description = "Призначення соціальної допомоги при народженні дитини на рахунок у банку", priority = 20)
    public void successMessagesServicesByAccountBank() throws AWTException {
        //------------------- Тестовые данные -------------------//
        String service = Constants.Services.InteractionWithPublicAuthorities.ASSIGN_SOCIAL_ASSISTANCE_FOR_CHILD_BIRTH;
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
        String address1 = "проспект карла маркса 22";
        String address2 = "проспект карла маркса 22";
        String phone = "039 123 4567";
        String email = "test@gmail.com";
        String innScanDocument = "src/test/resources/test.jpg";
        String birthScanDocument = "src/test/resources/test.jpg";
        String area = "Самарський, м.Дніпропетровськ";
        String transferType = "на рахунок у банку";
        String bankName = Constants.TestData.TestBankDetails.BANK_NAME;
        String bankMFO = Constants.TestData.TestBankDetails.BANK_MFO;
        String bankOKPO = Constants.TestData.TestBankDetails.BANK_OKPO;
        String bankAccount = Constants.TestData.TestBankDetails.BANK_ACCOUNT;
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
                .typeInPhoneField(phone);
        app.attachDocument(assignSocialAssistanceForChildBirthPage.attachInnScanDocument, innScanDocument);
        app.attachDocument(assignSocialAssistanceForChildBirthPage.attachBirthScanDocument, birthScanDocument);
        assignSocialAssistanceForChildBirthPage
            .selectArea(area)
                .selectTransferTypeField(transferType)
                .typeInBankNameField(bankName)
                .typeInBankMFOField(bankMFO)
                .typeInBankOKPOField(bankOKPO)
                .typeInBankAccountField(bankAccount);
        app.attachDocument(assignSocialAssistanceForChildBirthPage.attachBankTicket, bankTicket);
        assignSocialAssistanceForChildBirthPage
                .clickConfirmButton()
                .verifyServiceSuccessCreated(email)
                .saveReferenceNumber();
        mainPage.goToStatus();
        statusPage.enterReferenceNumber(AssignSocialAssistanceForChildBirthPage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(Constants.Status.SUCCESS_STATUS);
    }
}