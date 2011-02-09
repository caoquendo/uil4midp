package ec.edu.epn.fis.uil4midp.components.containers;

import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import ec.edu.epn.fis.uil4midp.util.Direction;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class StackedContainer extends Container {

    private int nextX;
    private int nextY;
    private int selectedComponentIndex = -1;
    private VisualComponent selectedComponent = null;

    public StackedContainer() {
        super();
    }

    public void paint(Graphics g) {
        super.paint(g);

        // Set the controls position
        nextX = x + margin + border;
        nextY = y + margin + border;

        int controlWidth = width - (2 * margin) - (2 * border);

        VisualComponent vc;

        for (int i = 0; i < visualComponents.size(); i++) {
            vc = (VisualComponent) visualComponents.elementAt(i);
            vc.setPosition(nextX, nextY);
            vc.setWidth(controlWidth);
            vc.paint(g);

            nextY = nextY + margin + border + controlSeparation + vc.getHeight();
        }
    }

    public boolean keyPressed(int action, int keyCode) {
        switch (action) {
            case Canvas.DOWN:
                return handleVerticalMovement(keyCode, Direction.DOWN);
            case Canvas.UP:
                return handleVerticalMovement(keyCode, Direction.UP);
            default:
                if (selectedComponent != null) {
                    return selectedComponent.keyPressed(action, keyCode);
                }
                break;
        }
        return false;
    }

    private boolean handleVerticalMovement(int keyCode, int direction) {
        boolean isComponentFocusable = false;
        boolean keyPressHandled = false;

        while (!isComponentFocusable) {
            System.out.println("KeyPressed: " + (direction == Direction.DOWN ? "DOWN" : "UP") + ", SelectedComponentIndex: " + selectedComponentIndex);

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

                isComponentFocusable = vc.isFocusable();

                if (isComponentFocusable) {
                    vc.setFocused(true);
                    selectedComponent = vc;
                }

                // Key press fue manejado
                keyPressHandled = true;

            } catch (Exception e) { //TODO: Checkout
                // Key press no fue manejado
                keyPressHandled = false;
                break;
            }
        }

        return keyPressHandled;
    }

    public boolean isFocusable() {
        return true;
    }
}
