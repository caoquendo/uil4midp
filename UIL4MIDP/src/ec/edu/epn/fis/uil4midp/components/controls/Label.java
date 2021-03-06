package ec.edu.epn.fis.uil4midp.components.controls;

import ec.edu.epn.fis.uil4midp.util.FontManager;
import ec.edu.epn.fis.uil4midp.util.TextManager;
import javax.microedition.lcdui.Graphics;

/**
 * A Label is a VisualComponent intended to display any text values.
 * @author Carlos Andrés Oquendo
 */
public class Label extends UserControl {

    public static final int LABEL_LEFT = Graphics.LEFT;
    public static final int LABEL_CENTER = Graphics.HCENTER;
    public static final int LABEL_RIGHT = Graphics.RIGHT;
    private String caption = "";
    private String[] captionLines;
    private boolean captionSynced;
    private int textAlignment = LABEL_LEFT;

    //<editor-fold desc="Constructors">
    /**
     * Creates a new Label instance without a text as caption.
     */
    public Label() {
        font = FontManager.getNormalFont();
        captionSynced = false;
    }

    /**
     * Creates a new Label instance with the specified caption.
     * @param caption Text to show in the Label.
     */
    public Label(String caption) {
        this();
        this.caption = caption;
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    /**
     * Gets the caption of the Label
     * @return String containing the caption of the Label
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Sets the caption of the Label
     * @param caption Caption of the Label
     */
    public void setCaption(String caption) {
        this.caption = caption;
        captionSynced = false;
    }

    /**
     * Sets the alignment of the text on the label
     * @param textAlignment Alignment of the text on the label. The available values
     * are Label.LABEL_LEFT, Label.LABEL_CENTER and Label.LABEL_RIGHT.
     */
    public void setTextAlignment(int textAlignment) {
        this.textAlignment = textAlignment;
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods Implementations">
    /**
     * Handles the key events.
     * Label can not handle any key press event.
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     * @return This method always returns False.
     */
    public boolean keyPressed(int action, int keyCode) {
        return false;
    }

    /**
     * Determines if the Label can be focused.
     * @return This method always return False, due to Label can not be focused.
     */
    public boolean isFocusable() {
        return false;
    }

    /**
     * Paints the Label.
     * @param g Graphics object on which paint.
     */
    public void paint(Graphics g) {
        prepareComponent();

        int ty = y - yOffset;

        // Draw text
        g.setColor(tm.getPrimaryFontColor());
        g.setFont(font);

        int[] pos = new int[]{0, ty + padding};
        switch (textAlignment) {
            case LABEL_LEFT:
                pos[0] = x + padding;
                break;
            case LABEL_CENTER:
                pos[0] = width / 2;
                break;
            case LABEL_RIGHT:
                pos[0] = width - padding;
                break;
        }

        for (int i = 0; i < captionLines.length; i++) {
            g.drawString(captionLines[i], pos[0], pos[1] + font.getHeight() * i, Graphics.TOP | textAlignment);
        }
    }

    /**
     * Prepares the layout of the Label.
     */
    public void prepareComponent() {
        if (!captionSynced || !layoutSynced) {
            int captionLineWidth = width - 2 * padding;

            captionLines = TextManager.getLines(caption, captionLineWidth, font);
            height = captionLines.length * font.getHeight() + 2 * padding;

            captionSynced = true;
            layoutSynced = true;
        }
    }
    //</editor-fold>
}
