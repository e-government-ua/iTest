package pages.service.authorities.interaction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.BaseServicePage;

public class SubsidyPage extends BaseServicePage {

    public static String referenceNumber;

    //---------------- Элементы страницы------------------//   
    @FindBy(xpath = "(//button[@type='button'])[3]")
    private WebElement areaField; // поле выбора раёна

    @FindBy(name = "place_of_living")
    private WebElement placeOfLivingField; //поле ввода места регистрации

    @FindBy(name = "phone")
    private WebElement phoneField;// поле ввода телефона

    @FindBy(name = "email")
    private WebElement emailField; //поле эмейла

    @FindBy(name = "subsidy")
    private WebElement subsidyField;//поле выбора типа субсидии

    @FindBy(name = "electricity")
    private WebElement electricityField;//электроенергия

    @FindBy(name = "house")
    private WebElement houseAreaField; //утримання дому та придомової території

    @FindBy(name = "gas")
    private WebElement gasField; //газ

    @FindBy(name = "coolwater")
    private WebElement coolWaterField; //холодная вода

    @FindBy(name = "hotwater")
    private WebElement hotWaterField; //горячая вода

    @FindBy(name = "waterback")
    private WebElement waterBackField; //водяной бак

    @FindBy(name = "warming")
    private WebElement warmingField; //отопление

    @FindBy(name = "garbage")
    private WebElement garbageField; //вывоз мусора

    @FindBy(name = "place_type")
    private WebElement placeTypeField; //тип дома

    @FindBy(name = "floors")
    private WebElement floorsField; //кол-во этажей

    @FindBy(name = "total_place")
    private WebElement totalPlaceField; //общая площадь

    @FindBy(name = "warming_place")
    private WebElement warmingPlaceField; //отопительная площадь

    @FindBy(name = "income0")
    private WebElement incomeField; //вид дохода

    @FindBy(name = "org0")
    private WebElement orgNameField; //название организации

    @FindBy(name = "other_people")
    private WebElement otherPeopleField; // кол-во проживающих

    @FindBy(name = "overload")
    private WebElement infoAboutoOverloadField; //информация про расходы

    @FindBy(xpath = "//div[@class='text-center ng-binding ng-scope']")
    private WebElement successText; //текст удачной создании заявки

    public SubsidyPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    //---------------- Методы ввода данных в поля------------------//

    public SubsidyPage selectArea(String area) {
        areaField.click();
        driver.findElement(By.linkText(area)).click();
        // new Select(areaField).selectByVisibleText(area);//выбор раёна
        return this;
    }

    public SubsidyPage typeInPlaceOfLivingField(String placeOfLiving) {
        placeOfLivingField.clear();
        placeOfLivingField.sendKeys(placeOfLiving); // ввод места регистрации
        return this;
    }

    public SubsidyPage typeInPhoneField(String phone) {

        phoneField.sendKeys(phone); // ввод телефона
        return this;
    }

    public SubsidyPage typeInEmailField(String email) {
        emailField.clear();
        emailField.sendKeys(email); // ввод емайла
        return this;
    }

    public SubsidyPage selectSubsidyType(String subsidyType) {
        new Select(subsidyField).selectByVisibleText(subsidyType);//выбор типа субсудии
        return this;
    }

    public SubsidyPage selectElectricity(String electricity) {
        new Select(electricityField).selectByVisibleText(electricity);//выбор електричества
        return this;
    }

    public SubsidyPage selectHouseArea(String houseArea) {
        new Select(houseAreaField).selectByVisibleText(houseArea);//выбор утримання дому та придомової території
        return this;
    }

    public SubsidyPage selectGas(String gas) {
        new Select(gasField).selectByVisibleText(gas);//выбор газа
        return this;
    }

    public SubsidyPage selectCoolWater(String coolWater) {
        new Select(coolWaterField).selectByVisibleText(coolWater);//выбор холодной воды
        return this;
    }

    public SubsidyPage selectHotWater(String hotWater) {
        new Select(hotWaterField).selectByVisibleText(hotWater);//выбор холодной воды
        return this;
    }

    public SubsidyPage selectWaterBack(String waterBack) {
        new Select(waterBackField).selectByVisibleText(waterBack);//выбор водяного бака
        return this;
    }

    public SubsidyPage selectWarming(String warming) {
        new Select(warmingField).selectByVisibleText(warming);//выбор отопления
        return this;
    }

    public SubsidyPage selectGarbage(String garbage) {
        new Select(garbageField).selectByVisibleText(garbage);//выбор вывоза мусора
        return this;
    }

    public SubsidyPage selectPlaceType(String placeType) {
        new Select(placeTypeField).selectByVisibleText(placeType);//выбор типа дома
        return this;
    }

    public SubsidyPage typeInFloorsField(String floors) {
        floorsField.clear();
        floorsField.sendKeys(floors); // ввод кол-ва этажей
        return this;
    }

    public SubsidyPage typeInTotalPlaceField(String totalPlace) {
        totalPlaceField.clear();
        totalPlaceField.sendKeys(totalPlace); // ввод общей площади
        return this;
    }

    public SubsidyPage typeInWarmingPlaceField(String warmingPlace) {
        warmingPlaceField.clear();
        warmingPlaceField.sendKeys(warmingPlace); // ввод отопительной площади
        return this;
    }

    public SubsidyPage typeInIncomeField(String income) {
        incomeField.clear();
        incomeField.sendKeys(income); // ввод вида доходов
        return this;
    }

    public SubsidyPage typeInOrgNameField(String orgName) {
        orgNameField.clear();
        orgNameField.sendKeys(orgName); // ввод названия организации
        return this;
    }

    public SubsidyPage selectOtherPeople(String otherPeople) {
        new Select(otherPeopleField).selectByVisibleText(otherPeople);//выбор кол-ва проживающих
        return this;
    }

    public SubsidyPage typeInInfoAboutoOverloa(String infoAboutoOverload) {
        infoAboutoOverloadField.clear();
        infoAboutoOverloadField.sendKeys(infoAboutoOverload); // ввод информации про расходы
        return this;
    }

    @Override
    public SubsidyPage clickConfirmButton() {
        super.clickConfirmButton();
        return this;
    }

    public SubsidyPage verifyServiceSuccessCreated() {
        successText.isDisplayed();// проверка успешного создания заявки
        return this;
    }
    //=================методы по работе с номером заявки=======================//

    @Override
    public String saveReferenceNumber() {
        referenceNumber = super.saveReferenceNumber();
        return referenceNumber;
    }
}
