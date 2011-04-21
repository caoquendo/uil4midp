package ec.edu.epn.fis.uil4midp.util;

import java.util.Hashtable;
import javax.microedition.lcdui.Image;

public class FramesManager {

    private static Hashtable framesCache = new Hashtable();
    private Image[] frames;
    private int actualFrameCount = 0;
    private int maximumFramesHeight = -1;

    public FramesManager(String basePath, String fileNamePrefix, int framesCount) {
        String basePathHash = fileNamePrefix + basePath.hashCode() + "Frames";

        if (framesCache.containsKey(basePathHash)) {
            frames = (Image[]) framesCache.get(basePathHash);
            actualFrameCount = frames.length;
        } else {
            frames = new Image[framesCount];
            for (int i = 0; i < framesCount; i++) {
                try {
                    Image img = Image.createImage(basePath + fileNamePrefix + i + ".png");
                    frames[i] = img;

                    if (maximumFramesHeight < img.getHeight()) maximumFramesHeight = img.getHeight();

                    actualFrameCount++;
                } catch (Exception ex) {
                    // Frame was not loaded
                }
            }
            framesCache.put(basePathHash, frames);
        }
    }

    public int getFramesCount() {
        return actualFrameCount;
    }

    public int getMaximumFrameHeight() {
        if (maximumFramesHeight == -1) {
            for (int i = 0; i < frames.length; i++) {
                Image img = frames[i];
                if (maximumFramesHeight < img.getHeight()) maximumFramesHeight = img.getHeight();
            }
        }

        return maximumFramesHeight;
    }

    public Image getFrameAt(int index) {
        if (index >= 0 && index < actualFrameCount) {
            return frames[index];
        }
        return null;
    }
}
