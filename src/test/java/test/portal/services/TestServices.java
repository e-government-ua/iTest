package test.portal.services;


import common.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.TestBase;

import java.awt.*;


public class TestServices extends TestBase {


    //TODO https://github.com/e-government-ua/iTest/issues/1

    //@Test
    public void test_dependence_form1() throws AWTException {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VERSION_SERVER;
        String service = Constants.TestService.TEST_DEPENDENCE_FORM;
        String serviceName = "_test_dependence_form";
        String client = "отримувач особисто";
        String info = "test";
        String document = "src/resources/test.jpg";
        String email = "test@gmail.com";
        String status = "Заявка подана";

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server, service);
        authorizationPage.privatBankAuthorization();
        Assert.assertEquals(testDependenceFormPage.getServiceName(), serviceName);
        testDependenceFormPage
                .selectClient(client)
                .typeInInfo1Field(info)
                .attachDocument(document)
                .typeInEmailField(email)
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();
        mainPage.goToStatus();
        statusPage.inputReferenceNumberForTest_dependence_form()
                .clickViewStatusButton()
                .verifyStatus(status);
    }

    @Test
    public void test_dependence_form2() throws AWTException {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VERSION_SERVER;
        String service = Constants.TestService.TEST_DEPENDENCE_FORM;
        String serviceName = "_test_dependence_form";
        String client = "представник отримувача";
        String info = "test";
        String document = "src/resources/test.jpg";
        String email = "test@gmail.com";
        String status = "Заявка подана";

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server, service);
        authorizationPage.privatBankAuthorization();
        Assert.assertEquals(testDependenceFormPage.getServiceName(), serviceName);
        testDependenceFormPage
                .selectClient(client)
                .typeInInfo2Field(info)
                .attachDocument(document)
                .typeInEmailField(email)
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();
        mainPage.goToStatus();
        statusPage.inputReferenceNumberForTest_dependence_form()
                .clickViewStatusButton()
                .verifyStatus(status);
    }

    //@Test
    public void test_fields_bankid() throws AWTException {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VERSION_SERVER;
        String service = Constants.TestService.TEST_FIELDS_BANKID;
        String serviceName = "_test_fields_bankid";
        String region = Constants.Settings.Region.DNIPROPETROVSKA;
        String city = Constants.Settings.City.DNIPROPETROVSK;
        String country = "country";
        String address = "address";
        String document = "src/resources/test.jpg";
        String status = "Заявка подана";


        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server, service);
        selectAreaPage.selectRegion(region);
        selectAreaPage.selectCity(city);
        authorizationPage.privatBankAuthorization();
        Assert.assertEquals(testFieldsBankidPage.getServiceName(), serviceName);
        testFieldsBankidPage
                .typeInCountryField(country)
                .typeInAddressField(address)
                .attachDocument(document)
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();
        mainPage.goToStatus();
        statusPage.inputReferenceNumberForTest_fields_bankid()
                .clickViewStatusButton()
                .verifyStatus(status);
    }

    //@Test
    public void test_liqpay() {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VERSION_SERVER;
        String service = Constants.TestService.TEST_LIQPAY;
        String serviceName = "_test_liqpay";
        String region = Constants.Settings.Region.DNIPROPETROVSKA;
        String city = Constants.Settings.City.DNIPROPETROVSK;
        String bankIdAddressField = Constants.Settings.Data.BIRTH_LOCAL;
        String vin = "12345678912345678";
        String brand = "brand";
        String model = "model";
        String number = "number";
        String invoiceNumber = "invoiceNumber";
        String phone = "380931234567";
        String email = Constants.Settings.Data.E_MAIL;
        String status = "Заявка подана";

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server, service);
        authorizationPage.privatBankAuthorization();
        Assert.assertEquals(testLiqpayPage.getServiceName(), serviceName);
        testLiqpayPage
                .typeInBankIdAddressField(bankIdAddressField)
                .typeInVinField(vin)
                .typeInBrandField(brand)
                .typeInModelField(model)
                .typeInNumberField(number)
                .typeInInvoiceNumberField(invoiceNumber)
                .selectDate()
                .typeInPhoneField(phone)
                .typeInEmailField(email)
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();
        mainPage.goToStatus();
        statusPage.inputReferenceNumberForTest_liqpay()
                .clickViewStatusButton()
                .verifyStatus(status);
    }

    //@Test
    public void test_mailer() {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VERSION_SERVER;
        String service = Constants.TestService.TEST_MAILER;

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server, service);
    }

    //@Test
    public void test_print_form() {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VERSION_SERVER;
        String service = Constants.TestService.TEST_PRINT_FORM;

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server, service);
    }

    //@Test
    public void test_queue_cancel() {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VERSION_SERVER;
        String service = Constants.TestService.TEST_QUEUE_CANCEL;

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server, service);
    }

    //@Test
    public void test_ZP_cnap_mailer() {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VERSION_SERVER;
        String service = Constants.TestService.TEST_ZP_CNAP_MAILER;

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server, service);
    }
}
