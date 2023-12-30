import com.google.common.collect.ImmutableList;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class UserCanSeeGameScreenshots extends BaseTest {
    public static final String GAME_URL = "https://store.steampowered.com/app/816090/Space_Hulk_Deathwing_Enhanced_Edition/";

    public static final List<String> SCREENSHOTS_URLS_PATTERNS = ImmutableList.of(
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_536208610a885dea07f783754ab7dbb059de2627",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_b905032bce19abcf28c9657a9a1039883178936a",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_52ce2d5b26cbb8d71f56c7fbe2e86d1a4161cd61",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_d6003190ee103afb4e3112df9999fd5982bf9c68",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_058900c61b9fe58228357f5131275a7993c4381b",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_90ff5262ac91f11ff3b1f51d74f3c54728fb632e",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_19e92f342d7a58de58a1bfcb937ae6020ae3172d",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_fb69cd6aafad883d047b8fdbf96441ae9b75f2ea");

    int firstThumbnail = 0;
    int lastScreenshot = 7;
    int clicksBySlider = 7;

    Path expectedThumbnail;
    Path expectedMiddleSizeScreenshot;
    Path expectedFullSizeScreenshot;

    List<String> expectedThumbnailsUrls = new ArrayList<>();
    List<String> expectedMiddleSizeScreenshotsUrls = new ArrayList<>();
    List<String> expectedFullSizeScreenshotsUrls = new ArrayList<>();


    //todo if there are troubles on Jenkins run change this annotation to @BeforeMethod
    @BeforeTest
    public void setup() throws Exception{
        //prepare files for expected screenshots
        expectedThumbnail = Path.of(getClass().getClassLoader().getResource("images/thumbnail_chaplain_116x65.jpg").toURI());
        expectedMiddleSizeScreenshot = Path.of(getClass().getClassLoader().getResource("images/screenshot_chaplain_600x338.jpg").toURI());
        expectedFullSizeScreenshot = Path.of(getClass().getClassLoader().getResource("images/full_size_screenshot_chaplain_1920x1080.jpg").toURI());

        //prepare urls for expected screenshots
        SCREENSHOTS_URLS_PATTERNS.iterator().forEachRemaining(s -> expectedThumbnailsUrls.add(s + ".116x65.jpg"));
        SCREENSHOTS_URLS_PATTERNS.iterator().forEachRemaining(s -> expectedMiddleSizeScreenshotsUrls.add(s + ".600x338.jpg"));
        SCREENSHOTS_URLS_PATTERNS.iterator().forEachRemaining(s -> expectedFullSizeScreenshotsUrls.add(s + ".1920x1080.jpg"));
    }
    @Test
    public void test() throws Exception {
        page.navigateOnPageByUrl(GAME_URL);

        gamePage.waitForPageLoaded()
                .getMediaZone()
                .checkScreenshotsThumbnailsUrls(expectedThumbnailsUrls)
                .clickThumbnail(firstThumbnail)
                .checkScreenshotThumbnailImage(expectedThumbnail)
                .clickSliderRight(clicksBySlider)
                .checkMiddleSizeScreenshotsUrls(expectedMiddleSizeScreenshotsUrls)
                .checkMiddleSizeScreenshotImage(expectedMiddleSizeScreenshot)
                .clickScreenshot(lastScreenshot)
                .checkFullSizeScreenshotsUrls(expectedFullSizeScreenshotsUrls)
                .checkFullSizeScreenshotImage(expectedFullSizeScreenshot);
    }
}
