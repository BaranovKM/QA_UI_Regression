import com.google.common.collect.ImmutableList;
import org.testng.annotations.Test;

import java.util.List;

public class UserCanSeeDlsPageTest extends BaseTest {
    public static final String GAME_URL = "https://store.steampowered.com/app/489630/Warhammer_40000_Gladius__Relics_of_War/";
    public static final List<String> SHORT_DLC_LIST = ImmutableList.of("Warhammer 40,000: Gladius - Drukhari", "Warhammer 40,000: Gladius - Firepower Pack", "Warhammer 40,000: Gladius - Adepta Sororitas", "Warhammer 40,000: Gladius - Escalation Pack", "Warhammer 40,000: Gladius - Adeptus Mechanicus");
    public static final List<String> FULL_DLC_LIST = ImmutableList.of("Warhammer 40,000: Gladius - Drukhari", "Warhammer 40,000: Gladius - Firepower Pack", "Warhammer 40,000: Gladius - Adepta Sororitas", "Warhammer 40,000: Gladius - Escalation Pack", "Warhammer 40,000: Gladius - Adeptus Mechanicus", "Warhammer 40,000: Gladius - Specialist Pack", "Warhammer 40,000: Gladius - Craftworld Aeldari", "Warhammer 40,000: Gladius - Assault Pack", "Warhammer 40,000: Gladius - T'au", "Warhammer 40,000: Gladius - Fortification Pack", "Warhammer 40,000: Gladius - Chaos Space Marines", "Warhammer 40,000: Gladius - Tyranids", "Warhammer 40,000: Gladius - Reinforcement Pack", "Warhammer 40,000: Gladius - Lord of Skulls", "Warhammer 40,000: Gladius - Relics of War - Soundtrack", "Warhammer 40,000: Gladius - Relics of War - Wallpapers");
    public static final List<String> DLC_SCREENSHOTS = ImmutableList.of("https://cdn.cloudflare.steamstatic.com/steam/apps/1788210/ss_95562de680a5db8b8d0deeb2183ff802d57268c6.1920x1080.jpg", "https://cdn.cloudflare.steamstatic.com/steam/apps/1788210/ss_ce91ca182e3d859ebd4b6c1f6d4f8afeb758452d.1920x1080.jpg", "https://cdn.cloudflare.steamstatic.com/steam/apps/1788210/ss_0d2793f2425d75802bfc5c49502b713c4bd48f55.1920x1080.jpg", "https://cdn.cloudflare.steamstatic.com/steam/apps/1788210/ss_71c0390ad61edc80ce350af5902b74f86a194b12.1920x1080.jpg", "https://cdn.cloudflare.steamstatic.com/steam/apps/1788210/ss_9c62e95bb96a4c4d0cfcea9f9cb0f1fb03e1cc24.1920x1080.jpg");
    public static final String DLC_NAME = "Warhammer 40,000: Gladius - Adeptus Mechanicus";
    public static final String DLC_DESCRIPTION = "ABOUT THIS CONTENT\n\"There is no truth in flesh, only betrayal.\"\n\"There is no strength in flesh, only weakness.\"\n\"There is no constancy in flesh, only decay.\"\n\"There is no certainty in flesh but death.\"\n— Credo Omnissiah\n\nYou of the Adeptus Mechanicus—the Cult of the Machine God—have worshipped the Omnissiah long before the Imperium came to your world of Mars. Following His edict, you have never doubted or ceased to pursue your calling—to gather knowledge and to employ it to gather more. In pursuit of that, you built the devices and machines that enabled the Imperium to conquer the galaxy 10,000 years ago and maintain it today.\n\nSo Gladius Prime was your planet more than it was the Imperium’s—what Hive World was not? Its cities, its satellites, its wastes—all were built with Mars’ technology, all answered only to the Adeptus Mechanicus—that is, to you. It was little effort to hide the Adeptus Mechanicus research into the Tyranids here, in a facility deep beneath the ashen wastes.\n\nNow, however, your facility has been compromised, your Skitarii forces scattered and your subjects have escaped. You must rebuild, recover and take Gladius Prime’s secrets for your own. For you have a mission to fulfil, from the Omnissiah himself…\nA new faction joins the war for Gladius Prime\nThe Adeptus Mechanicus are an empire within the Imperium, with their own agenda, their own god (‘The Omnissiah’) and their own fanatical purpose—the discovery of knowledge, of technology. Though they supply the technical and scientific expertise of the Imperium, they also possess their own elite armies of bionically-augmented soldiers. Xenos or man, stand in their way and they will annihilate you.\nGameplay Mechanics\n\nCanticles of the Omnissiah: Chant Mechanicus hymns to enhance units, increasing in power with every praying warrior.\n\nDoctrina Imperatives: Override Skitarii subroutines to amplify some combat aspects while reducing others.\n\nHive Cities: Place more buildings per tile by utilizing Adeptus Mechanicus’ capability to create layered constructions of rockcrete and adamantium.\n\nMonolithic Buildings: Increased output of buildings of the same type on a tile. Decreased output of buildings of differing types on a tile.\n\nPower Surge: Increase the output of buildings on a tile through forceful intensity.\n\nEnslaved to the Past: Increased research cost. The ability to truly innovate has long been lost, replaced with a reverence for the times when Humanity was the architect of its own destiny.\n\nReprogram Kastelan Robot: Capture enemy Kastelan Robots with Cybernetica Datasmiths’ programming rituals.\n\nBionics: Adeptus Mechanicus units have high damage resistances. Generally, artificial limbs are more durable than their biological counterparts.\n\nAdjacency Integration: Adeptus Mechanicus upgrades increase building output with each adjacent building.\nComplete units roster\nArchaeopter Transvector\nArchaeopter Stratoraptor\nCybernetica Datasmith\nFulgurite Electro-Priest\nIronstrider Ballistarius\nKataphron Destroyer\nKnight Crusader\nOnager Dunecrawler\nPteraxii Sterylizor\nSerberys Sulphurhound\nSicarian Infiltrator\nSkitarii Marshal\nSkitarii Vanguard\nSkorpius Disintegrator\nSkorpius Dunerider\nTech-Priest Dominus\nTech-Priest Manipulus";


    @Test
    public void test() {

        page.navigateOnPageByUrl(GAME_URL);

        gamePage.waitForPageLoaded()
                .getDlcList()
                .checkShortDlsList(SHORT_DLC_LIST)
                .checkAllDlsList(FULL_DLC_LIST)
                .selectDlc(DLC_NAME);

        dlcPage.waitForPageLoaded()
                .getHeader()
                .isDisplayed(true);

        dlcPage.getFooter()
                .isDisplayed(true);

        dlcPage.getSearch()
                .isDisplayed(true);

        dlcPage
                .expandDescription()
                .checkDescription(DLC_DESCRIPTION)
                .checkScreenshots(DLC_SCREENSHOTS);
    }
}
