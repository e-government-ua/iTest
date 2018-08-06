package autoTests.TestSiute.iDoc;

import static com.codeborne.selenide.Selenide.open;
import autoTests.CustomMethods;
import org.junit.Test;

public class Test_doc_btsol_vertical_sz__part_3_2_signer extends CustomMethods {
    @Test
    public void Test_part_3_2_signer() throws Exception {
        String sBP = "_doc_btsol_vertical_sz";
        String email = "autotestbeta@gmail.com";

        //logins beta-autotest
        String LoginAuthor = "ZCPK_310767TVV";
        String NameAuthor = "Терентьєв Володимир Володимирович";
        String Login1 = "ZCPK_020379CDP";
        String Name1 = "Чмихал Дмитро Павлович";
        String Login2 = "ZCPK_150960POV";
        String Name2 = "Пітула Олександр Володимирович";
        String Login3 = "ZCPK_050991BSO";
        String Name3 = "Будай Coломія Олексіївна";
        String Login4 = "ZCPK_280562DGI";
        String Name4 = "Долінська Галина Йосипівна";
        String Login5 = "ZCPK_220185NSV";
        String Name5 = "Норов Станіслав Валентинович";
        String Login6 = "ZCPK_230161DYR";
        String Name6 = "Давидчак Ярослав Романович";
        String Login7 = "ZCPK_031260SVM";
        String Name7 = "Стефанів Василь Михайлович";

        //logins beta for wiev and delete
        String LoginCollective1 = "IGOV_160582SOD";
        String NameCollective1 = "Смоктій Оксана Данилівна";
        String LoginCollective2 = "IGOV_310780BVV";
        String NameCollective2 = "Белявцев Володимир Володимирович";
        String LoginCollective3 = "IGOV_301082BOY";
        String NameCollective3 = "Бондарь Ольга Євгенієвна";
        String LoginCollective4 = "IGOV_100982SOV";
        String NameCollective4 = "Смірнова Олена Володимирівна";
        String LoginCollective5 = "IGOV_210961SMU";
        String NameCollective5 = "Соколова Марина Юріївна";
        String LoginCollective6 = "IGOV_311288BUD";
        String NameCollective6 = "Біла Юлія Данилівна";
        String LoginCollective7 = "IGOV_180277SMV";
        String NameCollective7 = "Свідрань Максим Володимирович";

        openURLdashboard(getRegionUrl());

        login(LoginAuthor, " ");
        navigateToggleMenu();
        createDocumentOrTask("Службова записка");
        clickButton("Далi");
        pause(5000);

        setDocTitle("Тест подписантов");
        setDocContent("Тест служебной записки , подписанты");

        //добавить подписантов (в таблицу)
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "0", NameCollective1);
        addRegionsTableRow("sTableAccept");
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "1", Name1);
        setApprover(sBP, "sTableAgree", "sName_Approver", "0", NameCollective2);
        addRegionsTableRow("sTableAgree");
        setApprover(sBP, "sTableAgree", "sName_Approver", "1", Name2);
        setDirect(sBP, "sTableDirect", "sName_Direct", "0", NameCollective3);
        addRegionsTableRow("sTableDirect");
        setDirect(sBP, "sTableDirect", "sName_Direct", "1", Name3);

        getOrderFromUrlCurrentPage();
        clickButtonCreate();
        pause(4000);
        logout();

        //заходим под подписантом Name1
        login(Login1, " ");
        setRegionFindOrderByNumberDocument();
        clickButton("Інші дії");

        //добавить на просмотр
        addViewer(NameCollective4);
        closeParticipant();
        //добавить на ознакомление
        addVisor(Name4);
        closeParticipant();
        //добавить на подпись
        addAcceptor(Name5);
        closeParticipant();
        //Делегирование себе
        addDelegate(LoginAuthor);
        pause(10000);





    }

}
