package manager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);//создали объект
//    WebDriver wd;

    EventFiringWebDriver wd;
    HelperUser user;
    Properties properties;

//        String browser;
//
//        public ApplicationManager(String browser){
//        this.browser = browser;
//        properties = new Properties();
//    }
    public ApplicationManager(){
        properties = new Properties();
    }

    public void init() throws IOException {

        properties.load(new FileReader(new File ("src/test/resources/config.properties")));

//        wd = new ChromeDriver();
//        if(browser .equals(BrowserType.CHROME)){//CW-18 -//cw-19 - config.properties
//            wd = new EventFiringWebDriver(new ChromeDriver());
//            logger.info("Testing on Chrome Driver");
//        } else if (browser.equals(BrowserType.FIREFOX)) {
//            wd = new EventFiringWebDriver(new FirefoxDriver());
//            logger.info("Testing on Firefox Driver");
//        }
        wd = new EventFiringWebDriver(new ChromeDriver());
        wd.register(new MyListener());
//        wd.manage().window().maximize();
//        wd.navigate().to("https://ilcarro.web.app/search");//cw_19

        wd.navigate().to(properties.getProperty("web.baseURL"));//cw_19
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        user = new HelperUser(wd);
    }

    public HelperUser getUser() {
        return user;
    }

    public String getEmail(){
        return properties.getProperty("web.email");
    }
    public String getPassword(){
        return properties.getProperty("web.password");
    }
    public void stop(){
//        wd.quit();
    }

}
