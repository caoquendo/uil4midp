package ec.edu.epn.fis.uil4midp.components.containers;

import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import ec.edu.epn.fis.uil4midp.views.View;
import java.util.Vector;

/**
 * Defines the structure of a Container
 * @author Carlos Andrés Oquendo
 */
public abstract class Container extends VisualComponent {

    protected Vector visualComponents;
    protected int margin = tm.getViewMargin();
    protected int controlSeparation = tm.getContainerControlSeparation();
    protected View view;
    protected int viewPortHeight;

    protected Container() {
        visualComponents = new Vector();
    }

    //<editor-fold desc="Abstract Methods">
    /**
     * Gets the currently selected visual component.
     * <i>Each subclass knows how to determine the selected VisualComponent</i>
     * @return Currently selected visual component.
     */
    public abstract VisualComponent getSelectedVisualComponent();
    //</editor-fold>

    //<editor-fold desc="Content Management Methods">
    /**
     * Removes all the holded controls in the Container
     */
    public void removeAllVisualComponents() {
        visualComponents.removeAllElements();
    }
    //</editor-fold>

    //<editor-fold desc="Basic Layout Methods">
    /**
     * Sets the margin of the Container
     * @param margin Margin of the container. Value must be equal or greater
     * than 0.
     */
    public void setMargin(int margin) {
        if (margin >= 0) {
            this.margin = margin;
        }
    }

    /**
     * Sets the distance between VisualComponents when drawn on screen.
     * @param controlSeparation Distance between VisualComponents.
     */
    public void setControlSeparation(int controlSeparation) {
        this.controlSeparation = controlSeparation;
    }

    /**
     * Sets the owner of the Container
     * @param view View instance to which the Container belongs.
     */
    public void setView(View view) {
        this.view = view;
    }

    /**
     * Gets the owner of the Container
     * @return View instance to which the Container belongs.
     */
    public View getView() {
        return this.view;
    }

    /**
     * Sets the height of the available space on screen
     * @param viewPortHeight Height of the available space on screen.
     */
    public void setViewPortHeight(int viewPortHeight) {
        this.viewPortHeight = viewPortHeight;
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods">
    /**
     * Adds a VisualComponent to the Container
     * @param visualComponent VisualComponent to be added to the Container
     */
    public abstract void addVisualComponent(VisualComponent visualComponent);
    //</editor-fold>

    //<editor-fold desc="Abstract Methods Implementations">
    /**
     * Determines if the Container can be focused.
     * @return This method always return True. This method may be overriden by
     * the implementations.
     */
    public boolean isFocusable() {
        return true;
    }
    //</editor-fold>
}
