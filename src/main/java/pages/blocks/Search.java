package pages.blocks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Search {
    public static final By DEFAULT_SEARCH_LOCATOR = By.cssSelector(".searchbox");
    public static final By INPUT_LOCATOR = By.xpath(".//input");
    public static final By RESULTS_LOCATOR = By.id("search_suggestion_contents");
    public static final By RESULTS_LINKS_LOCATOR = By.xpath("//div[@id='search_suggestion_contents']/a");
    private SelenideElement search;

    public Search() {
        search = $(DEFAULT_SEARCH_LOCATOR);
    }

    public Search isDisplayed(boolean isDisplayed){
        if(isDisplayed) {
            $(DEFAULT_SEARCH_LOCATOR).shouldBe(Condition.visible);
        } else {
            $(DEFAULT_SEARCH_LOCATOR).shouldNotBe(Condition.visible);
        }
        return this;
    }
    public void findGame(String game) {
        //stub
    }

    public Search type(String text) {
        search.
                $(INPUT_LOCATOR)
                .setValue(text);
//        search.sendKeys(text);
        //todo add waiter for js
        return this;
    }

    public void select(String resultByText) {
        search
                .$(RESULTS_LOCATOR);
        //todo finish
    }

    public void select(int resultByNumber) {
        $$(RESULTS_LINKS_LOCATOR)
                .get(resultByNumber - 1)//for correct get from array
                .click();
    }

    public ElementsCollection getSearchResults() {
        return $$(By.xpath("results"));
    }
}
