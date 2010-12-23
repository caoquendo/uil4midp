/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.epn.fis.uil4midp.components.controls;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Andrés
 */
public class Label extends UserControl {

    private String caption;

    public Label(String caption) {
        this.caption = caption;
        this.selectable = false;
    }

    public void paint(Graphics g) {
        // Heigth = TopPadding + FontHeight + BottomPadding
        height = g.getFont().getHeight() + padding + padding;

        // Draw text. TextPosition = (X + LeftPadding, Y + TopPadding)
        g.setColor(0x272926);
        g.drawString(caption, x + padding, y + padding, Graphics.TOP | Graphics.LEFT);
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
