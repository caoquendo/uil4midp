/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.epn.fis.uil4midp.components.controls;

import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Andrés
 */
public abstract class UserControl extends VisualComponent {

    protected boolean selected;
    protected int padding;

    public abstract void paint(Graphics g);

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }
}
