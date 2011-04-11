package ec.edu.epn.fis.uil4midp.controllers;

import ec.edu.epn.fis.uil4midp.views.View;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class StandaloneController extends Controller {

    private View holdedView;

    //<editor-fold desc="Abstract Methods Implementations">
    /**
     * Adds a View to the StandaloneController.
     * @param view View to be managed by the StandaloneController.
     * @param icon Icon to be used by the controller. This parameter is optional
     * so 'null' can be passed.
     */
    public void addView(View view, Image icon) {
        view.setController(this);
        view.setWidth(width);

        this.holdedView = view;
        view.initialize();
    }

    /**
     * Handles the key events.
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     */
    public void keyPressed(int action, int keyCode) {
        holdedView.keyPressed(action, keyCode);
    }

    /**
     * Paints the contents of the StandaloneController.
     * @param g Graphics object on which paint.
     */
    public void paint(Graphics g) {
        holdedView.setViewPortHeight(viewPortHeight);
        holdedView.paint(g);

        //TODO: Verificar esta implementacion de cuadros de diálogo.
        if (dialog != null) {
            g.drawImage(getWindow().getOverlayImage(), 0, 0, Graphics.LEFT | Graphics.TOP);
            dialog.paint(g);
        }
    }

    /**
     * Prepares the layout of the StandaloneController.
     */
    public void prepareController() {
        viewPortHeight = height;
    }
    //</editor-fold>   
}
