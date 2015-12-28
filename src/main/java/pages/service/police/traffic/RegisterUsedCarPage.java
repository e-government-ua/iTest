package pages.service.police.traffic;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.BaseServicePage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RegisterUsedCarPage extends BaseServicePage {

    public static String referenceNumber;

    @FindBy(name = "bankIdaddress")
    private WebElement addressOfRegistrationField;

    @FindBy(name = "vin")
    private WebElement carVinNumberField;

    @FindBy(name = "brand")
    private WebElement carBrandField;

    @FindBy(name = "model")
    private WebElement carModelField;

    @FindBy(name = "number")
    private WebElement existingNumberField;

    @FindBy(name = "trans_number")
    private WebElement transitNumberField;

    @FindBy(name = "regDocData")
    private WebElement carRegistrationNumberField;

    @FindBy(name = "invoiceNumber")
    private WebElement invoiceNumberField;

    @FindBy(name = "invoiceDate")
    private WebElement invoiceDateField;

    @FindBy(name = "mreo")
    private WebElement mreoDropDown;

    @FindBy(xpath = "//select[@ng-model='selected.date']")
    private WebElement dayField; // поле выбора даты визита

    @FindBy(xpath = "//select[@ng-disabled='!selected.date || slotsLoading']")
    private WebElement timeField; // поле выбора времени визита

    public RegisterUsedCarPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public RegisterUsedCarPage typeInAddressField(String address) {
        addressOfRegistrationField.sendKeys(address);
        return this;
    }


    public RegisterUsedCarPage typeInVinCarVinNumberField(String carVinNumber) {
        carVinNumberField.sendKeys(carVinNumber);
        return this;
    }

    public RegisterUsedCarPage typeInCarBrand(String carBrand) {
        carBrandField.sendKeys(carBrand);
        return this;
    }

    public RegisterUsedCarPage typeInModelField(String model) {
        carModelField.sendKeys(model);
        return this;
    }

    public RegisterUsedCarPage typeInExistingNumberField(String existingNumber) {
        existingNumberField.sendKeys(existingNumber);
        return this;
    }

    public RegisterUsedCarPage typeInTransitNumberField(String transitNumber) {
        transitNumberField.sendKeys(transitNumber);
        return this;
    }

    //Серія та номер свідоцтва про реєстрацію авто
    public RegisterUsedCarPage typeInCarRegistrationField(String carRegistrationNumber) {
        carRegistrationNumberField.sendKeys(carRegistrationNumber);
        return this;
    }

    //довідка-рахунок
    public RegisterUsedCarPage typeInInvoiceField(String invoiceNumber) {
        invoiceNumberField.sendKeys(invoiceNumber);
        return this;
    }

    public RegisterUsedCarPage typeInInvoiceDateField(String invoiceDate) {

        SimpleDateFormat date = new SimpleDateFormat(DATE_FORMAT);
        try {
            date.parse(invoiceDate);
        } catch (ParseException e) {
            System.out.println("Check if " + invoiceDate + " complies to expected date format " + DATE_FORMAT);
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("angular.element(document.getElementsByName('invoiceDate')[0]).context.value = '"
                + invoiceDate + "'");
        return this;
    }

    public RegisterUsedCarPage selectMreo(String mreo) {
        new Select(mreoDropDown).selectByVisibleText(mreo);
        return this;
    }

    public RegisterUsedCarPage typeInPhoneField(String phone) {
        phoneField.sendKeys(phone); // ввод телефона
        return this;
    }

    public RegisterUsedCarPage typeInEmailField(String email) {
        emailField.sendKeys(email); //email
        return this;
    }

    public RegisterUsedCarPage verifyServiceSuccessCreated() {
        successText.isDisplayed();// проверка успешного создания заявки
        return this;
    }

    public RegisterUsedCarPage selectDay() {
        new Select(dayField).selectByValue("0");
        return this;
    }

    public RegisterUsedCarPage selectTime() {
        new Select(timeField).selectByValue("0");
        return this;
    }

    @Override
    public RegisterUsedCarPage clickConfirmButton() {
        super.clickConfirmButton();
        return this;
    }
}
