package ec.edu.epn.fis.uil4midp.controllers;

import ec.edu.epn.fis.uil4midp.components.controls.UserControl;
import ec.edu.epn.fis.uil4midp.util.GradientManager;
import ec.edu.epn.fis.uil4midp.views.AbstractView;
import java.util.Vector;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class TabsController extends AbstractController {

    private TabBar tabBar;
    private int tabBarHeight;

    public TabsController(int tabBarHeight) {
        this.tabBarHeight = tabBarHeight;

        tabBar = new TabBar();
        tabBar.setPadding(4);
        tabBar.setHeight(tabBarHeight);
    }

    public void addView(AbstractView view, Image icon) {
        view.setWidth(width);

        Tab newTab = new Tab(icon, view);
        tabBar.addTab(newTab);
    }

    public void addController(AbstractController controller, Image icon) {
        controller.setWidth(width);
        controller.setHeight(height - tabBarHeight);

        Tab newTab = new Tab(icon, controller);
        tabBar.addTab(newTab);
    }

    public void paint(Graphics graphics) {
        tabBar.setPosition(0, height - tabBarHeight);
        tabBar.setWidth(width);
        tabBar.paint(graphics);

        // Verificar si se pinta la vista o el controlador del tab.
        Tab selectedTab = tabBar.getSelectedTab();
        if (selectedTab.getView() != null) {
            selectedTab.getView().paint(graphics);
        } else {
            // Pintar el controlador
            selectedTab.getController().paint(graphics);
        }
    }

    public void keyPressed(int action, int keyCode) {
        Tab selectedTab = tabBar.getSelectedTab();
        switch (action) {
            case Canvas.DOWN:
                
                break;
            case Canvas.UP:
                
                break;
            default:
                if (tabBar.isFocused()) {
                    tabBar.keyPressed(action, keyCode);
                } else {
                    if (selectedTab.getView() != null) {
                        selectedTab.getView().keyPressed(action, keyCode);
                    } else {
                        selectedTab.getController().keyPressed(action, keyCode);
                    }
                }
                break;
        }
    }
}

//<editor-fold desc="TabBar Class">
class TabBar extends UserControl {

    private Vector tabs;
    private Tab selectedTab;

    private int nextX;

    public TabBar() {
        tabs = new Vector();
    }

    public TabBar(Tab[] tabs) {
        this();

        if (tabs != null) {
            for (int i = 0; i < tabs.length; i++) {
                tabs[i].setHeight(height);
                this.tabs.addElement(tabs[i]);
            }
        }
    }

    public void paint(Graphics g) {
        int tabWidth = width / tabs.size();

        nextX = x;

        for (int i = 0; i < tabs.size(); i++) {
            Tab t = (Tab)tabs.elementAt(i);

            t.setWidth(tabWidth);
            t.setPosition(nextX, y);
            t.paint(g);

            nextX = nextX + tabWidth;
        }

    }

    public void addTab(Tab tab) {
        if (tabs.isEmpty()) {
            tab.setSelected(true);
            selectedTab = tab;
        }

        tab.setHeight(height);
        tabs.addElement(tab);
    }

    public Tab getSelectedTab() {
        return this.selectedTab;
    }

    public boolean isFocusable() {
        return true;
    }

    public void keyPressed(int action, int keyCode) {
        System.out.println("Action on TabBar");
    }

    public boolean isEmpty() {
        return tabs.isEmpty();
    }
}
//</editor-fold>

//<editor-fold desc="Tab Class">
class Tab extends UserControl {

    private boolean selected;
    private Image icon;

    private AbstractView holdedView;
    private AbstractController holdedController;

    private Tab(Image icon) {
        this.icon = icon;
    }

    public Tab(Image icon, AbstractView view) {
        this(icon);
        this.holdedView = view;
    }

    public Tab(Image icon, AbstractController controller) {
        this(icon);
        this.holdedController = controller;
    }

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

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void keyPressed(int action, int keyCode) {
        switch (action) {
        }

        setSelected(true);
    }

    public AbstractView getView() {
        return this.holdedView;
    }

    public AbstractController getController() {
        return this.holdedController;
    }
}
//</editor-fold>