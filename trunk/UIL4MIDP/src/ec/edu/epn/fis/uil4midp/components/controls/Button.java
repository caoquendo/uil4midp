package ec.edu.epn.fis.uil4midp.components.controls;

import ec.edu.epn.fis.uil4midp.actions.ActionListener;
import ec.edu.epn.fis.uil4midp.util.FontManager;
import ec.edu.epn.fis.uil4midp.util.GradientManager;
import ec.edu.epn.fis.uil4midp.util.TextManager;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class Button extends UserControl {

    private String caption;
    private String[] textLines;
    private boolean synced = false;
    private Font font = FontManager.getBoldFont();
    private ActionListener actionListener;

    public Button() {
    }

    public void paint(Graphics g) {
        int fontHeight = font.getHeight();

        // Get text lines
        if (!synced) {
            textLines = TextManager.getLines(caption, width - 2 * padding, font);
            height = (textLines.length * fontHeight) + 2 * padding;
            synced = true;
        }

        // Paint background
        if (isFocused()) {
            GradientManager.paintGradient(g, 0xb6bc3e, 0x80852c, x, y, width, height, GradientManager.VERTICAL);
        } else {
            GradientManager.paintGradient(g, 0xeceeed, 0xa7a8a7, x, y, width, height, GradientManager.VERTICAL);
        }

        // Paint border
        g.setColor(0x272926);
        g.drawRect(x, y, width - 1, height - 1);

        // Draw text
        g.setColor(0x272926);
        g.setFont(font);

        int[] pos = new int[]{x + width / 2, y + padding};
        for (int i = 0; i < textLines.length; i++) {
            g.drawString(textLines[i], pos[0], pos[1] + fontHeight * i, Graphics.TOP | Graphics.HCENTER);
        }
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
        synced = false;
    }

    public boolean isFocusable() {
        return true;
    }

    public boolean keyPressed(int action, int keyCode) {
        switch (action) {
            case Canvas.FIRE:
                if (actionListener != null) {
                    actionListener.execute();
                }
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
