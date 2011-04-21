
package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.components.controls.AnimatedImageBox;
import ec.edu.epn.fis.uil4midp.util.FramesManager;

public class ProgressDialog extends Dialog {

    private AnimatedImageBox progressIndicator;

    public ProgressDialog(String title, String message) {
        super(title, message);

        FramesManager fm = new FramesManager("/ec/edu/epn/fis/uil4midp/resources/", "s", 12);
        progressIndicator = new AnimatedImageBox(fm, 100);
    }

    public boolean keyPressed(int action, int keyCode) {
        return false;
    }

    public void close() {
        progressIndicator.cancelAnimation();
        getController().dismissDialog();
    }

    public void initialize() {
        super.initialize();
        addVisualComponent(progressIndicator);
    }
}
