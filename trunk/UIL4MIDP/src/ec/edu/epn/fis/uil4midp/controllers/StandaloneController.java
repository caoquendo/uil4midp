package ec.edu.epn.fis.uil4midp.controllers;

import ec.edu.epn.fis.uil4midp.views.AbstractView;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class StandaloneController extends AbstractController {

    private AbstractView holdedView;

    public void addView(AbstractView view, Image icon) {
        view.setController(this);
        this.holdedView = view;

        holdedView.setWidth(width);
    }

    public final void paint(Graphics g) {
        holdedView.paint(g);
    }

    public final void keyPressed(int action, int keyCode) {
        holdedView.keyPressed(action, keyCode);
    }
}
