package test.portal.main;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.TestBase;

public class MyJournal extends TestBase {


    @Test
    public void myJournalTest() throws Exception {
        mainPage.goToMyLog();
        Assert.assertEquals(journalPage.formSignInBankId.getText(), "Щоб почати користуватись сервісом “Мій журнал”, увійдіть через BankID\n" +
                "Сертифікат електронно-\n" +
                "цифрового підпису");
        authorizationPage.privatBankAuthorization();
        Assert.assertEquals(journalPage.myLog.getText(), "Мій журнал");
        Assert.assertEquals(journalPage.nextLink.getText(), "Показати ще");
        authorizationPage.logOut();
        mainPage.goToMyLog();
        Assert.assertEquals(journalPage.formSignInBankId.getText(), "Щоб почати користуватись сервісом “Мій журнал”, увійдіть через BankID\n" +
                "Сертифікат електронно-\n" +
                "цифрового підпису");


    }


}
