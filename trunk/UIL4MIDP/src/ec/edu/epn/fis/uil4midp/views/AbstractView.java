
package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.controllers.AbstractController;

public abstract class AbstractView implements IView {

    protected AbstractController controller;

    public void setController(AbstractController controller) {
        this.controller = controller;
    }

    public AbstractController getController() {
        return this.controller;
    }

    public abstract boolean keyPressed(int action, int keyCode);
}
