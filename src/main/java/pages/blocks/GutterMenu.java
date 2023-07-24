package pages.blocks;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GutterMenu {
    //todo set locator
    public static final By DEFAULT_GUTTER_MENU_LOCATOR = By.cssSelector("set locator");

    private SelenideElement gutterMenuElement;

    public GutterMenu() {
        gutterMenuElement = $(By.xpath("//*[.='Browse by genre']/.."));
    }

    public GutterMenu(String name) {
        gutterMenuElement = $(By.xpath("//*[.='" + name + "']/.."));
    }

    public GutterMenu checkMenuHasItems(List<String> items) {
        gutterMenuElement.$$(By.cssSelector(".gutter_item")).shouldHave(CollectionCondition.texts(items));
        return this;
    }
}
