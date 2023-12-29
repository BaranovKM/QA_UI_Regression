package pages.blocks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MediaZone {
    public static final By DEFAULT_MEDIA_LOCATOR = By.cssSelector(".highlight_overflow");
    public static final By THUMBNAIL_IMG_LOCATOR = By.cssSelector(".highlight_strip_screenshot > img");
    public static final By MIDDLE_SIZE_SCREENSHOT_LOCATOR = By.cssSelector(".highlight_screenshot_link");
    public static final By SLIDER_LEFT_LOCATOR = By.cssSelector(".slider_left");
    public static final By SLIDER_RIGHT_LOCATOR = By.cssSelector(".slider_right");

    private SelenideElement mediaZone;

    public MediaZone() {
        mediaZone = $(DEFAULT_MEDIA_LOCATOR);
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
        mediaZone.$$(THUMBNAIL_IMG_LOCATOR).get(thumbnailNumber).click();
        return this; //todo may be should create separated block for full size media
    }

    public MediaZone clickScreenshot(int screenshotNumber) {
        mediaZone.$$(MIDDLE_SIZE_SCREENSHOT_LOCATOR).get(screenshotNumber).click();
        return this;
    }

    //todo create separated block for slider
    public MediaZone clickSliderLeft() {
        mediaZone.$(SLIDER_LEFT_LOCATOR).click();
        return this;
    }

    public MediaZone clickSliderRight(int clicks) {
        for (int i = 0; i < clicks; i++) {
            mediaZone.$(SLIDER_RIGHT_LOCATOR).click();
        }
        return this;
    }

    public MediaZone checkScreenshotsThumbnailsUrls(List<String> screenshotsThumbnailUrls) {
        //todo refactoring
        List<String> actualUrls = new ArrayList<>();
        mediaZone.$$(THUMBNAIL_IMG_LOCATOR).stream().forEach(
                e -> actualUrls.add(
                        e.attr("src").substring(0,
                                e.attr("src").indexOf("?"))));//cut off last character because they are random id

        Assert.assertTrue(actualUrls.containsAll(screenshotsThumbnailUrls) && screenshotsThumbnailUrls.containsAll(actualUrls),
                //todo move errors to separeted class
                "Actual thumbnails doesn't match with expected");
        return this;
    }

    public MediaZone checkScreenshotThumbnailImage(int numberOfThumbnailForCompare, Path expectedThumbnail) {
        String actualThumbnailUrl = mediaZone.$$(THUMBNAIL_IMG_LOCATOR).get(numberOfThumbnailForCompare).attr("src");
        Path actualThumbnail = downloadImage(actualThumbnailUrl);
        compareImages(actualThumbnail, expectedThumbnail);
        //todo add actual thumbnail to allure report
        return this;
    }

    public MediaZone checkMiddleSizeScreenshotsUrls(List<String> expectedUrls) {
        List<String> rowUrls = new ArrayList<String>();
        mediaZone.$$(MIDDLE_SIZE_SCREENSHOT_LOCATOR)
                .iterator()
                .forEachRemaining(e -> rowUrls.add(e.lastChild().attr("src")));
        List<String> actualUrls = trimUrls(rowUrls);
        compareScreenshotsUrls(actualUrls, expectedUrls);
        return this;
    }

    public MediaZone checkMiddleSizeScreenshotImage(String screenshotPath, int screenshotNumber) {
        //todo add download image and compare with original
        return this;
    }

    public MediaZone checkFullSizeScreenshotsUrls(List<String> screenshotsUrls) {
        mediaZone.$(By.cssSelector(".highlight_screenshot_link"));
        //todo add check for urls

        return this;
    }

    public MediaZone checkFullSizeScreenshotImage(String screenshotPath, int screenshotNumber) {
        //todo add download image and compare with original
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

    private void compareImages(Path actualImage, Path expectedImage) {
        try {
            Assert.assertEquals(
                    Files.mismatch(actualImage, expectedImage),
                    -1L, // Files.mismatch return -1 when files are identical
                    "Images are different");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path downloadImage(String imageUrl) {
        Path image;
        try {
            image = Files.createTempFile(null, ".jpg");
            RenderedImage renderedImage = ImageIO.read(new URL(imageUrl)); //download image
            ImageIO.write(renderedImage, "jpg", image.toFile()); //save image
            image.toFile().deleteOnExit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    private MediaZone compareScreenshotsUrls(List<String> actualUrls, List<String> expectedUrls) {
        Assert.assertTrue(actualUrls.containsAll(expectedUrls) && expectedUrls.containsAll(actualUrls),
                "Actual URLs doesn't match with expected");
        return this;
    }

    private List<String> trimUrls(List<String> urls) {
        List<String> trimmedUrls = new ArrayList<>();
        urls.stream().forEach(e -> trimmedUrls.add(e.substring(0, e.indexOf("?"))));//cut off last character because they are random id
        return trimmedUrls;
    }

}
