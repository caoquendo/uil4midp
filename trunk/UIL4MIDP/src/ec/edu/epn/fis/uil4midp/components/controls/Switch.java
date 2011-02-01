package ec.edu.epn.fis.uil4midp.components.controls;

import ec.edu.epn.fis.uil4midp.util.GradientManager;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

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

        int selectorY;

        int selectorHeight = 8;

        // Paint background
        if (turnedOn) {
            GradientManager.paintGradient(g, 0xc79f3e, 0xd9be7a, x, y, 20, 20, GradientManager.VERTICAL);
            selectorY = y;
        } else {
            GradientManager.paintGradient(g, 0xe2e5e4, 0xeceeed, x, y, 20, 20, GradientManager.VERTICAL);
            selectorY = y + 20 - selectorHeight;
        }

        // Paint border
        g.setColor(0x272926);
        g.drawRect(x, y, 19, 19);

        // Paint selector
        if (isFocused()) {
            GradientManager.paintGradient(g, 0xb6bc3e, 0x83882d, x, selectorY, 20, selectorHeight, GradientManager.VERTICAL);
        } else {
            GradientManager.paintGradient(g, 0xeceeed, 0xaaacab, x, selectorY, 20, selectorHeight, GradientManager.VERTICAL);
        }

        // Paint selector border
        g.setColor(0x272926);
        g.drawRect(x, selectorY, 19, selectorHeight - 1);

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

    public boolean isFocusable() {
        return true;
    }

    public void keyPressed(int action, int keyCode) {
        switch (action) {
            case Canvas.FIRE:
                turnedOn = !turnedOn;
                break;
        }
    }

    public boolean isTurnedOn() {
        return turnedOn;
    }

    public void setTurnedOn(boolean turnedOn) {
        this.turnedOn = turnedOn;
    }
}
