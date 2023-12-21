import com.google.common.collect.ImmutableList;
import org.testng.annotations.Test;
import pages.blocks.GamesCarousel;
import pages.blocks.GutterMenu;

import java.util.List;


public class UserCanSeeMainPageTest extends BaseTest {
    private String gutterMenuName = "Browse by genre";
    private String gameCarouselName = "Featured & Recommended";
    private List<String> gutterMenuItems = ImmutableList.of("Free to Play", "Early Access", "Action", "Adventure", "Casual", "Indie", "Massively Multiplayer", "Racing", "RPG", "Simulation", "Sports", "Strategy");

    private GutterMenu menu;

    private GamesCarousel carousel;

    @Test
    public void test() {
        page.navigateOnMainPage()
                .waitForPageLoaded();

        page.getSearch()
                .isDisplayed(true);

        page.getHeader()
                .isDisplayed(true);

        page.getSearch()
                .isDisplayed(true);

        menu = page.getGutterMenu(gutterMenuName);
        menu.checkMenuHasItems(gutterMenuItems);

        carousel = page.getCarousel(gameCarouselName);
        carousel.checkCarouselHasUrls(true);
    }
}
