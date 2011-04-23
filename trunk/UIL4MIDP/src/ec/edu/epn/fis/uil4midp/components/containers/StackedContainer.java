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
    protected int[] nextPosition = {x, y};
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

        layoutSynced = false;
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
                return handleVerticalMovement(Direction.DOWN);
            case Canvas.UP:
                return handleVerticalMovement(Direction.UP);
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
        if (!layoutSynced) {
            nextPosition[0] = x + margin;
            nextPosition[1] = y + margin;

            for (int i = 0; i < visualComponents.size(); i++) {
                VisualComponent vc = (VisualComponent) visualComponents.elementAt(i);

                vc.setWidth(width - 2 * margin);
                vc.setPosition(nextPosition[0], nextPosition[1]);
                vc.prepareComponent();

                nextPosition[1] = nextPosition[1] + vc.getHeight() + controlSeparation;
                height = height + vc.getHeight() + controlSeparation;
            }

            layoutSynced = true;
        }
    }
    //</editor-fold>

    //<editor-fold desc="Utility Methods">
    /**
     * Handles the vertical movement of the screen.
     * @param direction Movement direction. May be Direction.UP or Direction.DOWN
     * @return True if the vertical movement succeeded, else, False.
     */
    private boolean handleVerticalMovement(byte direction) {
        boolean isComponentFocusable = false;
        boolean keyPressHandled = false;

        while (!isComponentFocusable) {
            try {
                if (direction == Direction.DOWN) {
                    if (selectedComponentIndex + 1 < visualComponents.size()) {
                        selectedComponentIndex++;
                    } else {
                        return false;
                    }
                } else {
                    if (selectedComponentIndex - 1 > -1) {
                        selectedComponentIndex--;
                    } else {
                        return false;
                    }
                }

                VisualComponent vc = (VisualComponent) visualComponents.elementAt(selectedComponentIndex);

                // Quitar el foco del componente actual
                if (selectedComponent != null) {
                    selectedComponent.setFocused(false);
                }

                // Determinar si el control es capaz de recibir el foco. Si el control
                // es capaz, establecerlo como seleccionado y marcar el evento como manejado
                if (isComponentFocusable = vc.isFocusable()) {
                    vc.setFocused(true);
                    selectedComponent = vc;

                    // Key press fue manejado
                    keyPressHandled = true;
                }

                switch (direction) {
                    case Direction.DOWN:
                        if (selectedComponent.getYCoordinate() + selectedComponent.getHeight() > y + viewPortHeight) {
                            controlYOffset = selectedComponent.getYCoordinate() + selectedComponent.getHeight() - y - viewPortHeight;
                        }
                        break;
                    case Direction.UP:
                        if (selectedComponent.getYCoordinate() - selectedComponent.getYOffset() < y) {
                            tOffset = y - selectedComponent.getYCoordinate() + selectedComponent.getYOffset();
                            controlYOffset = controlYOffset - tOffset;
                        }
                        break;
                }

            } catch (Exception e) {
                // Key press no fue manejado
                keyPressHandled = false;
                break;
            }
        }

        return keyPressHandled;
    }
    //</editor-fold>

    //<editor-fold desc="Overriden Methods">
    /**
     * Sets the margin of the Container
     * @param margin Margin of the container. Value must be equal or greater
     * than 0.
     */
    public void setMargin(int margin) {
        super.setMargin(margin);

        nextPosition[0] = x + margin;
        nextPosition[1] = y + margin;

        layoutSynced = false;
    }

    public void setPosition(int x, int y) {
        super.setPosition(x, y);

        nextPosition[0] = x + margin;
        nextPosition[1] = y + margin;

        for (int i = 0; i < visualComponents.size(); i++) {
            VisualComponent vc = (VisualComponent) visualComponents.elementAt(i);

            vc.setPosition(nextPosition[0], nextPosition[1]);

            nextPosition[1] = nextPosition[1] + vc.getHeight() + controlSeparation;
        }
    }
    //</editor-fold>
}
