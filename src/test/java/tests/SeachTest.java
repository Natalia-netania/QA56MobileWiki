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


public class SeachTest extends TestBase {

SeachPageHelper searchPage;
CurrentArticlePageHelper articleSeleniumSoftware;

@BeforeMethod(alwaysRun = true)
public void initTests(){
    searchPage = PageFactory.initElements(driver,SeachPageHelper.class);
    articleSeleniumSoftware = new CurrentArticlePageHelper(driver, "Selenium (software)");
    searchPage.waitUntilPageIsLoaded();
}

    @Test
    public void wikiTest()  {
    
       Assert.assertEquals("Search Wikipedia", searchPage.getSearchFieldText());
    }

    @Test
    public void searchArticle(){
        //searchPage.waitUntilPageIsLoaded();
        searchPage.enterSearchText("Selenium");
        Assert.assertTrue(searchPage.existArticleInTheSearchResult("Selenium (software)"));
    }
    @Test
    public void searchArticleAndOpens() throws InterruptedException {
        searchPage.enterSearchText("Selenium");
        searchPage.openArticle("Selenium (software)");
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        //searchPage.enterSearchText(search);
        //searchPage.openArticle(article);
        Assert.assertTrue(articleSeleniumSoftware.isOpenedCorrectly());
       //Thread.sleep((15000));
    }

    @Test
    public void searthArticleOpenAndRotate(){
        String search = "Selenium";
        String article ="Selenium (software)";
        searchPage.enterSearchText(search);
        searchPage.openArticle(article);
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        articleSeleniumSoftware.rotateScreenLandscape();
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        articleSeleniumSoftware.rotateScreenPortrait();
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        Assert.assertTrue(articleSeleniumSoftware.isOpenedCorrectly());
    }

   @Test
    public void searchArticleInReadingList() throws InterruptedException {
       searchPage.enterSearchText("Selenium");
       searchPage.openArticle("Selenium (software)");
       articleSeleniumSoftware.waitUntilPageIsLoaded();
       searchPage.openReadList();
       searchPage.openButtonGotIt();
       searchPage.enterNameReadList("Reading List");
       searchPage.waitUntilElementIsLoaded();
       searchPage.checkedReadingList();
       Assert.assertTrue(searchPage.existArticle("Selenium (software)"));
       //Thread.sleep((15000));

   }

   @Test
   public void searthArticleOpenAndBackground(){
       String search = "Selenium";
       String article ="Selenium (software)";
       searchPage.enterSearchText(search);
       searchPage.openArticle(article);
       articleSeleniumSoftware.waitUntilPageIsLoaded();
       articleSeleniumSoftware.runBackGround(2);
       articleSeleniumSoftware.waitUntilPageIsLoaded();
       Assert.assertTrue(articleSeleniumSoftware.isOpenedCorrectly());
   }

   @Test
    public void  searchActicleOpenSwipe() throws InterruptedException {
       searchPage.enterSearchText("Selenium");
       searchPage.openArticle("Selenium (software)");
       articleSeleniumSoftware.waitUntilPageIsLoaded();
        articleSeleniumSoftware.swipeUp();
       articleSeleniumSoftware.swipeUp();
        Thread .sleep(10000);
   }
   @Test
   public void  searchActicleOpenSwipeToFooter() throws InterruptedException {
       searchPage.enterSearchText("Selenium");
       searchPage.openArticle("Selenium (software)");
       articleSeleniumSoftware.waitUntilPageIsLoaded();
       articleSeleniumSoftware.swipeToFooter();
       Assert.assertTrue(articleSeleniumSoftware.isEndOfArticle());
       Thread .sleep(5000);
   }

   @Test(groups ={"smoke","regression"})
    public void searchArticleAndOpenMenuArticle(){
       String search = "Selenium";
       String article ="Selenium (software)";
       searchPage.enterSearchText(search);
       searchPage.openArticleMenu(article);
       try {
           Thread.sleep(2000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       searchPage.closeArticleMenu();
   }
       //articleSeleniumSoftware.waitUntilPageIsLoaded();
     //  articleSeleniumSoftware.openArticleMenu(article);

@Test(dataProviderClass = DataProviders.class,dataProvider = "searchArticle")
    public  void searchArticleAndOpen(String search, String article){

   // String search = "Selenium";
    //String article ="Selenium (software)";
    searchPage.enterSearchText(search);
    searchPage.openArticle(article);
    articleSeleniumSoftware.waitUntilPageIsLoaded();
    Assert.assertTrue(articleSeleniumSoftware.isOpenedCorrectly());
}

    @Test(dataProviderClass = DataProviders.class,
            dataProvider = "searchArticlesFromFile")
    public void searchArticles(String search, String article){
        searchPage.enterSearchText(search);
        searchPage.openArticle(article);
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        Assert.assertTrue(articleSeleniumSoftware.isOpenedCorrectly());
    }
   }

