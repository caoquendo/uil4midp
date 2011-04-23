package ec.edu.epn.fis.uil4midp.ui;

import ec.edu.epn.fis.uil4midp.controllers.Controller;
import ec.edu.epn.fis.uil4midp.util.FontManager;
import ec.edu.epn.fis.uil4midp.util.ThemeManager;
import ec.edu.epn.fis.uil4midp.views.SplashScreen;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;

/**
 * Defines the structure of a Window
 * @author Carlos Andrés Oquendo
 */
public abstract class Window extends Canvas {

    private MIDlet midlet;
    private Display display;
    private Controller controller;
    private SplashScreen splashScreen;
    private Image overlay;
    private int[] overlayData;
    private ThemeManager tm;

    /**
     * Creates a new Window instance.
     * @param midlet MIDlet to which the Window belongs
     */
    protected Window(MIDlet midlet) {
        this.setFullScreenMode(true);

        this.midlet = midlet;
        this.display = Display.getDisplay(midlet);

        // Inicializar los temas
        tm = ThemeManager.getInstance();

        // Create overlay image
        overlayData = new int[getWidth() * getHeight()];
        int baseColor = 0x80000000;
        for (int i = 0; i < overlayData.length; i++) {
            overlayData[i] = baseColor;
        }
        overlay = Image.createRGBImage(overlayData, getWidth(), getHeight(), true);
    }

    //<editor-fold desc="Mandatory Methods">
    /**
     * Handles the key events.
     * This method generates the Action code from the Key code.
     * @param keyCode Pressed key code. This code may be device-specific.
     */
    protected final void keyPressed(int keyCode) {
        int action = getGameAction(keyCode);

        controller.keyPressed(action, keyCode);

        repaint();
    }

    /**
     * Paints the contents of the Window.
     * @param g Graphics object on which paint.
     */
    public final void paint(Graphics g) {
        g.setFont(FontManager.getNormalFont());

        g.setColor(tm.getMainBackgroundColor());
        g.fillRect(0, 0, getWidth(), getHeight());

        controller.paint(g);
    }
    //</editor-fold>

    //<editor-fold desc="Basic Window Handling Methods">
    /**
     * Shows the Window on the screen.
     */
    public final void show() {
        display.setCurrent(this);
    }
    //</editor-fold>

    //<editor-fold desc="UI Management Methods">
    /**
     * Adds a Controller to the Window
     * @param controller Controller to be managed by the Window.
     */
    public final void setViewController(Controller controller) {
        controller.setHeight(getHeight());
        controller.setWidth(getWidth());
        controller.setWindow(this);
        controller.prepareController();

        this.controller = controller;
    }

    /**
     * Sets the splash screen to be shown during the application startup.
     * @param splashScreen SplashScreen of the MIDlet.
     */
    public void setSplashScreen(SplashScreen splashScreen) {
        this.splashScreen = splashScreen;
    }

    /**
     * Gets the Display instance assigned by the runtime to the MIDlet.
     * @return Display object belonging to the MIDlet.
     */
    public Display getDisplay() {
        return display;
    }
    //</editor-fold>

    //<editor-fold desc="Utility Methods">
    /**
     * Gets a semitransparent overlay image, needed to show a Dialog.
     * @return Semitransparent overlay image.
     */
    public final Image getOverlayImage() {
        return overlay;
    }
    //</editor-fold>
}
