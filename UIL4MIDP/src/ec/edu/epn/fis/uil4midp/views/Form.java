package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.actions.ActionListener;
import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import ec.edu.epn.fis.uil4midp.components.containers.Container;
import ec.edu.epn.fis.uil4midp.components.containers.StackedContainer;
import ec.edu.epn.fis.uil4midp.components.controls.UserControl;
import ec.edu.epn.fis.uil4midp.util.Direction;
import ec.edu.epn.fis.uil4midp.util.GradientManager;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class Form extends AbstractView {

    private StackedContainer baseContainer;
    private int width;
    private int nextX = 0;
    private int nextY = 0;
    private TitleBar titleBar;

    public Form(String title) {
        baseContainer = new StackedContainer();

        titleBar = new TitleBar(title);
        titleBar.setPadding(4);
        titleBar.setPosition(0, 0);

        initializeComponent();
    }

    private void initializeComponent() {
        baseContainer.setView(this);
    }

    public void addVisualComponent(VisualComponent visualComponent) {
        try {
            this.baseContainer.addUserControl((UserControl) visualComponent);
        } catch (Exception e) {
            this.baseContainer.addContainer((Container) visualComponent);
        }
    }

    public void paint(Graphics g) {
        titleBar.paint(g);
        nextY = titleBar.getHeight();

        baseContainer.setPosition(nextX, nextY);
        baseContainer.setWidth(width);
        baseContainer.paint(g);
    }

    public String getTitle() {
        return this.titleBar.getTitle();
    }

    public void setWidth(int width) {
        this.width = width;

        titleBar.setWidth(width);
    }

    public void setLeftButton(String caption, ActionListener actionListener) {
        titleBar.setTitleBarButton(caption, TitleBar.LEFT_BUTTON, actionListener);
    }

    public void setRightButton(String caption, ActionListener actionListener) {
        titleBar.setTitleBarButton(caption, TitleBar.RIGHT_BUTTON, actionListener);
    }

    public void keyPressed(int action, int keyCode) {
        // UP & DOWN: Navigate through controls
        // LEFT & RIGHT: Navigate through UI elements

        switch (action) {
            case Canvas.DOWN:
                handleVerticalMovement(action, keyCode, Direction.DOWN);
                break;
            case Canvas.UP:
                handleVerticalMovement(action, keyCode, Direction.UP);
                break;
            case Canvas.LEFT:
                handleHorizontalMovement(action, keyCode);
                break;
            case Canvas.RIGHT:
                handleHorizontalMovement(action, keyCode);
                break;
            default:
                if (baseContainer.isFocused()) {
                    baseContainer.keyPressed(action, keyCode);
                } else if (titleBar.isFocused()) {
                    titleBar.keyPressed(action, keyCode);
                }
                break;
        }
    }

    private void handleHorizontalMovement(int action, int keyCode) {
        if (titleBar.isFocused()) {
            titleBar.keyPressed(action, keyCode);
        } else {
            baseContainer.keyPressed(action, keyCode);
        }
    }

    private void handleVerticalMovement(int action, int keyCode, int direction) {
        switch (direction) {
            case Direction.DOWN:
                if (titleBar.isFocused()) {
                    if (!baseContainer.isFocused()) {
                        titleBar.setFocused(false);

                        baseContainer.setFocused(true);
                        if (baseContainer.canHandleVerticalMovement(direction)) {
                            baseContainer.keyPressed(action, keyCode);
                        }
                    }
                } else {
                    if (baseContainer.isFocused()) {
                        if (baseContainer.canHandleVerticalMovement(direction)) {
                            baseContainer.keyPressed(action, keyCode);
                        }
                    } else {
                        titleBar.setFocused(true);
                    }
                }
                break;
            case Direction.UP:
                if (titleBar.isFocused()) {
                    if (baseContainer.isFocused()) {
                        //DO NOTHING
                    } else {
                        //DO NOTHING
                    }
                } else {
                    if (baseContainer.isFocused()) {
                        if (baseContainer.canHandleVerticalMovement(direction)) {
                            baseContainer.keyPressed(action, keyCode);

                        } else {
                            titleBar.setFocused(true);
                            baseContainer.setFocused(false);
                        }

                    } else {
                        baseContainer.setFocused(true);
                    }
                }

                break;
        }
    }

    public void setBorder(int border) {
        baseContainer.setBorder(border);
    }

    public void setMargin(int margin) {
        baseContainer.setMargin(margin);
    }

    public boolean canHandleVerticalMovement(int direction) {
        switch (direction) {
            case Direction.DOWN:
                if (titleBar.isFocused()) {
                    if (!baseContainer.isFocused()) {
                        return baseContainer.canHandleVerticalMovement(direction);
                    }
                } else {
                    if (baseContainer.isFocused()) {
                        return baseContainer.canHandleVerticalMovement(direction);
                    } else {
                        return true;
                    }
                }
                break;
            case Direction.UP:
                if (titleBar.isFocused()) {
                    if (!baseContainer.isFocused()) {
                        return baseContainer.canHandleVerticalMovement(direction);
                    }
                } else {
                    if (baseContainer.isFocused()) {
                        return baseContainer.canHandleVerticalMovement(direction);
                    } else {
                        return true;
                    }
                }
                break;
        }
        return false;
    }
}

//<editor-fold desc="TitleBar Class">
final class TitleBar extends UserControl {

    public static final int LEFT_BUTTON = -1;
    public static final int RIGHT_BUTTON = 1;
    private String title;
    private TitleBarButton leftButton;
    private TitleBarButton rightButton;

    public TitleBar(String title) {
        this.title = title;
    }

    public void setTitleBarButton(String caption, int buttonPosition, ActionListener actionListener) {
        if (buttonPosition == LEFT_BUTTON) {
            leftButton = new TitleBarButton(caption, buttonPosition);
            leftButton.setPadding(this.padding);
            leftButton.setActionListener(actionListener);
        } else if (buttonPosition == RIGHT_BUTTON) {
            rightButton = new TitleBarButton(caption, buttonPosition);
            rightButton.setPadding(this.padding);
            rightButton.setActionListener(actionListener);
        }
    }

    public void removeButton(int buttonPosition) {
        if (buttonPosition == LEFT_BUTTON) {
            leftButton = null;
        } else if (buttonPosition == RIGHT_BUTTON) {
            rightButton = null;
        }
    }

    public void paint(Graphics g) {
        // Paint main title bar
        height = g.getFont().getHeight() + padding + padding;

        GradientManager.paintGradient(g, 0x272926, 0x191c1f, x, y, width, height, GradientManager.VERTICAL);

        g.setColor(0xFFFFFF);
        g.drawString(title, x + (width / 2), y + padding, Graphics.TOP | Graphics.HCENTER);

        if (leftButton != null) {
            leftButton.setHeight(height);
            leftButton.setPosition(0, 0);
            leftButton.paint(g);
        }
        if (rightButton != null) {
            rightButton.setHeight(height);
            rightButton.setPosition(width, 0);
            rightButton.paint(g);
        }
    }

    public String getTitle() {
        return this.title;
    }

    public void keyPressed(int action, int keyCode) {
        switch (action) {
            case Canvas.LEFT:
                handleHorizontalMovement();
                break;
            case Canvas.RIGHT:
                handleHorizontalMovement();
                break;
            case Canvas.FIRE:
                if (leftButton.isFocused()) {
                    leftButton.keyPressed(action, keyCode);
                } else if (rightButton.isFocused()) {
                    rightButton.keyPressed(action, keyCode);
                }
                break;
        }
    }

    private void handleHorizontalMovement() {
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
    }

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

    public boolean isFocusable() {
        return true;
    }
}
//</editor-fold>

//<editor-fold desc="TitleBarButton Class">
final class TitleBarButton extends UserControl {

    private String label;
    private int position;
    private ActionListener actionListener;

    public TitleBarButton(String label, int position) {
        this.label = label;
        this.position = position;
    }

    public void paint(Graphics g) {
        int tx;

        if (height == 0) {
            height = g.getFont().getHeight() + padding + padding;
        }

        if (width == 0) {
            width = g.getFont().stringWidth(label) + padding + padding;
        }

        if (position == TitleBar.RIGHT_BUTTON) {
            tx = x - width;
        } else {
            tx = x;
        }

        if (isFocused()) {
            GradientManager.paintGradient(g, 0x4e524c, 0x191c1f, tx, y, width, height, GradientManager.VERTICAL);
        } else {
            GradientManager.paintGradient(g, 0x3b3e39, 0x191c1f, tx, y, width, height, GradientManager.VERTICAL);
        }

        g.setColor(0xFFFFFF);
        g.drawString(label, tx + (width / 2), y + padding, Graphics.TOP | Graphics.HCENTER);
    }

    public String getLabel() {
        return this.label;
    }

    public boolean isFocusable() {
        return true;
    }

    public void keyPressed(int action, int keyCode) {
        switch (action) {
            case Canvas.FIRE:
                if (actionListener != null) {
                    actionListener.execute();
                }
                break;
        }
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }
}
//</editor-fold>

