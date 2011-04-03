package ec.edu.epn.fis.uil4midp.components.controls;

import ec.edu.epn.fis.uil4midp.util.FontManager;
import ec.edu.epn.fis.uil4midp.util.GradientManager;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class ListItem extends UserControl {

    private ImageBox image;
    private Label caption;
    private Label text;
    private Object value;
    private int mode;
    private boolean isIcon;

    //<editor-fold desc="Constructors">
    /**
     * Initializes internal fields
     */
    private ListItem() {
        font = FontManager.getNormalFont();
    }

    /**
     * Creates a new ListItem instance with the specified caption.
     * @param caption Text to show in the ListItem.
     */
    public ListItem(String caption) {
        this();
        this.caption = new Label(caption);
        this.caption.setFont(FontManager.getBoldFont());
        mode = 0;
    }

    /**
     * Creates a new ListItem instance with the specified caption and picture.
     * @param caption Text to show in the ListItem.
     * @param image Image to show in the ListItem.
     * @param isIcon Determines the layout of the image in the control. If set to
     * true, the image is trated as an icon.
     */
    public ListItem(String caption, Image image, boolean isIcon) {
        this();
        this.caption = new Label(caption);
        this.caption.setFont(FontManager.getBoldFont());
        this.image = new ImageBox(image);
        this.isIcon = isIcon;
        mode = 1;
    }

    /**
     * Creates a new ListItem instance with the specified caption and text.
     * @param caption Text to show in the ListItem.
     * @param text Additional text to show in the ListItem.
     */
    public ListItem(String caption, String text) {
        this();
        this.caption = new Label(caption);
        this.caption.setFont(FontManager.getBoldFont());
        this.text = new Label(text);
        this.text.setFont(font);
        mode = 2;
    }

    /**
     * Creates a new ListItem instance with the specified caption, text and picture.
     * @param caption Text to show in the ListItem.
     * @param text Additional text to show in the ListItem.
     * @param image Image to show in the ListItem.
     * @param isIcon Determines the layout of the image in the control. If set to
     * true, the image is trated as an icon.
     */
    public ListItem(String caption, String text, Image image, boolean isIcon) {
        this();
        this.caption = new Label(caption);
        this.caption.setFont(FontManager.getBoldFont());
        this.text = new Label(text);
        this.text.setFont(font);
        this.image = new ImageBox(image);
        this.isIcon = isIcon;
        mode = 3;
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    /**
     * Gets the value of the ListItem.
     * @return Value of the ListItem.
     */
    public Object getValue() {
        return value;
    }

    /**
     * Sets the value of the ListItem.
     * @param value Value of the ListItem.
     */
    public void setValue(Object value) {
        this.value = value;
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods Implementations">
    /**
     * Handles the key events.
     * ListItem will not handle any key press event at the moment.
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     * @return This method always returns False.
     */
    public boolean keyPressed(int action, int keyCode) {
        //TODO: Verificar si ListItem debe soportar keyPressed.
        return false;
    }

    /**
     * Determines if the ListItem can be focused.
     * @return This method always return true.
     */
    public boolean isFocusable() {
        return true;
    }

    /**
     * Paints the ListItem.
     * @param g Graphics object on which paint.
     */
    public void paint(Graphics g) {
        prepareComponent();

        // Pintar fondo
        if (isFocused()) {
            GradientManager.paintGradient(g, 0xb6bc3e, 0x80852c, x, y, width, height, GradientManager.VERTICAL);
        } else {
            g.setColor(0xFFFFFF);
            g.fillRect(x, y, width, height);
        }

        // Paint List Item Elements
        g.setColor(0x272926);

        caption.paint(g);

        if (image != null) {
            image.paint(g);
        }

        if (text != null) {
            text.paint(g);
        }

        // Pintar línea divisoria
        g.setColor(0x5d5f5c);
        g.drawLine(x, y + height - 1, x + width, y + height - 1);
    }

    /**
     * Prepares the layout of the ListItem.
     */
    public void prepareComponent() {
        if (layoutSynced) {
            return;
        }

        caption.setPadding(padding);

        if (text != null) {
            text.setPadding(padding);
        }

        if (image != null) {
            image.setPadding(0);
        }

        switch (mode) {
            case 0: // Caption Only
                caption.setWidth(width);
                caption.setPosition(x, y);
                caption.setPadding(padding);
                caption.prepareComponent();
                height = caption.getHeight();
                break;
            case 1: // Caption, Image
                image.setMaxWidth(width / 4);
                if (isIcon) image.setPadding(padding);
                image.prepareComponent();

                int imageWidth = isIcon ? 2 * padding + image.getActualImageWidth() : padding + image.getActualImageWidth();

                caption.setWidth(width - imageWidth);
                caption.setPadding(padding);
                caption.prepareComponent();

                if (caption.getHeight() > image.getHeight()) {
                    image.setPosition(x + padding, y + caption.getHeight() / 2 - image.getHeight() / 2);
                    caption.setPosition(x + imageWidth, y);
                    height = caption.getHeight();
                } else {
                    image.setPosition(isIcon ? x + padding : x, y);
                    caption.setPosition(x + imageWidth, y + image.getHeight() / 2 - caption.getHeight() / 2);
                    height = image.getHeight();
                }

                break;
            case 2: // Caption, Text
                caption.setWidth(width);
                caption.setPosition(x, y);
                caption.setPadding(padding);
                caption.prepareComponent();

                text.setWidth(width);
                text.setPosition(x, y + caption.getHeight());
                text.setPadding(padding);
                text.prepareComponent();

                height = caption.getHeight() + text.getHeight();
                break;
            case 3: // Caption, Text, Image
                image.setMaxWidth(width / 4);
                if (isIcon) image.setPadding(padding);
                image.prepareComponent();

                int picWidth = isIcon ? 2 * padding + image.getActualImageWidth() : padding + image.getActualImageWidth();

                caption.setWidth(width - picWidth);
                caption.setPadding(padding);
                caption.prepareComponent();

                text.setWidth(width - picWidth);
                text.setPadding(padding);
                text.prepareComponent();

                int dataHeight = caption.getHeight() + text.getHeight();

                if (dataHeight > image.getHeight()) {
                    image.setPosition(x + padding, y + dataHeight / 2 - image.getHeight() / 2);
                    caption.setPosition(x + picWidth, y);
                    text.setPosition(x + picWidth, y + caption.getHeight());
                    height = caption.getHeight() + text.getHeight();
                } else {
                    image.setPosition(isIcon ? x + padding : x, y);
                    caption.setPosition(x + picWidth, y + image.getHeight() / 2 - caption.getHeight());
                    text.setPosition(x + picWidth, y + caption.getHeight());
                    height = image.getHeight();
                }

                break;
        }

        layoutSynced = true;
    }
    //</editor-fold>
}
