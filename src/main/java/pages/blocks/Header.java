package pages.blocks;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;


public class Header {
    public static final By HEADER_LOCATOR = By.cssSelector("#global_header");

    //todo remove or make refactoring
    public boolean isDisplayed(){
        return $(HEADER_LOCATOR).isDisplayed();
    }
    public Header isDisplayed(boolean isDisplayed){
        if(isDisplayed) {
            $(HEADER_LOCATOR).shouldBe(Condition.visible);
        } else {
            $(HEADER_LOCATOR).shouldNotBe(Condition.visible);
        }
        return this;
    }
}
