package ec.edu.epn.fis.uil4midp.components.controls;

import ec.edu.epn.fis.uil4midp.actions.ActionListener;
import ec.edu.epn.fis.uil4midp.util.GradientManager;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class Button extends UserControl {

    private String caption;

    private ActionListener actionListener;

    public Button() {
    }

    public void paint(Graphics g) {
        // Heigth = TopPadding + FontHeight + BottomPadding
        height = g.getFont().getHeight() + padding + padding;

        // Paint background
        if (isFocused()) {
            GradientManager.paintGradient(g, 0xb6bc3e, 0x80852c, x, y, width, height, GradientManager.VERTICAL);
        } else {
            GradientManager.paintGradient(g, 0xeceeed, 0xa7a8a7, x, y, width, height, GradientManager.VERTICAL);
        }

        // Paint border
        g.setColor(0x272926);
        g.drawRect(x, y, width - 1, height - 1);

        // Draw text. TextPosition = (XCenter, Y + TopPadding)
        g.setColor(0x272926);
        g.drawString(caption, x + width / 2, y + padding, Graphics.TOP | Graphics.HCENTER);
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

    public boolean keyPressed(int action, int keyCode) {
        switch (action) {
            case Canvas.FIRE:
                if (actionListener != null) actionListener.execute();
                return true;
            default:
                System.out.println("KeyPressed Handled! > Button > " + getCaption() + " > " + keyCode);
                return false;
        }

    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }
}
