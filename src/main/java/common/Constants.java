package common;

public class Constants {

    public static class Settings {

        //------------------- Регионы  -------------------//
        public static class Region {
            public static final String DNIPROPETROVSKA = "Дніпропетровська";
            public static final String POLTAVSKA = "Полтавська";
        }

        //------------------- Города  --------------------//
        public static class City {
            public static final String DNIPROPETROVSK = "Дніпропетровськ";
            public static final String POLTAVA = "Полтава";
        }

        //------------------- Услуги  --------------------//

        // Міліція та ДАІ
        public static class ServiceMVD {
            public static final String CRIMINAL_RECORD = "Надання довідки про відсутність (наявність) судимості або обмежень, передбачених кримінально-процесуальним законодавством (терміново)";
        }

        // Взаємодія з державними органами
        public static class InteractionWithPublicAuthorities {
            public static final String SUBSIDY = "Отримання субсидії на оплату житлово-комунальних послуг";
            public static final String REGISTRATION = "Зняття з реєстрації місця проживання";
            public static final String ASSIGN_SOCIAL_ASSISTANCE_FOR_CHILD_BIRTH = "Призначення соціальної допомоги при народженні дитини";
        }

        // Посвідчення особи, Громадянство, Місце проживання
        public static class Identity {
            public static final String INTERNATIONAL_PASSPORT = "Видача/заміна паспорта громадянина для виїзду за кордон";
        }

        // Податки
        public static class Taxes {
            public static final String PERSONAL_INCOME_CERTIFICATE = "Отримання довідки про доходи фізичних осіб";
        }

        //------------------- Данные для подачи заявок  --------------//
        public static class Data {
            public static final String PHONE = "380102030405";
            public static final String FIO_UA = "Дмитро Олександрович Дубілет";
            public static final String FIO_RUS = "Дубилет Дмитрий Александрович";
            public static final String E_MAIL = "vidokgulich@gmail.com";
            public static final String BIRTH_DAY = "01.01.1970";
            public static final String BIRTH_LOCAL = "Україна,Дніпропетровська,Дніпропетровськ";
            public static final String RESIDENT = "Українське";
        }

        //---------- Данные для входа в Банк ИД ПриаптБанк  ----------//

        public static class BankIDprivatBank {
            public static final String LOGIN = "+380102030405";   // номер телефона
            public static final String PASSWORD = "value";   // пароль
            public static final String OTP1 = "12";   // Одноразовый пароль
            public static final String OTP2 = "34";   // Одноразовый пароль
            public static final String OTP3 = "56";   // Одноразовый пароль
        }

        public static class TestBankDetails {
            public static final String BANK_NAME = "ПриватБанк";
            public static final String BANK_MFO = "305299";
            public static final String BANK_OKPO = "14360570";
            public static final String BANK_IPN = "143605704021";
            public static final String BANK_ACCOUNT = "1234567890123456";
        }
    }

    //------------------- УРЛ для работы с тестовыми услугами  ------------------//
    public static class TestService {
        public static final String TEST_DEPENDENCE_FORM = "/service/671/general";
        public static final String TEST_FIELDS_BANKID = "/service/720/general";
        public static final String TEST_LIQPAY = "/service/674/general";
        public static final String TEST_MAILER = "/service/755/general";
        public static final String TEST_PRINT_FORM = "/service/673/general";
        public static final String TEST_QUEUE_CANCEL = "/service/756/general";
        public static final String TEST_ZP_CNAP_MAILER = "/service/1426/general";
    }

    //--------------------- Сервера для запуска тестов  ---------------------------//
    public static class Server {
        public static final String VERSION_SERVER = "https://test-version.igov.org.ua";
        public static final String TEST_SERVER = "https://test.igov.org.ua/";
        // https://test-version.igov.org.ua/   - для регреса
        // https://test.igov.org.ua/   - тестовый
    }
}
