package test.portal.main;

import org.testng.annotations.Test;
import test.TestBase;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MyDocument extends TestBase {

    @Test (priority = 10)
    public void getAccessToDocumentTest() {
        app.navHelper.openDocumentsPage();
        assertTrue(app.documentsPage.isLoginFormDisplayed());
        app.bankIdPage.loginByPrivatBankBankID();
        assertEquals(app.documentsPage.infoBlockDocument.getText(), "Тут знаходяться всі Ваші документи, які були раніше завантажені авторизованими організаціями. Ви можете їх скачати або надати доступ третім особам (в тому числі іншим державним або приватним організаціям).");
        app.documentsPage.getAccessCode("Test");
        app.documentsPage.isAccessCodeNotNull();
        //app.pause(2000); // временно
        assertEquals(app.documentsPage.alertInfoBlock.getText(), "Посилання, за яким користувач може отримати доступ");
        app.documentsPage.clickOkButton();
        app.bankIdPage.logOut();
        app.navHelper.openDocumentsPage();
        assertEquals(app.documentsPage.loginForm.getText(), "Крок 1. Увійдіть в систему через BankID\n" +
                "Сертифікат електронно-\n" +
                "цифрового підпису");
        app.documentsPage.searchDocumentWithCode();
        app.documentsPage.isDocumentFound();
    }

    @Test (priority = 20)
    public void getAccessToDocumentWithPhoneEmailTest() {
        app.navHelper.openDocumentsPage();
        assertEquals(app.documentsPage.loginForm.getText(), "Крок 1. Увійдіть в систему через BankID\n" +
                "Сертифікат електронно-\n" +
                "цифрового підпису");
        app.bankIdPage.loginByPrivatBankBankID();
        assertEquals(app.documentsPage.infoBlockDocument.getText(), "Тут знаходяться всі Ваші документи, які були раніше завантажені авторизованими організаціями. Ви можете їх скачати або надати доступ третім особам (в тому числі іншим державним або приватним організаціям).");
        app.documentsPage.getAccessCodeWithPhoneEmail("Test");
        app.documentsPage.isAccessCodeNotNull();
        assertEquals(app.documentsPage.alertInfoBlock.getText(), "Посилання, за яким користувач може отримати доступ");
        app.documentsPage.clickOkButton();
        app.bankIdPage.logOut();
        app.navHelper.openDocumentsPage();
        assertEquals(app.documentsPage.loginForm.getText(), "Крок 1. Увійдіть в систему через BankID\n" +
                "Сертифікат електронно-\n" +
                "цифрового підпису");
        app.documentsPage.searchDocumentWithCode();
        assertEquals(app.documentsPage.infoBlockSMS.getText(), "Введіть отриманий Вами SMS код на телефон +38010*****05");
        app.documentsPage.typeSMSCode();
        assertEquals(app.documentsPage.errorBlockSMS.getText(), "Неправильний код");
    }

    @Test (priority = 30)
    public void checkNotaryTabTest() {
        app.navHelper.openDocumentsPage();
        app.documentsPage.goToNotaryTab();
        assertEquals(app.documentsPage.notaryInfoBlock.getText(), "Тут нотаріуси зможуть завантажувати документи із електронно-цифровим підписом. Таким чином, через нотаріусів будь-який документ можна буде переводити із паперового вигляду у цифровий.\n" +
                "Докладніше про розділ Документи на порталі iGov\n" +
                "(розділ у процесі розробки)");
    }
}