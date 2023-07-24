import pages.DlcPage;
import pages.GamePage;
import pages.MainPage;

public class BaseTest {
    //todo make refactoring for page variables names
    protected MainPage page = new MainPage();
    protected GamePage gamePage = new GamePage();
    protected DlcPage dlcPage = new DlcPage();
    //todo add other elements like search field, gutter menu, carusel and etc
    //todo settings for tests
//    public static MainPage MAIN_PAGE = new MainPage();
}
