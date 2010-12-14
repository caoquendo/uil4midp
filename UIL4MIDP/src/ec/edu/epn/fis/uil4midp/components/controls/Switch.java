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
public class Switch extends UserControl {

    private String caption;
    private boolean turnedOn = false;

    private int selectorWidth = 20;

    public Switch() {
    }

    public void paint(Graphics g) {
        // Heigth = TopPadding + FontHeight + BottomPadding
        int tHeight = g.getFont().getHeight() + padding + padding;
        height = tHeight > 20 ? tHeight : 20;

        int selectorHeight = 8;

        // Paint background
        GradientManager.paintGradient(g, 0xe2e5e4, 0xeceeed, x, y, 20, 20, GradientManager.VERTICAL);

        // Paint border
        g.setColor(0x272926);
        g.drawRect(x, y, 19, 19);

        // Paint selector
        GradientManager.paintGradient(g, 0xeceeed, 0xaaacab, x, y + 20 - selectorHeight, 20, selectorHeight, GradientManager.VERTICAL);

        // Paint selector border
        g.setColor(0x272926);
        g.drawRect(x, y + 20 - selectorHeight, 19, selectorHeight - 1);

        // Draw text. TextPosition = (XCenter, Y + TopPadding)
        g.setColor(0x272926);
        g.drawString(caption, x + selectorWidth + padding, y + padding, Graphics.TOP | Graphics.LEFT);
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
