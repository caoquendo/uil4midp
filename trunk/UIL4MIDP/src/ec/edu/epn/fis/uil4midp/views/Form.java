/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import ec.edu.epn.fis.uil4midp.components.containers.Container;
import ec.edu.epn.fis.uil4midp.components.controls.UserControl;
import ec.edu.epn.fis.uil4midp.util.GradientManager;
import java.util.Vector;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Andrés
 */
public class Form implements IView {

    private Vector visualComponents;
    private int width;
    private int nextX = 0;
    private int nextY = 0;
    private TitleBar titleBar;
    private int selectedControlIndex = 0;

    public Form(String title) {
        visualComponents = new Vector();

        titleBar = new TitleBar(title);
        titleBar.setPadding(3);
        titleBar.setPosition(0, 0);
    }

    public void addVisualComponent(VisualComponent visualComponent) {
        this.visualComponents.addElement(visualComponent);
    }

    public void paint(Graphics g) {

        titleBar.paint(g);
        nextY = titleBar.getHeight();

        for (int i = 0; i < visualComponents.size(); i++) {
            VisualComponent vc = (VisualComponent) visualComponents.elementAt(i);
            vc.setPosition(nextX, nextY);
            vc.setWidth(width);
            vc.paint(g);

            nextY = nextY + vc.getHeight();
        }
    }

    public String getTitle() {
        return this.titleBar.getTitle();
    }

    public void setWidth(int width) {
        this.width = width;

        titleBar.setWidth(width);
    }

    public void setLeftButton(String caption) {
        titleBar.setTitleBarButton(caption, TitleBar.LEFT_BUTTON);
    }

    public void setRightButton(String caption) {
        titleBar.setTitleBarButton(caption, TitleBar.RIGHT_BUTTON);
    }

    public void keyPressed(int keyCode) {
        // UP & DOWN: Navigate through controls
        // LEFT & RIGHT: Navigate through UI elements

        switch (keyCode) {
            case Canvas.DOWN:
                manageVerticalMovement(keyCode, true);
                break;
            case Canvas.UP:
                manageVerticalMovement(keyCode, false);
                break;
            case Canvas.LEFT:
                manageHorizontalDisplacement(keyCode);
                break;
            case Canvas.RIGHT:
                manageHorizontalDisplacement(keyCode);
                break;
        }
    }

    private void manageHorizontalDisplacement(int keyCode) {
        titleBar.setSelected(true);
        titleBar.keyPressed(keyCode);
    }

    private void manageVerticalMovement(int keyCode, boolean isDown) {
        titleBar.setSelected(false);

        try {
            UserControl uc = (UserControl)visualComponents.elementAt(selectedControlIndex);
            uc.setSelected(true);
            selectedControlIndex++;
        } catch (Exception e) {
            // Component is a container
            Container cnt = (Container)visualComponents.elementAt(selectedControlIndex);
            cnt.keyPressed(keyCode);
        }
    }
}

final class TitleBar extends UserControl {

    public static final int LEFT_BUTTON = -1;
    public static final int RIGHT_BUTTON = 1;
    private String title;
    private TitleBarButton leftButton;
    private TitleBarButton rightButton;

    public TitleBar(String title) {
        this.title = title;
    }

    public void setTitleBarButton(String caption, int buttonPosition) {
        if (buttonPosition == LEFT_BUTTON) {
            leftButton = new TitleBarButton(caption, buttonPosition);
            leftButton.setPadding(this.padding);
        } else if (buttonPosition == RIGHT_BUTTON) {
            rightButton = new TitleBarButton(caption, buttonPosition);
            rightButton.setPadding(this.padding);
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

    public void keyPressed(int keyCode) {
        switch(keyCode){
            case Canvas.LEFT:
                if (!leftButton.isSelected() && !rightButton.isSelected())
                    rightButton.setSelected(true);
                else if (leftButton.isSelected()) {
                    leftButton.setSelected(false);
                    rightButton.setSelected(true);
                } else {
                    leftButton.setSelected(true);
                    rightButton.setSelected(false);
                }
                break;
            case Canvas.RIGHT:
                if (!leftButton.isSelected() && !rightButton.isSelected())
                    leftButton.setSelected(true);
                else if (leftButton.isSelected()) {
                    leftButton.setSelected(false);
                    rightButton.setSelected(true);
                } else {
                    leftButton.setSelected(true);
                    rightButton.setSelected(false);
                }
                break;
        }
    }

    public void setSelected(boolean selected) {
        super.setSelected(selected);

        if (!selected) {
            leftButton.setSelected(selected);
            rightButton.setSelected(selected);
        }
    }


}

final class TitleBarButton extends UserControl {

    private String label;
    private int position;

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

        if (isSelected())
            GradientManager.paintGradient(g, 0x4e524c, 0x191c1f, tx, y, width, height, GradientManager.VERTICAL);
        else
            GradientManager.paintGradient(g, 0x3b3e39, 0x191c1f, tx, y, width, height, GradientManager.VERTICAL);

        g.setColor(0xFFFFFF);
        g.drawString(label, tx + (width / 2), y + padding, Graphics.TOP | Graphics.HCENTER);
    }

    public String getLabel() {
        return this.label;
    }
}
