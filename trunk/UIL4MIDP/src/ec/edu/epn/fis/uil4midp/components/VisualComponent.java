package ec.edu.epn.fis.uil4midp.components;

import ec.edu.epn.fis.uil4midp.components.containers.Container;
import javax.microedition.lcdui.Graphics;

public abstract class VisualComponent {

    public static final int USER_CONTROL = 0;
    public static final int CONTAINER = 1;
    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected boolean focused = false;

    protected Container container;

    public abstract void paint(Graphics g);

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    public boolean isFocused() {
        return focused;
    }

    public abstract boolean isFocusable();

    public abstract int getComponentType();

    public abstract boolean keyPressed(int action, int keyCode);

    public void setContainer(Container container) {
        this.container = container;
    }

    public Container getContainer() {
        return this.container;
    }
}
