package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import appLogic.Constants;
import driverLogic.TestBase;

public class CriminalRecord extends TestBase  {
	
	
	
	
	@Test
    public void DnipropetrovskCriminalRecord() {
        // ------------------- Тестовые данные -------------------//
        String service = Constants.Settings.ServiceMVD.CriminalRecord;
        String region = Constants.Settings.Region.Dnipropetrovska;
        String city = Constants.Settings.City.Dnipropetrovsk;
        String birthDate = Constants.Settings.Data.BirthDay;
        String birthLoc = "Украина";
        String country = "Україна";
        String goal = "Оформлення візи для виїзду за кордон.";
        String phone = "931234567";
        String resType = "Прошу надати довідку в паперовому вигляді";
        String email = "test@gmail.com";
        String status = "Заявка подана";
        // --------------------- Тест-кейс----------------------//
        mainPage.typeInSearchField(service);
        app.pause(5000);
        mainPage.clickService(service);
        Assert.assertEquals(selectAreaPage.serviceName.getText(), service);
        selectAreaPage.selectRegion(region);
        selectAreaPage.selectCity(city);
        authorizationPage.privatBankAuthorization();
        Assert.assertEquals(criminalRecordPage.serviceName.getText(), service);
		
		criminalRecordPage.typeInBirthDateField(birthDate)
                          .typeInBirthLocField(birthLoc)
                          .selectСountry(country)
                          .selectGoal(goal)
                          .typeInPhoneField(phone)
                          .selectResType(resType)
                          .typeInEmailField(email)
                          .clickConfirmButton()
                          .verifyServiceSuccessCreated()
                          .saveReferenceNumber();
		mainPage.goToStatus();
        statusPage.inputReferenceNumberForCriminalRecord()
                  .clickViewStatusButton()
                  .verifyStatus(status);
                
                          
    }
}


