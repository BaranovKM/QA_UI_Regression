import com.google.common.collect.ImmutableList;
import org.testng.annotations.Test;

import java.util.List;

public class UnauthorizedUserCanSeeDlsPage extends BaseTest {
    public static final String GAME_URL = "https://store.steampowered.com/app/281990/Stellaris/";
    public static final List<String> SHORT_DLC_LIST = ImmutableList.of("Stellaris: Galactic Paragons", "Stellaris: First Contact Story Pack", "Stellaris: Toxoids Species Pack", "Stellaris: Overlord", "Stellaris: Aquatics Species Pack");
    public static final List<String> FULL_DLC_LIST = ImmutableList.of("Stellaris: Galactic Paragons", "Stellaris: First Contact Story Pack", "Stellaris: Toxoids Species Pack", "Stellaris: Overlord", "Stellaris: Aquatics Species Pack", "Stellaris: Nemesis", "Stellaris: Necroids Species Pack", "Stellaris: Federations", "Stellaris: Lithoids Species Pack", "Stellaris: Ancient Relics Story Pack", "Stellaris: MegaCorp", "Stellaris: Apocalypse", "Stellaris: Utopia", "Stellaris: Distant Stars Story Pack", "Stellaris: Synthetic Dawn Story Pack", "Stellaris: Leviathans Story Pack", "Stellaris: Humanoids Species Pack", "Stellaris: Plantoids Species Pack", "Stellaris: Complete Soundtrack", "Stellaris: Infinite Frontiers (eBook)", "Stellaris: Anniversary Portraits", "Stellaris: Galaxy Edition Upgrade Pack");
    public static final List<String> DLC_SCREENSHOTS = ImmutableList.of("https://cdn.cloudflare.steamstatic.com/steam/apps/716670/ss_89b4a01c02895eab8cd4192dc762958465baac66.1920x1080.jpg", "https://cdn.cloudflare.steamstatic.com/steam/apps/716670/ss_5351a1ce0691f58613e7d8d982659da3817f5dd2.1920x1080.jpg", "https://cdn.cloudflare.steamstatic.com/steam/apps/716670/ss_318693538512fecb466ef0ed77363ce75e03bcd0.1920x1080.jpg", "https://cdn.cloudflare.steamstatic.com/steam/apps/716670/ss_508cff61cd94ee245182187b5537acd06cf4d10c.1920x1080.jpg", "https://cdn.cloudflare.steamstatic.com/steam/apps/716670/ss_274fdef9b2b1031c67cb912ad0175ee57634fadb.1920x1080.jpg", "https://cdn.cloudflare.steamstatic.com/steam/apps/716670/ss_46b12178628fde52d726b782762c8723620e412b.1920x1080.jpg", "https://cdn.cloudflare.steamstatic.com/steam/apps/716670/ss_da19bfb14c063a1327369bbcb49349bdc9f20cd6.1920x1080.jpg", "https://cdn.cloudflare.steamstatic.com/steam/apps/716670/ss_3c14862c39c9cd7c8aaa79649d4ecd726bd83501.1920x1080.jpg", "https://cdn.cloudflare.steamstatic.com/steam/apps/716670/ss_e92d81767343af25acea23881a89b9ce69c3e677.1920x1080.jpg", "https://cdn.cloudflare.steamstatic.com/steam/apps/716670/ss_256b75adf6b2135764854abab4593a56877d6790.1920x1080.jpg");
    public static final String DLC_NAME = "Stellaris: Apocalypse";
    public static final String DLC_DESCRIPTION = "ABOUT THIS CONTENT\nStellaris: Apocalypse is a full expansion which redefines stellar warfare for all players with a host of new offensive and defensive options. Destroy entire worlds with terrifying new planet-killer weapons, fight against (or alongside) ruthless space pirates, and maybe discover a few non-violent game features as well.\n\nThe Apocalypse expansion includes:\nTHAT'S NO MOON, NEITHER IS THAT ONE, THAT ONE MIGHT BE A MOON, WAIT, NO\nKeep the local systems in line with fear of the new “Colossus” planet-killer weapon – a technological terror that eliminates entire worlds from the universe.\nALL YOUR BASE ARE BELONG TO YOU\nNew enormous “Titan” capital ships can lead your fleets to conquest, offering tremendous bonuses to the vessels under their command. Meanwhile, fortify key systems with massive orbital installations and secure your homeworld as an impenetrable bastion among the stars.\nPIRATES OF THE CONSTELLATION\nWatch out for Marauders – space nomads who raid settled empires and carve out their lives on the fringe of civilization. Hire them as mercenaries in your own conflicts, but take care that they don’t unify and trigger a new mid-game crisis!\nSOME NON-VIOLENT FEATURES\nNew Ascension Perks and Civics are added in the expansion, along with new Unity Ambitions that provide new ways to spend Unity and customize your development.\nSOUNDS OF DESTRUCTION\nTo accompany your planet-shattering expedition, three new musical pieces have been composed by Andreas Waldetoft for your listening pleasure.";


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
                .checkDescription(DLC_DESCRIPTION)
                .checkScreenshots(DLC_SCREENSHOTS);

    }
}
