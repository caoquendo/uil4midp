
package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.actions.ActionListener;
import ec.edu.epn.fis.uil4midp.components.controls.Button;
import ec.edu.epn.fis.uil4midp.components.controls.Label;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public abstract class MessageDialog extends Dialog{

    /**
     * Crea un MessageDialog que muestra un mensaje y tiene un botón Aceptar que
     * cierra el dialogo.
     * @param message
     */
    public MessageDialog(String message) {
        Label lblMessage = new Label(message);
        lblMessage.setPadding(3);
        addVisualComponent(lblMessage);

        Button btnOk = new Button("Aceptar");
        btnOk.setCaption("Aceptar");
        btnOk.setPadding(5);
        btnOk.setFocused(true);
        btnOk.setActionListener(new ActionListener() {
            public void execute() {
                getController().dismissDialog();
            }
        });
        addVisualComponent(btnOk);

    }

    public boolean keyPressed(int action, int keyCode) {
        switch (action) {
            case Canvas.FIRE:
                
                return true;
            default:
                return false;
        }
    }

    public void paint(Graphics g) {
    }

    public abstract void initialize();
}
