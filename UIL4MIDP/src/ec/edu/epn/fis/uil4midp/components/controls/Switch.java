package ec.edu.epn.fis.uil4midp.components.controls;

import ec.edu.epn.fis.uil4midp.util.FontManager;
import ec.edu.epn.fis.uil4midp.util.GradientManager;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class Switch extends UserControl {

    private Label caption;
    private boolean turnedOn = false;

    public Switch() {
    }

    public void paint(Graphics g) {
        int fontHeight = FontManager.getNormalFont().getHeight();

        int selectorY;
        int selectorHeight = 8;

        // Paint background
        if (turnedOn) {
            GradientManager.paintGradient(g, 0xc79f3e, 0xd9be7a, x, y, fontHeight, fontHeight, GradientManager.VERTICAL);
            selectorY = y;
        } else {
            GradientManager.paintGradient(g, 0xe2e5e4, 0xeceeed, x, y, fontHeight, fontHeight, GradientManager.VERTICAL);
            selectorY = y + 20 - selectorHeight;
        }

        // Paint border
        g.setColor(0x272926);
        g.drawRect(x, y, fontHeight - 1, fontHeight - 1);

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
        caption.setPosition(x + padding + fontHeight, y + padding);
        caption.setPadding(this.padding);
        caption.paint(g);

        height = caption.getHeight();
    }

    public String getCaption() {
        return caption.getCaption();
    }

    public void setCaption(String caption) {
        this.caption.setCaption(caption);
    }

    public boolean isFocusable() {
        return true;
    }

    public boolean keyPressed(int action, int keyCode) {
        switch (action) {
            case Canvas.FIRE:
                turnedOn = !turnedOn;
                return true;
        }
        return false;
    }

    public boolean isTurnedOn() {
        return turnedOn;
    }

    public void setTurnedOn(boolean turnedOn) {
        this.turnedOn = turnedOn;
    }
}
