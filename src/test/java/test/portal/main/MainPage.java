package test.portal.main;

import common.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.TestBase;

public class MainPage extends TestBase {

    @Test (priority = 10)
    public void simpleSearchArchivalCertificates() throws Exception {
        String service = "Видача архівних довідок, копій, витягів";
        mainPage.typeInSearchField(service);
        app.pause(5000);
        Assert.assertEquals(mainPage.services.getText(), service);
    }

    @Test (priority = 20)
    public void availabilityStatisticTab() throws Exception {
        // ------------------- Тестовые данные -------------------//
        String service = Constants.Services.MVD.CRIMINAL_RECORD;
        String serviceTab = "Статистика";
        String timingColumn = "Таймінг ?";
        String timingRow = "годин";
        String numberOfServicesProvidedColumn = "Кількість наданих послуг";
        String regionRow = "Дніпропетровська";

        // --------------------- Тест-кейс----------------------//
        mainPage.typeInSearchField(service);
        app.pause(6000);
        mainPage.clickService(service);
        app.pause(2000); // временно
        Assert.assertEquals(selectAreaPage.serviceName.getText(), service);
        selectAreaPage.clickServiceTab(serviceTab);
        app.pause(4000); // временно
        Assert.assertEquals(statisticTab.timingColumn.getText(), timingColumn);
        Assert.assertTrue(statisticTab.timingRow.getText().contains(timingRow));
        Assert.assertEquals(statisticTab.numberOfServicesProvidedColumn.getText(), numberOfServicesProvidedColumn);
        Assert.assertEquals(statisticTab.regionRow.getText(), regionRow);
    }
}