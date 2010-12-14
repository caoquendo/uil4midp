/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.epn.fis.uil4midp.components.controls;

import ec.edu.epn.fis.uil4midp.util.GradientManager;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Andrés
 */
public class Button extends UserControl {

    private String caption;

    public Button() {
    }

    public void paint(Graphics g) {
        height = g.getFont().getHeight() + 8;

        // Paint background
        GradientManager.paintGradient(g, 0xeceeed, 0xa7a8a7, x, y, width, height, GradientManager.VERTICAL);

        // Paint border
        g.setColor(0x272926);
        g.drawRect(x, y, width-1, height-1);

        // Draw text
        g.setColor(0x272926);
        g.drawString(caption, x + width / 2, y + 4, Graphics.TOP | Graphics.HCENTER);
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
