import com.google.common.collect.ImmutableList;
import org.testng.annotations.Test;
import pages.blocks.Search;

import java.util.List;

public class UnauthorizedUserCanSeeGamePage extends BaseTest{
public static final String GAME_NAME = "warhammer space marine 2";
public static final String GAME_DESCRIPTION = "ABOUT THIS GAME\n\n\nThe galaxy is in peril. Entire worlds are falling. The Imperium needs you.\n\nEmbody the superhuman skill and brutality of a Space Marine, the greatest of the Emperor’s warriors. Unleash deadly abilities and an arsenal of devastating weaponry to obliterate the relentless Tyranid hordes.\n\nHold at bay the horrors of the galaxy in epic battles on far-flung planets. Uncover dark secrets and drive back the everlasting night to prove your ultimate loyalty to humanity.\n\nHeed the call of battle.\nFor there is only war.";
public static final String GAME_REQUIREMENTS = "MINIMUM:\nAdditional Notes: To be announced\nRECOMMENDED:\nAdditional Notes: To be announced";
public static final List<String> GAME_SCREENSHOTS_URLS = ImmutableList.of(
        "https://cdn.cloudflare.steamstatic.com/steam/apps/2183900/ss_712fe9d84a853d1d2fc1faee8cd8efb36e2278d0.1920x1080.jpg",
        "https://cdn.cloudflare.steamstatic.com/steam/apps/2183900/ss_4751069d565b92fadeb6bfd507cef64cf6c70cd1.1920x1080.jpg",
        "https://cdn.cloudflare.steamstatic.com/steam/apps/2183900/ss_d0bc9105e638b0f8a5364c4a6c70c6ccaf9268bb.1920x1080.jpg",
        "https://cdn.cloudflare.steamstatic.com/steam/apps/2183900/ss_dec108d540d8ae215ef9f558e4642ef4bee10e86.1920x1080.jpg",
        "https://cdn.cloudflare.steamstatic.com/steam/apps/2183900/ss_f3cb28be981ddf20fccd4e8d827d0e340cf12b75.1920x1080.jpg",
        "https://cdn.cloudflare.steamstatic.com/steam/apps/2183900/ss_90d54dd35ea29f80ccbe7618682d36736c2697df.1920x1080.jpg");
    private Search search;
    @Test
    public void test() {
        page.navigateOnMainPage()
                .waitForPageLoaded();

        search = page.getSearch();
        search
                .type(GAME_NAME)
                .select(1);

        gamePage.waitForPageLoaded()
                .getSearch()
                .isDisplayed(true);

        gamePage.getHeader()
                .isDisplayed(true);

        gamePage.getFooter()
                .isDisplayed(true);

        gamePage.checkDescription(GAME_DESCRIPTION)
                .checkRequirements(GAME_REQUIREMENTS)
                .checkScreenshots(GAME_SCREENSHOTS_URLS);
    }
}
