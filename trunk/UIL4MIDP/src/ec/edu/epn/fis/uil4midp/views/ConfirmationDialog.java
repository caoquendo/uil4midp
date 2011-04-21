
package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.actions.ActionListener;
import ec.edu.epn.fis.uil4midp.components.controls.Button;
import javax.microedition.lcdui.Canvas;

public class ConfirmationDialog extends Dialog {

    private Button btnYes;
    private Button btnNo;

    /**
     * Crea un ConfirmationDialog que muestra un mensaje y tiene un botón Si y un Botón No
     * que cierran el diálogo.
     * @param title
     * @param message
     */
    public ConfirmationDialog(String title, String message) {
        super(title, message);

        btnYes = new Button("Si");
        btnYes.setFocused(true);
        btnYes.setActionListener(new ActionListener() {
            public void execute() {
                dialogResult = DIALOG_YES;
                getController().dismissDialog();
            }
        });

        btnNo = new Button("No");
        btnNo.setActionListener(new ActionListener() {
            public void execute() {
                dialogResult = DIALOG_NO;
                getController().dismissDialog();
            }
        });
    }

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

    public void initialize() {
        super.initialize();
        addVisualComponent(btnYes);
        addVisualComponent(btnNo);
    }

}
