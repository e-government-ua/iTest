package test.portal.main;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.TestBase;


public class MyDocument extends TestBase {


    @Test
    public void getAccessToDocumentTest() {
        mainPage.goToDocuments();
        Assert.assertEquals(documentsPage.formSignInBankId.getText(), "Крок 1. Увійдіть в систему через BankID\n" +
                "Сертифікат електронно-\n" +
                "цифрового підпису");
        authorizationPage.privatBankAuthorization();
        Assert.assertEquals(documentsPage.infoBlockDocument.getText(), "Тут знаходяться всі Ваші документи, які були раніше завантажені авторизованими організаціями. Ви можете їх скачати або надати доступ третім особам (в тому числі іншим державним або приватним організаціям).");
        documentsPage.getAccessCode("Test");
        documentsPage.isAccessCodeNotNull();
        Assert.assertEquals(documentsPage.alertInfoBlock.getText(), "Посилання, за яким користувач може отримати доступ");
        documentsPage.clickOkButton();
        authorizationPage.logOut();
        mainPage.goToDocuments();
        Assert.assertEquals(documentsPage.formSignInBankId.getText(), "Крок 1. Увійдіть в систему через BankID\n" +
                "Сертифікат електронно-\n" +
                "цифрового підпису");
        documentsPage.searchDocumentWithCode();
        documentsPage.isDocumentFound();
    }

    @Test
    public void getAccessToDocumentWithPhoneEmailTest() {
        mainPage.goToDocuments();
        Assert.assertEquals(documentsPage.formSignInBankId.getText(), "Крок 1. Увійдіть в систему через BankID\n" +
                "Сертифікат електронно-\n" +
                "цифрового підпису");
        authorizationPage.privatBankAuthorization();
        Assert.assertEquals(documentsPage.infoBlockDocument.getText(), "Тут знаходяться всі Ваші документи, які були раніше завантажені авторизованими організаціями. Ви можете їх скачати або надати доступ третім особам (в тому числі іншим державним або приватним організаціям).");
        documentsPage.getAccessCodeWithPhoneEmail("Test");
        documentsPage.isAccessCodeNotNull();
        app.pause(2000);
        Assert.assertEquals(documentsPage.alertInfoBlock.getText(), "Посилання, за яким користувач може отримати доступ");
        documentsPage.clickOkButton();
        authorizationPage.logOut();
        mainPage.goToDocuments();
        Assert.assertEquals(documentsPage.formSignInBankId.getText(), "Крок 1. Увійдіть в систему через BankID\n" +
                "Сертифікат електронно-\n" +
                "цифрового підпису");
        documentsPage.searchDocumentWithCode();
        Assert.assertEquals(documentsPage.infoBlockSMS.getText(), "Введіть отриманий Вами SMS код на телефон +38010*****05");
        documentsPage.typeSMSCode();
        Assert.assertEquals(documentsPage.errorBlockSMS.getText(), "Неправильний код");


    }


    @Test
    public void checkNotaryTabTest() {
        mainPage.goToDocuments();
        documentsPage.goToNotaryTab();
        Assert.assertEquals(documentsPage.notaryInfoBlock.getText(), "Тут нотаріуси зможуть завантажувати документи із електронно-цифровим підписом. Таким чином, через нотаріусів будь-який документ можна буде переводити із паперового вигляду у цифровий.\n" +
                "Докладніше про розділ Документи на порталі iGov\n" +
                "(розділ у процесі розробки)");
    }

}
