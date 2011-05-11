package ec.edu.epn.fis.uil4midp.components.containers;

import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import javax.microedition.lcdui.Graphics;

/**
 * A HorizontalSplittedContainer is a VisualComponent which has the ability to hold
 * two different VisualComponents. The objects within this container are laid
 * one next to another in a horizontal way.
 * @author Carlos Andrés Oquendo
 */
public class HorizontalSplittedContainer extends StackedContainer {

    private int leftControlMaxSize;

    //<editor-fold desc="Constructors">
    /**
     * Creates a new HorizontalSplittedContainer instance. This control has no default
     * margin.
     * @param leftControlMaxSize Sets the maximum size for the left control. If -1 is passed, it will occupy the 50% of the available width.
     */
    public HorizontalSplittedContainer(int leftControlMaxSize) {
        super();
        this.leftControlMaxSize = leftControlMaxSize;
        margin = 0;
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods Implementations">
    /**
     * Adds a VisualComponent to the HorizontalSplittedContainer
     * @param visualComponent VisualComponent to be added to the StackedContainer.
     * It is not possible to add a Container. If you add a Container, it will be ignored.
     */
    public void addVisualComponent(VisualComponent visualComponent) {
        if (visualComponent == null) {
            return;
        }

        if (visualComponent.getVisualComponentType() == VisualComponent.TYPE_CONTAINER) {
            return;
        }

        if (visualComponents.size() < 2) {
            visualComponent.setContainer(this);
            visualComponents.addElement(visualComponent);
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
            vc.setYOffset(yOffset);
            vc.paint(g);
        }
    }

    /**
     * Prepares the layout of the StackerContainer
     */
    public void prepareComponent() {
        int[] nextPosition = new int[]{x + margin, y + margin};

        if (visualComponents.size() > 0) {
            VisualComponent lVc = (VisualComponent) visualComponents.elementAt(0);
            VisualComponent rVc = visualComponents.size() > 1 ? (VisualComponent) visualComponents.elementAt(1) : null;

            // Intentar asignar tamaños equitativos a ambos controles.
            // Si hay solo un control, el control ocupa todo el ancho.
            // Si hay dos controles:
            // - Si leftControlMaxSize tiene un valor diferente a -1, asignar ese ancho al control izquierdo.
            // - Si leftControlMaxSize tiene el valor -1, el ancho se reparte entre ambos controles.
            int maxWidth = width - 2 * margin;
            int leftWidth = maxWidth;
            int rightHeight = 0;

            if (rVc != null) {
                leftWidth = leftControlMaxSize == -1 ? (maxWidth - controlSeparation) / 2 : leftControlMaxSize;

                rVc.setWidth(leftControlMaxSize == -1 ? leftWidth : maxWidth - leftWidth - controlSeparation);
                rVc.setPosition(nextPosition[0] + leftWidth + controlSeparation, nextPosition[1]);
                rVc.prepareComponent();

                rightHeight = rVc.getHeight();
            }

            lVc.setWidth(leftWidth);
            lVc.setPosition(nextPosition[0], nextPosition[1]);
            lVc.prepareComponent();

            height = (rightHeight < lVc.getHeight() ? lVc.getHeight() : rightHeight) + controlSeparation;
        }
    }
    //</editor-fold>

    //<editor-fold desc="Overriden Methods">
    /**
     * Sets the position of the HorizontalSplittedContainer.
     * @param x X-coordinate of the Container.
     * @param y Y-coordinate of the Container.
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //</editor-fold>
}
