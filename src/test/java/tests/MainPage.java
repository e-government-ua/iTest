package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import driverLogic.TestBase;


public class MainPage extends TestBase {

	@Test
	public void simpleSearchArchivalCertificates() throws Exception {
	String service = "Видача архівних довідок, копій, витягів";
        mainPage.typeInSearchField(service);
        app.pause(4000);
        Assert.assertEquals(mainPage.services.getText(), service);
	}


}
