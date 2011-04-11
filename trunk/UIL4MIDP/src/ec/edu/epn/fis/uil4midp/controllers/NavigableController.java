
package ec.edu.epn.fis.uil4midp.controllers;

import ec.edu.epn.fis.uil4midp.actions.ActionListener;
import ec.edu.epn.fis.uil4midp.views.Form;
import ec.edu.epn.fis.uil4midp.views.View;
import java.io.IOException;
import java.util.Stack;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class NavigableController extends Controller {

    private Stack holdedViews;
    private View activeView;

    private Image backIcon;

    //<editor-fold desc="Constructors">
    /**
     * Initializes internal fields and creates a new instance of NavigableController
     */
    public NavigableController() {
        try {
            backIcon = Image.createImage("/ec/edu/epn/fis/uil4midp/resources/back.png");
        } catch (IOException ex) {
            backIcon = Image.createImage(16, 16);
        }

        holdedViews = new Stack();
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods Implementations>
    /**
     * Adds a view (subclass of Form) to the NavigableController.
     * The View added automatically gets a listener which allows it to go back
     * to the previous View. Due to the view must be a Subclass of Form, the
     * ActionListener is mapped to the Form's left title bar button.
     * @param view View to be managed by the NavigableController. The view passed
     * must be subclass of Form. If not, nothing will be added.
     * @param icon Icon to be used by the Controller. This parameter is optional
     * so 'null' can be passed.
     */
    public void addView(View view, Image icon) {
        try {
            Form f = (Form) view;

            view.setController(this);
            view.setWidth(width);

            // Añadir la vista activa actual a la pila
            if (activeView != null) {
                holdedViews.push(activeView);
            }

            // Añadir el handler para Back.
            if (!holdedViews.isEmpty()) {
                f.setLeftButton(backIcon, new ActionListener() {

                    public void execute() {
                        activeView = (View)holdedViews.pop();
                    }
                });
            }

            // Establecer como vista activa la nueva vista.
            activeView = view;
            view.initialize();
        } catch (Exception e) {
            //Do nothing.
        }
    }

    /**
     * Handles the key events.
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     */
    public void keyPressed(int action, int keyCode) {
        activeView.keyPressed(action, keyCode);
    }

    public void paint(Graphics graphics) {
        activeView.setViewPortHeight(viewPortHeight);
        activeView.paint(graphics);

        //TODO: Implementar cuadros de diálogo
    }

    /**
     * Prepares the layout of the NavigableController.
     */
    public void prepareController() {
        if (autoCalcViewPortHeight) {
            viewPortHeight = height;
        }
    }
    //</editor-fold>

}
