package ec.edu.epn.fis.uil4midp.components.controls;

import ec.edu.epn.fis.uil4midp.util.FontManager;
import ec.edu.epn.fis.uil4midp.util.GradientManager;
import ec.edu.epn.fis.uil4midp.util.TextManager;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.TextField;

public class TextBox extends UserControl {

    private Label caption;
    private StringBuffer text;
    private String[] textLines;
    private boolean textSynced;
    private int[] textBoxPosition;
    private int textBoxHeight;
    private int maxLength;
    private int constraints;
    private char passwordMask;
    private boolean passwordModeEnabled;

    //<editor-fold desc="Constructors">
    /**
     * Initializes internal fields
     */
    private TextBox() {
        font = FontManager.getNormalFont();
        passwordMask = '*';
        text = new StringBuffer("");
        textSynced = false;
        textBoxPosition = new int[2];
    }

    /**
     * Creates a new TextBox instance.
     * @param caption Caption of the TextBox
     * @param maxLength Maximum text length.
     * @param constraints TextBox behaviour modifiers. The constraints defined
     * on LCDUI TextField class are supported.
     */
    public TextBox(String caption, int maxLength, int constraints) {
        this();

        this.maxLength = maxLength < 0 ? 0 : maxLength;

        if (caption != null && caption.length() > 0) {
            this.caption = new Label(caption);
        }

        this.constraints = constraints;
    }

    /**
     * Creates a new TextBox instance.
     * @param caption Caption of the TextBox
     * @param maxLength Maximum text length.
     * @param passwordInputEnabled Specifies if the TextBox will be used to
     * capture a password.
     */
    public TextBox(String caption, int maxLength, boolean passwordInputEnabled) {
        this(caption, maxLength, passwordInputEnabled ? TextField.PASSWORD : TextField.ANY);
        passwordModeEnabled = passwordInputEnabled;
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    /**
     * Gets the text of the TextBox
     * @return String containing the Text entered on the TextBox.
     */
    public String getText() {
        return text.toString();
    }

    /**
     * Sets the text of the TextBox
     * @param text Text of the TextBox
     */
    public void setText(String text) {
        this.text.setLength(0);
        this.text.append(text);
        textSynced = false;
    }

    /**
     * Gets the caption of the TextBox
     * @return String containing the caption of the TextBox. If the caption was
     * set to null, null is returned.
     */
    public final String getCaption() {
        return caption == null ? null : caption.getCaption();
    }

    /**
     * Gets the maximum length of the text on the textbox.
     * @return Maximum length of the text.
     */
    public final int getMaxLength() {
        return maxLength;
    }

    /**
     * Sets the maximum length of the text on the textbox.
     * @param maxLength Maximum length of the text. If the value is less than 0
     * it is discarded. If the value is less than the current text length, the text
     * will be truncated.
     */
    public final void setMaxLength(int maxLength) {
        if (maxLength > 0) {
            this.maxLength = maxLength;
            if (text.length() > maxLength) {
                text.setLength(maxLength);
                textSynced = false;
            }
        }
    }

    /**
     * Gets the constraints applied to the TextBox.
     * @return Value representing all the LCDUI TextField constraints applied.
     */
    public final int getConstraints() {
        return constraints;
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods Implementation">
    /**
     * Handles the key events.
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     * @return This method returns True if the key event was handled, False, otherwise.
     */
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

    /**
     * Determines if the TextBox can be focused.
     * @return This method always return True.
     */
    public boolean isFocusable() {
        return true;
    }

    /**
     * Paints the TextBox.
     * @param g Graphics object on which paint.
     */
    public void paint(Graphics g) {
        prepareComponent();

        // Draw caption
        if (caption != null){
            caption.setYOffset(yOffset);
            caption.paint(g);
        }

        // Paint textBox background
        GradientManager.paintGradient(g, 0xe2e5e4, 0xeceeed, textBoxPosition[0], textBoxPosition[1] - yOffset, width, textBoxHeight, GradientManager.VERTICAL);

        // Paint border
        g.setColor(0x272926);
        g.drawRect(textBoxPosition[0], textBoxPosition[1] - yOffset, width - 1, textBoxHeight - 1);

        if (isFocused()) {
            // Paint inner border
            g.setColor(0xb6bc3e);
            g.drawRect(textBoxPosition[0] + 1, textBoxPosition[1] - yOffset + 1, width - 3, textBoxHeight - 3);
            g.drawRect(textBoxPosition[0] + 2, textBoxPosition[1] - yOffset + 2, width - 5, textBoxHeight - 5);
        }

        // Draw text
        g.setColor(0x272926);
        int[] pos = new int[]{textBoxPosition[0] + padding, textBoxPosition[1] - yOffset + padding};
        for (int i = 0; i < textLines.length; i++) {
            g.drawString((passwordModeEnabled ? maskText(textLines[i]) : textLines[i]), pos[0], pos[1] + font.getHeight() * i, Graphics.TOP | Graphics.LEFT);
        }
    }

    /**
     * Prepares the layout of the TextBox
     */
    public void prepareComponent() {
        textBoxPosition[0] = x;
        textBoxPosition[1] = y;
        int captionHeight = 0;

        if (caption != null && !layoutSynced) {
            caption.setPosition(x, y);
            caption.setWidth(width);
            caption.setPadding(padding);
            caption.prepareComponent();
        }

        textBoxPosition[1] = y + caption.getHeight();
        captionHeight = caption.getHeight();

        if (!textSynced || !layoutSynced) {
            int textLineWidth = width - 2 * padding;

            textLines = TextManager.getLines(text.toString(), textLineWidth, font);
            textBoxHeight = (textLines.length > 0 ? textLines.length : 1) * font.getHeight() + 2 * padding;

            textSynced = true;
            layoutSynced = true;
        }

        height = captionHeight + textBoxHeight;
    }
    //</editor-fold>

    //<editor-fold desc="Utility Methods">
    /**
     * Hides the actual text using a mask
     * @param text String to be masked
     * @return String containing the masked text.
     */
    private String maskText(String text) {
        StringBuffer sbText = new StringBuffer();

        for (int i = 0; i < text.length(); i++) {
            sbText.append(passwordMask);
        }

        return sbText.toString();
    }
    //</editor-fold>
}
