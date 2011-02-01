package ec.edu.epn.fis.uil4midp.components.controls;

import ec.edu.epn.fis.uil4midp.util.GradientManager;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.TextField;

public class TextBox extends UserControl {

    private StringBuffer text;
    private Label caption;
    private int maxLength = 20;
    private int constraints = TextField.ANY;

    public TextBox() {
        text = new StringBuffer("");
    }

    public TextBox(String caption) {
        this();
        
        if (caption.length() > 0) {
            this.caption = new Label(caption.endsWith(":") ? caption : caption + ":");
        }
    }

    public TextBox(String caption, int maxLength, int constraints) {
        this(caption);
        
        if (maxLength > 0) {
            this.maxLength = maxLength;
        }

        this.constraints = constraints;

    }

    public void paint(Graphics g) {
        
        int capHeight = 0;
        if (caption != null) {
            caption.setPosition(x, y);
            caption.setPadding(padding);
            caption.setWidth(width);
            caption.paint(g);

            capHeight = caption.getHeight();
        }

        // Heigth = TopPadding + FontHeight + BottomPadding
        int textBoxHeight = g.getFont().getHeight() + padding + padding;
        height = textBoxHeight + capHeight;
        
        // Paint background
        GradientManager.paintGradient(g, 0xe2e5e4, 0xeceeed, x, y + capHeight, width, textBoxHeight, GradientManager.VERTICAL);

        // Paint border
        g.setColor(0x272926);
        g.drawRect(x, y + capHeight, width - 1, textBoxHeight - 1);

        if (isFocused()) {
            // Paint inner border
            g.setColor(0xb6bc3e);
            g.drawRect(x + 1, y + 1 + capHeight, width - 3, textBoxHeight - 3);
        }

        // Draw text. TextPosition = (XCenter, Y + TopPadding)
        g.setColor(0x272926);
        g.drawString(text.toString(), x + padding, y + padding + capHeight, Graphics.TOP | Graphics.LEFT);
    }

    public String getText() {
        return text.toString();
    }

    public void setText(String text) {
        this.text = new StringBuffer(text);
    }

    public void setCaption(String caption) {
        if (caption.length() > 0) {
            this.caption = new Label(caption);
        } else {
            this.caption = null;
        }
    }

    public String getCaption() {
        return this.caption.getCaption();
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getMaxLength() {
        return this.maxLength;
    }

    public void setConstraints(int constraints) {
        this.constraints = constraints;
    }

    public int getConstraints() {
        return this.constraints;
    }

    public boolean isFocusable() {
        return true;
    }

    public void keyPressed(int action, int keyCode) {
        switch (action) {
            case Canvas.FIRE:
                //Launch editor.
                getContainer().getView().getController().showNativeTextScreen(this);

                break;
            default:
                System.out.println("KeyPressed Captured! > TextBox > " + getText() + " > " + keyCode);
                break;
        }

    }
}
