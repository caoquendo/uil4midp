/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.edu.epn.fis.uil4midp.components.containers;

import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import ec.edu.epn.fis.uil4midp.components.controls.UserControl;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Andrés
 */
public class StackedContainer extends Container {

    private int nextX;
    private int nextY;

    private int selectedControlIndex = -1;
    private UserControl selectedControl = null;

    public StackedContainer() {
        super();
    }

    public void paint(Graphics g) {
        super.paint(g);

        // Set the controls position
        nextX = x + margin + border;
        nextY = y + margin + border;

        int controlWidth = width - (2 * margin) - (2 * border);

        VisualComponent vc;

        for (int i = 0; i < visualComponents.size(); i++) {
            vc = (VisualComponent)visualComponents.elementAt(i);
            vc.setPosition(nextX, nextY);
            vc.setWidth(controlWidth);
            vc.paint(g);

            nextY = nextY + margin + border + controlSeparation + vc.getHeight();
        }
    }

    public void keyPressed(int keyCode) {
        switch (keyCode) {
            case Canvas.DOWN:
                manageVerticalMovement(keyCode, true);
                break;
            case Canvas.UP:
                manageVerticalMovement(keyCode, false);
                break;
        }
    }

    private void manageVerticalMovement(int keyCode, boolean isDown) {
        try {
            if (selectedControl != null)
                selectedControl.setSelected(false);

            if (isDown)
                selectedControlIndex++;
            else
                selectedControlIndex--;

            UserControl uc = (UserControl)visualComponents.elementAt(selectedControlIndex);
            if (uc.isSelectable())  {
                uc.setSelected(true);
                selectedControl = uc;
            }
        } catch (Exception e) {
            // Component is a container
            Container cnt = (Container)visualComponents.elementAt(selectedControlIndex);
            cnt.keyPressed(keyCode);
        }
    }
}
