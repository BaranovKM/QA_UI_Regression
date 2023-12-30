package utils;

import com.codeborne.selenide.ElementsCollection;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static Path downloadImage(String imageUrl) {
        Path image;
        try {
            image = Files.createTempFile(null, ".jpg");
            RenderedImage renderedImage = ImageIO.read(new URL(imageUrl)); //download image
            ImageIO.write(renderedImage, "jpg", image.toFile()); //save image
            image.toFile().deleteOnExit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    public static void compareImages(Path actualImage, Path expectedImage) {
        try {
            Assert.assertEquals(
                    Files.mismatch(actualImage, expectedImage),
                    -1L, // Files.mismatch return -1 when files are identical
                    "Images are different");
            //todo add expected and actual images to allure report
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //todo make refactoring and add this method instead other urls extractions
    public static List<String> extractUrls(ElementsCollection elements) {
        List<String> urls = new ArrayList<String>();
        elements
                .iterator()
                .forEachRemaining(e -> urls.add(e.lastChild().attr("src")));
        return urls;
    }

    public static List<String> trimUrls(List<String> rawUrls) {
        List<String> trimmedUrls = new ArrayList<>();
        rawUrls.stream().forEach(e -> trimmedUrls.add(e.substring(0, e.indexOf("?"))));//cut off last character because they are random id
        return trimmedUrls;
    }

    public static void compareUrls(List<String> actualUrls, List<String> expectedUrls) {
        //equality check ignoring order
        Assert.assertTrue(actualUrls.containsAll(expectedUrls) && expectedUrls.containsAll(actualUrls),
                "Actual URLs doesn't match with expected");
    }
}
