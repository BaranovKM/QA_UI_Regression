import com.google.common.collect.ImmutableList;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class UserCanSeeGameScreenshots extends BaseTest {
    public static final String GAME_URL = "https://store.steampowered.com/app/816090/Space_Hulk_Deathwing_Enhanced_Edition/";
    public static final List<String> THUMBNAILS_URLS = ImmutableList.of(
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_536208610a885dea07f783754ab7dbb059de2627.116x65.jpg",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_b905032bce19abcf28c9657a9a1039883178936a.116x65.jpg",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_52ce2d5b26cbb8d71f56c7fbe2e86d1a4161cd61.116x65.jpg",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_d6003190ee103afb4e3112df9999fd5982bf9c68.116x65.jpg",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_058900c61b9fe58228357f5131275a7993c4381b.116x65.jpg",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_90ff5262ac91f11ff3b1f51d74f3c54728fb632e.116x65.jpg",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_19e92f342d7a58de58a1bfcb937ae6020ae3172d.116x65.jpg",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_fb69cd6aafad883d047b8fdbf96441ae9b75f2ea.116x65.jpg");

    public static final List<String> SCREENSHOTS_URLS = ImmutableList.of(
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_536208610a885dea07f783754ab7dbb059de2627",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_b905032bce19abcf28c9657a9a1039883178936a",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_52ce2d5b26cbb8d71f56c7fbe2e86d1a4161cd61",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_d6003190ee103afb4e3112df9999fd5982bf9c68",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_058900c61b9fe58228357f5131275a7993c4381b",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_90ff5262ac91f11ff3b1f51d74f3c54728fb632e",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_19e92f342d7a58de58a1bfcb937ae6020ae3172d",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/816090/ss_fb69cd6aafad883d047b8fdbf96441ae9b75f2ea");


    @Test
    public void test() throws Exception {
        //todo move all variables to @beforeall
        Path expectedThumbnail = Path.of(getClass().getClassLoader().getResource("images/thumbnail_chaplain_116x65.jpg").toURI());
//        Path expectedScreenshot = Path.of(getClass().getClassLoader().getResource("images/screenshot.jpg").toURI());

        page.navigateOnPageByUrl(GAME_URL);

        gamePage.waitForPageLoaded()
                .getMediaZone()
                //todo make refactoring and add .116x65.jpg to every string
                .checkScreenshotsThumbnailsUrls(THUMBNAILS_URLS)
                .checkScreenshotThumbnailImage(0, expectedThumbnail)
                .clickThumbnail(0)
                .clickSliderRight(7)
                .checkMiddleSizeScreenshotsUrls(prepareMiddleSizeScreenshotsUrls())
                .clickScreenshot(7)
//                .clickThumbnail(7)
                //todo add check for middle size screenshot
                //todo move to separated block
                .checkFullSizeScreenshotsUrls(SCREENSHOTS_URLS)
                .checkFullSizeScreenshotImage("", 0);

    }

    private List<String> prepareMiddleSizeScreenshotsUrls() {
        //todo move this to @beforeall
        List<String> middleSizeScreenshotsUrls = new ArrayList<String>();
        SCREENSHOTS_URLS.iterator().forEachRemaining(s -> middleSizeScreenshotsUrls.add(s + ".600x338.jpg"));
        return middleSizeScreenshotsUrls;
    }
}
