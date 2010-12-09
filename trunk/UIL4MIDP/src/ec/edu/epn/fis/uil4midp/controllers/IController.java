/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.edu.epn.fis.uil4midp.controllers;

import ec.edu.epn.fis.uil4midp.views.IView;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Andrés
 */
public interface IController {

    void addView(IView view);

    void paint(Graphics g);

    public void setWidth(int width);
}
