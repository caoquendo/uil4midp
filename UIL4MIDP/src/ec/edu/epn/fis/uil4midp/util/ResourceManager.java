package ec.edu.epn.fis.uil4midp.util;

import java.io.IOException;
import java.util.Hashtable;
import javax.microedition.lcdui.Image;

/**
 * Manages the loading of image resources
 * @author Carlos Andrés Oquendo
 */
public class ResourceManager {

    private static Hashtable resourcesCache = new Hashtable();

    /**
     * Loads an Image resource. If the image was previously loaded, it may be
     * available on the cache.
     * @param imageFileName Image file to load.
     * @return Loaded image.
     */
    public static Image loadImage(String imageFileName) {
        if (resourcesCache.containsKey(imageFileName)) {
            return (Image) resourcesCache.get(imageFileName);
        }

        Image img;
        
        try {
            img = Image.createImage(imageFileName);
        } catch (IOException ex) {
            img = Image.createImage(16, 16);
            ex.printStackTrace();
        }

        resourcesCache.put(imageFileName, img);

        return img;
    }
}
