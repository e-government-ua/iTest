package autoTests.pages.main;

import autoTests.ConfigurationVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Slame on 24.02.16.
 */
public class StatisticTab  {
    WebDriver driver;
    ConfigurationVariables configVariables = ConfigurationVariables.getInstance();
    // Variables

    @FindBy(xpath = "//*[@class='table table-striped ng-scope']//th[2]/center[text()='Кількість наданих послуг']")
    public WebElement numberOfProvidedServicesColumn;

    @FindBy(xpath = "//*[@class='table table-striped ng-scope']//th[3]/center[contains(.,'Середня оцінка')]")
    public WebElement averageScoreColumn;

    @FindBy(xpath = "//*[@class='table table-striped ng-scope']//th[4]/center[contains(.,'Таймінг')]")
    public WebElement timingColumn;

    @FindBy(xpath = "//*[@class='table table-striped ng-scope']//tr/td[3]")
    public WebElement timingRow;

    @FindBy(xpath = "//*[@class='table table-striped ng-scope']//tr/td[1]")
    public WebElement regionRow;


    // Methods

    public StatisticTab(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
