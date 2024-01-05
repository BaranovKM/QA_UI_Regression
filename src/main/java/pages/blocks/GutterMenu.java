package pages.blocks;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GutterMenu {
    //todo set locator
    public static final By DEFAULT_GUTTER_MENU_LOCATOR = By.xpath("//*[.='Browse by genre']/..");
    public static final By GUTTER_ITEM_LOCATOR = By.cssSelector(".gutter_item");

    private SelenideElement gutterMenuElement;

    public GutterMenu() {
        gutterMenuElement = $(DEFAULT_GUTTER_MENU_LOCATOR);
    }

    public GutterMenu(String name) {
        gutterMenuElement = $(By.xpath("//*[.='" + name + "']/.."));
    }

    public GutterMenu checkMenuHasItems(List<String> items) {
        gutterMenuElement.findAll(GUTTER_ITEM_LOCATOR).shouldHave(CollectionCondition.texts(items));
        return this;
    }
}
