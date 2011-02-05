
package ec.edu.epn.fis.uil4midp.controllers;

import ec.edu.epn.fis.uil4midp.views.AbstractView;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

interface IController {

    void addView(AbstractView view, Image icon);

    void paint(Graphics g);

    void setWidth(int width);

    void keyPressed(int action, int keyCode);
    
}
