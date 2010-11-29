/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.edu.epn.fis.uil4midp.ui;

import ec.edu.epn.fis.uil4midp.controllers.IController;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Canvas;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author Andrés
 */
public class Window extends Canvas {

    Display d;
    private final int DISP_W;
    private final int DISP_H;

    private IController viewController;

    public Window(MIDlet midlet) throws Exception {
        d = Display.getDisplay(midlet);

        DISP_W = getWidth();
        DISP_H = getHeight();
    }

    public void paint(Graphics g) {
        g.setColor(0xFF0000);
        g.fillRect(0, 0, DISP_W, DISP_H);
    }

    public void setViewController(IController viewController) {
        this.viewController = viewController;
    }
    
}
