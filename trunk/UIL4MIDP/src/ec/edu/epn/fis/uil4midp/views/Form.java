/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import ec.edu.epn.fis.uil4midp.components.controls.UserControl;
import ec.edu.epn.fis.uil4midp.util.GradientManager;
import java.util.Vector;
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

    public Form(String title) {
        visualComponents = new Vector();

        titleBar = new TitleBar(title);
        titleBar.setPosition(0, 0);
    }

    public void addVisualComponent(VisualComponent visualComponent) {
        this.visualComponents.addElement(visualComponent);
    }

    public void paint(Graphics g) {

        titleBar.paint(g);
        nextY = titleBar.getHeight();
        
        for (int i = 0; i < visualComponents.size(); i++) {
            VisualComponent vc = (VisualComponent)visualComponents.elementAt(i);
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
}

final class TitleBar extends UserControl {

    public static int LEFT_BUTTON = -1;
    public static int RIGHT_BUTTON = 1;

    private String title;
    private TitleBarButton leftButton;
    private TitleBarButton rightButton;

    public TitleBar(String title) {
        this.title = title;
    }

    public void setTitleBarButton(String caption, int buttonPosition) {
        if (buttonPosition == LEFT_BUTTON) {
            leftButton = new TitleBarButton(caption, buttonPosition);
        } else if (buttonPosition == RIGHT_BUTTON) {
            rightButton = new TitleBarButton(caption, buttonPosition);
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
        height = g.getFont().getHeight() + 6;
        GradientManager.paintGradient(g, 0x272926, 0x191c1f, x, y, width, height, GradientManager.VERTICAL);
        g.setColor(0xFFFFFF);
        g.drawString(title, x + (width / 2), y + 3, Graphics.TOP | Graphics.HCENTER);

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

        if (height == 0)
            height = g.getFont().getHeight() + 6;           

        if (width == 0)
            width = g.getFont().stringWidth(label) + 9;

        if (position == TitleBar.RIGHT_BUTTON)
            tx = x - width;
        else
            tx = x;

        GradientManager.paintGradient(g, 0x3b3e39, 0x191c1f, tx, y, width, height, GradientManager.VERTICAL);
        g.setColor(0xFFFFFF);
        g.drawString(label, tx + (width / 2), y + 3, Graphics.TOP | Graphics.HCENTER);
    }

    public String getLabel() {
        return this.label;
    }
}
