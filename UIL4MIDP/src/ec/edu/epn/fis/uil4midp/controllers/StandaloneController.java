
package ec.edu.epn.fis.uil4midp.controllers;

import ec.edu.epn.fis.uil4midp.views.AbstractView;
import javax.microedition.lcdui.Graphics;

public class StandaloneController extends AbstractController {

    private AbstractView holdedView;
    private int width;

    public void addView(AbstractView view) {
        view.setController(this);
        this.holdedView = view;
    }

    public final void paint(Graphics g) {
        holdedView.setWidth(width);
        holdedView.paint(g);
    }

    public final void setWidth(int width) {
        this.width = width;
    }

    public final void keyPressed(int action, int keyCode) {
        holdedView.keyPressed(action, keyCode);
    }

}
