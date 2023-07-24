package pages.blocks;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Footer {
    public static final By FOOTER_LOCATOR = By.cssSelector(".footer_content");

    //todo remove this or refactoring
    public boolean isDisplayed(){
        return $(FOOTER_LOCATOR).isDisplayed();
    }

    public Footer isDisplayed(boolean isDisplayed){
        if(isDisplayed){
            $(FOOTER_LOCATOR).shouldBe(Condition.visible);
        } else {
            $(FOOTER_LOCATOR).shouldNotBe(Condition.visible);
        }
        return this;
    }
}
