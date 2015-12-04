package ServicePages;

import appLogic.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Page to describe service "Зняття з реєстрації місця проживання"
 */
public class UnregisterFromLocationPage extends ApplicationManager {
    public static String referenceNumber;
    private final WebDriver driver;
    @FindBy(xpath = "//div[@class='service-name ng-binding']")
    public WebElement serviceName; // название услуги

    @FindBy(name = "phone")
    private WebElement phoneField;// поле ввода телефона

    @FindBy(name = "email")
    private WebElement emailField; //поле эмейла

    @FindBy(name = "PrevName1")
    private WebElement wasSurnameChangedField; // Ви змінювали прізвище, ім’я та по батькові?

    @FindBy(name = "Date_of_birth")
    private WebElement birthDateField; // поле даты рождения

    @FindBy(name = "Areabirth")
    private WebElement birthLocField; // поле места рождения

    @FindBy(name = "country")
    private WebElement countryField; // поле гражданства

    @FindBy(name = "district")
    private WebElement districtField; // поле района

    @FindBy(name = "RegistrationAddress")
    private WebElement registrationAddressField; // поле настоящего адреса

    @FindBy(name = "newAddress")
    private WebElement newAddressField; // поле нового адреса

    @FindBy(name = "milit_status")
    private WebElement militaryStatusField; // поле военного учета

    @FindBy(name = "kids")
    private WebElement kidsUpToFourteenField; // поле 'есть ли дети до 14'

    @FindBy(name = "Nationality")
    private WebElement nationalityField;

    @FindBy(xpath = "//select[@ng-disabled='!selected.date || slotsLoading']")
    private WebElement timeField; // поле выбора времени визита

    @FindBy(xpath = "//div[@class='text-center ng-binding ng-scope']")
    private WebElement successText; //текст удачной создании заявки

    @FindBy(xpath = "//div[@class='text-center ng-binding']")
    private WebElement referenceNumberField; //поле референс заявки

    @FindBy(xpath = "//select[@ng-model='selected.date']")
    private WebElement dayField; // поле выбора даты визита

    @FindBy(xpath = "//button[@class='btn btn-info']")
    private WebElement confirmButton; // кнопка подтверждения создания услуги

    public UnregisterFromLocationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //---------------- Методы ввода данных в поля------------------//

    public UnregisterFromLocationPage typeInBirthDateField(String birthDate) {
        birthDateField.clear();
        birthDateField.sendKeys(birthDate);
        return this;
    }

    public UnregisterFromLocationPage typeInPhoneField(String phone) {
        phoneField.sendKeys(phone); // ввод телефона
        return this;
    }

    public UnregisterFromLocationPage typeInEmailField(String email) {
        emailField.clear();
        emailField.sendKeys(email); // ввод емайла
        return this;
    }

    public UnregisterFromLocationPage selectSurnameChanged(String wasChanged) {
        new Select(wasSurnameChangedField).selectByVisibleText(wasChanged);
        return this;
    }

    public UnregisterFromLocationPage typeInBirthLocField(String birthLoc) {
        birthLocField.clear();
        birthLocField.sendKeys(birthLoc); // ввод места рождения
        return this;
    }

    public UnregisterFromLocationPage selectСountry(String country) {
        countryField.click();//выбор гражданства
        driver.findElement(By.linkText(country)).click();
        return this;
    }

    public UnregisterFromLocationPage selectDistrict(String disrict) {
        new Select(districtField).selectByVisibleText(disrict);
        return this;
    }

    public UnregisterFromLocationPage typeInCurrentAddress(String currentAddress) {
        registrationAddressField.clear();
        registrationAddressField.sendKeys(currentAddress);
        return this;
    }

    public UnregisterFromLocationPage typeInNewAddress(String newAddress) {
        newAddressField.clear();
        newAddressField.sendKeys(newAddress);
        return this;
    }

    public UnregisterFromLocationPage selectMilitaryStatus(String militaryStatus) {
        new Select(militaryStatusField).selectByVisibleText(militaryStatus);
        return this;
    }

    public UnregisterFromLocationPage selectNationality(String nationality) {
        nationalityField.sendKeys(nationality);
        return this;
    }

    public UnregisterFromLocationPage selectIfKidsUnderFourteen(String kidsUnderFourteen) {
        new Select(kidsUpToFourteenField).selectByVisibleText(kidsUnderFourteen);
        return this;
    }

    public UnregisterFromLocationPage selectTime() {
        new Select(timeField).selectByValue("0");
        pause(1000);
        return this;
    }

    public UnregisterFromLocationPage verifyServiceSuccessCreated() {
        successText.isDisplayed();// проверка успешного создания заявки
        return this;
    }

    //=================методы по работе с номером заявки=======================//

    public UnregisterFromLocationPage clickConfirmButton() {
        confirmButton.click(); //нажать конпку подтверждения создания услуги
        return this;
    }

    public String saveReferenceNumber() {
        String refField = referenceNumberField.getText();
        this.referenceNumber = refField.substring(16, 23);
        return refField;
    }

    public UnregisterFromLocationPage selectDay() {
        new Select(dayField).selectByValue("0");
        return this;
    }
}
