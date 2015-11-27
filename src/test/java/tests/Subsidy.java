package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import appLogic.Constants;
import driverLogic.TestBase;
import pages.StatusPage;

public class Subsidy extends TestBase {
	
	
	
   



	@Test
    public void DnipropetrovskSubsidyTest() {
        // ------------------- Тестовые данные -------------------//
        String service = Constants.Settings.InteractionWithPublicAuthorities.Subsidy;
        String region = Constants.Settings.Region.Dnipropetrovska;
        String area = "Амур-Нижньодніпровський район, м.Дніпропетровськ";
        String placeOfLiving = "test";
        String phone = "0931234567";
        String email = "test@gmail.com";
        String subsidyType = "Оплата житлово-комунальних послуг";
        String electricity = "Не користуюсь";
        String houseArea = "Не користуюсь";
        String gas = "Не користуюсь";
        String coolWater = "Не користуюсь";
        String hotWater = "Не користуюсь";
        String waterBack = "Не користуюсь";
        String warming = "Не користуюсь";
        String garbage = "Не користуюсь";
        String placeType = "окремий будинок";
        String floors = "2";
        String totalPlace = "35";
        String warmingPlace = "32";
        String income = "test";
        String orgName = "test";
        String otherPeople = "Ні";
        String infoAboutoOverload = "test";
        String status = "Заявка подана";
        
        
        
       
        // --------------------- Тест-кейс----------------------//
        mainPage.typeInSearchField(service);
        app.pause(5000);
        mainPage.clickService(service);
        Assert.assertEquals(selectAreaPage.serviceName.getText(), service);
        selectAreaPage.selectRegion(region);
        authorizationPage.privatBankAuthorization();
        Assert.assertEquals(subsidyPage.serviceName.getText(), service);
        subsidyPage
                   .selectArea(area)
                   .typeInPlaceOfLivingField(placeOfLiving)
                   .typeInPhoneField(phone)
                   .typeInEmailField(email)
                   .selectSubsidyType(subsidyType)
                   .selectElectricity(electricity)
                   .selectHouseArea(houseArea)
                   .selectGas(gas)
                   .selectCoolWater(coolWater)
                   .selectHotWater(hotWater)
                   .selectWaterBack(waterBack)
                   .selectWarming(warming)
                   .selectGarbage(garbage)
                   .selectPlaceType(placeType)
                   .typeInFloorsField(floors)
                   .typeInTotalPlaceField(totalPlace)
                   .typeInWarmingPlaceField(warmingPlace)
                   .typeInIncomeField(income)
                   .typeInOrgNameField(orgName)
                   .selectOtherPeople(otherPeople)
                   .typeInInfoAboutoOverloa(infoAboutoOverload)
                   .clickConfirmButton()
                   .verifyServiceSuccessCreated()
                   .saveReferenceNumber(); 
        mainPage.goToStatus();
        statusPage.inputReferenceNumberForSubsidy()
                  .clickViewStatusButton()
                  .verifyStatus(status);
                
        
        
    }
}


