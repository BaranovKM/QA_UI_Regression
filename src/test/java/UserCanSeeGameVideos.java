import com.google.common.collect.ImmutableList;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCanSeeGameVideos extends BaseTest {
    public static final String GAME_URL = "https://store.steampowered.com/app/1361210/Warhammer_40000_Darktide/";

    public static final List<String> VIDEO_URLS_PATTERNS = ImmutableList.of(
            "https://cdn.cloudflare.steamstatic.com/steam/apps/256918275/movie.184x123.jpg?",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/256981917/movie.184x123.jpg?");

    Map<String, String> videoAttributes = Map.of(
            "data-video-title", "Warhammer 40,000: Darktide - The Traitor Curse Part II Breakdown",
            "data-webm-source", "https://cdn.cloudflare.steamstatic.com/steam/apps/256986232/movie480_vp9.webm",
            "data-webm-hd-source", "https://cdn.cloudflare.steamstatic.com/steam/apps/256986232/movie_max_vp9.webm",
            "data-mp4-hd-source", "https://cdn.cloudflare.steamstatic.com/steam/apps/256986232/movie_max.mp4",
            "data-poster", "https://cdn.cloudflare.steamstatic.com/steam/apps/256986232/movie.293x165.jpg"
    );

    @Test
    public void test() {
        page.navigateOnPageByUrl(GAME_URL);

        gamePage.waitForPageLoaded()
                .getMediaZone()
                .checkVideosThumbnailsUrls(VIDEO_URLS_PATTERNS)
                .checkMiddleSizeVideo(videoAttributes);

    }
}
