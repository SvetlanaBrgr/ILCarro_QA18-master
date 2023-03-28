package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

//@BeforeMethod
//        public void preCondition(){
//    if(app.getUser().isLogged()){
//        app.getUser().logout();
//    }
//}
    @Test
    public void loginPositiveTest(){
        int i = (int)(System.currentTimeMillis() / 1000) % 3600;
//        User data = new User().withEmail("asd@fgh.com").withPassword("$Asdf1234");
        User data = User.builder()
                .email("test"+i+"@mail.ru")
                .password("Qwer1234$")
                                .build();
        logger.info("regPositiveTest starts with:" + data.getEmail() + " & " + data.getPassword());
        app.getUser().openLoginForm();
//        app.getUser().fillLoginForm("asd@fgh.com", "$Asdf1234");
        app.getUser().fillLoginForm(data);
        app.getUser().submitForm();
        app.getUser().clickOkButton();
//        Assert.assertTrue(app.getUser().isLoggedSuccess());//не находит элемент в методе.isLoggedSuccess()
    }

    @Test
    public void loginNegativeTestWrongEmail(){
        int i = (int)(System.currentTimeMillis() / 1000) % 3600;
        User data = User.builder()
                .email("testmail.ru")
                .password("Qwer1234$")
                .build();
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(data);


    }
    @Test
    public void loginNegativeTestWrongPassword(){
        User data = User.builder()
                .email("test+i+@mail.ru")
                .password("qw34")
                .build();
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(data);

    }
    @AfterMethod
    public void postCondition(){
        app.getUser().clickOkButton();
    }
}
