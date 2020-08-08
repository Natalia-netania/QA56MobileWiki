package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CurrentArticlePageHelper extends PageBase{

    @FindBy(id = "org.wikipedia:id/view_page_title_text")
    WebElement articleTitle;

    private  String article;

    public CurrentArticlePageHelper(WebDriver driver, String article) {
        super(driver);
        this.article = article;
        PageFactory.initElements(driver,this);


    }

    public void waitUntilPageIsLoaded() {

        waitUntilElementIsVisible(articleTitle,40);
    }

    public boolean isOpenedCorrectly() {

        return articleTitle.getText().equals(article);
    }
}
