package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.components.controls.UserControl;
import ec.edu.epn.fis.uil4midp.util.FontManager;
import ec.edu.epn.fis.uil4midp.util.GradientManager;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class List extends Form {

    public List(String caption) {
        super(caption);
    }

    public void addListItem(String caption) {
        ListItem li = new ListItem(caption);
        addVisualComponent(li);
    }

    public void addListItem(String caption, String text) {
        ListItem li = new ListItem(caption, text);
        addVisualComponent(li);
    }

    public void addListItem(String caption, Image image, boolean isIcon) {
        ListItem li = new ListItem(caption, image, isIcon);
        addVisualComponent(li);
    }

    public void addListItem(String caption, Image image, String text, boolean isIcon) {
        ListItem li = new ListItem(caption, text, image, isIcon);
        addVisualComponent(li);
    }
}

//<editor-fold desc="ListItem">
class ListItem extends UserControl {

    private Image image;
    private String caption;
    private String text;
    private int mode = -1;
    private boolean isIcon;

    public ListItem(String caption) {
        this.caption = caption;
        mode = 0;
    }

    public ListItem(String caption, Image image, boolean isIcon) {
        this.caption = caption;
        this.image = image;
        this.isIcon = isIcon;
        mode = 1;
    }

    public ListItem(String caption, String text) {
        this.caption = caption;
        this.text = text;
        mode = 2;
    }

    public ListItem(String caption, String text, Image image, boolean isIcon) {
        this.caption = caption;
        this.text = text;
        this.image = image;
        this.isIcon = isIcon;
        mode = 3;
    }

    public void paint(Graphics g) {
        //TODO: Esto debe setearse desde otro lado
        padding = 3;

        int fontHeight = g.getFont().getHeight();
        int baseHeight = 2 * padding + fontHeight;

        // Determinar dimensiones
        switch (mode) {
            case 0: // Caption only
                height = baseHeight;
                break;
            case 1: // Caption + image
                if (isIcon) {
                    int imageHeight = 2 * padding + image.getHeight();
                    height = baseHeight > imageHeight ? baseHeight : imageHeight;
                } else {
                    height = baseHeight > image.getHeight() ? baseHeight : image.getHeight();
                }
                break;
            case 2: // Caption + text
                height = baseHeight + padding + fontHeight;
                break;
            case 3: // Caption + image + text
                if (isIcon) {
                    int imageHeight = 2 * padding + image.getHeight();
                    height = baseHeight + padding + fontHeight > imageHeight ? baseHeight + padding + fontHeight : imageHeight;
                } else {
                    height = baseHeight + padding + fontHeight > image.getHeight() ? baseHeight + padding + fontHeight : image.getHeight();
                }
                break;
        }

        // Pintar fondo
        if (isFocused()) {
            GradientManager.paintGradient(g, 0xb6bc3e, 0x80852c, x, y, width, height, GradientManager.VERTICAL);
        } else {
            g.setColor(0xFFFFFF);
            g.fillRect(x, y, width, height);
        }

        // Paint List Item Elements
        g.setColor(0x272926);
        g.setFont(FontManager.getBoldFont());

        int delta;
        int fontHeightDelta;

        switch (mode) {
            case 0: // Caption Only
                g.drawString(caption, x + padding, y + padding, Graphics.TOP | Graphics.LEFT);
                break;
            case 1: // Caption + Image
                delta = height / 2;
                fontHeightDelta = fontHeight / 2;
                if (isIcon) { // Icon
                    g.drawImage(image, x + padding, y + delta, Graphics.LEFT | Graphics.VCENTER);
                    g.drawString(caption, x + image.getWidth() + 2 * padding, y + delta - fontHeightDelta, Graphics.TOP | Graphics.LEFT);
                } else { // Big Picture
                    g.drawImage(image, x, y, Graphics.TOP | Graphics.LEFT);
                    g.drawString(caption, x + padding + image.getWidth(), y + delta - fontHeightDelta, Graphics.LEFT | Graphics.TOP);
                }
                break;
            case 2: // Caption + Text
                g.drawString(caption, x + padding, y + padding, Graphics.TOP | Graphics.LEFT);
                g.setFont(FontManager.getNormalFont());
                g.drawString(text, x + padding, y + baseHeight, Graphics.TOP | Graphics.LEFT);
                break;
            case 3: // Caption + Text + Image
                delta = height / 2;
                fontHeightDelta = fontHeight / 2;
                if (isIcon) { // Icon
                    g.drawImage(image, x + padding, y + delta, Graphics.LEFT | Graphics.VCENTER);
                    delta = height / 3;
                    g.drawString(caption, x + image.getWidth() + 2 * padding, y + delta - fontHeightDelta, Graphics.TOP | Graphics.LEFT);
                    g.setFont(FontManager.getNormalFont());
                    g.drawString(text, x + image.getWidth() + 2 * padding, y + 2 * delta - fontHeightDelta, Graphics.TOP | Graphics.LEFT);
                } else { // Big Picture
                    g.drawImage(image, x, y, Graphics.TOP | Graphics.LEFT);
                    delta = height / 3;
                    g.drawString(caption, x + padding + image.getWidth(), y + delta - fontHeightDelta, Graphics.LEFT | Graphics.TOP);
                    g.setFont(FontManager.getNormalFont());
                    g.drawString(text, x + padding + image.getWidth(), y + 2 * delta - fontHeightDelta, Graphics.LEFT | Graphics.TOP);
                }
                break;
        }

        // Pintar línea divisoria
        g.setColor(0x5d5f5c);
        g.drawLine(x, y + height - 1, x + width, y + height - 1);

        g.setFont(FontManager.getNormalFont());
    }

    public boolean isFocusable() {
        return true;
    }

    public boolean keyPressed(int action, int keyCode) {
        return false;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
//</editor-fold>

