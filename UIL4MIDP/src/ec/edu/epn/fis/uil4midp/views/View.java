package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.actions.ActionListener;
import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import ec.edu.epn.fis.uil4midp.controllers.Controller;
import ec.edu.epn.fis.uil4midp.util.ThemeManager;
import javax.microedition.lcdui.Graphics;

/**
 * Defines the structure of a View.
 * @author Carlos Andrés Oquendo
 */
public abstract class View {

    protected Controller controller;
    protected int width;
    protected ActionListener actionListener;
    protected int viewPortHeight;
    protected ThemeManager tm = ThemeManager.getInstance();

    //<editor-fold desc="Abstract Methods">
    /**
     * Adds a VisualComponent to the View
     * <i>This method must be implemented on all the subclasses.</i>
     * @param visualComponent VisualComponent to be used on a View.
     */
    public abstract void addVisualComponent(VisualComponent visualComponent);

    /**
     * Handles the key events.
     * <i>This method must be implemented on all the subclasses.</i>
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     * @return True if the key event was handled, else, False.
     */
    public abstract boolean keyPressed(int action, int keyCode);

    /**
     * Paints the contents of the View.
     * <i>This method must be implemented on all the subclasses.</i>
     * @param g Graphics object on which paint.
     */
    public abstract void paint(Graphics g);

    /**
     * Initializes the layout of the View.
     * In this method, the implementation should add all the VisualComponents that
     * will compose the View.
     */
    public abstract void initialize();
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    /**
     * Gets the associated ActionListener
     * @return ActionListener of the View
     */
    public ActionListener getActionListener() {
        return actionListener;
    }

    /**
     * Sets the associated ActionListener of the view.
     * @param actionListener ActionListener of the view.
     */
    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }
    //</editor-fold>

    //<editor-fold desc="Basic Layout Methods">
    /**
     * Sets the width of the view.
     * This method can be overriden by the subclasses.
     * @param width Width of the view.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets the owner of the View.
     * @param controller Controller instance to which the View belongs.
     */
    public final void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Gets the owner of the View
     * @return Controller instance to which the View belongs.
     */
    public final Controller getController() {
        return this.controller;
    }

    /**
     * Sets the height of the available space on screen
     * @param viewPortHeight Height of the available space on screen.
     */
    public void setViewPortHeight(int viewPortHeight) {
        this.viewPortHeight = viewPortHeight;
    }
    //</editor-fold>

    //<editor-fold desc="UI Methods">
    /**
     * Shows a Dialog box.
     * @param dialog Dialog box instance to be shown.
     */
    public void showDialog(Dialog dialog) {
        controller.setDialog(dialog);
    }
    //</editor-fold>
}
