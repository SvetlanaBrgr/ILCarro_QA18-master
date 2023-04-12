package tests;

import manager.ProviderData;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getUser().isLogged()) {
            app.getUser().logout();
        }
    }

    @Test(dataProvider = "registrationCsv", dataProviderClass = ProviderData.class)

    public void regPositiveTestCSV (User user) {
        logger.info("regPositiveTest starts with:" + user.getEmail() + " & " + user.getPassword());

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().clickcheckbox();// 3 варианта метода в HelperUser
        app.getUser().buttonYalla();
        logger.info("registrationPositiveTest completed");
        Assert.assertTrue(app.getUser().isLoggedSuccess());

    }
        @Test
        public void regPositiveTest () {
            int i = (int) (System.currentTimeMillis() / 1000) % 3600;
            User user = User.builder()
                    .name("Rosa")
                    .lastName("Levit")
                    .email("test" + i + "@mail.ru")
                    .password("Qwer1234$")
                    .build();

            logger.info("regPositiveTest starts with:" + user.getEmail() + " & " + user.getPassword());
            app.getUser().openRegistrationForm();
//      app.getUser().fillRegistrationForm(name, lastName,email,password);
            app.getUser().fillRegistrationForm(user);
            app.getUser().clickcheckbox();// 3 варианта метода в HelperUser
            app.getUser().buttonYalla();
//        app.getUser().submitButtonYalla(); // не находит элемент
//        Assert.assertTrue(app.getUser().isLogged());
            logger.info("registrationPositiveTest completed");
            Assert.assertTrue(app.getUser().isLoggedSuccess());

//        User user = new User()
//                .withName("Rosa")
//                .withLastName("Levit")
//                .withEmail("test"+i+"@mail.ru")
//                .withPassword("Qwer1234$");
//        String name = "Rosa";
//        String lastName = "Levit";
//        String email =  "test"+i+"@mail.ru";
//        String password = "Qwer1234$";
        }

        @Test
        public void regNegativeTestWrongEmail () {
            User user = User.builder()
                    .name("Rosa")
                    .lastName("Levit")
                    .email("testmail.ru")
                    .password("Qwer1234$")
                    .build();

            app.getUser().openRegistrationForm();
            app.getUser().fillRegistrationForm(user);
            app.getUser().clickcheckbox();// 3 варианта метода в HelperUser
            app.getUser().buttonYalla();
            //        app.getUser().submitButtonYalla(); // не находит элемент
        }
        @Test
        public void regNegativeTestWrongPassword () {
            int i = (int) (System.currentTimeMillis() / 1000) % 3600;
            User user = new User()
                    .withName("Rosa")
                    .withLastName("Levit")
                    .withEmail("test" + i + "@mail.ru")
                    .withPassword("qw12");

            app.getUser().openRegistrationForm();
            app.getUser().fillRegistrationForm(user);
            app.getUser().clickcheckbox();// 3 варианта метода в HelperUser
            app.getUser().buttonYalla();
//        app.getUser().submitButtonYalla(); // не находит элемент
        }

//        @AfterMethod
//        public void postCondition () {
//            app.getUser().clickOkButton();
//        }
    }



