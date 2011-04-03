package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import javax.microedition.lcdui.Graphics;

public abstract class Dialog extends View {

    public abstract boolean keyPressed(int action, int keyCode);

    public void addVisualComponent(VisualComponent visualComponent) {

    }

    public abstract void paint(Graphics g);
}
