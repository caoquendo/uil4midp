package ec.edu.epn.fis.uil4midp.controllers;

import ec.edu.epn.fis.uil4midp.actions.ActionListener;
import ec.edu.epn.fis.uil4midp.ui.Window;
import ec.edu.epn.fis.uil4midp.util.ResourceManager;
import ec.edu.epn.fis.uil4midp.views.Form;
import ec.edu.epn.fis.uil4midp.views.View;
import java.util.Stack;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * A NavigableController is a kind of Controller intended to support the navigation
 * between Views. The views are added to the controlles and it provides a mechanism
 * to go back between them. Only one View may be active at a given time. This controller
 * can be added as a child of a TabsController to allow the programmer to build a
 * more complex user experience.
 * @author Carlos Andrés Oquendo
 */
public class NavigableController extends Controller {

    private Stack holdedViews;
    private View activeView;
    private View firstView;
    private Image backIcon;

    //<editor-fold desc="Constructors">
    /**
     * Initializes internal fields and creates a new instance of NavigableController
     */
    public NavigableController(Window window) {
        super(window);
        backIcon = ResourceManager.loadImage(tm.getBackIconPath());
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

            // Establecer la primera vista.
            if (firstView == null) {
                firstView = view;
            }
            
            // Añadir la vista activa actual a la pila
            if (activeView != null) {
                holdedViews.push(activeView);
            }

            // Añadir el handler para Back.
            if (!holdedViews.isEmpty()) {
                f.setLeftButton(backIcon, new ActionListener() {

                    public void execute() {
                        activeView = (View) holdedViews.pop();
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
        if (dialog == null || dialog.isDismissed()) {
            keyPressHandled = activeView.keyPressed(action, keyCode);
        } else {
            keyPressHandled = dialog.keyPressed(action, keyCode);
        }
    }

    /**
     * Paints the contents of the NavigableController.
     * @param g Graphics object on which paint.
     */
    public void paint(Graphics g) {
        activeView.setViewPortHeight(viewPortHeight);
        activeView.paint(g);

        // Mostrar el cuadro de diálogo
        paintDialog(g);
    }

    /**
     * Prepares the layout of the NavigableController.
     */
    public void prepareController() {
        if (autoCalcViewPortHeight) {
            viewPortHeight = height;
        }
    }

    /**
     * Gets the view that is being displayed by the NavigableController
     * @return View that is being displayed by the NavigableController.
     */
    public View getView() {
        return activeView;
    }
    //</editor-fold>

    //<editor-fold desc="Navigation Methods">
    /**
     * Makes the controller to go to the first holded view.
     */
    public void goToStartView() {
        holdedViews.removeAllElements();
        
        activeView = firstView;
        getWindow().repaint();
    }

    /**
     * Goes to the previous view in the Navigation flow.
     */
    public void goToPreviousView() {
        if (holdedViews.isEmpty()) return;

        activeView = (View)holdedViews.pop();
        getWindow().repaint();
    }
    //</editor-fold>
}
