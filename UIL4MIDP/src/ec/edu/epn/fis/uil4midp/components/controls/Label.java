package ec.edu.epn.fis.uil4midp.components.controls;

import ec.edu.epn.fis.uil4midp.util.FontManager;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class Label extends UserControl {

    private String caption;
    private Font font = FontManager.getNormalFont();

    public Label(String caption) {
        this.caption = caption;
    }

    public void paint(Graphics g) {
        g.setFont(font);

        // Heigth = TopPadding + FontHeight + BottomPadding
        height = g.getFont().getHeight() + padding + padding;

        // Draw text. TextPosition = (X + LeftPadding, Y + TopPadding)
        g.setColor(0x272926);
        g.drawString(caption, x + padding, y + padding, Graphics.TOP | Graphics.LEFT);

        // Restore the default font
        g.setFont(FontManager.getNormalFont());
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public boolean isFocusable() {
        return false;
    }

    public void keyPressed(int action, int keyCode) {
        return;
    }

    public void setFont(Font font) {
        if (font != null)
            this.font = font;
    }
}
