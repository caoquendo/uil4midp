package ec.edu.epn.fis.uil4midp.components.controls;

import ec.edu.epn.fis.uil4midp.util.GradientManager;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class OptionPicker extends UserControl {

    private String caption;
    private String[] values;
    private int selectedValueIndex = 0;

    public OptionPicker() {
    }

    public void paint(Graphics g) {
        // Heigth = TopPadding + FontHeight + BottomPadding
        int fontHeight = g.getFont().getHeight();
        height =  fontHeight + fontHeight + padding + padding + padding;

        // Paint background
        if (isFocused()) {
            GradientManager.paintGradient(g, 0xb6bc3e, 0x80852c, x, y, width, height, GradientManager.VERTICAL);
        } else {
            GradientManager.paintGradient(g, 0xeceeed, 0xa7a8a7, x, y, width, height, GradientManager.VERTICAL);
        }

        // Paint border
        g.setColor(0x272926);
        g.drawRect(x, y, width - 1, height - 1);

        // Draw text. TextPosition = (XCenter, Y + TopPadding)
        g.setColor(0x272926);
        g.drawString(caption, x + width / 2, y + padding, Graphics.TOP | Graphics.HCENTER);
        g.drawString(values[selectedValueIndex], x + width / 2, y + padding + fontHeight + padding, Graphics.TOP | Graphics.HCENTER);

        // Paint buttons
        paintButtons(g);
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    private void paintButtons(Graphics g) {
        int bWidth = 26;

        // Paint backgrounds
        //GradientManager.paintGradient(g, 0xeceeed, 0xa7a8a7, x, y, bWidth, height, GradientManager.VERTICAL);
        //GradientManager.paintGradient(g, 0xeceeed, 0xa7a8a7, width - bWidth + x, y, bWidth, height, GradientManager.VERTICAL);

        // Paint borders
        //g.setColor(0x272926);
        //g.drawRect(x, y, bWidth - 1, height - 1);
        //g.drawRect(width - bWidth + x, y, bWidth - 1, height - 1);

        // Paint arrows
        int tx1, tx2;
        int ty1, ty2, ty3;
        tx1 = bWidth / 4;
        tx2 = tx1 * 3;
        ty1 = height / 4;
        ty2 = ty1 * 2;
        ty3 = ty1 * 3;
        g.fillTriangle(x + tx1, y + ty2, x + tx2, y + ty1, x + tx2, y + ty3);
        g.fillTriangle(width - bWidth + tx1 + x, y + ty1, width - bWidth + tx1 + x, y + ty3, width - bWidth + tx2 + x, y + ty2);
    }

    public boolean isFocusable() {
        return true;
    }

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
        }
        return false;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public String getSelectedValue() {
        if (values != null || values.length > 0) {
            return values[selectedValueIndex];
        } else {
            return null;
        }
    }
}
