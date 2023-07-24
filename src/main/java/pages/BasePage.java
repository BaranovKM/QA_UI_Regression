package pages;

import pages.blocks.Footer;
import pages.blocks.Header;
import pages.blocks.Search;

import static com.codeborne.selenide.Selenide.open;



public class BasePage {
    public static final String DEFAULT_URL = "https://store.steampowered.com/";

    public Header getHeader() {
        return new Header();
    }

    public Footer getFooter() {
        return new Footer();
    }

    public Search getSearch(){
        return new Search();
    }

    public BasePage navigateOnPageByUrl(String url) {
        open(url);
        return this;
    }

}
