package ec.edu.epn.fis.uil4midp.components.controls;

import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import javax.microedition.lcdui.Font;

/**
 * Defines the structure of a UserControl
 * @author Carlos Andrés Oquendo
 */
public abstract class UserControl extends VisualComponent {

    protected int padding;
    protected Font font;

    //<editor-fold desc="Basic Layout Methods">
    /**
     * Sets the Font to be used on the UserControl
     * @param font Font for the UserControl
     */
    public final void setFont(Font font) {
        if (font != null) {
            this.font = font;
            layoutSynced = false;
        }
    }

    /**
     * Sets the padding for the UserControl
     * @param padding Padding for the UserControl
     */
    public void setPadding(int padding) {
        if (padding > 0) {
            this.padding = padding;
            layoutSynced = false;
        }
    }
    //</editor-fold>
}
