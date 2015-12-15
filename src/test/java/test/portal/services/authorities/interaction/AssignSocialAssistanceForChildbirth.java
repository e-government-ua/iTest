package test.portal.services.authorities.interaction;

import common.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.TestBase;

import java.awt.*;

public class AssignSocialAssistanceForChildbirth extends TestBase {

    @Test(description = "Призначення соціальної допомоги при народженні дитини через національного оператора поштовогозв'язку")
    public void successMessagesServicesByPostOffice() throws AWTException {
        //------------------- Тестовые данные -------------------//
        String service = Constants.Settings.InteractionWithPublicAuthorities.ASSIGN_SOCIAL_ASSISTANCE_FOR_CHILD_BIRTH;
        String region = Constants.Settings.Region.Dnipropetrovska;
        String address1 = "проспект карла маркса 22";
        String address2 = "проспект карла маркса 22";
        String phone = "039 123 4567";
        String email = "test@gmail.com";
        String innScanDocument = "src/resources/test.jpg";
        String birthScanDocument = "src/resources/test.jpg";
        String area = "Самарський, м.Дніпропетровськ";
        String transferType = "через національного оператора поштового зв'язку";
        String numberPostOffice = "12345";
        String status = "Заявка подана";

        //------------------- Тест-кейс -------------------//
        mainPage.typeInSearchField(service);
        app.pause(4000);
        mainPage.clickService(service);
        Assert.assertEquals(selectAreaPage.serviceName.getText(), service);
        selectAreaPage.selectRegion(region);
        authorizationPage.privatBankAuthorization();
        Assert.assertEquals(assignSocialAssistanceForChildbirthPage.serviceName.getText(), service);
        assignSocialAssistanceForChildbirthPage
                .typeInAdress1Field(address1)
                .typeInAdress2Field(address2)
                .typeInEmailField(email)
                .typeInPhoneField(phone)
                .attachDocument(assignSocialAssistanceForChildbirthPage.attachInnScanDocument, innScanDocument)
                .attachDocument(assignSocialAssistanceForChildbirthPage.attachBirthScanDocument, birthScanDocument)
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

    @Test(description = "Призначення соціальної допомоги при народженні дитини на рахунок у банку")
    public void successMessagesServicesByAccountBank() throws AWTException {
        //------------------- Тестовые данные -------------------//
        String service = Constants.Settings.InteractionWithPublicAuthorities.ASSIGN_SOCIAL_ASSISTANCE_FOR_CHILD_BIRTH;
        String region = Constants.Settings.Region.Dnipropetrovska;
        String address1 = "проспект карла маркса 22";
        String address2 = "проспект карла маркса 22";
        String phone = "039 123 4567";
        String email = "test@gmail.com";
        String innScanDocument = "src/resources/test.jpg";
        String birthScanDocument = "src/resources/test.jpg";
        String area = "Самарський, м.Дніпропетровськ";
        String transferType = "на рахунок у банку";
        String bankName = "ПриватБанк";
        String bankMFO = "305299";
        String bankOKPO = "14360570";
        String bankAccount = "1234567890123456";
        String bankTicket = "src/resources/test.jpg";
        String status = "Заявка подана";

        //------------------- Тест-кейс -------------------//
        mainPage.typeInSearchField(service);
        app.pause(4000);
        mainPage.clickService(service);
        Assert.assertEquals(selectAreaPage.serviceName.getText(), service);
        selectAreaPage.selectRegion(region);
        authorizationPage.privatBankAuthorization();
        Assert.assertEquals(assignSocialAssistanceForChildbirthPage.serviceName.getText(), service);
        assignSocialAssistanceForChildbirthPage
                .typeInAdress1Field(address1)
                .typeInAdress2Field(address2)
                .typeInEmailField(email)
                .typeInPhoneField(phone)
                .attachDocument(assignSocialAssistanceForChildbirthPage.attachInnScanDocument, innScanDocument)
                .attachDocument(assignSocialAssistanceForChildbirthPage.attachBirthScanDocument, birthScanDocument)
                .selectArea(area)
                .selectTransferTypeField(transferType)
                .typeInBankNameField(bankName)
                .typeInBankMFOField(bankMFO)
                .typeInBankOKPOField(bankOKPO)
                .typeInBankAccountField(bankAccount)
                .attachDocument(assignSocialAssistanceForChildbirthPage.attachBankTicket, bankTicket)
                .clickConfirmButton()
                .verifyServiceSuccessCreated(email)
                .saveReferenceNumber();
        mainPage.goToStatus();
        statusPage.inputReferenceNumberForAssignSocialAssistanceForChildbirth()
                .clickViewStatusButton()
                .verifyStatus(status);
    }
}
