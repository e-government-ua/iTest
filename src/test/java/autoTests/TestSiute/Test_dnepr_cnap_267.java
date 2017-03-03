/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 *
 * @author HS
 */
public class Test_dnepr_cnap_267 extends CustomMethods {
//<editor-fold desc="Тестовый пример загрузки файла">
    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 2)
    public void Test_dnepr_cnap_267() throws Exception {
        /*****************************************объявляем элементы страниц*******************************************/
        TemplatePage o = new TemplatePage(driver);
        String sBP = "dnepr_cnap_267";
        String email = "autotestbeta@gmail.com";

        _step("1. Вход по прямому URL на услугу");
        openURLservice(driver, CV.baseUrl + "/service/160/general");
        
        _step("3. Выбор области/города");
        o.selectRegion("Дніпропетровська");
        
            _step("4. Авторизация Off AuthMock/BankID");
            o.mokAuthorization();
        
        _step("5. Заполняем форму услуги");
        setFieldValue(driver, sBP, "phone", "+380623155533");
        setFieldValue(driver, sBP, "email", email);
        setFieldSelectByText(driver,sBP,"type_lic","експорт"); // ENUM
        setFieldSelectByText(driver,sBP,"subekt","фізична особа-підприємець"); // ENUM
        setFieldValue(driver, sBP, "fiz_name", "Іван Іванов Іванович");
        setFieldValue(driver, sBP, "fiz_adres", " Україна, Дніпропетровська область, м. Дніпро");
        setFieldValue(driver, sBP, "fiz_drfo", "1234567899123");
        
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sNumberAccountUah0", "12345678901223456789");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sBankNameUah0", "PRIVATBANK");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sBankMfoUah0", "012345");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sBankAdresUah0", "вул. Банкова, 11, м. Київ, 01220");
        addTableRow(driver, sBP, "sTable1");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sNumberAccountUah1", "12345678901223456789");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sBankNameUah1", "PRIVATBANK");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sBankMfoUah1", "012345");
        setTableCellsInputTypeString(driver, sBP, "sTable1", "sBankAdresUah1", "вул. Банкова, 11, м. Київ, 01220");

        setTableCellsInputTypeString(driver, sBP, "sTable2", "sNumberAccountCurrency0", "12345678901223456789");
        setTableCellsInputTypeString(driver, sBP, "sTable2", "sBankNameCurrency0", "PRIVATBANK");
        setTableCellsInputTypeString(driver, sBP, "sTable2", "sBankMfoCurrency0", "012345");
        setTableCellsInputTypeString(driver, sBP, "sTable2", "sBankAdresCurrency0", "вул. Банкова, 11, м. Київ, 01220");
        addTableRow(driver, sBP, "sTable2");
        setTableCellsInputTypeString(driver, sBP, "sTable2", "sNumberAccountCurrency1", "12345678901223456789");
        setTableCellsInputTypeString(driver, sBP, "sTable2", "sBankNameCurrency1", "PRIVATBANK");
        setTableCellsInputTypeString(driver, sBP, "sTable2", "sBankMfoCurrency1", "012345");
        setTableCellsInputTypeString(driver, sBP, "sTable2", "sBankAdresCurrency1", "вул. Банкова, 11, м. Київ, 01220");
        
        setFieldSelectByText(driver, sBP, "subekt_1", "юридична особа");
        setFieldValue(driver, sBP, "ur_name_1", "Deutsche Bank");
        setFieldValue(driver, sBP, "ur_adres_1", "PJSC “Deutsche Bank DBU” Lavrska street 20 01015 Kiev UKRAINE");
        setFieldValue(driver, sBP, "applicant_account_for", "1234567899123");
        setFieldValue(driver, sBP, "for_bank_name", "Deutsche Bank DBU");
        setFieldValue(driver, sBP, "for_bank_adres", "PJSC “Deutsche Bank DBU” Lavrska street 20 01015 Kiev UKRAINE");
        
        setTableCellsInputTypeSelect(driver, sBP, "sTable3","sObjectCustoms0", "-0-0");
        setTableCellsInputTypeString(driver, sBP, "sTable3", "nQuantity0", "2");
        setTableCellsInputTypeEnum(driver, sBP, "sTable3","sUnit0", "1");
        setTableCellsInputTypeString(driver, sBP, "sTable3", "sCostUsd0","12.02020");
        setTableCellsInputTypeString(driver, sBP, "sTable3", "sCostCurrency0","12.02020");
        setTableCellsInputTypeString(driver, sBP, "sTable3", "sAddNameProduct0","12.02020");
        
        setTableCellsInputTypeEnum(driver, sBP, "sTable4","sBasis0","1");
        setTableCellsInputTypeString(driver, sBP, "sTable4", "sBasisName0","м. Дніпро");
        addTableRow(driver, sBP, "sTable4");
        setTableCellsInputTypeEnum(driver, sBP, "sTable4","sBasis1","1");
        setTableCellsInputTypeString(driver, sBP, "sTable4", "sBasisName1","м. Дніпро");
        
        setTableCellsInputTypeSelect(driver, sBP, "sTable5", "sCountry_00", "-1-0");
        
        
        setFieldTypeSelect(driver, sBP, "sCountry_1", "-6-2");
        setTableCellsInputTypeSelect(driver, sBP, "sTable6", "sCountry_20", "-2-1");
        setTableCellsInputTypeSelect(driver, sBP, "sTable7", "sCurrency0", "-3-1");
        setTableCellsInputTypeSelect(driver, sBP, "sTable8", "sCurrency_10", "-4-3");
        setTableCellsInputTypeEnum(driver, sBP, "sTable10", "SCharakterUgoda0", "1");    
        setFieldValue(driver, sBP, "dogovor_number", "№32145");
        setFieldCalendar(driver, sBP, "dogovor_date", "01/03/17"); 
        setTableCellsInputTypeString(driver, sBP, "sTable11", "sNameDodatok0","Назва документу");
        setTableCellsInputTypeString(driver, sBP, "sTable11","sNumberDodatok0", "1234657");
        setTableCellsTypeCalendar(driver, sBP, "sTable11", "sDateDodatok0", "01/03/18"); 
        setFieldValue(driver, sBP, "special", "№32145");
        setFieldFile(driver, sBP, "dogovor", "src/test/resources/files/test.jpg");
        setFieldFile(driver, sBP, "appeal", "src/test/resources/files/test.jpg");
        setFieldFile(driver, sBP, "visnovok", "src/test/resources/files/test.jpg");
        setFieldFile(driver, sBP, "other_docs", "src/test/resources/files/test.jpg");
         
        click(driver, o.buttonSendingForm);

        
        
        
    }
}
