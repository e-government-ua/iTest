package pages.service.taxes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.BaseServicePage;

public class PersonalIncomeCertificatePage extends BaseServicePage {


    //------------------- Page elements -------------------//

    public static String referenceNumber;

    @FindBy(xpath = "//select[@name='area']")
    public WebElement fiscalBranchField; //fiscal branch

    @FindBy(xpath = "//input[@name='place_living']")
    public WebElement placeOfLivingField; //living address

    @FindBy(xpath = "//input[@name='date_start']")
    public WebElement startOfPeriodField; //certificate's information start of period

    @FindBy(xpath = "//input[@name='date_stop']")
    public WebElement endOfPeriodField; //certificate's information end of period

    @FindBy(xpath = "//input[@name='aim']")
    public WebElement aimField; //certificate request purpose

    @FindBy(xpath = "//div[@class='text-center ng-binding ng-scope']")
    private WebElement successText;

    public PersonalIncomeCertificatePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    //------------------- Fields input methods -------------------//

    public PersonalIncomeCertificatePage selectFiscalBranchField(String fiscalBranch) {
        new Select(fiscalBranchField).selectByVisibleText(fiscalBranch); //fiscal branch selection
        return this;
    }

    public PersonalIncomeCertificatePage typeInPhoneField (String phone) {
        phoneField.sendKeys(phone); //phone number
        return this;
    }

    public PersonalIncomeCertificatePage typeInEmailField (String email) {
        emailField.clear();
        emailField.sendKeys(email); //email
        return this;
    }

    public PersonalIncomeCertificatePage typeInPlaceLivingField (String placeLiving) {
        placeOfLivingField.clear();
        placeOfLivingField.sendKeys(placeLiving); //living address
        return this;
    }

    public PersonalIncomeCertificatePage selectStartOfPeriodField (String startOfPeriod) {
        startOfPeriodField.sendKeys(startOfPeriod); //certificate's information start of period
        return this; //TEMP, until interaction with Calendar dropdown will be implemented
    }

    public PersonalIncomeCertificatePage selectEndOfPeriodField (String endOfPeriod) {
        endOfPeriodField.sendKeys(endOfPeriod);  //certificate's information start of period
        return this; //TEMP, until interaction with Calendar dropdown will be implemented
    }

    public PersonalIncomeCertificatePage typeInAimField (String aim) {
        aimField.clear();
        aimField.sendKeys(aim); //certificate request purpose
        return this;
    }

    public PersonalIncomeCertificatePage verifyServiceSuccessCreated() {
        successText.isDisplayed();
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