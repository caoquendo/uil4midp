package ec.edu.epn.fis.uil4midp.controllers;

import ec.edu.epn.fis.uil4midp.components.controls.TextBox;
import ec.edu.epn.fis.uil4midp.ui.Window;
import ec.edu.epn.fis.uil4midp.util.ThemeManager;
import ec.edu.epn.fis.uil4midp.views.Dialog;
import ec.edu.epn.fis.uil4midp.views.View;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Defines the structure of a View Controller
 * @author Carlos Andrés Oquendo
 */
public abstract class Controller {

    // Dimensiones
    protected int width;
    protected int height;
    protected int viewPortHeight;
    protected boolean autoCalcViewPortHeight = true;
    // Propietarios
    private Window window;
    private Controller controller;
    // Otros
    protected Dialog dialog;
    protected ThemeManager tm = ThemeManager.getInstance();
    protected boolean keyPressHandled = false;

    //<editor-fold desc="Constructors">
    protected Controller(Window window) {
        setWidth(window.getWidth());
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods">
    /**
     * Adds a View to the Controller.
     * <i>This method must be implemented on all the subclasses.</i>
     * @param view View to be managed by the controller.
     * @param icon Icon to be used by the controller. Depending on the subclass
     * this parameter may be optional.
     */
    public abstract void addView(View view, Image icon);

    /**
     * Handles the key events.
     * <i>This method must be implemented on all the subclasses.</i>
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     */
    public abstract void keyPressed(int action, int keyCode);

    /**
     * Paints the contents of the Controller.
     * <i>This method must be implemented on all the subclasses.</i>
     * @param g Graphics object on which paint.
     */
    public abstract void paint(Graphics g);

    /**
     * prepares the layout of the Controller.
     */
    public abstract void prepareController();

    /**
     * Gets the view that is being displayed by the Controller
     * @return View that is being displayed by the Controller.
     */
    public abstract View getView();
    //</editor-fold>

    //<editor-fold desc="KeyPress Management Methods">
    /**
     * Determines if the last KeyPress event was handled.
     * @return True is the last key press event was handled, otherwise False.
     */
    public final boolean isKeyPressHandled() {
        return keyPressHandled;
    }

    //</editor-fold>
    
    //<editor-fold desc="Basic Layout Methods">
    /**
     * Sets the width of the Controller.
     * @param width Width of the controller.
     */
    public final void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets the height of the Controller.
     * @param height height of the Controller.
     */
    public final void setHeight(int height) {
        this.height = height;
    }

    /**
     * Sets the height of the ViewPort.
     * @param viewPortHeight Height of the viewport. This value is frequently
     * calculated automatically. Use this method to override the automatic
     * calculation.
     */
    public void setViewPortHeight(int viewPortHeight) {
        this.viewPortHeight = viewPortHeight;

        autoCalcViewPortHeight = viewPortHeight == -1 ? true : false;
    }

    /**
     * Sets a Windows as the owner of the Controller.
     * @param window Window instance to which this Controller belongs.
     */
    public final void setWindow(Window window) {
        this.window = window;
    }

    /**
     * Gets the Window that is the owner of the controller. If this Controller belongs
     * to another Controller, this method will try to find the owner's Window.
     * @return Window instance to which the Controller belongs.
     */
    public final Window getWindow() {
        if (controller == null) {
            return this.window;
        }
        return controller.getWindow();
    }

    /**
     * Sets a Controller as the owner of the Controller.
     * @param controller Controller instance to which this Controller belongs.
     */
    public final void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Gets the Controller that is the owner of the controller.
     * @return Controller instance to which the Controller belongs.
     */
    public final Controller getController() {
        return this.controller;
    }
    //</editor-fold>

    //<editor-fold desc="Dialog Management Functions">
    /**
     * Sets the Dialog instace to be shown.
     * @param dialog Dialog to be shown.
     */
    public final void setDialog(Dialog dialog) {
        dialog.setDismissed(false);
        dialog.setController(this);
        dialog.setWidth(width);
        dialog.initialize();

        this.dialog = dialog;
    }

    /**
     * Dismisses the currently shown Dialog.
     */
    public final void dismissDialog() {
        dialog.setDismissed(true);

        if (dialog.getDismissActionListener() != null) {
            dialog.getDismissActionListener().execute();
        }

        getWindow().repaint();
    }

    /**
     * Dismisses the currently shown Dialog and optionally executes the ActionListener of the Dialog.
     */
    public final void dismissDialog(boolean executeActionListener) {
        dialog.setDismissed(true);

        if (executeActionListener && dialog.getDismissActionListener() != null) {
            dialog.getDismissActionListener().execute();
        }

        getWindow().repaint();
    }
    //</editor-fold>

    //<editor-fold desc="Utility Functions">
    /**
     * Show a platform native text input screen.
     * @param uilTextBox UIL4MIDP TextBox instance which needs to show a
     * native text input screen. The native text input screen gets its properties
     * from this parameter.
     */
    public final void showNativeTextScreen(TextBox uilTextBox) {
        final Command cmdDone = new Command("Done", Command.OK, 0);

        javax.microedition.lcdui.TextBox nativeTextBox = new javax.microedition.lcdui.TextBox(
                uilTextBox.getCaption(),
                uilTextBox.getText(),
                uilTextBox.getMaxLength(),
                uilTextBox.getConstraints()
                );
        nativeTextBox.addCommand(cmdDone);

        nativeTextBox.setCommandListener(new DoneCommandListener(uilTextBox));

        getWindow().getDisplay().setCurrent(nativeTextBox);
    }

    /**
     * CommandListener that closes the native text input screen and assigns the
     * entered value to the corresponding UIL4MIDP TextBox.
     */
    private class DoneCommandListener implements CommandListener {

        private TextBox textBox;

        /**
         * Creates a new instance of DoneCommandListener
         * @param textBox UIL4MIDP TextBox that needs to be updated with the
         * text gathered by the native text input screen.
         */
        public DoneCommandListener(TextBox textBox) {
            this.textBox = textBox;
        }

        public void commandAction(Command c, Displayable d) {
            textBox.setText(((javax.microedition.lcdui.TextBox)d).getString());
            getWindow().getDisplay().setCurrent(getWindow());
            getWindow().repaint();
        }
    }

    /**
     * Paints the Dialog on the screen.
     * @param g Graphics object on which paint.
     */
    protected void paintDialog(Graphics g) {
        // Mostrar el cuadro de dialogo
        if (dialog != null && !dialog.isDismissed()) {
            g.drawImage(getWindow().getOverlayImage(), 0, 0, Graphics.LEFT | Graphics.TOP);
            dialog.refreshLayout();
            dialog.setPosition(0, height - dialog.getHeight());
            dialog.paint(g);
        }
    }
    //</editor-fold>
}
