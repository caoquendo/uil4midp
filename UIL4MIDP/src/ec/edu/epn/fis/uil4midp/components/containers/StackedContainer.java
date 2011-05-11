package ec.edu.epn.fis.uil4midp.components.containers;

import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import ec.edu.epn.fis.uil4midp.util.Direction;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

/**
 * A StackedContainer is a VisualComponent which has the ability to
 * hold other VisualComponents. The objects within a StackedContainer
 * instance are able to use all the available screen width and are
 * located one over another.
 * @author Carlos Andrés Oquendo
 */
public class StackedContainer extends Container {

    private VisualComponent selectedComponent;
    private int selectedComponentIndex = -1;
    private int controlYOffset;
    private int tOffset;

    //<editor-fold desc="Constructors">
    /**
     * Creates a new StackedContainer instance.
     */
    public StackedContainer() {
        super();
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods Implementations">
    /**
     * Adds a VisualComponent to the StackedContainer
     * @param visualComponent VisualComponent to be added to the StackedContainer
     */
    public void addVisualComponent(VisualComponent visualComponent) {
        if (visualComponent == null) {
            return;
        }

        visualComponent.setContainer(this);
        visualComponents.addElement(visualComponent);
    }

    /**
     * Gets the currently selected component on the Container
     * @return If there is a selected component, this method will return a
     * VisualComponent's subclass instance, else, null.
     */
    public VisualComponent getSelectedVisualComponent() {
        return selectedComponent;
    }

    /**
     * Handles the key events.
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     * @return True if the key event was handled, else, False.
     */
    public boolean keyPressed(int action, int keyCode) {
        switch (action) {
            case Canvas.DOWN:
                return handleVerticalMovement(Direction.DOWN, action, keyCode);
            case Canvas.UP:
                return handleVerticalMovement(Direction.UP, action, keyCode);
            default:
                return selectedComponent == null ? false : selectedComponent.keyPressed(action, keyCode);
        }
    }

    /**
     * Paints the StackedContainer and its contents.
     * @param g Graphics object on which paint.
     */
    public void paint(Graphics g) {
        prepareComponent();

        for (int i = 0; i < visualComponents.size(); i++) {
            VisualComponent vc = (VisualComponent) visualComponents.elementAt(i);
            vc.setYOffset(controlYOffset);
            vc.paint(g);
        }
    }

    /**
     * Prepares the layout of the StackerContainer
     */
    public void prepareComponent() {
        int[] nextPosition = {x + margin, y + margin};

        height = 0;
        for (int i = 0; i < visualComponents.size(); i++) {
            VisualComponent vc = (VisualComponent) visualComponents.elementAt(i);

            vc.setWidth(width - 2 * margin);
            vc.setPosition(nextPosition[0], nextPosition[1]);
            vc.prepareComponent();

            nextPosition[1] = nextPosition[1] + vc.getHeight() + controlSeparation;
            height = height + vc.getHeight() + controlSeparation;
        }
    }
    //</editor-fold>

    //<editor-fold desc="Utility Methods">
    /**
     * Handles the vertical movement of the screen.
     * @param direction Movement direction. May be Direction.UP or Direction.DOWN
     * @return True if the vertical movement succeeded, else, False.
     */
    private boolean handleVerticalMovement(byte direction, int action, int keyCode) {
        boolean handledByContainer;
        boolean isComponentFocusable = false;
        boolean keyPressHandled = false;

        // Si el componente seleccionado es un Contenedor, pasarle el control.
        // Si el Contenedor no pudo manejar el evento, continuar con el siguiente control
        if (selectedComponent != null && selectedComponent.getVisualComponentType() == VisualComponent.TYPE_CONTAINER) {
            handledByContainer = selectedComponent.keyPressed(action, keyCode);
            if (handledByContainer) {
                return true;
            }
        }

        while (!isComponentFocusable) {
            // Deseleccionar el componente actual
            if (selectedComponent != null) {
                selectedComponent.setFocused(false);
            }

            // Cambiar el índice del componente seleccionado.
            switch (direction) {
                case Direction.DOWN:
                    selectedComponentIndex++;
                    if (selectedComponentIndex >= visualComponents.size()) {
                        selectedComponentIndex = visualComponents.size();
                        return false;
                    }
                    break;
                case Direction.UP:
                    selectedComponentIndex--;
                    if (selectedComponentIndex <= -1) {
                        selectedComponentIndex = -1;
                        return false;
                    }
                    break;
            }

            // Obtener el siguiente control
            VisualComponent vc = (VisualComponent) visualComponents.elementAt(selectedComponentIndex);

            // Determinar si el control puede recibir el foco
            // Si es así, seleccionarlo y marcar el evento como manejado.
            if (isComponentFocusable = vc.isFocusable()) {
                vc.setFocused(true);
                selectedComponent = vc;

                // Si el componente seleccionado es un contenedor, pasarle el evento
                if (vc.getVisualComponentType() == VisualComponent.TYPE_CONTAINER) {
                    keyPressHandled = vc.keyPressed(action, keyCode);
                } else {
                    keyPressHandled = true;
                }
            }

            // Determinar si se debe hacer scrolling.
            switch (direction) {
                case Direction.DOWN:
                    if (vc.getYCoordinate() + vc.getHeight() > y + viewPortHeight) {
                        controlYOffset = vc.getYCoordinate() + vc.getHeight() - y - viewPortHeight;
                    }
                    break;
                case Direction.UP:
                    if (vc.getYCoordinate() - vc.getYOffset() < y) {
                        tOffset = y - vc.getYCoordinate() + vc.getYOffset();
                        controlYOffset = controlYOffset - tOffset;
                    }
                    break;
            }
        }

        return keyPressHandled;
    }
    //</editor-fold>

    //<editor-fold desc="Overriden Methods">
    /**
     * Sets the position of the Container.
     * @param x X-coordinate of the Container.
     * @param y Y-coordinate of the Container.
     */
    public void setPosition(int x, int y) {
        super.setPosition(x, y);

        int[] nextPosition = {x + margin, y + margin};
        
        for (int i = 0; i < visualComponents.size(); i++) {
            VisualComponent vc = (VisualComponent) visualComponents.elementAt(i);

            vc.setPosition(nextPosition[0], nextPosition[1]);

            nextPosition[1] = nextPosition[1] + vc.getHeight() + controlSeparation;
        }
    }

    /**
     * Sets the focus status of the Container
     * @param focused True to set the focus on the VisualComponent, else, False.
     */
    public void setFocused(boolean focused) {
        super.setFocused(focused);
        if (selectedComponent != null) {
            selectedComponent.setFocused(focused);
        }
    }
    //</editor-fold>
}
