package ec.edu.epn.fis.uil4midp.components;

import ec.edu.epn.fis.uil4midp.components.containers.Container;
import ec.edu.epn.fis.uil4midp.util.ThemeManager;
import javax.microedition.lcdui.Graphics;

/**
 * Defines the structure of a VisualComponent. A VisualComponent represents an
 * abstract object which is part of a User Interface.
 * @author Carlos Andrés Oquendo
 */
public abstract class VisualComponent {

    public static final int TYPE_USER_CONTROL = 0;
    public static final int TYPE_CONTAINER = 1;

    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected int yOffset;
    protected boolean focused = false;
    protected Container container;
    protected ThemeManager tm = ThemeManager.getInstance();
    
    private int type;

    //<editor-fold desc="Constructors">
    /**
     * Initializes the VisualComponent.
     * @param type Type of VisualComponent.
     */
    protected VisualComponent(int type) {
        this.type = type;
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods">
    /**
     * Handles the key events.
     * <i>This method must be implemented on all the subclasses.</i>
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     * @return True if the key event was handled, else, False.
     */
    public abstract boolean keyPressed(int action, int keyCode);

    /**
     * Determines if the VisualComponent can get focus.
     * @return True if the VisualComponent can be focused, otherwise, False.
     */
    public abstract boolean isFocusable();

    /**
     * Paints the contents of the VisualComponent.
     * <i>This method must be implemented on all the subclasses.</i>
     * @param g Graphics object on which paint.
     */
    public abstract void paint(Graphics g);

    /**
     * Prepares the layout of the VisualComponent.
     * The implementation of this method must be designed to be executed
     * whenever the values of the VisualComponent change.
     */
    public abstract void prepareComponent();
    //</editor-fold>

    //<editor-fold desc="Basic Layout Methods">
    /**
     * Sets the position of the VisualComponent.
     * @param x Coordinate X of the position.
     * @param y Coordinate Y of the position.
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the X coordinate of the VisualComponent
     * @return X coordinate of the VisualComponent
     */
    public int getXCoordinate() {
        return x;
    }

    /**
     * Gets the Y coordinate of the VisualComponent
     * @return Y coordinate of the VisualComponent
     */
    public int getYCoordinate() {
        return y;
    }

    /**
     * Sets the Y displacement of the position of the VisualComponent.
     * @param yOffset Y displacement of the position of the VisualComponent.
     */
    public void setYOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    /**
     * Gets the Y displacement of the position of the VisualComponent.
     * @return Y displacement of the position of the VisualComponent.
     */
    public int getYOffset() {
        return yOffset;
    }

    /**
     * Gets the width of the VisualComponent
     * @return Width of the VisualComponent
     */
    public final int getWidth() {
        return width;
    }

    /**
     * Sets the width of the VisualComponent.
     * <i>This method can be overriden by the subclasses.</i>
     * @param width Width of the VisualComponent.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the height of the VisualComponent
     * @return Height of the VisualComponent.
     */
    public final int getHeight() {
        return height;
    }

    /**
     * Sets the owner of the VisualComponent.
     * @param container Container instance to which the VisualComponent belongs
     */
    public void setContainer(Container container) {
        this.container = container;
    }

    /**
     * Gets the owner of the VisualComponent.
     * @return Container instance to which the VisualComponent belongs.
     */
    public Container getContainer() {
        return this.container;
    }
    //</editor-fold>

    //<editor-fold desc="Behavior Methods">
    /**
     * Sets the focus status of the VisualComponent
     * @param focused True to set the focus on the VisualComponent, else, False.
     */
    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    /**
     * Gets the focus status of the VisualComponent
     * @return True if the component is focused, False, otherwise.
     */
    public boolean isFocused() {
        return focused;
    }

    /**
     * Gets the type of VisualComponent.
     * @return Type of VisualComponent. This type may be VisualComponent.TYPE_USER_CONTROL
     * or VisualComponent.TYPE_CONTAINER
     */
    public int getVisualComponentType() {
        return type;
    }
    //</editor-fold>
}
