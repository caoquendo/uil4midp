package ec.edu.epn.fis.uil4midp.components.containers;

import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import ec.edu.epn.fis.uil4midp.components.controls.UserControl;
import ec.edu.epn.fis.uil4midp.views.AbstractView;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public abstract class Container extends VisualComponent {

    public static final int UP = 0;
    public static final int DOWN = 1;
    protected Vector visualComponents;
    protected int margin = 0;
    protected int border = 0;
    protected int borderColor = 0;
    protected int controlSeparation = 0;

    protected AbstractView view;

    public Container() {
        visualComponents = new Vector();
    }

    public void paint(Graphics g) {
        // Paint border
    }

    public void addContainer(Container container) {
        container.setContainer(this);
        this.visualComponents.addElement(container);
    }

    public void addUserControl(UserControl userControl) {
        userControl.setContainer(this);
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

    public abstract boolean canHandleVerticalMovement(int direction);

    public int getComponentType() {
        return CONTAINER;
    }

    public void setView(AbstractView view) {
        this.view = view;
    }

    public AbstractView getView() {
        return this.view;
    }
}
