package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyCurrentListHelper extends PageBase{
    @FindBy(id = "org.wikipedia:id/item_title")
    WebElement title;
    @FindBy(id = "org.wikipedia:id/page_list_item_title")
    List<WebElement> articleTitlesList;

    public MyCurrentListHelper(WebDriver driver) {
        super(driver);
    }

    public MyCurrentListHelper waitUntilPageIsLoaded(){
        waitUntilElementIsVisible(title,15);
        waitUntilAllElementsAreVisible(articleTitlesList,15);
        return this;
    }


    public boolean existsArticle(String article) {
        boolean flag = false;
        for(WebElement title: articleTitlesList){
            if(title.getText().equals(article)) flag = true;
        }
        return flag;
    }
}
