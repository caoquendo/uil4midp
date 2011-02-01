
package ec.edu.epn.fis.uil4midp.components.controls;

import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import javax.microedition.lcdui.Graphics;

public abstract class UserControl extends VisualComponent {

    protected int padding;

    public abstract void paint(Graphics g);

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public int getComponentType() {
        return USER_CONTROL;
    }
}
