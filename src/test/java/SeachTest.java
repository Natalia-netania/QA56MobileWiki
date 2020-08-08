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

import java.net.MalformedURLException;
import java.net.URL;


public class SeachTest extends TestBase {

SeachPageHelper searchPage;
CurrentArticlePageHelper articleSeleniumSoftware;

@BeforeMethod
public void initTests(){
    searchPage = PageFactory.initElements(driver,SeachPageHelper.class);
    articleSeleniumSoftware = new CurrentArticlePageHelper(driver, "Selenium (software)");
    searchPage.waitUntilPageIsLoaded();
}

    @Test
    public void wikiTest()  {

        //searchPage.waitUntilPageIsLoaded();
        //WebElement element = driver.findElement(By.xpath("//*[contains(@text,'Search Wikipedia')]"));
        //System.out.println("text of element:" + element.getText());
       Assert.assertEquals("Search Wikipedia", searchPage.getSearchFieldText());
    }

    @Test
    public void searchArticle(){
        //searchPage.waitUntilPageIsLoaded();
        searchPage.enterSearchText("Selenium");
        Assert.assertTrue(searchPage.existArticleInTheSearchResult("Selenium (software)"));
    }
    @Test
    public void searchArticleAndOpen() throws InterruptedException {
        searchPage.enterSearchText("Selenium");
        searchPage.openArticle("Selenium (software)");
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        Assert.assertTrue(articleSeleniumSoftware.isOpenedCorrectly());
       //Thread.sleep((15000));
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
}
