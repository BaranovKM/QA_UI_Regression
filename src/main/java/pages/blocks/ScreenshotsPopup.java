package pages.blocks;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.TestUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class ScreenshotsPopup {
    public static final By DEFAULT_SCREENSHOTS_POPUP_LOCATOR = By.cssSelector(".screenshot_popup_modal");
    public static final By SCREENSHOT_LOCATOR = By.cssSelector(".screenshot_img_ctn > img");
    public static final By PREVIOUS_BUTTON_LOCATOR = By.cssSelector(".previous");
    public static final By NEXT_BUTTON_LOCATOR = By.cssSelector(".next");

    public static final String ATTRIBUTE_SRC = "src";

    private SelenideElement popup;

    public ScreenshotsPopup() {
        popup = $(DEFAULT_SCREENSHOTS_POPUP_LOCATOR);
    }

    public ScreenshotsPopup clickPrev() {
        $(PREVIOUS_BUTTON_LOCATOR).click();
        return this;
    }

    public ScreenshotsPopup clickNext() {
        $(NEXT_BUTTON_LOCATOR).click();
        return this;
    }

    public ScreenshotsPopup checkFullSizeScreenshotsUrls(List<String> expectedUrls) {
        List<String> actualUrls = new ArrayList<>();
        //todo refactoring
        while ($(PREVIOUS_BUTTON_LOCATOR).isDisplayed()) { //click throw gallery and collect all screenshots urls
            actualUrls.add($(SCREENSHOT_LOCATOR).attr(ATTRIBUTE_SRC));
            $(PREVIOUS_BUTTON_LOCATOR).click();
        }
        actualUrls.add($(SCREENSHOT_LOCATOR).attr(ATTRIBUTE_SRC)); //collect last url
        actualUrls = TestUtils.trimUrls(actualUrls);
        TestUtils.compareUrls(actualUrls, expectedUrls);
        return this;
    }

    public ScreenshotsPopup checkFullSizeScreenshotImage(Path expectedImage) {
        String actualImageUrl = popup.find(SCREENSHOT_LOCATOR).attr(ATTRIBUTE_SRC);
        Path actualImage = TestUtils.downloadImage(actualImageUrl);
        TestUtils.compareImages(actualImage, expectedImage);
        return this;
    }
}
