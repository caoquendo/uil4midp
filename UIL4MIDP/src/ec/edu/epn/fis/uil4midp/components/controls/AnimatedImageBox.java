package ec.edu.epn.fis.uil4midp.components.controls;

import ec.edu.epn.fis.uil4midp.util.FramesManager;
import java.util.Timer;
import java.util.TimerTask;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * An AnimatedImageBox is a VisualComponent which is able to show a sequence of
 * images.
 * @author Andrés
 */
public class AnimatedImageBox extends UserControl {

    private FramesManager framesManager;
    private int delay;
    private Image image;
    private Timer timer;
    private TimerTask task;
    private int imageIndex = -1;

    //<editor-fold desc="Constructors">
    /**
     * Creates a new AnimatedImageBox instance with the specified Frame Manager.
     * @param framesManager FramesManager instance which holds all the frames of the animation
     */
    public AnimatedImageBox(FramesManager framesManager, int delay) {
        this.framesManager = framesManager;
        this.delay = delay;

        timer = new Timer();
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods Implementations">
    /**
     * Handles the key events.
     * Label can not handle any key press event.
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     * @return This method always returns False.
     */
    public boolean keyPressed(int action, int keyCode) {
        return false;
    }

    /**
     * Determines if the AnimatedImageBox can be focused.
     * @return This method always return False, due to AnimatedImageBox can not be focused.
     */
    public boolean isFocusable() {
        return false;
    }

    /**
     * Paints the AnimatedImageBox.
     * @param g Graphics object on which paint.
     */
    public void paint(Graphics g) {
        prepareComponent();

        // Draw picture;
        int[] pos = {width / 2, y + padding - yOffset};
        g.drawImage(image, pos[0], pos[1], Graphics.HCENTER | Graphics.TOP);
    }

    /**
     * Prepares the layout of the ImageBox.
     */
    public void prepareComponent() {
        if (!layoutSynced) {
            height = framesManager.getMaximumFrameHeight() + 2 * padding;

            layoutSynced = true;

            image = framesManager.getFrameAt(0);
            imageIndex = 1;

            task = new TimerTask() {

                public void run() {
                    image = framesManager.getFrameAt(imageIndex);
                    imageIndex = imageIndex == framesManager.getFramesCount() - 1 ? 0 : imageIndex + 1;
                    System.out.println("image " + imageIndex);
                    repaint();
                }

            };
            timer.scheduleAtFixedRate(task, delay, delay);
        }
    }

    /**
     * Stops the animation being handled by the AnimatedImageBox.
     */
    public void cancelAnimation() {
        try {
            timer.cancel();
        } catch (Exception ex) {
            // Do nothing
        }
    }

    /**
     * Fires the re-painting of the objects visible on screen.
     */
    private void repaint() {
        getContainer().getView().getController().getWindow().repaint();
    }
    //</editor-fold>
}
