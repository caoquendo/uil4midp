/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.epn.fis.uil4midp.components;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Andrés
 */
public abstract class VisualComponent {

    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected boolean active = false;

    public abstract void paint(Graphics g);

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
