package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CurrentArticlePageHelper;
import pages.SeachPageHelper;
import tests.TestBase;
import util.DataProviders;

import java.net.MalformedURLException;
import java.net.URL;


public class MyTests extends TestBase{

    SeachPageHelper searchPage;
    CurrentArticlePageHelper articleSeleniumSoftware;

    @BeforeMethod
    public void initTests(){
        searchPage = PageFactory.initElements(driver,SeachPageHelper.class);
        articleSeleniumSoftware = new CurrentArticlePageHelper(driver, "Selenium (software)");
        searchPage.waitUntilPageIsLoaded();
    }

    @Test
    public void myTestArticalSwitzerland(){
        String search = "Switzerland";
        String article ="Languages of Switzerland";
        searchPage.enterSearchText(search);
        searchPage.openArticle(article);
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        articleSeleniumSoftware.rotateScreenLandscape();
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        articleSeleniumSoftware.rotateScreenPortrait();
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        Assert.assertTrue(articleSeleniumSoftware.isOpenedCorrectly());
    }
}
