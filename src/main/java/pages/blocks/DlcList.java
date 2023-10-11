package pages.blocks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.google.common.collect.ImmutableList;
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


    private SelenideElement dlsList;

    public DlcList() {
        dlsList = $(DLC_LIST_LOCATOR);
    }

    public boolean isDisplayed() {
        return $(DLC_LIST_LOCATOR).isDisplayed();
    }

    public DlcList checkShortDlsList(List<String> listOfDlc) {
        $$(DLC_NAME_LOCATOR).texts().containsAll(listOfDlc);
        return this;
    }

    public DlcList checkAllDlsList(List<String> listOfDlc) {
        $(SHOW_ALL_DLS_BUTTON_LOCATOR).click();
        $(SHOW_ALL_DLS_BUTTON_LOCATOR).shouldNot(Condition.visible, Duration.ofSeconds(1));
        $$(DLC_NAME_LOCATOR).texts().containsAll(listOfDlc);
        return this;
    }

    public GamePage selectDlc(String dlcName) {
        dlsList.findElement(By.xpath("//*[.='" + dlcName + "']")).click();
        return new GamePage();
    }
}
