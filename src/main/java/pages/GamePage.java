package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.blocks.DlcList;
import pages.blocks.MediaZone;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GamePage extends BasePage {

    public static final String COLLAPSED_SECTION_CLASS_NAME = ".game_page_autocollapse_ctn collapsed";
    public static final By GAME_TITLE_LOCATOR = By.cssSelector(".apphub_HomeHeaderContent");
    public static final By GAME_DESCRIPTION_LOCATOR = By.cssSelector(".game_area_description");
    public static final By GAME_REQUIREMENTS_LOCATOR = By.cssSelector(".sysreq_contents");
    public static final By GAME_SCREENSHOTS_LOCATOR = By.cssSelector(".highlight_screenshot_link");
    public static final By READ_MORE_LOCATOR = By.cssSelector(".game_page_autocollapse_readmore");

    private DlcList dlcList;
    private MediaZone mediaZone;

    public GamePage waitForPageLoaded() {
        $(GAME_TITLE_LOCATOR).shouldBe(Condition.visible, Duration.ofSeconds(10));
        return this;
    }

    public GamePage checkDescription(String text) {
        $(GAME_DESCRIPTION_LOCATOR).should(Condition.exactText(text));
        return this;
    }

    public GamePage checkRequirements(String text) {
        $(GAME_REQUIREMENTS_LOCATOR).should(Condition.exactText(text));
        return this;
    }

    public GamePage checkScreenshots(List<String> expectedUrls) {
        List<String> actualUrls = new ArrayList<>();
        $$(GAME_SCREENSHOTS_LOCATOR).stream().forEach(
                e -> actualUrls.add(
                        e.getAttribute("href").substring(0,
                                e.getAttribute("href").indexOf("?"))));//cut off last character because they are random id

        Assert.assertTrue(actualUrls.containsAll(expectedUrls) && expectedUrls.containsAll(actualUrls),
                "Actual screenshots doesn't match with expected");
        return this;
    }

    public GamePage expandDescription() {
        $(GAME_DESCRIPTION_LOCATOR)
                .ancestor(COLLAPSED_SECTION_CLASS_NAME)
                .find(READ_MORE_LOCATOR)
                .click();
        return this;
    }

    public DlcList getDlcList() {
        if (dlcList == null) {
            dlcList = new DlcList();
        }
        return dlcList;
    }

    public MediaZone getMediaZone() {
        if (mediaZone == null) {
            mediaZone = new MediaZone();
        }
        return mediaZone;
    }

}
