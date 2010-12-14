/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.edu.epn.fis.uil4midp.components.containers;

import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import ec.edu.epn.fis.uil4midp.components.controls.UserControl;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Andrés
 */
public abstract class Container extends VisualComponent {

    protected Vector visualComponents;
    protected int margin = 0;
    protected int border = 0;
    protected int borderColor = 0;
    protected int controlSeparation = 0;

    public Container() {
        visualComponents = new Vector();
    }

    public void paint(Graphics g) {
        // Paint border
    }

    public void addContainer(Container container) {
        this.visualComponents.addElement(container);
    }

    public void addUserControl(UserControl userControl) {
        this.visualComponents.addElement(userControl);
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public void setBorder(int border) {
        this.border = border;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public void setControlSeparation(int controlSeparation) {
        this.controlSeparation = controlSeparation;
    }
}
