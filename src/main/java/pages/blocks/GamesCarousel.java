package pages.blocks;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class GamesCarousel {
    private SelenideElement carousel;

    public static final By DEFAULT_CAROUSEL_LOCATOR = By.cssSelector("[class*='carousel_container']");
    public static final By CAROUSEL_LINKS_LOCATOR = By.cssSelector("[class*='store_main_capsule']");

    public static final String GAMES_URLS_REGEX_PATTERN = ".*https://store.steampowered.com/app/.*";
    public GamesCarousel() {
        carousel = $(DEFAULT_CAROUSEL_LOCATOR);
    }

    public GamesCarousel(String name) {
        carousel = $(By.xpath("//*[.='" + name + "']/.."));
    }

    public boolean checkCarouselContainsUrls(List<String> urls) {
        List<String> actualLinks = carousel.findAll(CAROUSEL_LINKS_LOCATOR).texts();
        return actualLinks.containsAll(urls);
    }

    public void checkCarouselHasUrls(boolean hasUrls) {
        if (hasUrls) {
            carousel
                    .findAll(CAROUSEL_LINKS_LOCATOR)
                    .stream().forEach(e -> e
                            .shouldHave(Condition.attributeMatching("href", GAMES_URLS_REGEX_PATTERN)));
        } else {
            carousel
                    .find(CAROUSEL_LINKS_LOCATOR)
                    .shouldNotBe(Condition.exist, Duration.ofSeconds(10));
        }
    }
}
