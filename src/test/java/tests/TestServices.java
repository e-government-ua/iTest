package tests;


import appLogic.Constants;
import driverLogic.TestBase;
import org.testng.annotations.Test;



public class TestServices extends TestBase {


    //TODO https://github.com/e-government-ua/iTest/issues/1

    @Test
     public void test_dependence_form() {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.SERVER;
        String service = Constants.TestService.TEST_DEPENDENCE_FORM;

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server,service);

    }

    @Test
    public void test_fields_bankid() {
       // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.SERVER;
        String service = Constants.TestService.TEST_FIELDS_BANKID;

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server,service);
    }

    @Test
    public void test_liqpay() {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.SERVER;
        String service = Constants.TestService.TEST_LIQPAY;

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server,service);
    }

    @Test
    public void test_mailer() {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.SERVER;
        String service = Constants.TestService.TEST_MAILER;

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server,service);
    }

    @Test
    public void test_print_form() {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.SERVER;
        String service = Constants.TestService.TEST_PRINT_FORM;

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server,service);
    }

    @Test
    public void test_queue_cancel() {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.SERVER;
        String service = Constants.TestService.TEST_QUEUE_CANCEL;

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server,service);
    }

    @Test
    public void test_ZP_cnap_mailer() {
        // ------------------- Тестовые данные -------------------//
        String server = Constants.Server.SERVER;
        String service = Constants.TestService.TEST_ZP_CNAP_MAILER;

        // --------------------- Тест-кейс----------------------//
        mainPage.goToTestServices(server,service);
    }
}
