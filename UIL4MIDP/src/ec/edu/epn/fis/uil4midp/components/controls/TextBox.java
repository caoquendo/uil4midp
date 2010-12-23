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
public class TextBox extends UserControl {

    private String text;

    public TextBox() {
    }

    public void paint(Graphics g) {
        // Heigth = TopPadding + FontHeight + BottomPadding
        height = g.getFont().getHeight() + padding + padding;

        // Paint background
        GradientManager.paintGradient(g, 0xe2e5e4, 0xeceeed, x, y, width, height, GradientManager.VERTICAL);

        // Paint border
        g.setColor(0x272926);
        g.drawRect(x, y, width-1, height-1);

        if (isSelected()) {
            // Paint inner border
            g.setColor(0xb6bc3e);
            g.drawRect(x+1, y+1, width-3, height-3);
        }

        // Draw text. TextPosition = (XCenter, Y + TopPadding)
        g.setColor(0x272926);
        g.drawString(text, x + padding, y + padding, Graphics.TOP | Graphics.LEFT);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
