package view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Class for accessing the API.
 * The API, from https://deckofcardsapi.com, is used for accessing card images.
 */
public class APIAccess {
    private static final String apiString = "https://deckofcardsapi.com/static/img/";

    /**
     * Loads an image from the api.
     * @param suit The suit of the card
     * @param num The number of the card
     * @return The image as a BufferedImage
     */
    public static BufferedImage getCard(char suit, char num){
        try {
            URL url = new URL(apiString + num + suit + ".png");
            return ImageIO.read(url);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
