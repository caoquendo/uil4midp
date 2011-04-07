package ec.edu.epn.fis.uil4midp.controllers;

import ec.edu.epn.fis.uil4midp.components.controls.TextBox;
import ec.edu.epn.fis.uil4midp.ui.Window;
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

    protected int width;
    protected int height;
    protected int viewPortHeight;
    protected Window window;
    protected Dialog dialog;

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
     * Sets the owner of the Controller.
     * @param window Window instance to which the Controller belongs.
     */
    public final void setWindow(Window window) {
        this.window = window;
    }

    /**
     * Gets the owner of the controller.
     * @return Window instance to which the Controller belongs.
     */
    public final Window getWindow() {
        return this.window;
    }
    //</editor-fold>

    //<editor-fold desc="Dialog Management Functions">
    public final void setDialog(Dialog dialog) {
        dialog.setController(this);
        this.dialog = dialog;

        dialog.setWidth(width);
    }

    public final void dismissDialog() {
        this.dialog = null;
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

        window.getDisplay().setCurrent(nativeTextBox);
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
            window.getDisplay().setCurrent(window);
            window.repaint();
        }
    }
    //</editor-fold>
}
