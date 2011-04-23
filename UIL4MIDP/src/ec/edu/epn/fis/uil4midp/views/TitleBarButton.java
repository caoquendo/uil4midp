package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.actions.ActionListener;
import ec.edu.epn.fis.uil4midp.components.controls.UserControl;
import ec.edu.epn.fis.uil4midp.util.FontManager;
import ec.edu.epn.fis.uil4midp.util.GradientManager;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * A TitleBarButton is a kind of UserControl which only can be located on a TitleBar.
 * @author Carlos Andrés Oquendo
 */
final class TitleBarButton extends UserControl {

    public static final int LEFT_BUTTON = -1;
    public static final int RIGHT_BUTTON = 1;
    private String label;
    private Image icon;
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
     * Creates a new TitleBarButton instance with the specified Label and Position.
     * This constructor is intended to be called only by the TitleBar class.
     * @param label Text to show in the TitleBarButton
     * @param position Position of the TitleBarButton within the TitleBar. The possible
     * values are TitleBarButton.LEFT_BUTTON or TitleBarButton.RIGHT_BUTTON.
     */
    public TitleBarButton(String label, int position) {
        this();
        this.label = label;
        this.position = position;
    }

    /**
     * Creates a new TitleBarButton instance with the specified Icon and Position.
     * This constructor is intended to be called only by the TitleBar class.
     * @param icon Icon to show in the TitleBarButton. The dimensions of the icon
     * must be 16 x 16 pixels.
     * @param position Position of the TitleBarButton within the TitleBar. The possible
     * values are TitleBarButton.LEFT_BUTTON or TitleBarButton.RIGHT_BUTTON.
     */
    public TitleBarButton(Image icon, int position) {
        this();
        this.icon = icon;
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

        int[] back = focused ? tm.getTitlebarButtonHoverBackground() : tm.getTitlebarButtonNormalBackground();
        GradientManager.paintGradient(g, back[0], back[1], buttonX, y, width, height, GradientManager.VERTICAL);
        
        if (icon == null) { // Label
            g.setColor(tm.getInvertedFontColor());
            g.drawString(label, buttonX + (width / 2), y + padding, Graphics.TOP | Graphics.HCENTER);
        } else {
            g.drawImage(icon, buttonX + padding, y + (height / 2), Graphics.VCENTER | Graphics.LEFT);
        }
    }

    /**
     * Prepares the layout of the TitleBarButton.
     */
    public void prepareComponent() {
        if (!layoutSynced) {
            height = font.getHeight() + 2 * padding;

            if (icon == null) { // Text Label
                width = font.stringWidth(label) + 2 * padding;
            } else { // Icon
                width = icon.getWidth() + 2 * padding;
            }

            layoutSynced = true;
        }
    }
    //</editor-fold>
}
