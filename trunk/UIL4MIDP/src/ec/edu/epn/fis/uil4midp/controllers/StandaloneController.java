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
public class StandaloneController implements IController {

    private IView holdedView;
    private int width;

    public void addView(IView view) {
        this.holdedView = view;
    }

    public void paint(Graphics g) {
        holdedView.setWidth(width);
        holdedView.paint(g);
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
