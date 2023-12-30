package pages.blocks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.TestUtils;
import java.nio.file.Path;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class MediaZone {
    public static final By DEFAULT_MEDIA_ZONE_LOCATOR = By.cssSelector(".highlight_overflow");
    public static final By THUMBNAIL_IMAGE_LOCATOR = By.cssSelector(".highlight_strip_screenshot > img");
    public static final By MIDDLE_SIZE_SCREENSHOT_LOCATOR = By.cssSelector(".highlight_screenshot_link");
    public static final By MIDDLE_SIZE_SCREENSHOT_IMAGE_LOCATOR = By.cssSelector(".highlight_screenshot_link > img");
    public static final By SLIDER_LEFT_LOCATOR = By.cssSelector(".slider_left");
    public static final By SLIDER_RIGHT_LOCATOR = By.cssSelector(".slider_right");

    public static final String IMAGE_LINK_ATTRIBUTE = "src";

    private SelenideElement mediaZone;

    public MediaZone() {
        mediaZone = $(DEFAULT_MEDIA_ZONE_LOCATOR);
    }

    public MediaZone isDisplayed(boolean isDisplayed) {
        if (isDisplayed) {
            mediaZone.shouldBe(Condition.visible);
        } else {
            mediaZone.shouldNotBe(Condition.visible);
        }
        return this;
    }

    public MediaZone clickThumbnail(int thumbnailNumber) {
        mediaZone.findAll(THUMBNAIL_IMAGE_LOCATOR).get(thumbnailNumber).click();
        return this;
    }

    public ScreenshotsPopup clickScreenshot(int screenshotNumber) {
        mediaZone.findAll(MIDDLE_SIZE_SCREENSHOT_LOCATOR).get(screenshotNumber).click();
        return new ScreenshotsPopup();
    }

    //todo create separated block for slider
    public MediaZone clickSliderLeft() {
        mediaZone.find(SLIDER_LEFT_LOCATOR).click();
        return this;
    }

    public MediaZone clickSliderRight(int clicks) {
        for (int i = 0; i < clicks; i++) {
            mediaZone.find(SLIDER_RIGHT_LOCATOR).click();
        }
        return this;
    }

    public MediaZone checkScreenshotsThumbnailsUrls(List<String> expectedUrls) {
        List<String> actualUrls = TestUtils.extractUrls(mediaZone.findAll(THUMBNAIL_IMAGE_LOCATOR));
        actualUrls = TestUtils.trimUrls(actualUrls);
        TestUtils.compareUrls(actualUrls, expectedUrls);
        return this;
    }

    public MediaZone checkScreenshotThumbnailImage(Path expectedThumbnail) {
        String actualThumbnailUrl = mediaZone.find(THUMBNAIL_IMAGE_LOCATOR).attr(IMAGE_LINK_ATTRIBUTE);
        Path actualThumbnail = TestUtils.downloadImage(actualThumbnailUrl);
        TestUtils.compareImages(actualThumbnail, expectedThumbnail);
        return this;
    }

    public MediaZone checkMiddleSizeScreenshotsUrls(List<String> expectedUrls) {
        List<String> actualUrls = TestUtils.extractUrls(mediaZone.findAll(MIDDLE_SIZE_SCREENSHOT_IMAGE_LOCATOR));
        actualUrls = TestUtils.trimUrls(actualUrls);
        TestUtils.compareUrls(actualUrls, expectedUrls);
        return this;
    }

    public MediaZone checkMiddleSizeScreenshotImage(Path expectedImage) {
        String actualImageUrl = mediaZone.find(MIDDLE_SIZE_SCREENSHOT_IMAGE_LOCATOR).attr(IMAGE_LINK_ATTRIBUTE);
        Path actualImage = TestUtils.downloadImage(actualImageUrl);
        TestUtils.compareImages(actualImage, expectedImage);
        return this;
    }

    public MediaZone checkVideosThumbnailsUrls(List<String> videosThumbnailUrls) {
        //todo add check for urls
        return this;
    }

    public MediaZone checkFullSizeVideo(String videoPath) {
        //todo add download video and compare with original
        return this;
    }
}
