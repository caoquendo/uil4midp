package ec.edu.epn.fis.uil4midp.util;

import javax.microedition.lcdui.Graphics;

/**
 * Manages the creation of gradients
 * @author Carlos Andrés Oquendo
 */
public class GradientManager {

    /** Defines a Vertical gradient */
    public static final int VERTICAL = 0;
    /** Defines a Horizontal gradient */
    public static final int HORIZONTAL = 1;

    /**
     * Paints a gradient on a defined region
     * @param g Graphic object on which the gradient will be painted
     * @param startColor Start color of the gradient
     * @param endColor End color of the gradient
     * @param x Coordinate X of the position in which the region begins
     * @param y Coordinate Y of the position in which the region begins
     * @param width Width of the region
     * @param height Height of the region
     * @param orientation Orientation of the gradient
     */
    public static void paintGradient(Graphics g, int startColor, int endColor, int x, int y, int width, int height, int orientation) {

        int max = orientation == VERTICAL ? height : width;

        for (int i = 0; i < max; i++) {
            int color = midColor(startColor, endColor, max * (max - 1 - i) / (max - 1), max);

            g.setColor(color);

            if (orientation == VERTICAL) {
                g.drawLine(x, y + i, x + width - 1, y + i);
            } else {
                g.drawLine(x + i, y, x + i, y + height - 1);
            }
        }
    }

    /**
     * Determines a color between two given colors
     * @param firstColor First given color
     * @param secondColor Second given color
     * @param prop
     * @param max Maximum number of steps to create a middle color.
     * @return Color between the two given colors.
     */
    static int midColor(int firstColor, int secondColor, int prop, int max) {
        int red = (((firstColor >> 16) & 0xff) * prop + ((secondColor >> 16) & 0xff) * (max - prop)) / max;
        int green = (((firstColor >> 8) & 0xff) * prop + ((secondColor >> 8) & 0xff) * (max - prop)) / max;
        int blue = (((firstColor >> 0) & 0xff) * prop + ((secondColor >> 0) & 0xff) * (max - prop)) / max;
        int color = red << 16 | green << 8 | blue;
        return color;
    }
}
