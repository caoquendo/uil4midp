
package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.actions.ActionListener;
import ec.edu.epn.fis.uil4midp.components.containers.HorizontalSplittedContainer;
import ec.edu.epn.fis.uil4midp.components.controls.Button;
import ec.edu.epn.fis.uil4midp.util.ResourceManager;
import ec.edu.epn.fis.uil4midp.util.ThemeManager;
import javax.microedition.lcdui.Canvas;

/**
 * A ConfirmationDialog is a kind of Dialog which shows a message which
 * only can be confirmed or denied.
 * @author Carlos Andrés Oquendo
 */
public class ConfirmationDialog extends Dialog {

    private Button btnYes;
    private Button btnNo;
    private HorizontalSplittedContainer hspButtons;


    //<editor-fold desc="Constructors">
    /**
     * Creates a MessageDialog showing a message. It contains a OK button that
     * dismisses the Dialog when fired.
     * @param title Title of the Dialog
     * @param message Message to be shown on the Dialog.
     */
    public ConfirmationDialog(String title, String message) {
        super(title, message);

        btnYes = new Button(ResourceManager.loadImage(tm.getOkYesIconPath()));
        btnYes.setFocused(true);
        btnYes.setActionListener(new ActionListener() {
            public void execute() {
                dialogResult = DIALOG_YES;
                getController().dismissDialog();
            }
        });

        btnNo = new Button(ResourceManager.loadImage(tm.getCancelNoIconPath()));
        btnNo.setActionListener(new ActionListener() {
            public void execute() {
                dialogResult = DIALOG_NO;
                getController().dismissDialog();
            }
        });

        hspButtons = new HorizontalSplittedContainer(-1);
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods Implementations">
    /**
     * Handles the key events.
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     * @return True if the key event was handled, else, False.
     */
    public boolean keyPressed(int action, int keyCode) {
        switch (action) {
            case Canvas.FIRE:
                return btnYes.isFocused() ? btnYes.keyPressed(action, keyCode) : btnNo.keyPressed(action, keyCode);
            case Canvas.LEFT:
                return handleMovement();
            case Canvas.RIGHT:
                return handleMovement();
            case Canvas.UP:
                return handleMovement();
            case Canvas.DOWN:
                return handleMovement();
            default:
                return false;
        }
    }
    //</editor-fold>

    //<editor-fold desc="Utility Methods">
    /**
     * Handles the directional movement on the Dialog.
     * @return True, due to the movement always will be handled.
     */
    private boolean handleMovement() {
        if (btnYes.isFocused()) {
            btnYes.setFocused(false);
            btnNo.setFocused(true);
        } else {
            btnYes.setFocused(true);
            btnNo.setFocused(false);
        }
        return true;
    }
    //</editor-fold>

    //<editor-fold desc="Overriden Methods">
    /**
     * Initializes additional components of the ConfirmationDialog.
     */
    public void initialize() {
        super.initialize();

        hspButtons.addVisualComponent(btnYes);
        hspButtons.addVisualComponent(btnNo);

        addVisualComponent(hspButtons);
    }
    //</editor-fold>
}
