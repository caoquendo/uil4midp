
package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.components.controls.AnimatedImageBox;
import ec.edu.epn.fis.uil4midp.util.FramesManager;

/**
 * A ProgressDialog is a kind of Dialog which shows a message during the period
 * of time in which a long running task is being performed.
 * @author Carlos Andrés Oquendo
 */
public class ProgressDialog extends Dialog {

    private AnimatedImageBox progressIndicator;

    //<editor-fold desc="Constructors">
    /**
     * Creates a ProgressDialog showing a message. It contains an animation which
     * is shown during a long running task.
     * @param title Title of the Dialog
     * @param message Message to be shown on the Dialog.
     */
    public ProgressDialog(String title, String message) {
        super(title, message);

        String[] vrs = tm.getProgressAnimationFrames();
        FramesManager fm = new FramesManager(vrs[0], vrs[1], vrs[2], Integer.parseInt(vrs[3]));
        progressIndicator = new AnimatedImageBox(fm, 100);
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods Implementations">
    /**
     * Handles the key events.
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     * @return False due to this Dialog does not respond to any key input.
     */
    public final boolean keyPressed(int action, int keyCode) {
        return false;
    }
    //</editor-fold>

    //<editor-fold desc="Dialog Management Methods">
    /**
     * Dismisses the ProgressDialog. This method is intended to be called by a
     * long running task.
     */
    public final void close() {
        progressIndicator.cancelAnimation();
        getController().dismissDialog();
    }

    /**
     * Dismisses the ProgressDialog and allows the user to execute or not the
     * associated ActionListener. This method is intended to be called by a
     * long running task.
     * @param executeActionListener If true is passed, the ActionListener of
     * this Dialog is executed. If false is passed, the ActionListener is not
     * executed.
     */
    public final void close(boolean executeActionListener) {
        progressIndicator.cancelAnimation();
        getController().dismissDialog(executeActionListener);
    }
    //</editor-fold>

    //<editor-fold desc="Overriden Methods">
    /**
     * Initializes additional components of the ConfirmationDialog.
     */
    public final void initialize() {
        super.initialize();
        addVisualComponent(progressIndicator);
    }
    //</editor-fold>
}
