package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.CurrentArticlePageHelper;
import pages.SeachPageHelper;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {

    public AppiumDriver driver;
    SeachPageHelper seachPageHelper;
    CurrentArticleTests currentArticleTests;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","CustomePhone7");
        capabilities.setCapability("platformVersion","7.0");
        //capabilities.setCapability("noSign",true);
        //capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:/Users/Elena/IdeaProjects/untitled3/untitled3/untitled3/MvnTest/MvnTest_QA56/OscarShopProjectQA56/QA56MobileWiki/apk/wikipedia.apk");
        capabilities.setCapability("automationName","Uiautomator1");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        seachPageHelper = PageFactory.initElements(driver,SeachPageHelper.class);
       // currentArticleTests = PageFactory.initElements(driver, CurrentArticlePageHelper.class);
        seachPageHelper.waitUntilPageIsLoaded();
        //currentArticleTests.rotateScreenPortrait



    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
