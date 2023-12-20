package pages.blocks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import pages.GamePage;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DlcList {
    public static final By DLC_LIST_LOCATOR = By.cssSelector(".game_area_dlc_list");

    public static final By DLC_NAME_LOCATOR = By.cssSelector(".game_area_dlc_name");
    public static final By SHOW_ALL_DLS_BUTTON_LOCATOR = By.id("dlc_show_all_link");


    private ElementsCollection dlsList;

    public DlcList() {
        dlsList = $$(DLC_NAME_LOCATOR);
    }

    public boolean isDisplayed() {
        return $(DLC_LIST_LOCATOR).isDisplayed();
    }

    public DlcList checkShortDlsList(List<String> listOfDlc) {
        //checking as 'contains all' because dlc's list contain another 10+ items(but they are empty)
        dlsList.texts().containsAll(listOfDlc);
        return this;
    }

    public DlcList checkAllDlsList(List<String> listOfDlc) {
        $(SHOW_ALL_DLS_BUTTON_LOCATOR).click();
        $(SHOW_ALL_DLS_BUTTON_LOCATOR).shouldNot(Condition.visible, Duration.ofSeconds(1));
        dlsList.texts().containsAll(listOfDlc);
        return this;
    }

    public GamePage selectDlc(String dlcName) {
        dlsList.findBy(Condition.exactText(dlcName)).click();
        return new GamePage();
    }
}
