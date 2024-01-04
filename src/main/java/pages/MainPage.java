package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.blocks.*;
import utils.Errors;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class MainPage extends BasePage {
    public static final By FEATURED_SECTION_LOCATOR = By.cssSelector(".home_page_content");

    public static final String MAIN_PAGE_TITLE_TEXT = "Welcome to Steam";
    public MainPage navigateOnMainPage() {
        navigateOnPageByUrl(DEFAULT_URL);
        return this;
    }

    public MainPage waitForPageLoaded() {
        $(FEATURED_SECTION_LOCATOR).shouldBe(Condition.visible, Duration.ofSeconds(10));
        Assert.assertTrue(
                Selenide.title().equalsIgnoreCase(MAIN_PAGE_TITLE_TEXT),
                Errors.PAGE_TITLE_MISMATCH_ERROR);
        return this;
    }

    public GutterMenu getGutterMenu(String name) {
        return new GutterMenu(name);
    }

    public GamesCarousel getCarousel(String name){
        return new GamesCarousel(name);
    }

    public MainPage checkHeaderIsDisplayed(boolean isDisplayed) {
        Assert.assertEquals(
                getHeader().isDisplayed(),
                isDisplayed,
                Errors.PAGE_HEADER_ERROR);
        return this;
    }

    public MainPage checkFooterIsDisplayed(boolean isDisplayed) {
        Assert.assertEquals(
                getFooter().isDisplayed(),
                isDisplayed,
                Errors.PAGE_FOOTER_ERROR);
        return this;
    }

}
