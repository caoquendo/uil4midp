package ec.edu.epn.fis.uil4midp.controllers;

import ec.edu.epn.fis.uil4midp.views.View;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * A StandaloneController is a kind of Controller intended to hold only one View
 * at a time. It does not automatically handle the navigation between views, so
 * it must be implemented on the Views. This controller must not be added to another
 * controller, as adding a StandaloneController to other Controller is not useful.
 * @author Carlos Andrés Oquendo
 */
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
        if (dialog == null || dialog.isDismissed()) {
            holdedView.keyPressed(action, keyCode);
        } else {
            dialog.keyPressed(action, keyCode);
        }
    }

    /**
     * Paints the contents of the StandaloneController.
     * @param g Graphics object on which paint.
     */
    public void paint(Graphics g) {
        holdedView.setViewPortHeight(viewPortHeight);
        holdedView.paint(g);

        // Mostrar el cuadro de diálogo
        paintDialog(g);
    }

    /**
     * Prepares the layout of the StandaloneController.
     */
    public void prepareController() {
        viewPortHeight = height;
    }
    //</editor-fold>   
}
