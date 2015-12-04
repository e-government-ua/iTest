package tests;

import appLogic.Constants;
import driverLogic.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Покрытые в этом классе услуги:
 * - Зняття з реєстрації місця проживання (https://github.com/e-government-ua/iTest/issues/5)
 */
public class Registration extends TestBase {

    @Test
    public void unregisterFromCurrentAddressTest() {

        String service = Constants.Settings.InteractionWithPublicAuthorities.REGISTRATION;
        String region = Constants.Settings.Region.Dnipropetrovska;
        String city = Constants.Settings.City.Dnipropetrovsk;
        String birthDate = Constants.Settings.Data.BirthDay;
        String birthLocation = "Україна,Дніпропетровська,Дніпропетровськ";
        String nationality = "Україна";
        String district = "Амур-Нижньодніпровський";
        String phone = "0931234567";
        String email = "test@gmail.com";
        String area = "Дніпропетровськ (Центральний), вул. Поля, 1";
        String status = "Заявка подана";
        String surnameChanged = "Ні";
        String militStatus = "Ні";
        String kids = "Ні";


        mainPage.typeInSearchField(service);
        app.pause(5000);
        mainPage.clickService(service);
        Assert.assertEquals(selectAreaPage.serviceName.getText(), service);
        selectAreaPage.selectRegion(region);
        selectAreaPage.selectCity(city);
        authorizationPage.privatBankAuthorization();
        Assert.assertEquals(unregisterFromLocationPage.serviceName.getText(), service);

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
        statusPage.inputReferenceNumberForUnregisterFromLocation()
                .clickViewStatusButton()
                .verifyStatus(status);
    }
}
