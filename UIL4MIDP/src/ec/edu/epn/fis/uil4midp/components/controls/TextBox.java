package ec.edu.epn.fis.uil4midp.components.controls;

import ec.edu.epn.fis.uil4midp.util.FontManager;
import ec.edu.epn.fis.uil4midp.util.GradientManager;
import ec.edu.epn.fis.uil4midp.util.TextManager;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.TextField;

public class TextBox extends UserControl {

    private StringBuffer text;
    private String[] textLines;
    private Label caption;
    private int maxLength = 20;
    private int constraints = TextField.ANY;
    private boolean passwordEnabled = false;
    private char passwordMaskChar = '*';
    private boolean synced = false;
    private Font font = FontManager.getNormalFont();

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

    public TextBox(String caption, int maxLength, boolean enablePasswordMode) {
        this(caption, maxLength, (enablePasswordMode ? TextField.PASSWORD : TextField.ANY));

        this.passwordEnabled = enablePasswordMode;
    }

    public TextBox(String caption, int maxLength, char passwordMaskChar) {
        this(caption, maxLength, TextField.PASSWORD);
        this.passwordEnabled = true;
        this.passwordMaskChar = passwordMaskChar;
    }

    public void paint(Graphics g) {
        int captionHeight = 0;
        if (caption != null) {
            caption.setPosition(x, y);
            caption.setPadding(padding);
            caption.setWidth(width);
            caption.paint(g);

            captionHeight = caption.getHeight();
        }

        int fontHeight = font.getHeight();

        // Get text lines
        if (!synced) {
            textLines = TextManager.getLines(text.toString(), width - 2 * padding, font);
            synced = true;
        }

        int textBoxHeight = (textLines.length == 0 ? fontHeight : textLines.length * fontHeight) + 2 * padding;
        height = textBoxHeight + captionHeight;

        // Paint background
        GradientManager.paintGradient(g, 0xe2e5e4, 0xeceeed, x, y + captionHeight, width, textBoxHeight, GradientManager.VERTICAL);

        // Paint border
        g.setColor(0x272926);
        g.drawRect(x, y + captionHeight, width - 1, textBoxHeight - 1);

        if (isFocused()) {
            // Paint inner border
            g.setColor(0xb6bc3e);
            g.drawRect(x + 1, y + 1 + captionHeight, width - 3, textBoxHeight - 3);
            g.drawRect(x + 2, y + 2 + captionHeight, width - 5, textBoxHeight - 5);
        }

        // Draw text
        g.setColor(0x272926);
        g.setFont(font);

        int[] pos = new int[]{x + padding, y + padding + captionHeight};
        for (int i = 0; i < textLines.length; i++) {
            g.drawString(passwordEnabled ? maskText(textLines[i]) : textLines[i], pos[0], pos[1] + fontHeight * i, Graphics.TOP | Graphics.LEFT);
        }
    }

    public String getText() {
        return text.toString();
    }

    public void setText(String text) {
        this.text = new StringBuffer(text);
        synced = false;
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
        if (text.length() > maxLength) {
            text.setLength(maxLength);
            synced = false;
        }
    }

    public int getMaxLength() {
        return this.maxLength;
    }

    public int getConstraints() {
        return this.constraints;
    }

    public boolean isFocusable() {
        return true;
    }

    public boolean keyPressed(int action, int keyCode) {
        switch (action) {
            case Canvas.FIRE:
                //Launch Native Text Editor.
                getContainer().getView().getController().showNativeTextScreen(this);
                return true;
            default:
                return false;
        }
    }

    private String maskText(String text) {
        StringBuffer sbText = new StringBuffer();

        for (int i = 0; i < text.length(); i++) {
            sbText.append(passwordMaskChar);
        }

        return sbText.toString();
    }
}
