package test.portal.services.identity.citizenship.residense;

import common.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.service.identity.citizenship.residense.UnregisterFromLocationPage;
import test.TestBase;

/**
 * Покрытые в этом классе услуги:
 * - Зняття з реєстрації місця проживання (https://github.com/e-government-ua/iTest/issues/5)
 */
public class Registration extends TestBase {

    @Test (priority = 10)
    public void unregisterFromCurrentAddressTest() {

        String service = Constants.Services.InteractionWithPublicAuthorities.REGISTRATION;
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
        String city = Constants.Areas.City.DNIPROPETROVSK;
        String birthDate = Constants.TestData.PersonalInfo.BIRTH_DAY;
        String birthLocation = "Україна,Дніпропетровська,Дніпропетровськ";
        String nationality = "Україна";
        String district = "Амур-Нижньодніпровський";
        String phone = "0931234567";
        String email = "test@gmail.com";
        String area = "Дніпропетровськ (Центральний), вул. Поля, 1";
        String surnameChanged = "Ні";
        String militStatus = "Ні";
        String kids = "Ні";


        mainPage.typeInSearchField(service);
        mainPage.clickService(service);
        Assert.assertEquals(selectAreaPage.serviceName.getText(), service);
        selectAreaPage.selectRegion(region);
        selectAreaPage.selectCity(city);
        authorizationPage.privatBankAuthorization();
        Assert.assertEquals(unregisterFromLocationPage.getServiceName(), service);

        unregisterFromLocationPage
                .typeInPhoneField(phone)
                .typeInEmailField(email)
                .selectSurnameChanged(surnameChanged)
                .typeInBirthDateField(birthDate)
                .typeInBirthLocField(birthLocation)
                .selectNationality(nationality)
                .selectDistrict(district)
                .typeInCurrentAddress(birthLocation + district)
                .typeInNewAddress(area)
                .selectIfKidsUnderFourteen(kids)
                .selectMilitaryStatus(militStatus)
                .selectDay()
                .selectTime()
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();

        mainPage.goToStatus();
        statusPage.enterReferenceNumber(UnregisterFromLocationPage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(Constants.Status.SUCCESS_STATUS);
    }
}