package autoTests.pages.service.appeal.head.region;

import autoTests.ConfigurationVariables;
import autoTests.CustomMethods;
import autoTests.pages.service.BaseServicePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Администратор on 20.03.2016.
 */
public class AppealToHeadOfRegion extends BaseServicePage {
    WebDriver driver;
    ConfigurationVariables configVariables = ConfigurationVariables.getInstance();
    CustomMethods customMethods = new CustomMethods();
    @FindBy(name = "phone")
    private WebElement phoneField; // поле ввода телефона

    @FindBy(name = "email")
    private WebElement emailField; //поле эмейла

    @FindBy(name = "place_of_reg")
    private WebElement addressField; //поле адрес регистрации

    @FindBy(name="vid")
    private WebElement listOfTypeAppeal; // Выпадающий список вид обращения

    @FindBy(name="tema")
    private WebElement listOfThemeAppeal; // Выпадающий список тема обращения

    @FindBy(name="tema_det")
    private WebElement themeField; // Деталізація теми

    @FindBy(name="messageText")
    private WebElement messageTextField; // Текст заявки

    @FindBy(name="resultWay")
    private WebElement listOfResponseWay; // Выпадающий список обратной связи

    @FindBy(name="addFile")
    private WebElement buttonAttachedFile; // Кнопка выбрать файл

    @FindBy(xpath = "//button[@class='btn btn-info']")
    private WebElement confirmButton; // кнопка подтверждения создания услуги

    @FindBy(xpath = "//div[@class='service-name ng-binding']")
    private WebElement serviceName; // название услуги

    @FindBy(xpath = "//div[@class=\"text-center\"]/a[@class=\"ng-binding\"]")
    private WebElement saveReferenceNumber; // название услуги

    @FindBy(xpath = "//div[@class=\"clearfix ng-scope\"]/a[@ng-click=\"fillSelfPreviousBack()\"]")
    private WebElement turnOffAutoFillField; // название услуги

    public static String referenceNumber;

    public AppealToHeadOfRegion(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public AppealToHeadOfRegion clickTurnOffAutoFillField(){
        turnOffAutoFillField.click();
        return this;
    }

    public String getServiceName() {
        return serviceName.getText();
    }

    public AppealToHeadOfRegion fillEmailField(String email){
        emailField.clear();
        emailField.sendKeys(email); // ввод имейла
        return this;
    }
    public AppealToHeadOfRegion fillPhoneNumber(String phoneNumber){
        phoneField.clear();
        phoneField.sendKeys(phoneNumber); // ввод номера телефона
        return this;
    }

    public AppealToHeadOfRegion fillAddressField(String address){
        addressField.clear();
        addressField.sendKeys(address); // ввод адреса
        return this;
    }

    public AppealToHeadOfRegion selectTypeAppeal(String typeAppeal){
        new Select(listOfTypeAppeal).selectByVisibleText(typeAppeal);  // выбор типа обращения
        return this;
    }

    public AppealToHeadOfRegion selectThemeAppeal(String themeAppeal){
        new Select(listOfThemeAppeal).selectByVisibleText(themeAppeal);  // выбор типа обращения
        return this;
    }

    public AppealToHeadOfRegion fillThemeField(String theme){
        themeField.clear();
        themeField.sendKeys(theme); // ввод детализованой темы
        return this;
    }

    public AppealToHeadOfRegion fillMessageTextField(String messageText){
        messageTextField.clear();
        messageTextField.sendKeys(messageText); // ввод детализованой темы
        return this;
    }

    public AppealToHeadOfRegion selectResponseWay(String responseWay){
        new Select(listOfResponseWay).selectByVisibleText(responseWay);  // выбор типа обращения
        return this;
    }
    @Override
    public AppealToHeadOfRegion clickConfirmButton() {
        super.clickConfirmButton();
        return this;
    }
    public String saveReferenceNumber() {
        try {
            customMethods.waitForElementPresent(driver, saveReferenceNumber, 1 , 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        referenceNumber = saveReferenceNumber.getText();
        return referenceNumber;
    }
}
