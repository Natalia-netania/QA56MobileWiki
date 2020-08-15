package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SeachPageHelper extends PageBase{
    @FindBy(xpath ="//*[contains(@text,'Search Wikipedia')]" )
    WebElement searchField;

    @FindBy(id = "org.wikipedia:id/search_src_text")
    WebElement searchInput;

    @FindBy(id = "org.wikipedia:id/page_list_item_title")
    List<WebElement> articlesNameList;

    @FindBy(xpath ="//*[contains(@content-desc,'Add this article to a reading list')]" )
    WebElement buttonCreateReadList;

    @FindBy(id = "org.wikipedia:id/onboarding_button")
    WebElement buttonGotIt;

    @FindBy(id = "org.wikipedia:id/text_input")
    WebElement nameOfThisList;

    @FindBy(id = "android:id/button1")
    WebElement buttonOK;

    @FindBy(xpath ="//*[contains(@content-desc,'Navigate up')]" )
    WebElement buttonX;

    @FindBy(xpath ="//*[contains(@content-desc,'My lists')]" )
    WebElement buttonMyList;

    @FindBy(id = "org.wikipedia:id/item_title")
    WebElement titleReadingList;

    @FindBy(id = "org.wikipedia:id/page_list_item_title")
    WebElement titleArticle;

    @FindBy(xpath = "//*[@class='android.widget.FrameLayout'][@content-desc = 'My lists']")
    WebElement openMyListsButton;

    public SeachPageHelper(WebDriver driver){
        super(driver);
    }

    public void waitUntilPageIsLoaded() {
        //WebElement element = driver.findElement(By.xpath("//*[contains(@text,'Search Wikipedia')]"));
        waitUntilElementIsClickable(searchField,15);

    }

    public  String getSearchFieldText(){

        return searchField.getText();
    }

    public SeachPageHelper enterSearchText(String text) {
        System.out.println("x: " + searchField.getLocation().x);
        System.out.println("y: " + searchField.getLocation().y);
        searchField.click();
        waitUntilElementIsClickable(searchInput,10);
        searchInput.sendKeys(text);
        waitUntilAllElementsAreVisible(articlesNameList,15);
        System.out.println("Articles quantity: " + articlesNameList.size());
        return  this;

    }

    public boolean existArticleInTheSearchResult(String article) {
        Boolean flag = false;
        for (WebElement element: articlesNameList){
            if (element.getText().equals(article)) flag = true;
        }
        return  flag;
    }

    public SeachPageHelper openArticle(String article) {

        driver.findElement(By.xpath(xPathArticleName(article))).click();
        return this;
    }

    public SeachPageHelper openMyListsPage() {
        openMyListsButton.click();
        return this;
    }

    private String xPathArticleName(String article){

        return "//*[@text='" + article +"']";
    }

    public void openArticleMenu(String article) {
        AppiumDriver appDriver = (AppiumDriver)(driver);
        TouchAction action = new TouchAction(appDriver);
        WebElement articleName = driver
                .findElement(By.xpath(xPathArticleName(article)));
        int x = articleName.getLocation().x+3;
        int y = articleName.getLocation().y+3;
        action.longPress(PointOption.point(x,y))
                .waitAction()
                .release()
                .perform();

    }

    public void closeArticleMenu() {
        AppiumDriver appDriver = (AppiumDriver)(driver);
        TouchAction action = new TouchAction(appDriver);
        WebElement menuOpen = driver
                .findElement(By.xpath("//*[@text='Open']"));
        int x = (int)(menuOpen.getLocation().x *0.5);
        int y = menuOpen.getLocation().y;
        action.press(PointOption.point(x,y))
                .waitAction()
                .release()
                .perform();
    }
//my programs
    public void openReadList() {
        buttonCreateReadList.click();
    }

    public void openButtonGotIt(){
        buttonGotIt.click();
    }

    public void enterNameReadList(String name){
        nameOfThisList.clear();
        nameOfThisList.sendKeys(name);
        buttonOK.click();
    }

   public void waitUntilElementIsLoaded() {
        waitUntilElementIsVisible(buttonCreateReadList,40);
    }

    public void checkedReadingList(){
        buttonX.click();
        buttonMyList.click();
        titleReadingList.click();

    }

    public boolean existArticle(String text) {
        System.out.println("Article in ReadingList: " + titleArticle.getText());
        return titleArticle.getText().equals(text);
    }
}
