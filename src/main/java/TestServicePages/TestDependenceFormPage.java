package TestServicePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import ServicePages.SubsidyPage;
import appLogic.ApplicationManager;

public class TestDependenceFormPage extends ApplicationManager {
	
	
	private  WebDriver driver;

    public TestDependenceFormPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    
//---------------- Элементы страницы------------------//   
    
    @FindBy(xpath = "//div[@class='service-name ng-binding']")
    public WebElement serviceName; // название услуги
    
    @FindBy(name = "client")
    public WebElement clientField; // поле выбора заявителя
    
    @FindBy(name = "info1")
    public WebElement info1Field; //поле ввода информации
    
    @FindBy(name = "info2")
    public WebElement info2Field; //поле ввода информации
    
    @FindBy(css = "button.btn.btn-success")
    public WebElement attachDocumentButton;// поле аттача документа
    
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
    
    public TestDependenceFormPage selectClient(String client){
   new Select(clientField).selectByVisibleText(client);//выбор заявителя
    return this;
      }
    
    public TestDependenceFormPage typeInInfo1Field(String info){
    	info1Field.clear();
    	info1Field.sendKeys(info); // ввод информации
    	 return this;
        }
    public TestDependenceFormPage typeInInfo2Field(String info){
    	info1Field.clear();
    	info1Field.sendKeys(info); // ввод информации
    	 return this;
        }
    public TestDependenceFormPage attachDocument(String document){
    	attachDocumentButton.click();
        driver.findElement(By.cssSelector("input[type=\"file\"]")).click();
        driver.findElement(By.cssSelector("input[type=\"file\"]")).clear();
        driver.findElement(By.cssSelector("input[type=\"file\"]")).sendKeys("src/main/resources/"+document);// аттач файла
    	 return this;
        }
    public TestDependenceFormPage typeInEmailField(String email){
    	emailField.clear();
    	emailField.sendKeys(email); // ввод емайла
    	 return this;
        }
    
    public TestDependenceFormPage clickConfirmButton(){
    	confirmButton.click(); //нажать конпку подтверждения создания услуги
    	return this;
    }
    
    public TestDependenceFormPage verifyServiceSuccessCreated(){
    	successText.isDisplayed();// проверка успешного создания заявки
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
		SubsidyPage.referenceNumber = referenceNumber;
	}
    



}
