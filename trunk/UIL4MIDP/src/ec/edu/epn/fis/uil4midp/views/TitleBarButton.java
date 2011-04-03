package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.actions.ActionListener;
import ec.edu.epn.fis.uil4midp.components.controls.UserControl;
import ec.edu.epn.fis.uil4midp.util.FontManager;
import ec.edu.epn.fis.uil4midp.util.GradientManager;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

final class TitleBarButton extends UserControl {

    public static final int LEFT_BUTTON = -1;
    public static final int RIGHT_BUTTON = 1;
    private String label;
    private int position;
    private ActionListener actionListener;

    //<editor-fold desc="Constructors">
    /**
     * Initializes internal fields
     */
    private TitleBarButton() {
        font = FontManager.getNormalFont();
    }

    /**
     * Creates a new TitleBarButton instance with the specified Label and Positions.
     * This is intended to be instantiated only by the TitleBar class.
     * @param label Text to show in the TitleBarButton
     * @param position Position of the TitleBarButton within the TitleBar. The possible
     * values are TitleBarButton.LEFT_BUTTON or TitleBarButton.RIGHT_BUTTON.
     */
    public TitleBarButton(String label, int position) {
        this();
        this.label = label;
        this.position = position;
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    /**
     * Sets the ActionListener of the TitleBarButton
     * @param actionListener ActionListener of the TitleBarButton
     */
    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
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
        if (action == Canvas.FIRE && actionListener != null) {
            actionListener.execute();
            return true;
        }
        return false;
    }

    /**
     * Determines if the TitleBarButton can be focused.
     * @return This method always return True.
     */
    public boolean isFocusable() {
        return true;
    }

    /**
     * Paints the TitleBarButton.
     * @param g Graphics object on which paint.
     */
    public void paint(Graphics g) {
        prepareComponent();

        int buttonX = position == RIGHT_BUTTON ? x - width : x;

        if (isFocused()) {
            GradientManager.paintGradient(g, 0x4e524c, 0x191c1f, buttonX, y, width, height, GradientManager.VERTICAL);
        } else {
            GradientManager.paintGradient(g, 0x3b3e39, 0x191c1f, buttonX, y, width, height, GradientManager.VERTICAL);
        }

        g.setColor(0xFFFFFF);
        g.drawString(label, buttonX + (width / 2), y + padding, Graphics.TOP | Graphics.HCENTER);
    }

    /**
     * Prepares the layout of the TitleBarButton.
     */
    public void prepareComponent() {
        if (!layoutSynced) {
            width = font.stringWidth(label) + 2 * padding;
            height = font.getHeight() + 2 * padding;

            layoutSynced = true;
        }
    }
    //</editor-fold>
}
