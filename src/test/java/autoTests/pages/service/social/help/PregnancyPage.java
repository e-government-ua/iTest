package autoTests.pages.service.social.help;

import autoTests.ConfigurationVariables;
import autoTests.CustomMethods;
import autoTests.pages.service.BaseServicePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertEquals;

public class PregnancyPage extends BaseServicePage {
    WebDriver driver;
    ConfigurationVariables configVariables = ConfigurationVariables.getInstance();
    CustomMethods customMethods = new CustomMethods();
    public static java.lang.String referenceNumber;

    //---------------- Элементы страницы------------------//
    @FindBy(xpath = "//input[@class='form-control ng-pristine ng-untouched ng-valid ng-valid-required']")
    // выпадающий список выбор города
    private WebElement selectTown;

    @FindBy(xpath = "//select[@name='zayavka']")
    // выпадающий список выбор колл-ва подач заявки
    private WebElement requestFirstTime;

    @FindBy(xpath = "//input[@name='adress1']")
    // адрес регистрации
    private WebElement adress1;

    @FindBy(xpath = "//input[@name='adress2']")
    // адрес проживания
    private WebElement adress2;

    @FindBy(xpath = "//input[@name='phone']")
    // номер телефона
    private WebElement phone;

    @FindBy(xpath = "//input[@name='email']")
    // электронный ящик
    private WebElement email;

    @FindBy(xpath = "//select[@name='area_reestr']")
    // регистрация в районе
    private WebElement area_reestr;

    @FindBy(xpath = "//select[@name='adoption']")
    // опека
    private WebElement adoption;

    @FindBy(xpath = "//select[@name='woman']")
    // льгота
    private WebElement exemption;

    @FindBy(xpath = "//select[@name='transfer_type']")
    // счет для перечисления средств
    private WebElement transfer_type;

    @FindBy(xpath = "(//input[@type='file'])[3]")
    // кнопка загрузки справки о беременности
    private WebElement btnPregnancyDoc;

    @FindBy(xpath = "//div[@class='service-name ng-binding']")
    private WebElement serviceName; // название услуги

    @FindBy(xpath = "//div[@class='text-center']")
    private WebElement successText;

    @FindBy(xpath = "//a[@class='ng-binding']")
    private WebElement resortLink;

    public PregnancyPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    //---------------- Методы ------------------//

    public String getServiceName() {
        return serviceName.getText();
    }

    public PregnancyPage selectTown(String region) {
        selectTown.click();
        driver.findElement(By.xpath("//a[contains(text(),'" + region + "')]")).click();
        return this;
    }

    public PregnancyPage selectZayavka(String text) {
        requestFirstTime.click();
        driver.findElement(By.xpath("//select[@name='zayavka']//option[contains(text(),'" + text + "')]")).click();
        return this;
    }

    public PregnancyPage typeInRegAddr(String regaddr) {
        adress1.clear();
        // ввод адреса регистрации
        adress1.sendKeys(regaddr);
        return this;
    }

    public PregnancyPage typeInLivAddr(String livaddr) {
        adress2.clear();
        // ввод адреса проживания
        adress2.sendKeys(livaddr);
        return this;
    }

    public PregnancyPage enterPhone(String phone) {
        this.phone.clear();
        // ввод телефона
        this.phone.sendKeys(phone);
        return this;
    }

    public PregnancyPage enterEmail(String email) {
        this.email.clear();
        // ввод электронного ящика
        this.email.sendKeys(email);
        return this;
    }

    public PregnancyPage selectArea_reestr(String text) {
        area_reestr.click();
        driver.findElement(By.xpath("//select[@name='area_reestr']//option[contains(text(),'" + text + "')]")).click();
        return this;
    }

    public PregnancyPage selectAdoption(String text) {
        adoption.click();
        driver.findElement(By.xpath("//select[@name='adoption']//option[contains(text(),'" + text + "')]")).click();
        return this;
    }

    public PregnancyPage selectExemption(String text) {
        exemption.click();
        driver.findElement(By.xpath("//select[@name='woman']//option[contains(text(),'" + text + "')]")).click();
        return this;
    }

    public PregnancyPage selectTransfer_type(String text) {
        transfer_type.click();
        driver.findElement(By.xpath("//select[@name='transfer_type']//option[contains(text(),'" + text + "')]")).click();
        return this;
    }

    public PregnancyPage attachFile(String filePath) {
        customMethods.attachDocument(btnPregnancyDoc, filePath, driver);
        return this;
    }

    public PregnancyPage typeInPhoneField(String phone) {
        phoneField.clear();
        phoneField.sendKeys(phone); // ввод телефона
        return this;
    }

    public PregnancyPage typeInEmailField(String email) {
        emailField.clear();
        emailField.sendKeys(email); // ввод емайла
        return this;
    }


    public PregnancyPage verifyServiceSuccessCreated() {
        String text = successText.getText().substring(26,successText.getText().length());
        assertEquals(text,"успішно зареєстровано");
        referenceNumber = successText.getText().substring(15,25);
        successText.isDisplayed();// проверка успешного создания заявки

        return this;
    }

    @Override
    public PregnancyPage clickConfirmButton() {
        super.clickConfirmButton();
        return this;
    }



//=================методы по работе с номером заявки=======================//

    @Override
    public String saveReferenceNumber() {
        referenceNumber = super.saveReferenceNumber();
        return referenceNumber;
    }
    public PregnancyPage clickLinkResort() {
        resortLink.click();
        return this;
    }
}
