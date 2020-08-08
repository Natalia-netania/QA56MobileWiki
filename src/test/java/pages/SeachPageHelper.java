package pages;

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

    public void enterSearchText(String text) {
        searchField.click();
        waitUntilElementIsClickable(searchInput,10);
        searchInput.sendKeys(text);
        waitUntilAllElementsAreVisible(articlesNameList,15);
        System.out.println("Articles quantity: " + articlesNameList.size());

    }

    public boolean existArticleInTheSearchResult(String article) {
        Boolean flag = false;
        for (WebElement element: articlesNameList){
            if (element.getText().equals(article)) flag = true;
        }
        return  flag;
    }

    public void openArticle(String article) {
        driver.findElement(By.xpath(xPathArticleName(article))).click();
    }

    private String xPathArticleName(String article){
        return "//*[@text='" + article +"']";
    }

    public void openReadList() {
        buttonCreateReadList.click();
    }

    public void openButtonGotIt(){
        buttonGotIt.click();
    }

    public void enterNameReadList(String name){
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
