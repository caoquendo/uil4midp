
package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.actions.ActionListener;
import ec.edu.epn.fis.uil4midp.components.controls.Button;
import javax.microedition.lcdui.Canvas;

public class MessageDialog extends Dialog{

    private Button btnOk;

    /**
     * Crea un MessageDialog que muestra un mensaje y tiene un botón Aceptar que
     * cierra el dialogo.
     * @param message
     */
    public MessageDialog(String title, String message) {
        super(title, message);

        btnOk = new Button("Aceptar");
        btnOk.setFocused(true);
        btnOk.setActionListener(new ActionListener() {
            public void execute() {
                dialogResult = DIALOG_OK;
                getController().dismissDialog();
            }
        });
    }

    public boolean keyPressed(int action, int keyCode) {
        switch (action) {
            case Canvas.FIRE:
                return btnOk.keyPressed(action, keyCode);
            default:
                return false;
        }
    }

    public void initialize() {
        super.initialize();
        addVisualComponent(btnOk);
    }
}
