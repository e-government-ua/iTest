package tests;


import appLogic.Constants;
import driverLogic.TestBase;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;


public class TestServices extends TestBase {


    //TODO https://github.com/e-government-ua/iTest/issues/1

    @Test
     public void test_dependence_form1() throws AWTException {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.TestSERVER;
        String service = Constants.TestService.TEST_DEPENDENCE_FORM;
        String serviceName = "_test_dependence_form";
        String client = "отримувач особисто";
        String info = "test";
        String document = "src/resources/test.jpg";
        String email = "test@gmail.com";
        String status = "Заявка подана";

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server,service);
        authorizationPage.privatBankAuthorization();
        Assert.assertEquals(testDependenceFormPage.serviceName.getText(), serviceName);
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
       String server = Constants.Server.VersionSERVER;
       String service = Constants.TestService.TEST_DEPENDENCE_FORM;
       String serviceName = "_test_dependence_form";
       String client = "представник отримувача";
       String info = "test";
       String document = "src/resources/test.jpg";
       String email = "test@gmail.com";
       String status = "Заявка подана";

       // --------------------- Тест-кейс----------------------//
       mainPage.goToTestServices(server,service);
       authorizationPage.privatBankAuthorization();
       Assert.assertEquals(testDependenceFormPage.serviceName.getText(), serviceName);
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
    public void test_fields_bankid() {
       // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VersionSERVER;
        String service = Constants.TestService.TEST_FIELDS_BANKID;

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server,service);
    }

    //@Test
    public void test_liqpay() {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VersionSERVER;
        String service = Constants.TestService.TEST_LIQPAY;

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server,service);
    }

    //@Test
    public void test_mailer() {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VersionSERVER;
        String service = Constants.TestService.TEST_MAILER;

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server,service);
    }

    //@Test
    public void test_print_form() {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VersionSERVER;
        String service = Constants.TestService.TEST_PRINT_FORM;

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server,service);
    }

    //@Test
    public void test_queue_cancel() {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VersionSERVER;
        String service = Constants.TestService.TEST_QUEUE_CANCEL;

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server,service);
    }

    //@Test
    public void test_ZP_cnap_mailer() {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.VersionSERVER;
        String service = Constants.TestService.TEST_ZP_CNAP_MAILER;

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server,service);
    }
}
