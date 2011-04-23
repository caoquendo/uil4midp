package ec.edu.epn.fis.uil4midp.components.controls;

import ec.edu.epn.fis.uil4midp.util.FontManager;
import ec.edu.epn.fis.uil4midp.util.GradientManager;
import ec.edu.epn.fis.uil4midp.util.TextManager;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

/**
 * An OptionPicker is a UserControl that allows the user to select a value from
 * a given list of data.
 * @author Carlos Andrés Oquendo
 */
public class OptionPicker extends UserControl {

    private String caption;
    private String[] captionLines;
    private boolean captionSynced;
    private String[] values;
    private int selectedValueIndex = -1;
    private int arrowDimension;

    //<editor-fold desc="Constructors">
    /**
     * Initializes internal fields
     */
    private OptionPicker() {
        font = FontManager.getBoldFont();
        arrowDimension = 12;
        captionSynced = false;
    }

    /**
     * Creates a new OptionPicker instance with the specified Caption. An array
     * of values must be provided using the corresponding setter method.
     * @param caption Text to show in the OptionPicker.
     */
    public OptionPicker(String caption) {
        this();
        this.caption = caption;
    }

    /**
     * Creates a new OptionPicker instance with the specified Caption.
     * @param caption Text to show in the OptionPicker
     * @param values Values to be handled by the OptionPicker
     */
    public OptionPicker(String caption, String[] values) {
        this(caption);

        if (values != null) {
            this.values = values;
            selectedValueIndex = 0;
        }
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    /**
     * Sets the caption of the OptionPicker
     * @param caption Caption of the OptionPicker
     */
    public void setCaption(String caption) {
        this.caption = caption;
        captionSynced = false;
    }

    /**
     * Sets the values to be handled by the OptionPicker
     * @param values Array of String containing the values.
     */
    public void setValues(String[] values) {
        this.values = values;
    }

    /**
     * Gets the selected value on the OptionPicker
     * @return The selected value on the optionPicker. If the OptionPicker does
     * not have a values array, this method returns null.
     */
    public String getSelectedValue() {
        if (values != null || values.length > 0) {
            return values[selectedValueIndex];
        } else {
            return null;
        }
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods Implementations">
    /**
     * Handles the key events.
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     * @return This method returns True if the key event was handled, False, otherwise.
     */
    public boolean keyPressed(int action, int keyCode) {
        switch (action) {
            case Canvas.LEFT:
                if (selectedValueIndex > 0) {
                    selectedValueIndex--;
                } else {
                    selectedValueIndex = values.length - 1;
                }
                return true;
            case Canvas.RIGHT:
                if (selectedValueIndex < values.length - 1) {
                    selectedValueIndex++;
                } else {
                    selectedValueIndex = 0;
                }
                return true;
            default:
                return false;
        }
    }

    /**
     * Determines if the OptionPicker can be focused.
     * @return This method always return True.
     */
    public boolean isFocusable() {
        return true;
    }

    /**
     * Paints the OptionPicker.
     * @param g Graphics object on which paint.
     */
    public void paint(Graphics g) {
        prepareComponent();

        int ty = y - yOffset;

        // Paint background
        int[] back = focused ? tm.getButtonActiveBackground() : tm.getButtonInactiveBackground();
        GradientManager.paintGradient(g, back[0], back[1], x, ty, width, height, GradientManager.VERTICAL);

        // Paint border
        g.setColor(focused ? tm.getButtonActiveBorder() : tm.getButtonInactiveBorder());
        g.drawRect(x, ty, width - 1, height - 1);

        // Draw text
        g.setColor(tm.getPrimaryFontColor());
        g.setFont(font);
        g.drawString(caption, x + width / 2, ty + padding, Graphics.TOP | Graphics.HCENTER);
        g.setFont(FontManager.getNormalFont());
        g.drawString(values[selectedValueIndex], x + width / 2, ty + padding + font.getHeight() + padding, Graphics.TOP | Graphics.HCENTER);

        // Paint buttons
        paintArrows(g);
    }

    /**
     * Prepares the layout of the OptionPicker.
     */
    public void prepareComponent() {
        if (!captionSynced || !layoutSynced) {
            int captionWidth = width - 2 * padding - 2 * arrowDimension;

            captionLines = TextManager.getLines(caption, captionWidth, font);

            height = (captionLines.length + 1) * font.getHeight() + 3 * padding;

            captionSynced = true;
            layoutSynced = true;
        }
    }
    //</editor-fold>

    //<editor-fold desc="Utility Methods">
    /**
     * Paints the arrows of the OptionPicker
     * @param g Graphics object on which paint
     * @param size Size of the arrow.
     */
    private void paintArrows(Graphics g) {
        int deltaSize = arrowDimension / 2;
        int deltaHeight = height / 2;

        int x0, x1;
        int y0, y1, y2;

        x0 = x + padding;
        x1 = x0 + deltaSize;
        y0 = y + deltaHeight;
        y1 = y0 - deltaSize;
        y2 = y0 + deltaSize;

        g.fillTriangle(x0, y0, x1, y1, x1, y2);

        int startX = x + padding + width;

        g.fillTriangle(startX - x0, y0, startX - x1, y1, startX - x1, y2);
    }
    //</editor-fold>
}
