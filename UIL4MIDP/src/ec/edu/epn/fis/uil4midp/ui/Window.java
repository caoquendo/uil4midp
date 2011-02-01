package ec.edu.epn.fis.uil4midp.ui;

import ec.edu.epn.fis.uil4midp.controllers.AbstractController;
import ec.edu.epn.fis.uil4midp.util.FontManager;
import ec.edu.epn.fis.uil4midp.views.SplashScreen;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class Window extends Canvas {

    private MIDlet midlet;
    private Display display;

    private final int DISPLAY_WIDTH;
    private final int DISPLAY_HEIGHT;
    private AbstractController viewController;
    private SplashScreen splashScreen;
    
    protected Window(MIDlet midlet){
        this.setFullScreenMode(true);

        this.midlet = midlet;
        this.display = Display.getDisplay(midlet);

        DISPLAY_WIDTH = getWidth();
        DISPLAY_HEIGHT = getHeight();
    }

    public final void paint(Graphics g) {
        g.setFont(FontManager.getNormalFont());

        g.setColor(0xFFFFFF);
        g.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);

        viewController.setWidth(DISPLAY_WIDTH);
        viewController.paint(g);
    }

    public void setViewController(AbstractController viewController) {
        this.viewController = viewController;
        this.viewController.setWindow(this);
    }

    protected final void keyPressed(int keyCode) {
        int action = getGameAction(keyCode);

        viewController.keyPressed(action, keyCode);

        repaint();
    }

    public void setSplashScreen(SplashScreen splashScreen) {
        this.splashScreen = splashScreen;
    }

    public void show() {
        display.setCurrent(this);
    }

    public Display getDisplay() {
        return display;
    }
}
