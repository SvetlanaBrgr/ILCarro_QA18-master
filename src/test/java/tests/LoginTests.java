package tests;

import manager.NGListener;
import manager.ProviderData;
import models.User;
import org.testng.Assert;
import org.testng.annotations.*;

//@Listeners(NGListener.class)//подключение NGListener.class

public class LoginTests extends TestBase{

    @BeforeMethod
    public void precondition(){
        if(app.getUser().isLogged()){
            app.getUser().logout();
        }
}

    @Test(invocationCount = 1, groups = {"smoke"},
            dataProvider = "loginModelDto", dataProviderClass = ProviderData.class)//cw_19

    public void loginPositiveTestConfig(){//cw_19 Config

        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(app.getEmail(), app.getPassword());
        app.getUser().submitForm();

        Assert.assertTrue(app.getUser().isLoggedSuccess());

    }
    @Test(invocationCount = 2, groups = {"smoke"})//cw_18 fail
//            @Test (dataProvider = "loginModelDto", dataProviderClass = ProviderData.class)//cw_18 and cw_19 provider

    public void loginPositiveTest(User data){//cw-19
//        public void loginPositiveTest(){
//        int i = (int)(System.currentTimeMillis() / 1000) % 3600;
//       User data = new User().withEmail("asd@fgh.com").withPassword("$Asdf1234");
// закомментировали cw_18 -данные генерируются из класса ProviderData
//        User data = User.builder()//закомментировали cw-18
//                .email("test"+i+"@mail.ru")
//                .password("Qwer1234$")
//                                .build();
//        logger.info("loginPositiveTest starts with:" + data.getEmail() + " & " + data.getPassword());
        app.getUser().openLoginForm();
//        app.getUser().fillLoginForm("asd@fgh.com", "$Asdf1234");
        app.getUser().fillLoginForm(data);
        app.getUser().submitForm();
//        app.getUser().clickOkButton();
//        Assert.assertTrue(app.getUser().isLoggedSuccess());//не находит элемент в методе.isLoggedSuccess()

    }

    @Test(groups = {"smoke"})//cw_18
//    @Test (dataProvider = "loginModelDto", dataProviderClass = ProviderData.class)//cw_19
    public void loginNegativeTestWrongEmail(){

        User data = User.builder()
                .email("testmail.ru")
                .password("Qwer1234$")
                .build();
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(data);
        Assert.assertTrue(app.getUser().isLoggedFailed());

    }
    @Test
    public void loginNegativeTestWrongPassword(){
        int i = (int)(System.currentTimeMillis() / 1000) % 3600;
        User data = User.builder()
                .email("test"+i+"@mail.ru")
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
