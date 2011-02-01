package ec.edu.epn.fis.uil4midp.components.containers;

import ec.edu.epn.fis.uil4midp.components.VisualComponent;
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

    public void keyPressed(int action, int keyCode) {
        switch (action) {
            case Canvas.DOWN:
                handleVerticalMovement(keyCode, Container.DOWN);
                break;
            case Canvas.UP:
                handleVerticalMovement(keyCode, Container.UP);
                break;
            default:
                if (selectedComponent != null)
                    selectedComponent.keyPressed(action, keyCode);
                break;
        }
    }

    private void handleVerticalMovement(int keyCode, int direction) {
        boolean isComponentFocusable = false;

        if (selectedComponent != null) {
            selectedComponent.setFocused(false);
        }

        while (!isComponentFocusable) {

            if (direction == DOWN) {
                selectedComponentIndex++;
            } else {
                selectedComponentIndex--;
            }

            System.out.println("KeyPressed: " + (direction == DOWN ? "DOWN" : "UP") + ", SelectedComponentIndex: " + selectedComponentIndex);

            try {
                VisualComponent vc = (VisualComponent) visualComponents.elementAt(selectedComponentIndex);

                isComponentFocusable = vc.isFocusable();

                if (isComponentFocusable) {
                    vc.setFocused(true);
                    selectedComponent = vc;
                }
            } catch (Exception e) {
                return;
            }
        }

        //TODO: Component is a container
            /*Container cnt = (Container) visualComponents.elementAt(selectedControlIndex);

        if (cnt.canHandleVerticalMovement(isDown)) {
        cnt.keyPressed(keyCode);
        }*/
    }

    public boolean canHandleVerticalMovement(int direction) {
        switch (direction) {
            case Container.DOWN:
                if (selectedComponentIndex + 1 >= visualComponents.size()) {
                    return false;
                }

                break;
            case Container.UP:
                if (selectedComponentIndex - 1 <= -1) {
                    return false;
                }

                break;
        }
        return true;
    }

    public boolean isFocusable() {
        return true;
    }
}
