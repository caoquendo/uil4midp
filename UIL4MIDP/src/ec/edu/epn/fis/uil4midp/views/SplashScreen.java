package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import javax.microedition.lcdui.Graphics;

public final class SplashScreen implements IView {

    private boolean shown = false;
    private int[] backgroundColors = new int[]{0xFFFFFF};
    private int width;
    private int height;
    private String caption;
    private int timeout = 500;

    /**
     * @param visualComponent
     * @deprecated This method does nothing valuable on this class.
     */
    public void addVisualComponent(VisualComponent visualComponent) {
    }

    public void paint(Graphics g) {



        shown = true;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void keyPressed(int action, int keyCode) {
    }

    /**
     * Sets the background colors that will be used to paint the splash screen
     * @param backgroundColors Array containing a list of colors represented on
     * integer format (Eg. 0x00FF00). If the array contains only one value, the
     * SplashScreen background will be filled with a solid color. If the array
     * contains two or more values, the background will be filled with a
     * vertical gradient composed of the two first values.
     */
    public void setBackgroundColors(int[] backgroundColors) {
        if (backgroundColors != null) {
            this.backgroundColors = backgroundColors;
        }
    }

    public boolean wasShown() {
        return shown;
    }

    public int getTimeout() {
        return timeout;
    }

    /**
     * Sets the duration for the splash screen.
     * @param timeout Value in milliseconds that the splash screen should appear
     * on the screen. This must be greater or equal than 500 milliseconds.
     */
    public void setTimeout(int timeout) {
        if (timeout > 500) {
            this.timeout = timeout;
        }
    }
}
