package pages.service.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.BaseServicePage;

public class TestLiqpayPage extends BaseServicePage {

    public static String referenceNumber;

    //---------------- Элементы страницы------------------//

    @FindBy(name = "bankIdaddress")
    private WebElement bankIdAddressField; // поле ввода адреса

    @FindBy(name = "vin")
    private WebElement vinField; // поле ввода вин кода

    @FindBy(name = "brand")
    private WebElement brandField;// поле ввода марки авто

    @FindBy(name = "model")
    private WebElement modelField;// поле ввода модели авто

    @FindBy(name = "number")
    private WebElement numberField;// поле ввода номера авто

    @FindBy(name = "invoiceNumber")
    private WebElement invoiceNumberField;// поле ввода инвойса

    @FindBy(name = "phone")
    private WebElement phoneField;// поле ввода телефона

    @FindBy(name = "email")
    private WebElement emailField; //поле эмейла

    @FindBy(xpath = "//div[@class='text-center ng-binding ng-scope']")
    private WebElement successText; //текст удачной создании заявки

    public TestLiqpayPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //---------------- Методы ввода данных в поля------------------//
    public TestLiqpayPage typeInBankIdAddressField(String bankIdAddress) {
        bankIdAddressField.clear();
        bankIdAddressField.sendKeys(bankIdAddress); // ввод адреса
        return this;
    }

    public TestLiqpayPage typeInVinField(String vin) {
        vinField.clear();
        vinField.sendKeys(vin); // ввод Vin кода
        return this;
    }

    public TestLiqpayPage typeInBrandField(String brand) {
        brandField.clear();
        brandField.sendKeys(brand); // ввод марки автомобиля
        return this;
    }

    public TestLiqpayPage typeInModelField(String model) {
        modelField.clear();
        modelField.sendKeys(model); // ввод модели авто
        return this;
    }

    public TestLiqpayPage typeInNumberField(String number) {
        numberField.clear();
        numberField.sendKeys(number); // ввод номера авто
        return this;
    }

    public TestLiqpayPage typeInInvoiceNumberField(String invoiceNumber) {
        invoiceNumberField.clear();
        invoiceNumberField.sendKeys(invoiceNumber); // ввод инвойса
        return this;
    }

    public TestLiqpayPage selectDate() {
        driver.findElement(By.cssSelector("p.input-group.ng-scope > span.input-group-btn > button.btn.btn-default")).click();
        driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();// выбор даты
        return this;
    }

    public TestLiqpayPage typeInPhoneField(String phone) {
        phoneField.clear();
        phoneField.sendKeys(phone); // ввод телефона
        return this;

    }

    public TestLiqpayPage typeInEmailField(String email) {
        emailField.clear();
        emailField.sendKeys(email); // ввод емайла
        return this;
    }

    @Override
    public TestLiqpayPage clickConfirmButton() {
        super.clickConfirmButton();
        return this;
    }

    public TestLiqpayPage verifyServiceSuccessCreated() {
        Assert.assertEquals(successText.getText(), "Дякуемо, що скористались нашим сервісом!");// проверка успешного создания заявки
        return this;
    }
//=================методы по работе с номером заявки=======================//

    @Override
    public String saveReferenceNumber() {
        referenceNumber = super.saveReferenceNumber();
        return referenceNumber;
    }
}


