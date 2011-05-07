package ec.edu.epn.fis.uil4midp.controllers;

import ec.edu.epn.fis.uil4midp.ui.Window;
import ec.edu.epn.fis.uil4midp.views.View;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * A TabsController is a kind of Controller which is intended to hold several
 * Views and to provide an easy way to navigate between them using a TabBar, which
 * has a image button dedicated to change to a specific View. This Controller can also
 * hold a Controller, specifically a NavigableController. Adding another TabsController
 * instance may result in inappropriate behaviors and adding a StandaloneController
 * does not represent a useful action.
 * @author Carlos Andrés Oquendo
 */
public class TabsController extends Controller {

    private TabBar tabBar;

    //<editor-fold desc="Constructors">
    /**
     * Creates a new TabsController instance.
     */
    public TabsController(Window window) {
        super(window);
        tabBar = new TabBar(this);
        tabBar.setPadding(tm.getTabbarPadding());
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods Implementations">
    /**
     * Adds a view to the TabsController
     * @param view View to be managed by the TabsController
     * @param icon Image to be used as the icon of the view
     */
    public void addView(View view, Image icon) {
        view.setController(this);
        view.setWidth(width);
        view.initialize();

        Tab newTab = new Tab(icon, view);
        tabBar.addTab(newTab);
    }

    /**
     * Adds a Controller (as a sub controller) to the TabsController
     * @param view Additional controller to be managed by the TabsController
     * @param icon Image to be used as the icon of the Controller
     */
    public void addController(Controller controller, Image icon) {
        controller.setController(this);
        controller.setWidth(width);
        controller.prepareController();

        Tab newTab = new Tab(icon, controller);
        tabBar.addTab(newTab);
    }

    /**
     * Handles the key events.
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     */
    public void keyPressed(int action, int keyCode) {
        if (dialog == null || dialog.isDismissed()) {
            Tab selectedTab = tabBar.getSelectedTab();

            switch (action) {
                case Canvas.DOWN:
                    handleVerticalMovement(action, keyCode, selectedTab);
                    break;
                case Canvas.UP:
                    handleVerticalMovement(action, keyCode, selectedTab);
                    break;
                default:
                    if (tabBar.isFocused()) {
                        keyPressHandled = tabBar.keyPressed(action, keyCode);
                    } else {
                        if (selectedTab.getView() != null) {
                            keyPressHandled = selectedTab.getView().keyPressed(action, keyCode);
                        } else {
                            selectedTab.getController().keyPressed(action, keyCode);
                            keyPressHandled = selectedTab.getController().isKeyPressHandled();
                        }
                    }
                    break;
            }
        } else {
            keyPressHandled = dialog.keyPressed(action, keyCode);
        }
    }

    /**
     * Paints the contents of the Controller.
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
            selectedTab.getController().setViewPortHeight(viewPortHeight);
            selectedTab.getController().paint(g);
        }

        // Pintar el tabBar
        tabBar.paint(g);

        // Mostrar el cuadro de dialogo
        paintDialog(g);
    }

    /**
     * Prepares the layout of the TabsController
     */
    public void prepareController() {
        tabBar.setWidth(width);
        tabBar.prepareComponent();

        if (autoCalcViewPortHeight) {
            viewPortHeight = height - tabBar.getHeight();
        }

        tabBar.setPosition(0, height - tabBar.getHeight());
    }

    /**
     * Gets the view that is being displayed by the Controller
     * @return View that is being displayed by the Controller.
     */
    public View getView() {
        Tab selectedTab = tabBar.getSelectedTab();
        if (selectedTab.getView() != null) {
            return selectedTab.getView();
        } else {
            return selectedTab.getController().getView();
        }
    }
    //</editor-fold>

    //<editor-fold desc="Utility Methods">
    /**
     * Handles the vertical movement on the TabsController.
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     * @param selectedTab Selected Tab on the TabsController
     */
    private void handleVerticalMovement(int action, int keyCode, Tab selectedTab) {
        if (selectedTab.getView() != null) {
            keyPressHandled = selectedTab.getView().keyPressed(action, keyCode);
        } else {
            selectedTab.getController().keyPressed(action, keyCode);
            keyPressHandled = selectedTab.getController().isKeyPressHandled();
        }

        if (tabBar.isFocused()) {
            tabBar.setFocused(false);
        } else {
            if (!keyPressHandled) {
                tabBar.setFocused(true);
            }
        }
    }
    //</editor-fold>
}
