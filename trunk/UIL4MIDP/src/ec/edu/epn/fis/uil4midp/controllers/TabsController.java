package ec.edu.epn.fis.uil4midp.controllers;

import ec.edu.epn.fis.uil4midp.actions.ActionListener;
import ec.edu.epn.fis.uil4midp.components.controls.UserControl;
import ec.edu.epn.fis.uil4midp.util.GradientManager;
import ec.edu.epn.fis.uil4midp.views.AbstractView;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class TabsController extends AbstractController {

    public void addView(AbstractView view) {
    }

    public void paint(Graphics graphics) {
    }

    public void setWidth(int width) {
    }

    public void keyPressed(int action, int keyCode) {
    }
}

class TabBar extends UserControl {

    private Vector tabs;

    public TabBar() {
        tabs = new Vector();
    }

    public TabBar(Tab[] tabs) {
        this();

        if (tabs != null) {
            for (int i = 0; i < tabs.length; i++) {
                this.tabs.addElement(tabs[i]);
            }
        }
    }

    public void paint(Graphics g) {
        int tabWidth = width / tabs.size();


    }

    public void addTab(Tab tab) {
        tabs.addElement(tab);
    }

    public boolean isFocusable() {
        return true;
    }

    public void keyPressed(int action, int keyCode) {
    }
}

class Tab extends UserControl {

    private boolean selected;
    private Image icon;

    public void paint(Graphics g) {
        // Paint Background
        if (selected) {
            GradientManager.paintGradient(g, 0x847351, 0x1f1a17, x, y, width, height, GradientManager.VERTICAL);
        } else {
            if (focused) {
                GradientManager.paintGradient(g, 0x3d342d, 0x67593e, x, y, width, height, GradientManager.VERTICAL);
            } else {
                GradientManager.paintGradient(g, 0x3d342d, 0x1f1a17, x, y, width, height, GradientManager.VERTICAL);
            }
        }

        // Paint icon
        g.drawImage(icon, x + (width / 2), y + (height / 2), Graphics.HCENTER | Graphics.VCENTER);
    }

    public boolean isFocusable() {
        return true;
    }

    public void keyPressed(int action, int keyCode) {
        //TODO: FIRE

        setSelected(true);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }
}
