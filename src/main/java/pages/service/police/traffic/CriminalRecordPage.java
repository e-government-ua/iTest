package pages.service.police.traffic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.BaseServicePage;

public class CriminalRecordPage extends BaseServicePage {

    public static String referenceNumber;

    //---------------- Элементы страницы------------------//
    @FindBy(name = "birthDate")
    private WebElement birthDateField; // поле даты рождения

    @FindBy(name = "birthLoc")
    private WebElement birthLocField; // поле места рождения

    @FindBy(name = "country")
    private WebElement countryField; // поле гражданства

    @FindBy(name = "goal")
    private WebElement goalField; // поле цели получения документа

    @FindBy(name = "phone")
    private WebElement phoneField;// поле ввода телефона

    @FindBy(name = "res_type")
    private WebElement resTypeField; // поле результата оработки обращения

    @FindBy(name = "email")
    private WebElement emailField; //поле эмейла

    @FindBy(xpath = "//div[@class='text-center ng-binding ng-scope']")
    private WebElement successText; //текст удачной создании заявки

    public CriminalRecordPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //---------------- Методы ввода данных в поля------------------//   

    public CriminalRecordPage typeInBirthDateField(String birthDate) {
        birthDateField.clear();
        birthDateField.sendKeys(birthDate); // ввод даты рождения
        return this;
    }

    public CriminalRecordPage typeInBirthLocField(String birthLoc) {
        birthLocField.clear();
        birthLocField.sendKeys(birthLoc); // ввод места рождения
        return this;
    }

    public CriminalRecordPage selectСountry(String country) {
        countryField.click();//выбор гражданства
        driver.findElement(By.linkText(country)).click();
        return this;
    }

    public CriminalRecordPage selectGoal(String goal) {
        new Select(goalField).selectByVisibleText(goal);  //выбор цели получения документа
        return this;
    }

    public CriminalRecordPage typeInPhoneField(String phone) {
        phoneField.sendKeys(phone); // ввод телефона
        return this;
    }

    public CriminalRecordPage selectResType(String resType) {
        new Select(resTypeField).selectByVisibleText(resType);  //выбор результата оработки обращения
        return this;
    }

    public CriminalRecordPage typeInEmailField(String email) {
        emailField.clear();
        emailField.sendKeys(email); // ввод емайла
        return this;
    }

    @Override
    public CriminalRecordPage clickConfirmButton() {
        super.clickConfirmButton();
        return this;
    }

    public CriminalRecordPage verifyServiceSuccessCreated() {
        successText.isDisplayed();// проверка успешного создания заявки
        return this;
    }

//=================методы по работе с номером заявки=======================//

    @Override
    public String saveReferenceNumber() {
        referenceNumber = super.saveReferenceNumber();
        return referenceNumber;
    }
}
