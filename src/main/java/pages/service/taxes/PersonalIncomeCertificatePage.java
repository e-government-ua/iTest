package pages.service.taxes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.BaseServicePage;

public class PersonalIncomeCertificatePage extends BaseServicePage {

    //---------------- Элементы страницы------------------//

    public static String referenceNumber;

    @FindBy(xpath = "//select[@name='area']")
    public WebElement fiscalBranchField; //отделение фискальной службы

    @FindBy(xpath = "//input[@name='phone']")
    public WebElement phoneField;// поле ввода номера телефона

    @FindBy(xpath = "//input[@name='email']")
    public WebElement emailField; //поле имейла

    @FindBy(xpath = "//input[@name='place_living']")
    public WebElement placeOfLivingField; //поле ввода адреса проживания

    @FindBy(xpath = "//input[@name='date_start']")
    public WebElement startOfPeriodField; //дата начала периода справки

    @FindBy(xpath = "//input[@name='date_stop']")
    public WebElement endOfPeriodField; //дата конца периода справки

    @FindBy(xpath = "//input[@name='aim']")
    public WebElement aimField; //цель получения справки

    @FindBy(xpath = "//div[@class='text-center ng-binding ng-scope']")
    private WebElement successText;

    public PersonalIncomeCertificatePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    //---------------- Методы ввода данных в поля------------------//

    public PersonalIncomeCertificatePage selectFiscalBranchField(String fiscalBranch) {
        new Select(fiscalBranchField).selectByVisibleText(fiscalBranch); //выбор отделения фискальной службы
        return this;
    }

    public PersonalIncomeCertificatePage typeInPhoneField (String phone) {
        phoneField.sendKeys(phone); //ввод номера телефона
        return this;
    }

    public PersonalIncomeCertificatePage typeInEmailField (String email) {
        emailField.clear();
        emailField.sendKeys(email); //ввод имейла
        return this;
    }

    public PersonalIncomeCertificatePage typeInPlaceLivingField (String placeLiving) {
        placeOfLivingField.clear();
        placeOfLivingField.sendKeys(placeLiving); //ввод адреса проживания
        return this;
    }

    public PersonalIncomeCertificatePage selectStartOfPeriodField (String startOfPeriod) {
        startOfPeriodField.sendKeys(startOfPeriod); //выбор даты начала периода справки
        return this; //временно, пока не реализован выбор даты в календаре
    }

    public PersonalIncomeCertificatePage selectEndOfPeriodField (String endOfPeriod) {
        endOfPeriodField.sendKeys(endOfPeriod);  //выбор даты окончания периода справки
        return this; //временно, пока не реализован выбор даты в календаре
    }

    public PersonalIncomeCertificatePage typeInAimField (String aim) {
        aimField.clear();
        aimField.sendKeys(aim); //ввод цели получения справки
        return this;
    }

    public PersonalIncomeCertificatePage verifyServiceSuccessCreated() {
        successText.isDisplayed();// проверка успешного создания заявки
        return this;
    }

    @Override
    public PersonalIncomeCertificatePage clickConfirmButton() {
        super.clickConfirmButton();

        return this;
    }

    @Override
    public String saveReferenceNumber() {
        referenceNumber = super.saveReferenceNumber();
        return referenceNumber;
    }

}