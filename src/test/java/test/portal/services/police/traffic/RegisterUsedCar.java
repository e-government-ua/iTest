package test.portal.services.police.traffic;

import common.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.service.police.traffic.RegisterUsedCarPage;
import test.TestBase;

/**
 * Test for services in this class:
 * - " Реєстрація авто з пробігом в МРЕВ" (https://github.com/e-government-ua/iTest/issues/41)
 */
public class RegisterUsedCar extends TestBase {

    @Test(priority = 10)
    public void registerUsedCarTest() {

        String service = Constants.Services.MVD.REGISTER_USED_CAR;
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
        String city = Constants.Areas.City.DNIPROPETROVSK;
        String address = "Дніпропетровськ (Центральний), вул. Поля, 1";
        String carVinNumber = "5HGEG644387712845";
        String carBrand = "Honda";
        String model = "Pilot";
        String number = "AH4568EE";
        String transitNumber = "AВ7845EE";
        String invoice = "АБВ123456";
        String invoiceDate = "01/02/2009";
        String mreoAddress = "узвіз Тольятті, 2";
        String phone = "0931234567";
        String email = Constants.TestData.PersonalInfo.E_MAIL;

        mainPage.typeInSearchField(service);
        mainPage.clickExpandAllFoundServices();
        //TODO: remove leading whitespace from clickService(" " + service);
        mainPage.clickService(" " + service);

        Assert.assertEquals(selectAreaPage.serviceName.getText(), service);
        selectAreaPage.selectRegion(region);
        selectAreaPage.selectCity(city);
        authorizationPage.privatBankAuthorization();


        registerUsedCarPage
                .typeInAddressField(address)
                .typeInVinCarVinNumberField(carVinNumber)
                .typeInCarBrand(carBrand)
                .typeInModelField(model)
                .typeInExistingNumberField(number)
                .typeInTransitNumberField(transitNumber)
                .typeInCarRegistrationField(invoice)
                .typeInInvoiceField(invoice)
                .typeInInvoiceDateField(invoiceDate)
                .selectMreo(mreoAddress)
                .typeInPhoneField(phone)
                .typeInEmailField(email)
                .selectDay()
                .selectTime()
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();

        mainPage.goToStatus();
        statusPage.enterReferenceNumber(RegisterUsedCarPage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(Constants.Status.SUCCESS_STATUS);
    }
}