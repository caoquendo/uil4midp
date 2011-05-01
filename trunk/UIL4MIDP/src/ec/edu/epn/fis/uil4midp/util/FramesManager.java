package ec.edu.epn.fis.uil4midp.util;

import java.util.Hashtable;
import javax.microedition.lcdui.Image;

/**
 * Manages the frames of an animation.
 * @author Carlos Andrés Oquendo
 */
public class FramesManager {

    private static Hashtable framesCache = new Hashtable();
    private Image[] frames;
    private int actualFrameCount = 0;
    private int maximumFramesHeight = -1;

    /**
     * Creates a new instance of FrameManager using the files found on the basePath.
     * The FrameManager class tries to find and load the image files which are
     * located on the Base Path, which may have the same prefix and that are
     * ordered with a number starting on 0 to the framesCount value minus 1.
     * Eg.: file0.png, file1.png, file2.png, ..., fileN.png. The quantity of
     * files loaded depends on the framesCount value. If a file can not be loaded
     * the FrameManager skips it and tries to load the next one.
     * @param basePath Path where the FramesManager should look for image files.
     * @param fileNamePrefix Common prefix that all the required files may have.
     * @param framesCount Maximum number of files to load.
     */
    public FramesManager(String basePath, String fileNamePrefix, String extension, int framesCount) {
        String basePathHash = fileNamePrefix + basePath.hashCode() + "Frames";

        if (framesCache.containsKey(basePathHash)) {
            frames = (Image[]) framesCache.get(basePathHash);
            actualFrameCount = frames.length;
        } else {
            frames = new Image[framesCount];
            for (int i = 0; i < framesCount; i++) {
                try {
                    Image img = Image.createImage(basePath + fileNamePrefix + i + (extension.startsWith(".") ? extension : "." + extension));
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

    /**
     * Gets the actual frame count for the animation. If there were problems loading
     * the frame files, the value returned by this method may be lower than the
     * value specified on the constructor.
     * @return Frame count of the animation.
     */
    public int getFramesCount() {
        return actualFrameCount;
    }

    /**
     * Gets the maximum height found on all the frames.
     * @return Maximum frame height.
     */
    public int getMaximumFrameHeight() {
        if (maximumFramesHeight == -1) {
            for (int i = 0; i < frames.length; i++) {
                Image img = frames[i];
                if (maximumFramesHeight < img.getHeight()) maximumFramesHeight = img.getHeight();
            }
        }

        return maximumFramesHeight;
    }

    /**
     * Gets an specific animation frame.
     * @param index Index of the frame.
     * @return Image object containing the frame image.
     */
    public Image getFrameAt(int index) {
        if (index >= 0 && index < actualFrameCount) {
            return frames[index];
        }
        return null;
    }
}
