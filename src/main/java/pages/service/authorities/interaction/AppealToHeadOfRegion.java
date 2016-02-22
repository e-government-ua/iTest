package pages.service.authorities.interaction;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.service.BaseServicePage;

import java.util.List;
import java.util.Random;

/**
 * Created by dlapin on 2/22/2016.
 */
public class AppealToHeadOfRegion extends BaseServicePage {


    @FindBy(name = "vid")
    private WebElement typeOfAppealDropDown;

    @FindBy(name = "tema")
    private WebElement subjectOfAppeal;

    @FindBy(name = "tema_det")
    private WebElement subjectDetails;

    @FindBy(name = "messageText")
    private WebElement appealText;

    @FindBy(name = "resultWay")
    private WebElement answerForm;

    @FindBy(xpath = "//input[@type = 'file']")
    private WebElement attachFile;

    @FindBy(xpath = "//div[@class='text-center ng-binding ng-scope']")
    private WebElement successMessageText; //for some reason locator is different for this articular page

    @FindBy(xpath = "//div[@class='text-center']")
    private WebElement referenceNumberField;

    public AppealToHeadOfRegion(){
        PageFactory.initElements(driver, this);
    }

    public AppealToHeadOfRegion selectRandomTypeOfAppeal() {
        selectRandomFromDropdown(typeOfAppealDropDown);
        return this;
    }

    public AppealToHeadOfRegion selectRandomSubjectOfAppeal() {
        selectRandomFromDropdown(subjectOfAppeal);
        return this;
    }

    public AppealToHeadOfRegion typeInSubjectDetails(String subject) {
        subjectDetails.clear();
        subjectDetails.sendKeys(subject);
        return this;
    }

    public AppealToHeadOfRegion typeInTextOfAppeal(String message){
        appealText.clear();
        appealText.sendKeys(message);
        return this;
    }

    public AppealToHeadOfRegion selectRandomAnswerForm() {
        selectRandomFromDropdown(answerForm);
        return this;
    }

    public WebElement getAttachFileElement() {
        return attachFile;
    }

    public AppealToHeadOfRegion clickConfirmButton() {
        super.clickConfirmButton();
        return this;
    }

    public AppealToHeadOfRegion verifyAppealSuccessCreated(String email){
        Assert.assertEquals(successMessageText.getText(),
                "(номер також відправлено Вам електронною поштою на Ваш e-mail "
                + email +") Результати будуть спрямовані також на email.");
        return this;
    }

    @Override
    public String saveReferenceNumber() {

        String refField = referenceNumberField.getText();
        this.referenceNumber = refField.substring(15, 24);
        return referenceNumber;
    }

    private void selectRandomFromDropdown(WebElement dropdown){
        Select dropdownSelect = new Select(dropdown);
        List<WebElement> options = dropdownSelect.getOptions();
        int rand = 1 + (new Random().nextInt(options.size()-1));
        dropdownSelect.selectByIndex(rand);
    }
}
