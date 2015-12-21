package test.portal.services.taxes;

import common.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.TestBase;

public class PersonalIncomeCertificate extends TestBase {

    @Test(priority = 10)
    public void personalIncomeCertificateTest() {
        //------------------- Test Data -------------------//
        String service = Constants.Settings.Taxes.PERSONAL_INCOME_CERTIFICATE;
        String region = Constants.Settings.Region.DNIPROPETROVSKA;
        String startOfPeriod = ""; //TEMP, until interaction with Calendar dropdown will be implemented
        String endOfPeriod = ""; //TEMP, until interaction with Calendar dropdown will be implemented
        String fiscalBranch = "Дніпропетровськ - АНД (пров. Універсальний, 12)|0461";
        String phone = "0931234567";
        String email = "test@mail.com";
        String placeOfLiving = "Дніпропетровськ, вул. Поля, 1";
        String aim = "ТЕСТ для подання за вимогою";
        String status = "Заявка подана";

        //------------------- Test Case -------------------//
        mainPage.typeInSearchField(service);
        mainPage.clickService(service);
        Assert.assertEquals(selectAreaPage.serviceName.getText(), service);
        selectAreaPage.selectRegion(region);
        authorizationPage.privatBankAuthorization();
        personalIncomeCertificatePage
                .selectFiscalBranchField(fiscalBranch)
                .typeInPhoneField(phone)
                .typeInEmailField(email)
                .typeInPlaceLivingField(placeOfLiving)
                .selectStartOfPeriodField(startOfPeriod)
                .selectEndOfPeriodField(endOfPeriod)
                .typeInAimField(aim)
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();
        mainPage.goToStatus();
        statusPage.inputReferenceNumberForPersonalIncomeCertificate()
                .clickViewStatusButton()
                .verifyStatus(status);

    }

}
