package pages.service.social.help;

import common.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.service.BaseServicePage;


public class PregnancyPage extends BaseServicePage {

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
    //@FindBy(xpath = "//p[@class='ng-scope ng-invalid ng-invalid-required']/button")
    // кнопка загрузки справки о беременности
    private WebElement btnPregnancyDoc;

    @FindBy(xpath = "//div[@class='service-name ng-binding']")
    private WebElement serviceName; // название услуги

    @FindBy(xpath = "//div[@class='text-center ng-binding']")
    private WebElement successText;

    public PregnancyPage() {
        PageFactory.initElements(driver, this);
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

    public PregnancyPage attachFile(ApplicationManager app, String filePath) {
        app.attachDocument(btnPregnancyDoc, filePath);
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

    /*public boolean checkAcceptPregnancy(String text) {
        if (this.infoSuccess.getTagName().contains((CharSequence) text)) {
            return true;
        } else return false;
    }*/

    public PregnancyPage verifyServiceSuccessCreated() {
        successText.isDisplayed();// проверка успешного создания заявки
        referenceNumber = successText.getText().substring(16,25);
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
}
