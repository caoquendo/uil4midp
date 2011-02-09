package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import javax.microedition.lcdui.Graphics;

public interface IView {

    void addVisualComponent(VisualComponent visualComponent);

    void paint(Graphics g);

    void setWidth(int width);

    boolean keyPressed(int action, int keyCode);

    //boolean canHandleVerticalMovement(int direction);
}
