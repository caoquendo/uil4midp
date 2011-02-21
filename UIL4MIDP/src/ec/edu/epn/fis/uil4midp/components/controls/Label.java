package ec.edu.epn.fis.uil4midp.components.controls;

import ec.edu.epn.fis.uil4midp.util.FontManager;
import ec.edu.epn.fis.uil4midp.util.TextManager;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class Label extends UserControl {

    private String caption;
    private String[] textLines;
    private Font font = FontManager.getNormalFont();
    private boolean synced = false;

    public Label(String caption) {
        this.caption = caption;
    }

    public void paint(Graphics g) {
        int fontHeight = font.getHeight();

        // Get text lines
        if (!synced) {
            textLines = TextManager.getLines(caption, width - 2 * padding, font);
            height = (textLines.length * fontHeight) + 2 * padding;
            synced = true;
        }

        // Draw text
        g.setColor(0x272926);
        g.setFont(font);

        int[] pos = new int[]{x + padding, y + padding};
        for (int i = 0; i < textLines.length; i++) {
            g.drawString(textLines[i], pos[0], pos[1] + fontHeight * i, Graphics.TOP | Graphics.LEFT);
        }
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
        this.synced = false;
    }

    public boolean isFocusable() {
        return false;
    }

    public boolean keyPressed(int action, int keyCode) {
        return false;
    }

    public void setFont(Font font) {
        if (font != null) {
            this.font = font;
        }
    }
}
