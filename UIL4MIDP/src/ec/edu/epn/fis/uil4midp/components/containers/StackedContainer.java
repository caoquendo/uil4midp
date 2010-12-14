/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.edu.epn.fis.uil4midp.components.containers;

import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Andrés
 */
public class StackedContainer extends Container {

    private int nextX;
    private int nextY;

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
}
