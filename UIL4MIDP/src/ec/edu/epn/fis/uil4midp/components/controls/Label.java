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
    }

    public void paint(Graphics g) {
        height = g.getFont().getHeight() + 6;

        g.setColor(0x272926);
        g.drawString(caption, x + 3, y + 3, Graphics.TOP | Graphics.LEFT);
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
