package pages;

import ServicePages.CriminalRecordPage;
import ServicePages.InternationalPassportPage;
import ServicePages.SubsidyPage;
import ServicePages.UnregisterFromLocationPage;
import TestServicePages.TestDependenceFormPage;
import TestServicePages.TestFieldsBankidPage;
import appLogic.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestServicePages.TestLiqpayPage;

public class StatusPage extends ApplicationManager {

    @FindBy(id = "code")
    public WebElement refIdField;          // поле ввода реф
    @FindBy(xpath = "//a[contains(.,'Переглянути')]")
    public WebElement viewStatusButton;    //кнопка просмотра статуса


    //    ------------------- Элементы ID Bank------------------------------//
    private WebDriver driver;

    public StatusPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    // =============================================== МЕТОДЫ  =================================================//

    public StatusPage inputReferenceNumberForSubsidy() {
        refIdField.sendKeys(SubsidyPage.referenceNumber);
        return this;
    }

    public StatusPage inputReferenceNumberForCriminalRecord() {
        refIdField.sendKeys(CriminalRecordPage.referenceNumber);
        return this;
    }

    public StatusPage inputReferenceNumberForInternationalPassport() {
        refIdField.sendKeys(InternationalPassportPage.referenceNumber);
        return this;
    }

    public StatusPage inputReferenceNumberForTest_dependence_form() {
        refIdField.sendKeys(TestDependenceFormPage.referenceNumber);
        return this;
    }

    public StatusPage inputReferenceNumberForTest_fields_bankid() {
        refIdField.sendKeys(TestFieldsBankidPage.referenceNumber);
		return this;
	}
	public StatusPage inputReferenceNumberForTest_liqpay() {
		refIdField.sendKeys(TestLiqpayPage.referenceNumber);
        return this;
    }

    public StatusPage inputReferenceNumberForUnregisterFromLocation() {
        refIdField.sendKeys(UnregisterFromLocationPage.referenceNumber);
        return this;
    }

    public StatusPage clickViewStatusButton() {
        viewStatusButton.click();
        return this;
    }


    public void verifyStatus(String status) {
        driver.findElement(By.xpath("//td[contains(.,'" + status + "')]")).isDisplayed();

    }


}
