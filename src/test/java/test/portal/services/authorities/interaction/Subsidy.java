package test.portal.services.authorities.interaction;

import common.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.service.authorities.interaction.SubsidyPage;
import test.TestBase;

public class Subsidy extends TestBase {

    @Test (priority = 10)
    public void DnipropetrovskSubsidyTest() {
        // ------------------- Тестовые данные -------------------//
        String service = Constants.Services.InteractionWithPublicAuthorities.SUBSIDY;
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
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

        // --------------------- Тест-кейс----------------------//
        mainPage.typeInSearchField(service);
        mainPage.clickService(service);
        Assert.assertEquals(selectAreaPage.serviceName.getText(), service);
        selectAreaPage.selectRegion(region);
        authorizationPage.privatBankAuthorization();
        Assert.assertEquals(subsidyPage.getServiceName(), service);
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
        statusPage.enterReferenceNumber(SubsidyPage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(Constants.Status.SUCCESS_STATUS);

    }
}