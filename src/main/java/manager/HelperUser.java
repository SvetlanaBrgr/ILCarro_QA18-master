package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperUser extends HelperBase{

    public HelperUser(WebDriver wd) {
        super(wd);
    }
    public void openLoginForm() {
        click(By.xpath("//a[.=' Log in ']"));
    }
    public void openRegistrationForm() {
        click(By.xpath("//a[.=' Sign up ']"));
    }
    public void fillLoginForm(String email, String password){
        type(By.id("email"), email);
        type(By.id("password"), password);
    }
    public void fillLoginForm(User data){//overloading
        type(By.id("email"), data.getEmail());
        type(By.id("password"), data.getPassword());
    }
//    public void fillRegistrationForm(String name, String lastName, String email, String password){
//        type(By.id("name"), name);
//        type(By.id("lastName"), lastName);
//        type(By.id("email"), email);
//        type(By.id("password"), password);
//    }
    public void fillRegistrationForm(User user){
        type(By.id("name"), user.getName());
        type(By.id("lastName"), user.getName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }
    public void submitForm(){
        wd.findElement(By.cssSelector("[type='submit']")).submit();
    }

//    public void clickCheckbox(){}



    public void logout(){
        click(By.xpath("//a[.='Logged in']"));
    }
    public boolean isLogged() {
        return isElementPresent(By.xpath("//a[.='Logged in']"));
    }


    public boolean isLoggedSuccess() {//для Логина и для Регистрации одно окно
        WebDriverWait wait = new WebDriverWait(wd, 10);
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.xpath("//h2[.='You are logged in success']"))));
        return wd.findElement(By.xpath("//h2[.='You are logged in success']")).getText().contains("success");
    }


    public boolean isLoggedFailed() {
        WebDriverWait wait = new WebDriverWait(wd, 10);
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.xpath("//div[.=\"It'snot look like email\"]"))));
        return wd.findElement(By.xpath("//div[.=\"It'snot look like email\"]")).getText().contains("like email");
    }
    public void clickOkButton(){
        if (isElementPresent(By.xpath("//button[.='Ok']"))){
            click(By.xpath("//button[.='Ok']"));
        }
    }

    public void checkboxLabel(){
//        click(By.xpath("//label[@class = 'checkbox-label terms-label']"));// 1-й вариант

        //==== 2-й вариант через библиотеку Javascript
//        JavascriptExecutor script = (JavascriptExecutor) wd;
//        script.executeScript("document.querySelector('#terms-of-use').click()");
        //"document.querySelector('#terms-of-use').click()" запрос через консоль кода страницы

        //==== 3-й вариант
        Rectangle rect = wd.findElement(By.xpath("//*[@class='checkbox-container']")).getRect();
        int x = rect.getX() + 5;//размер пикселей +5
        int y = rect.getY() + rect.getHeight()*1/2;//или так сдвиг на размер пикселей
        Actions actions = new Actions(wd) ;
                actions.moveByOffset(x,y).click().perform();
    }

    public void buttonYalla(){
        click(By.xpath("//button[@type='submit']"));
    }

//    public void submitButtonYalla() {
//        click(By.xpath("//button[@type='submit']"));
//    }

}
