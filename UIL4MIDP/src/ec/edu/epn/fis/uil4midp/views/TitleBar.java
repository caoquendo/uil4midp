package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.actions.ActionListener;
import ec.edu.epn.fis.uil4midp.components.controls.UserControl;
import ec.edu.epn.fis.uil4midp.util.FontManager;
import ec.edu.epn.fis.uil4midp.util.GradientManager;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class TitleBar extends UserControl {

    private String title;
    private TitleBarButton leftButton;
    private TitleBarButton rightButton;

    //<editor-fold desc="Constructors">
    /**
     * Initializes internal fields
     */
    private TitleBar() {
        font = FontManager.getNormalFont();
        setFocused(false);
    }

    /**
     * Creates a new TitleBar instance with the specified Title
     * @param title Text to show on the TitleBar
     */
    public TitleBar(String title) {
        this();
        this.title = title;
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    /**
     * Sets a TitleBarButton on the TitleBar
     * @param caption Text to show on the TitleBarButton
     * @param buttonPosition Position of the button on the TitleBar. The accepted
     * values are TitleBarButton.LEFT_BUTTON or TitleBarButton.RIGHT_BUTTON
     * @param actionListener ActionListener for the TitleBarButton
     */
    public void setTitleBarButton(String caption, int buttonPosition, ActionListener actionListener) {
        if (buttonPosition == TitleBarButton.LEFT_BUTTON) {
            leftButton = new TitleBarButton(caption, buttonPosition);
            initializeButton(leftButton, actionListener);
        } else if (buttonPosition == TitleBarButton.RIGHT_BUTTON) {
            rightButton = new TitleBarButton(caption, buttonPosition);
            initializeButton(rightButton, actionListener);
        }
        layoutSynced = false;
    }

    /**
     * Removes a TitleBarButton from the TitleBar
     * @param buttonPosition Position of the button on the TitleBar. The accepted
     * values are TitleBarButton.LEFT_BUTTON or TitleBarButton.RIGHT_BUTTON.
     */
    public void removeTitleBarButton(int buttonPosition) {
        if (buttonPosition == TitleBarButton.LEFT_BUTTON) {
            leftButton = null;
        } else if (buttonPosition == TitleBarButton.RIGHT_BUTTON) {
            rightButton = null;
        }
        layoutSynced = false;
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
            case Canvas.LEFT:
                return handleHorizontalMovement();
            case Canvas.RIGHT:
                return handleHorizontalMovement();
            case Canvas.FIRE:
                if (leftButton != null && leftButton.isFocused()) {
                    return leftButton.keyPressed(action, keyCode);
                } else if (rightButton != null && rightButton.isFocused()) {
                    return rightButton.keyPressed(action, keyCode);
                }
            default:
                return false;
        }
    }

    /**
     * Determines if the TitleBar can be focused.
     * @return If the title bar has TitleBarButtons on it, this method returns True
     * else, False.
     */
    public boolean isFocusable() {
        if (leftButton != null || rightButton != null) {
            return true;
        }

        return false;
    }

    /**
     * Paints the TitleBar.
     * @param g Graphics object on which paint.
     */
    public void paint(Graphics g) {
        prepareComponent();

        GradientManager.paintGradient(g, 0x272926, 0x191c1f, x, y, width, height, GradientManager.VERTICAL);

        g.setColor(0xFFFFFF);
        g.drawString(title, x + (width / 2), y + padding, Graphics.TOP | Graphics.HCENTER);

        if (leftButton != null) {
            leftButton.paint(g);
        }
        
        if (rightButton != null) {
            rightButton.paint(g);
        }
    }

    /**
     * Prepares the layout of the TitleBar
     */
    public void prepareComponent() {
        if (layoutSynced) {
            return;
        }

        if (leftButton != null) {
            leftButton.setPadding(padding);
            leftButton.setPosition(x, y);
            leftButton.prepareComponent();

            height = leftButton.getHeight();
        } else if (rightButton != null) {
            rightButton.setPadding(padding);
            rightButton.prepareComponent();

            rightButton.setPosition(x + width - rightButton.getWidth(), y);

            height = rightButton.getHeight();
        } else {
            height = font.getHeight() + 2 * padding;
        }

        layoutSynced = true;
    }
    //</editor-fold>

    //<editor-fold desc="Overriden Methods">
    /**
     * Sets the focused status of the TitleBar
     * @param focused True if the TitleBar must get the focus, else, False. The
     * specified value is passed to the most appropriate button.
     */
    public void setFocused(boolean focused) {
        super.setFocused(focused);

        if (leftButton != null) {
            leftButton.setFocused(focused);
        } else {
            if (rightButton != null) {
                rightButton.setFocused(focused);
            }
        }

        if (!focused) {
            if (rightButton != null) {
                rightButton.setFocused(focused);
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="Utility Methods">
    /**
     * Initializes a TitleBarButton instance on the TitleBar
     * @param button TitleBarButton instance to be initialized.
     * @param actionListener ActionListener for the TitleBarButton.
     */
    private void initializeButton(TitleBarButton button, ActionListener actionListener) {
        button.setPadding(this.padding);
        button.setActionListener(actionListener);
    }

    /**
     * Handles the interaction between the buttons of the TitleBar
     * @return True if the horizontal movement was handled.
     */
    private boolean handleHorizontalMovement() {
        if (leftButton != null && leftButton.isFocused()) {
            leftButton.setFocused(false);
            if (rightButton != null) {
                rightButton.setFocused(true);
            }
        } else if (rightButton != null && rightButton.isFocused()) {
            rightButton.setFocused(false);
            if (leftButton != null) {
                leftButton.setFocused(true);
            }
        }
        return true;
    }
    //</editor-fold>
}
