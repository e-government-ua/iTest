package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import appLogic.ApplicationManager;

public class SelectAreaPage extends ApplicationManager {
	
    private  WebDriver driver;

    public SelectAreaPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
	
  //---------------- Элементы страницы------------------//
    
    @FindBy(xpath = "//div[@class='service-name ng-binding']")
    public WebElement serviceName;             // название услуги
    
    @FindBy(css = "button.btn.btn-default")
    public WebElement regions;         // выпадающий список региона

    @FindBy(xpath = "(//button[@type='button'])[3]")
    public WebElement cities;          // выпадающий список города
    
    
    
    //----------------Методы по работе с элементами------------------// 
    
    //    ------------------- Метод выбора региона  ------------------------------//
    public void selectRegion (String region) {
        regions.click();
        driver.findElement(By.xpath("//a[contains(text(),'"+region+"')]")).click();
    }

    //    ------------------- Метод выбора города  ------------------------------//
    public void selectCity (String city) {
        cities.click();
        driver.findElement(By.linkText(city)).click();
    }

    //---------------- Методы выбора табов услуги ------------------//

    public void clickServiceTab (String serviceTab) {
        driver.findElement(By.xpath("//a[contains(.,'" + serviceTab + "')]")).click();
    }

}
