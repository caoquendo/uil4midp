
package ec.edu.epn.fis.uil4midp.util;

import javax.microedition.lcdui.Font;

public class FontManager {

    private static Font normalFont;
    private static Font italicFont;
    private static Font boldFont;
    private static Font italicBoldFont;
    private static Font underlinedFont;

    private static final int TYPE_FACE = Font.FACE_PROPORTIONAL;
    private static final int FONT_SIZE = Font.SIZE_SMALL;

    public static Font getBoldFont() {
        if (boldFont == null)
            boldFont = Font.getFont(TYPE_FACE, Font.STYLE_BOLD, FONT_SIZE);

        return boldFont;
    }

    public static Font getItalicBoldFont() {
        if (italicBoldFont == null)
            italicBoldFont = Font.getFont(TYPE_FACE, Font.STYLE_ITALIC | Font.STYLE_BOLD, FONT_SIZE);

        return italicBoldFont;
    }

    public static Font getItalicFont() {
        if (italicFont == null)
            italicFont = Font.getFont(TYPE_FACE, Font.STYLE_ITALIC, FONT_SIZE);

        return italicFont;
    }

    public static Font getNormalFont() {
        if (normalFont == null)
            normalFont = Font.getFont(TYPE_FACE, Font.STYLE_PLAIN, FONT_SIZE);

        return normalFont;
    }

    public static Font getUnderlinedFont() {
        if (underlinedFont == null)
            underlinedFont = Font.getFont(TYPE_FACE, Font.STYLE_UNDERLINED, FONT_SIZE);

        return underlinedFont;
    }

}
