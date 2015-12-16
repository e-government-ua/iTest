package test.portal.main;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.TestBase;

public class Footer extends TestBase {


    @Test
    public void portalsNewsOnFacebookLinkTest() {
        mainPage.goToServices();
        Assert.assertEquals(mainPage.portalsNewsOnFacebookLink.getText(), "Новини порталу\n" +
                "на Facebook");
    }

    @Test
    public void checkElectronDigitalSignatureLinkTest() {
        mainPage.goToServices();
        Assert.assertEquals(mainPage.checkElectronDigitalSignatureLink.getText(), "Перевірити електронно-\n" +
                "цифровий підпис");
    }

    @Test
    public void errorOrABugOnThePortalLinkTest() {
        mainPage.goToServices();
        Assert.assertEquals(mainPage.errorOrABugOnThePortalLink.getText(), "Помилка або баг\n" +
                "на Порталі?");
    }

    @Test
    public void joinOnGitHubLinkTest() {
        mainPage.goToServices();
        Assert.assertEquals(mainPage.joinOnGitHubLink.getText(), "Приєднатись\n" +
                "на GitHub!");
    }

    @Test
    public void commentsAndOpportunitiesTest() {
        mainPage.goToServices();
        Assert.assertEquals(mainPage.volunteerIGov.getText(), "NEW Станьте\n" +
                "волонтером iGov!");
    }


}

