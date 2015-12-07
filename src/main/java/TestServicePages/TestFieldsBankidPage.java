package TestServicePages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ServicePages.SubsidyPage;
import appLogic.ApplicationManager;

public class TestFieldsBankidPage extends ApplicationManager {


	private  WebDriver driver;

    public TestFieldsBankidPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    
    //---------------- Элементы страницы------------------// 
    
    @FindBy(xpath = "//div[@class='service-name ng-binding']")
    public WebElement serviceName; // название услуги
    
    @FindBy(name = "bankIdsID_Country")
    public WebElement countryField; // поле ввода гражданства
    
    @FindBy(name = "bankIdAddressFactual")
    public WebElement addressField; // поле ввода адреса прописки
    
    @FindBy(css = "button.btn.btn-success")
    public WebElement attachDocumentButton;// поле аттача документа
    
    @FindBy(xpath = "//button[@class='btn btn-info']")
    public WebElement confirmButton; // кнопка подтверждения создания услуги
    
    @FindBy(xpath = "//div[@class='text-center ng-binding ng-scope']")
    public WebElement successText; //текст удачной создании заявки 
    
    @FindBy(xpath = "//div[@class='text-center ng-binding']")
    public WebElement referenceNumberField; //поле референс заявки

    public static String referenceNumber;
    
    //---------------- Методы ввода данных в поля------------------// 
    public TestFieldsBankidPage typeInCountryField(String country){
    	countryField.clear();
    	countryField.sendKeys(country); // ввод гражданства
    	 return this;
    }
    public TestFieldsBankidPage typeInAddressField(String address){
    	addressField.clear();
    	addressField.sendKeys(address); // ввод адреса прописки
    	 return this;
    }
 // upload Document

    public static void setClipboardData(String document) {
        StringSelection stringSelection = new StringSelection(document);
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(stringSelection, null);
    }

    public TestFieldsBankidPage attachDocument (String document) throws  AWTException {
        File file = new File(document);
        //
        attachDocumentButton.click();
        //
        setClipboardData(file.getAbsolutePath());
        //
        Robot robot = new Robot();
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(300);

        return this;
    }
    public TestFieldsBankidPage clickConfirmButton(){
    	confirmButton.click(); //нажать конпку подтверждения создания услуги
    	return this;
    }
    
    public TestFieldsBankidPage verifyServiceSuccessCreated(){
    	Assert.assertEquals(successText.getText(), "Результати будуть спрямовані на Ваш e-mail");// проверка успешного создания заявки
    	return this;
    }
//=================методы по работе с номером заявки=======================//
    
    public String saveReferenceNumber(){
    	String refField = referenceNumberField.getText();
    	setReferenceNumber(refField.substring(16,23));
		return getReferenceNumber();
    }

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		TestFieldsBankidPage.referenceNumber = referenceNumber;
	}
    
}
