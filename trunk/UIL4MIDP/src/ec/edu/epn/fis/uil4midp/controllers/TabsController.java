package ec.edu.epn.fis.uil4midp.controllers;

import ec.edu.epn.fis.uil4midp.views.View;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class TabsController extends Controller {

    private TabBar tabBar;

    //<editor-fold desc="Constructors">
    /**
     * Creates a new TabsController instance.
     */
    public TabsController() {
        tabBar = new TabBar(this);
        tabBar.setPadding(4);
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods Implementations">
    /**
     * Adds a view to the TabsController
     * @param view View to be managed by the TabsController
     * @param icon Image to be used as the icon of the view
     */
    public void addView(View view, Image icon) {
        view.setWidth(width);
        view.initialize();

        Tab newTab = new Tab(icon, view);
        tabBar.addTab(newTab);
    }

    /**
     * Handles the key events.
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     */
    public void keyPressed(int action, int keyCode) {
        Tab selectedTab = tabBar.getSelectedTab();

        switch (action) {
            case Canvas.DOWN:
                if (tabBar.isFocused()) {
                    tabBar.setFocused(false);

                    if (selectedTab.getView() != null) {
                        selectedTab.getView().keyPressed(action, keyCode);
                    } else {
                        selectedTab.getController().keyPressed(action, keyCode);
                    }
                } else {
                    if (selectedTab.getView() != null) {
                        if (!selectedTab.getView().keyPressed(action, keyCode)) {
                            tabBar.setFocused(true);
                        }
                    } else {
                        selectedTab.getController().keyPressed(action, keyCode);
                    }
                }
                break;
            case Canvas.UP:
                if (tabBar.isFocused()) {
                    tabBar.setFocused(false);

                    if (selectedTab.getView() != null) {
                        selectedTab.getView().keyPressed(action, keyCode);
                    } else {
                        selectedTab.getController().keyPressed(action, keyCode);
                    }
                } else {
                    tabBar.setFocused(true);
                }
                break;
            case Canvas.LEFT:
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
            case Canvas.RIGHT:
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

    /**
     * Paints the contents of the Controller.
     * <i>This method must be implemented on all the subclasses.</i>
     * @param g Graphics object on which paint.
     */
    public void paint(Graphics g) {
        // Verificar si se pinta la vista o el controlador del tab.
        Tab selectedTab = tabBar.getSelectedTab();
        if (selectedTab.getView() != null) {
            selectedTab.getView().setViewPortHeight(viewPortHeight);
            selectedTab.getView().paint(g);
        } else {
            // Pintar el controlador
            selectedTab.getController().paint(g);
        }

        // Pintar el tabBar
        tabBar.paint(g);
    }

    /**
     * Prepares the layout of the TabsController
     */
    public void prepareController() {
        tabBar.setWidth(width);
        tabBar.prepareComponent();

        viewPortHeight = height - tabBar.getHeight();
        
        tabBar.setPosition(0, height - tabBar.getHeight());
    }
    //</editor-fold>

    //<editor-fold desc="Disabled Optional Methods">
    /*
    public void addController(Controller controller, Image icon) {
    controller.setWidth(width);
    controller.setHeight(height - tabBarHeight);

    Tab newTab = new Tab(icon, controller);
    tabBar.addTab(newTab);
    }
     */
    //</editor-fold>
}
