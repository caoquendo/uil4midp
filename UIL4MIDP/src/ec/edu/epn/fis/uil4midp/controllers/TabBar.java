package ec.edu.epn.fis.uil4midp.controllers;

import ec.edu.epn.fis.uil4midp.components.controls.UserControl;
import ec.edu.epn.fis.uil4midp.util.Direction;
import java.util.Vector;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

/**
 * The TabBar is a kind of UserControl which holds several Tab instances. A TabBar
 * is intended to be used only by the TabsController class. The TabBar handles the
 * way the user can change between the views associated to the holded tabs.
 * @author Carlos Andrés Oquendo
 */
final class TabBar extends UserControl {

    private Vector tabs;
    private Tab selectedTab;
    private int selectedTabIndex;
    private TabsController owner;

    //<editor-fold desc="Constructors">
    /**
     * Creates a new TabBar instance with no tabs
     * @param owner TabBarController which holds the TabBar
     */
    public TabBar(TabsController owner) {
        tabs = new Vector();
        this.owner = owner;
    }

    /**
     * Creates a new TabBar instance with the specified tabs
     * @param tabs Tabs to show on the TabBar.
     * @param owner TabBarController which holds the TabBar
     */
    public TabBar(Tab[] tabs, TabsController owner) {
        this(owner);

        if (tabs != null) {
            for (int i = 0; i < tabs.length; i++) {
                this.tabs.addElement(tabs[i]);
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    /**
     * Gets the currently selected tab on the TabBar
     * @return Selected tab on the TabBar.
     */
    public Tab getSelectedTab() {
        return this.selectedTab;
    }

    /**
     * Adds a Tab to the TabBar
     * @param tab Tab to be added to the TabBar.
     */
    public void addTab(Tab tab) {
        if (tabs.isEmpty()) {
            tab.setSelected(true);

            selectedTab = tab;
            selectedTabIndex = 0;
        }

        tabs.addElement(tab);
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
                return handleHorizontalMovement(Direction.LEFT);
            case Canvas.RIGHT:
                return handleHorizontalMovement(Direction.RIGHT);
            case Canvas.FIRE:
                if (selectedTab != null) {
                    if (selectedTab.getView() != null) {
                         if (selectedTab.getView().getActionListener() != null) {
                            selectedTab.getView().getActionListener().execute();
                        }
                    }
                } else {
                    return false;
                }
        }
        return false;

    }

    /**
     * Determines if the TabBar can be focused.
     * @return This method always return True.
     */
    public boolean isFocusable() {
        return true;
    }

    /**
     * Paints the TabBar.
     * @param g Graphics object on which paint.
     */
    public void paint(Graphics g) {
        prepareComponent();

        for (int i = 0; i < tabs.size(); i++) {
            Tab t = (Tab) tabs.elementAt(i);

            t.paint(g);
        }
    }

    /**
     * Prepares the layout of the component.
     */
    public void prepareComponent() {
        if (!layoutSynced) {
            int tabWidth = width / (tabs.isEmpty() ? 1 : tabs.size());

            for (int i = 0; i < tabs.size(); i++) {
                Tab t = (Tab) tabs.elementAt(i);

                t.setPadding(padding);
                t.setWidth(tabWidth);
                t.prepareComponent();

                if (height < t.getHeight()) {
                    height = t.getHeight();
                }
            }

            layoutSynced = true;
        }
    }
    //</editor-fold>

    //<editor-fold desc="Overriden Methods">
    /**
     * Sets the focused status of the TabBar
     * @param focused True if the TabBar must get the focus, else, False. The
     * specified value is replicated to the selected tab.
     */
    public void setFocused(boolean focused) {
        super.setFocused(focused);

        if (selectedTab != null) {
            selectedTab.setFocused(focused);
        }
    }

    /**
     * Sets the position of the TabBar.
     * @param x Coordinate X of the position.
     * @param y Coordinate Y of the position.
     */
    public void setPosition(int x, int y) {
        super.setPosition(x, y);

        int nextX = x;

        for (int i = 0; i < tabs.size(); i++) {
            Tab t = (Tab) tabs.elementAt(i);

            t.setPosition(nextX, y);

            nextX = nextX + t.getWidth();
        }
    }
    //</editor-fold>

    //<editor-fold desc="Utility Methods">
    /**
     * Handles the interaction between the tabs of the TabBar
     * @param direction Direction of the movement. The accepted values are
     * Direction.RIGHT or Direction.LEFT
     * @return True if the horizontal movement was handled.
     */
    private boolean handleHorizontalMovement(int direction) {
        if (direction == Direction.RIGHT) {
            if (selectedTabIndex + 1 < tabs.size()) {
                selectedTabIndex++;
            } else {
                return false;
            }
        } else {
            if (selectedTabIndex - 1 > -1) {
                selectedTabIndex--;
            } else {
                return false;
            }
        }

        selectedTab.setFocused(false);
        selectedTab.setSelected(false);

        selectedTab = (Tab) tabs.elementAt(selectedTabIndex);
        selectedTab.setFocused(true);
        selectedTab.setSelected(true);

        if (selectedTab.getView() != null) {
            if (selectedTab.getView().getActionListener() != null) {
                selectedTab.getView().getActionListener().execute();
            }
        }

        return true;
    }
    //</editor-fold>
}
