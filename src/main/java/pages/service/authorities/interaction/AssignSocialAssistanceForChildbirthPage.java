package pages.service.authorities.interaction;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.BaseServicePage;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

public class AssignSocialAssistanceForChildbirthPage extends BaseServicePage {

    private WebDriver driver;

    public AssignSocialAssistanceForChildbirthPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //------------------- Элементы страницы -------------------//

    @FindBy(xpath = "//div[@class='service-name ng-binding']")
    public WebElement serviceName; // название услуги

    @FindBy(name = "adress1")
    public WebElement adress1Field; // адрес регистрации

    @FindBy(name = "adress2")
    public WebElement adress2Field; // адрес проживания

    @FindBy(name = "phone")
    public WebElement phoneField; // поле ввода телефона

    @FindBy(name = "email")
    public WebElement emailField; //поле эмейла

    @FindBy(xpath = "//form[@name='inn_scan']/button")
    public WebElement attachInnScanDocument;// поле аттача справки о присвоении ИНН

    @FindBy(xpath = "//form[@name='birthdocument_scan']/button")
    public WebElement attachBirthScanDocument;// поле аттача свидетельства о рождении

    @FindBy(name = "area")
    public WebElement areaField; // раен обслуживания

    @FindBy(name = "transfer_type")
    public WebElement transferTypeField; // перевод денег

    @FindBy(name = "post_office")
    public WebElement numberPostOfficeField; // № отделения почтовой связи (индекс)

    @FindBy(name = "bank_name")
    public WebElement bankNameField; //

    @FindBy(name = "bank_mfo")
    public WebElement bankMFOField; //

    @FindBy(name = "bank_okpo")
    public WebElement bankOKPOField; //

    @FindBy(name = "bank_account")
    public WebElement bankAccountField; //

    @FindBy(xpath = "//form[@name='bankticket']/button")
    public WebElement attachBankTicket;// поле аттача довідки з банку з реквізитами рахунку

    @FindBy(xpath = "//button[@class='btn btn-info']")
    public WebElement confirmButton; // кнопка подтверждения создания услуги

    @FindBy(xpath = "//div[@class='text-center ng-binding ng-scope']")
    public WebElement successText; //текст удачной создании заявки

    @FindBy(xpath = "//div[@class='text-center ng-binding']")
    public WebElement referenceNumberField; //поле референс заявки

    public static String referenceNumber;


    //------------------- Методы ввода данных в поля -------------------//

    public AssignSocialAssistanceForChildbirthPage typeInAdress1Field(String adress1){
        adress1Field.clear();
        adress1Field.sendKeys(adress1); // ввод адреса регистрации
        return this;
    }

    public AssignSocialAssistanceForChildbirthPage typeInAdress2Field(String adress2){
        adress2Field.clear();
        adress2Field.sendKeys(adress2); // ввод адреса проживания
        return this;
    }

    public AssignSocialAssistanceForChildbirthPage typeInPhoneField(String phone){
        phoneField.clear();
        phoneField.sendKeys(phone); // ввод телефона
        return this;
    }

    public AssignSocialAssistanceForChildbirthPage typeInEmailField(String email){
        emailField.clear();
        emailField.sendKeys(email); // ввод эмейла
        return this;
    }

    public static void setClipboardData(String document) {
        StringSelection stringSelection = new StringSelection(document);
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(stringSelection, null);
    }

    public AssignSocialAssistanceForChildbirthPage attachDocument (WebElement locator, String document) throws AWTException {
        File file = new File(document);
        //
        locator.click();
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

    public AssignSocialAssistanceForChildbirthPage selectArea(String area){
        new Select(areaField).selectByVisibleText(area);  // выбор раена обслуживания
        return this;
    }

    public AssignSocialAssistanceForChildbirthPage selectTransferTypeField(String area){
        new Select(transferTypeField).selectByVisibleText(area);  // выбор перевода денег
        return this;
    }

    public AssignSocialAssistanceForChildbirthPage typeInNumberPostOfficeField(String postCode){
        numberPostOfficeField.clear();
        numberPostOfficeField.sendKeys(postCode);
        return this;
    }

    public AssignSocialAssistanceForChildbirthPage typeInBankNameField(String bankName){
        bankNameField.clear();
        bankNameField.sendKeys(bankName); // Назва банку
        return this;
    }

    public AssignSocialAssistanceForChildbirthPage typeInBankMFOField(String bankMFO){
        bankMFOField.clear();
        bankMFOField.sendKeys(bankMFO); // МФО банку
        return this;
    }

    public AssignSocialAssistanceForChildbirthPage typeInBankOKPOField(String bankOKPO){
        bankOKPOField.clear();
        bankOKPOField.sendKeys(bankOKPO); // ЄДРПОУ банку
        return this;
    }

    public AssignSocialAssistanceForChildbirthPage typeInBankAccountField(String bankAccount){
        bankAccountField.clear();
        bankAccountField.sendKeys(bankAccount); // номер рахунку/картки в банку
        return this;
    }

    @Override
    public AssignSocialAssistanceForChildbirthPage clickConfirmButton() {
        super.clickConfirmButton();
        return this;
    }

    public AssignSocialAssistanceForChildbirthPage verifyServiceSuccessCreated(String email){
        Assert.assertEquals(successText.getText(), "Результати будуть спрямовані на Ваш e-mail " + email); // проверка успешного создания заявки
        return this;
    }


    //------------------- методы по работе с номером заявки -------------------//

    @Override
    public String saveReferenceNumber() {
        referenceNumber = super.saveReferenceNumber();
        return referenceNumber;
    }

}
