package test.portal.services.taxes;

import common.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.TestBase;

public class PersonalIncomeCertificate extends TestBase {

    @Test(priority = 1)
    public void personalIncomeCertificateTest() {

        String service = Constants.Settings.Taxes.PERSONAL_INCOME_CERTIFICATE;
        String region = Constants.Settings.Region.DNIPROPETROVSKA;
        String startOfPeriod = ""; //временно, пока не реализован выбор даты в календаре
        String endOfPeriod = ""; //временно, пока не реализован выбор даты в календаре
        String fiscalBranch = "Дніпропетровськ - АНД (пров. Універсальний, 12)|0461";
        String phone = "0931234567";
        String email = "test@gmail.com";
        String placeOfLiving = "Дніпропетровськ, вул. Поля, 1";
        String aim = "ТЕСТ для подання за вимогою";
        String status = "Заявка подана";

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
