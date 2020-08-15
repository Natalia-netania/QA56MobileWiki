package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CurrentArticlePageHelper extends PageBase{

    //@FindBy(id = "org.wikipedia:id/view_page_title_text")
    //WebElement articleTitle;//org.wikipedia:id/page_list_item_title
    @FindBy(xpath ="//*[contains(@text,'Selenium (software)')]")
    WebElement articleTitle;

    @FindBy(className = "android.support.v7.app.ActionBar$Tab")
    WebElement addToReadingListButton;
    @FindBy(id = "org.wikipedia:id/onboarding_button")
    WebElement gotItButton;
    @FindBy(id = "org.wikipedia:id/text_input")
    WebElement inputReadingListNameField;
    @FindBy(id = "android:id/button1")
    WebElement okButtonAddReadingList;
    @FindBy(xpath = "//*[@content-desc ='Navigate up']")
    WebElement closeArticleButton;

    private  String article;

    public CurrentArticlePageHelper(WebDriver driver, String article) {
        super(driver);
        this.article = article;
        PageFactory.initElements(driver,this);


    }

    public CurrentArticlePageHelper waitUntilPageIsLoaded() {

        waitUntilElementIsVisible(articleTitle,40);
        return this;
    }

    public boolean isOpenedCorrectly() {

        return articleTitle.getText().equals(article);
    }

    public CurrentArticlePageHelper addToNewReadingList(String listName) {
        addToReadingListButton.click();
        this.waitUntilElementIsClickable(gotItButton,15);
        gotItButton.click();
        waitUntilElementIsClickable(inputReadingListNameField,15);
        inputReadingListNameField.clear();
        inputReadingListNameField.sendKeys(listName);
        okButtonAddReadingList.click();
        //waitUntilElementIsInvisible(okButtonAddReadingList,15);
        waitUntilElementIsClickable(closeArticleButton,15);
        return this;

    }

    public CurrentArticlePageHelper closeArticle(){
        closeArticleButton.click();
        return this;
    }

    public void swipeUp(){
        AppiumDriver appiumDriver = (AppiumDriver)(driver);
        TouchAction action = new TouchAction(appiumDriver);
        Dimension size = driver.manage().window().getSize();
        int x1 = (int) (size.width*0.5);
        int y1 = (int) (size.height*0.8);
        int y2 = (int) (size.height*0.2);
        action.press(PointOption.point(x1,y1)).waitAction().moveTo(PointOption.point(x1,y2)).release().perform();
    }

    public void swipeToFooter() {
        swipeUpToElement(By.xpath("//*[@test = 'View page in browser']"),20);
    }

   //private void swipeUpToElement(By by, int maxTim) {
    //    int counter = 0;
    //    while (driver.findElements(by).size()==0&&counter<maxTim){
   //         swipeUp();
    //        counter++;
   //     }
  //  }


    public boolean isEndOfArticle() {
        return driver.findElements(By.id("org.wikipedia:id/read_more_header")).size()>0;
    }

    public void rotateScreenLandscape() {
        AppiumDriver appiumDriver = (AppiumDriver)(driver);
        appiumDriver.rotate(ScreenOrientation.LANDSCAPE);

    }

    public void rotateScreenPortrait() {
        AppiumDriver appiumDriver = (AppiumDriver)(driver);
        appiumDriver.rotate(ScreenOrientation.PORTRAIT);

    }
   /* public void openArticleMenu(String article){
        AppiumDriver appiDriver = (AppiumDriver)(driver);
        TouchAction action = new TouchAction((appiDriver));
        WebElement articleName = driver.findElement(By.xpath(xPathArticleName(article)));
        int x = articleName.getLocation().x+3;
        int y = articleName.getLocation().y+3;
                action.longPress(PointOption.point(x,y)).waitAction().release().perform();
    }

    public void closeMenu(){
        AppiumDriver appDriver = (AppiumDriver)(driver);
        TouchAction action = new TouchAction((appDriver));
        WebElement menuOpen = driver.findElement(By.xpath("//*[text = 'Open']"));
        int x = (int)(menuOpen.getLocation().x*0.5);
        
        action.press(PointOption.point(x,y));
    }*/
}
