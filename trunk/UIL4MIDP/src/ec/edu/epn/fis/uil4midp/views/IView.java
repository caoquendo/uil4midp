/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Andrés
 */
public interface IView {
    
    void addVisualComponent(VisualComponent visualComponent);

    void paint(Graphics g);

    public void setWidth(int width);
    
}
