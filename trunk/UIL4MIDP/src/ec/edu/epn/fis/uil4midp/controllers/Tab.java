package ec.edu.epn.fis.uil4midp.controllers;

import ec.edu.epn.fis.uil4midp.components.controls.UserControl;
import ec.edu.epn.fis.uil4midp.util.GradientManager;
import ec.edu.epn.fis.uil4midp.views.View;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * A Tab is a UserControl which is part of the TabsController. A Tab instance has
 * an associated view and icon. The icon is used to identify the view on the TabBar
 * and the View is shown everytime the user selects the Tab.
 * @author Andrés
 */
final class Tab extends UserControl {

    private Image icon;
    private View holdedView;
    private Controller holdedController;
    private boolean selected;

    //<editor-fold desc="Constructors">
    /**
     * Initializes internal fields
     * @param icon Image to show as icon in the Tab
     */
    private Tab(Image icon) {
        this.icon = icon;
    }

    /**
     * Creates a new Tab instance with the specified Image and View.
     * This is intended to be instantiated only by the TabBar class.
     * @param icon Image to show as icon in the Tab
     * @param view View associated to the Tab
     */
    public Tab(Image icon, View view) {
        this(icon);
        this.holdedView = view;
    }

    /**
     * Creates a new Tab instance with the specified Image and Controller.
     * This is intended to be instantiated only by the TabBar class.
     * @param icon Image to show as icon in the Tab
     * @param controller Controller associated to the Tab
     */
    public Tab(Image icon, Controller controller) {
        this(icon);
        this.holdedController = controller;
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    /**
     * Sets the selected status of the Tab
     * @param selected True if the Tab must be selected, False, otherwise.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Determines if the Tab is selected.
     * @return True if the Tab is selected, False, otherwise.
     */
    public boolean isSelected() {
        return this.selected;
    }

    /**
     * Gets the View associated to the Tab.
     * @return View instance associated to the Tab.
     */
    public View getView() {
        return this.holdedView;
    }

    /**
     * Gets the Controller associated to the Tab.
     * @return Controller instance associated to the Tab.
     */
    public Controller getController() {
        return this.holdedController;
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods Implementation">
    /**
     * Handles the key events.
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     * @return This method always returns False.
     */
    public boolean keyPressed(int action, int keyCode) {
        return false;
    }

    /**
     * Determines if the Tab can be focused.
     * @return This method always return True.
     */
    public boolean isFocusable() {
        return true;
    }

    /**
     * Paints the Tab.
     * @param g Graphics object on which paint.
     */
    public void paint(Graphics g) {
        prepareComponent();

        // Paint Background
        int back[];
        if (focused) {
            back = tm.getTabbarHoverBackground();
        } else if (selected) {
            back = tm.getTabbarSelectedBackground();
        } else {
            back = tm.getTabbarNormalBackground();
        }
        GradientManager.paintGradient(g, back[0], back[1], x, y, width, height, GradientManager.VERTICAL);

        // Paint icon
        g.drawImage(icon, x + (width / 2), y + (height / 2), Graphics.HCENTER | Graphics.VCENTER);
    }

    /**
     * Prepares the layout of the component
     */
    public void prepareComponent() {
        if (!layoutSynced) {
            height = icon.getHeight() + 2 * padding;
            layoutSynced = true;
        }
    }
    //</editor-fold>
}
