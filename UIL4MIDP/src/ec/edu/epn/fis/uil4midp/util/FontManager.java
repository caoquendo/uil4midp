package ec.edu.epn.fis.uil4midp.util;

import javax.microedition.lcdui.Font;

/**
 * Manages the use of device Fonts.
 * @author Andrés
 */
public class FontManager {

    private static Font normalFont;
    private static Font italicFont;
    private static Font boldFont;
    private static Font italicBoldFont;
    private static Font underlinedFont;
    private static final int FONT_FACE = Font.FACE_PROPORTIONAL;
    private static final int FONT_SIZE = Font.SIZE_SMALL;

    /**
     * Gets a plain and bold font
     * @return Bold font
     */
    public static Font getBoldFont() {
        if (boldFont == null) {
            boldFont = Font.getFont(FONT_FACE, Font.STYLE_BOLD, FONT_SIZE);
        }

        return boldFont;
    }

    /**
     * Gets a bold and italic font
     * @return Bold and italic font
     */
    public static Font getItalicBoldFont() {
        if (italicBoldFont == null) {
            italicBoldFont = Font.getFont(FONT_FACE, Font.STYLE_ITALIC | Font.STYLE_BOLD, FONT_SIZE);
        }

        return italicBoldFont;
    }

    /**
     * Gets a plain and italic font
     * @return Italic font
     */
    public static Font getItalicFont() {
        if (italicFont == null) {
            italicFont = Font.getFont(FONT_FACE, Font.STYLE_ITALIC, FONT_SIZE);
        }
        
        return italicFont;
    }

    /**
     * Gets the default plain font
     * @return Normal font
     */
    public static Font getNormalFont() {
        if (normalFont == null) {
            normalFont = Font.getFont(FONT_FACE, Font.STYLE_PLAIN, FONT_SIZE);
        }

        return normalFont;
    }

    /**
     * Gets a plain and underlined font
     * @return Underlined font
     */
    public static Font getUnderlinedFont() {
        if (underlinedFont == null) {
            underlinedFont = Font.getFont(FONT_FACE, Font.STYLE_UNDERLINED, FONT_SIZE);
        }

        return underlinedFont;
    }
}
