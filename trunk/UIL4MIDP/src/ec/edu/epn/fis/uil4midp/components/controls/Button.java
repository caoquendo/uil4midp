package ec.edu.epn.fis.uil4midp.components.controls;

import ec.edu.epn.fis.uil4midp.actions.ActionListener;
import ec.edu.epn.fis.uil4midp.util.FontManager;
import ec.edu.epn.fis.uil4midp.util.GradientManager;
import ec.edu.epn.fis.uil4midp.util.TextManager;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * A Button is a VisualComponent which is intended to execute actions.
 * @author Carlos Andrés Oquendo
 */
public class Button extends UserControl {

    private String caption;
    private String[] captionLines;
    private Image icon;
    private boolean contentSynced;
    private ActionListener actionListener;

    //<editor-fold desc="Constructors">
    /**
     * Initializes internal fields
     */
    private Button() {
        font = FontManager.getBoldFont();
        contentSynced = false;
    }

    /**
     * Creates a new Button instance with the specified Caption.
     * @param caption Text to show in the Button.
     */
    public Button(String caption) {
        this();
        this.caption = caption;
    }

    /**
     * Creates a new Button instance with the specified Icon.
     * @param caption Image to show in the Button.
     */
    public Button(Image icon) {
        this();
        this.icon = icon;
    }

    /**
     * Creates a new Button instance with the specified Caption and ActionListener
     * @param caption Text to show in the Button.
     * @param actionListener Object containing the actions for the Button.
     */
    public Button(String caption, ActionListener actionListener) {
        this(caption);
        this.actionListener = actionListener;
    }

    /**
     * Creates a new Button instance with the specified Icon and ActionListener
     * @param caption Image to show in the Button.
     * @param actionListener Object containing the actions for the Button.
     */
    public Button(Image icon, ActionListener actionListener) {
        this(icon);
        this.actionListener = actionListener;
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    /**
     * Sets the caption of the Button. If the Button was initialized with an Icon
     * and you set a caption, the button will display it instead of the Icon.
     * @param caption Caption of the Button
     */
    public void setCaption(String caption) {
        this.caption = caption;
        this.icon = null;
        contentSynced = false;
    }

    /**
     * Sets the icon of the Button. If the Button was initialized with a Text caption
     * and you set a icon, the button will display it instead of the Text caption.
     * @param icon Icon of the Button
     */
    public void setIcon(Image icon) {
        this.icon = icon;
        contentSynced = false;
    }

    /**
     * Sets the ActionListener of the Button
     * @param actionListener ActionListener of the Button
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
        switch (action) {
            case Canvas.FIRE:
                if (actionListener != null) {
                    actionListener.execute();
                    return true;
                }
            default:
                return false;
        }
    }

    /**
     * Determines if the Button can be focused.
     * @return This method always return True.
     */
    public boolean isFocusable() {
        return true;
    }

    /**
     * Paints the Button.
     * @param g Graphics object on which paint.
     */
    public void paint(Graphics g) {
        prepareComponent();

        int ty = y - yOffset;

        // Paint background
        int[] back = focused ? tm.getButtonActiveBackground() : tm.getButtonInactiveBackground();
        GradientManager.paintGradient(g, back[0], back[1], x, ty, width, height, GradientManager.VERTICAL);


        // Paint border
        g.setColor(focused ? tm.getButtonActiveBorder() : tm.getButtonInactiveBorder());
        g.drawRect(x, ty, width - 1, height - 1);

        // Draw text
        int[] pos = new int[]{x + width / 2, ty + padding};
        if (icon == null) {
            g.setColor(tm.getPrimaryFontColor());
            g.setFont(font);

            for (int i = 0; i < captionLines.length; i++) {
                g.drawString(captionLines[i], pos[0], pos[1] + font.getHeight() * i, Graphics.TOP | Graphics.HCENTER);
            }
        } else {
            g.drawImage(icon, pos[0], pos[1], Graphics.TOP | Graphics.HCENTER);
        }
    }

    /**
     * Prepares the layout of the Button.
     */
    public void prepareComponent() {
        if (!contentSynced || !layoutSynced) {
            if (icon == null) {
                int captionWidth = width - 2 * padding;

                captionLines = TextManager.getLines(caption, captionWidth, font);
                height = captionLines.length * font.getHeight() + 2 * padding;
            } else {
                height = icon.getHeight() + 2 * padding;
            }

            contentSynced = true;
            layoutSynced = true;
        }
    }
    //</editor-fold>
}
