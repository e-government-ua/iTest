package test.portal.services.identity.citizenship.residense;

import common.Constants;
import org.testng.annotations.Test;
import pages.service.identity.citizenship.residense.InternationalPassportPage;
import test.TestBase;

import static org.testng.Assert.assertEquals;

public class InternationalPassport extends TestBase {

    @Test (priority = 10)
    public void DnipropetrovskInternationalPassportTest() {

        // ------------------- Тестовые данные -------------------//
        String service = Constants.Services.Identity.INTERNATIONAL_PASSPORT;
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
        String havePassport = "ні, буду отримувати перший раз";
        String biometrical = "ні";
        String phone = Constants.TestData.PersonalInfo.PHONE;
        String email = "test@gmail.com";
        String area = "Дніпропетровськ (Центральний), вул. Поля, 1";

        // --------------------- Тест-кейс----------------------//
        app.mainPage.typeInSearchField(service);
        app.mainPage.clickService(service);
        assertEquals(app.selectAreaPage.serviceName.getText(), service);
        app.selectAreaPage.selectRegion(region);
        app.bankIdPage.loginByPrivatBankBankID();
        assertEquals(app.internationalPassportPage.getServiceName(), service);
        app.internationalPassportPage
                .selectHavePassport(havePassport)
                .selectBiometrical(biometrical)
                .typeInPhoneField(phone)
                .typeInEmailField(email)
                .selectArea(area)
                .selectDay()
                .selectTime()
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();
        app.bankIdPage.logOut();
        app.navHelper.openStatusesPage();
        app.statusPage.enterReferenceNumber(InternationalPassportPage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(Constants.Status.SUCCESS_STATUS);
    }
}