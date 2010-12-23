/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.edu.epn.fis.uil4midp.ui;

import ec.edu.epn.fis.uil4midp.controllers.IController;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;

/**
 *
 * @author Andrés
 */
public class Window extends Canvas {

    private final int DISP_W;
    private final int DISP_H;

    private IController viewController;

    public Window() throws Exception {
        this.setFullScreenMode(true);
        
        DISP_W = getWidth();
        DISP_H = getHeight();
    }

    public void paint(Graphics g) {
        g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));

        g.setColor(0xFFFFFF);
        g.fillRect(0, 0, DISP_W, DISP_H);

        viewController.setWidth(DISP_W);
        viewController.paint(g);
    }

    public void setViewController(IController viewController) {
        this.viewController = viewController;
    }

    protected void keyPressed(int keyCode) {
        int action = getGameAction(keyCode);
        
        viewController.keyPressed(action);
        repaint();
    }

    

}
