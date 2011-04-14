package ec.edu.epn.fis.uil4midp.components.controls;

import ec.edu.epn.fis.uil4midp.util.FontManager;
import ec.edu.epn.fis.uil4midp.util.GradientManager;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class Switch extends UserControl {

    private Label caption;
    private boolean turnedOn = false;
    private int switchDimension;
    private int selectorHeight;

    //<editor-fold desc="Constructors">
    /**
     * Initializes internal fields
     */
    private Switch() {
        font = FontManager.getNormalFont();
    }

    /**
     * Creates a new Switch instance with the specified caption.
     * @param caption Text to show in the Switch
     */
    public Switch(String caption) {
        this();
        this.caption = new Label(caption);
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    /**
     * Determines if the Switch is turned on.
     * @return True if the Switch is turned on, else, False.
     */
    public boolean isTurnedOn() {
        return turnedOn;
    }

    /**
     * Sets the activation status of the Switch.
     * @param turnedOn Activation status of the Switch.
     */
    public void setTurnedOn(boolean turnedOn) {
        this.turnedOn = turnedOn;
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods Implementations">
    /**
     * Handles the key events.
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     * @return This method returns True if the key event was handled, False, otherwise.
     */
    public boolean keyPressed(int action, int keyCode) {
        switch (action) {
            case Canvas.FIRE:
                turnedOn = !turnedOn;
                return true;
        }
        return false;
    }

    /**
     * Determines if the Switch can be focused.
     * @return This method always return True.
     */
    public boolean isFocusable() {
        return true;
    }

    /**
     * Paints the Switch.
     * @param g Graphics object on which paint.
     */
    public void paint(Graphics g) {
        prepareComponent();

        int ty = y - yOffset;

        // Paint switch background
        int[] swBack = turnedOn ? tm.getSwitchActiveBackground() : tm.getSwitchInactiveBackground();
        int selectorY = turnedOn ? ty : ty + switchDimension - selectorHeight;
        GradientManager.paintGradient(g, swBack[0], swBack[1], x, ty, switchDimension, switchDimension, GradientManager.VERTICAL);

        // Paint border
        int border = focused ? tm.getSwitchActiveBorder() : tm.getSwitchInactiveBorder();

        g.setColor(border);
        g.drawRect(x, ty, switchDimension - 1, switchDimension - 1);

        // Paint selector
        int[] selBack = focused ? tm.getSwitchActiveSelector() : tm.getSwitchInactiveSelector();
        GradientManager.paintGradient(g, selBack[0], selBack[1], x, selectorY, switchDimension, selectorHeight, GradientManager.VERTICAL);
        
        // Paint selector border
        g.setColor(border);
        g.drawRect(x, selectorY, switchDimension - 1, selectorHeight - 1);

        // Draw text
        caption.setYOffset(yOffset);
        caption.paint(g);
    }

    /**
     * Prepares the layout of the Switch.
     */
    public void prepareComponent() {
        switchDimension = font.getHeight() + 2 * padding;
        selectorHeight = switchDimension / 3;

        caption.setPosition(x + switchDimension, y);
        caption.setWidth(width - switchDimension);
        caption.setPadding(padding);
        caption.prepareComponent();
        
        height = caption.getHeight();
    }
    //</editor-fold>
}
