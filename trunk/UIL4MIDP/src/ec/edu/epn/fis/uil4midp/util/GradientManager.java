package ec.edu.epn.fis.uil4midp.util;

import javax.microedition.lcdui.Graphics;

public class GradientManager {

    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;

    public static void paintGradient(Graphics g, int startColor, int endColor, int left, int top, int width, int height, int orientation) {

        int max = orientation == VERTICAL ? height : width;

        for (int i = 0; i < max; i++) {
            int color = midColor(startColor, endColor, max * (max - 1 - i) / (max - 1), max);

            g.setColor(color);

            if (orientation == VERTICAL) {
                g.drawLine(left, top + i, left + width - 1, top + i);
            } else {
                g.drawLine(left + i, top, left + i, top + height - 1);
            }
        }
    }

    static int midColor(int startColor, int endColor, int prop, int max) {
        int red = (((startColor >> 16) & 0xff) * prop + ((endColor >> 16) & 0xff) * (max - prop)) / max;
        int green = (((startColor >> 8) & 0xff) * prop + ((endColor >> 8) & 0xff) * (max - prop)) / max;
        int blue = (((startColor >> 0) & 0xff) * prop + ((endColor >> 0) & 0xff) * (max - prop)) / max;
        int color = red << 16 | green << 8 | blue;
        return color;
    }
}
