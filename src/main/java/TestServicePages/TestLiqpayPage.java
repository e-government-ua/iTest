package TestServicePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ServicePages.SubsidyPage;
import appLogic.ApplicationManager;

public class TestLiqpayPage extends ApplicationManager {
	
	private  WebDriver driver;

    public TestLiqpayPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    
 //---------------- Элементы страницы------------------// 
    
    @FindBy(xpath = "//div[@class='service-name ng-binding']")
    public WebElement serviceName; // название услуги
    
    @FindBy(name = "bankIdaddress")
    public WebElement bankIdAddressField; // поле ввода адреса
    
    @FindBy(name = "vin")
    public WebElement vinField; // поле ввода вин кода
    
    @FindBy(name = "brand")
    public WebElement brandField;// поле ввода марки авто
    
    @FindBy(name = "model")
    public WebElement modelField;// поле ввода модели авто
    
    @FindBy(name = "number")
    public WebElement numberField;// поле ввода номера авто
    
    @FindBy(name = "invoiceNumber")
    public WebElement invoiceNumberField;// поле ввода инвойса
    
    @FindBy(name = "phone")
    public WebElement phoneField;// поле ввода телефона
    
    @FindBy(name = "email")
    public WebElement emailField; //поле эмейла
    
    @FindBy(xpath = "//button[@class='btn btn-info']")
    public WebElement confirmButton; // кнопка подтверждения создания услуги
    
    @FindBy(xpath = "//div[@class='text-center ng-binding ng-scope']")
    public WebElement successText; //текст удачной создании заявки 
    
    @FindBy(xpath = "//div[@class='text-center ng-binding']")
    public WebElement referenceNumberField; //поле референс заявки

    public static String referenceNumber;

    
    //---------------- Методы ввода данных в поля------------------//   
    public TestLiqpayPage typeInBankIdAddressField(String bankIdAddress){
    	bankIdAddressField.clear();
    	bankIdAddressField.sendKeys(bankIdAddress); // ввод адреса
    	 return this;
        }
    public TestLiqpayPage typeInVinField(String vin){
    	vinField.clear();
    	vinField.sendKeys(vin); // ввод Vin кода
    	 return this;
        }
    public TestLiqpayPage typeInBrandField(String brand){
    	brandField.clear();
    	brandField.sendKeys(brand); // ввод марки автомобиля
    	 return this;
        }
    public TestLiqpayPage typeInModelField(String model){
    	modelField.clear();
    	modelField.sendKeys(model); // ввод модели авто
    	 return this;
        }
    public TestLiqpayPage typeInNumberField(String number){
    	numberField.clear();
    	numberField.sendKeys(number); // ввод номера авто
    	 return this;
        }
    public TestLiqpayPage typeInInvoiceNumberField(String invoiceNumber){
    	invoiceNumberField.clear();
    	invoiceNumberField.sendKeys(invoiceNumber); // ввод инвойса
    	 return this;
        }
    public TestLiqpayPage selectDate(){
    	driver.findElement(By.cssSelector("p.input-group.ng-scope > span.input-group-btn > button.btn.btn-default")).click();
        driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();// выбор даты
    	 return this;
        }
    public TestLiqpayPage typeInPhoneField(String phone){
    	phoneField.clear();
    	phoneField.sendKeys(phone); // ввод телефона
    	 return this;
    	 
        }
    public TestLiqpayPage typeInEmailField(String email){
 	emailField.clear();
 	emailField.sendKeys(email); // ввод емайла
     return this;
     }
    public TestLiqpayPage clickConfirmButton(){
    	confirmButton.click(); //нажать конпку подтверждения создания услуги
    	return this;
    }
    
    public TestLiqpayPage verifyServiceSuccessCreated(){
    	Assert.assertEquals(successText.getText(), "Дякуемо, що скористались нашим сервісом!");// проверка успешного создания заявки
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
		TestLiqpayPage.referenceNumber = referenceNumber;
	}
    

}


