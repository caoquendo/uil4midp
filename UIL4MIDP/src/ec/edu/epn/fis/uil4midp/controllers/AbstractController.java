package ec.edu.epn.fis.uil4midp.controllers;

import ec.edu.epn.fis.uil4midp.components.controls.TextBox;
import ec.edu.epn.fis.uil4midp.ui.Window;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

public abstract class AbstractController implements IController {

    protected int width;
    protected int height;
    protected Window window;
    
    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public Window getWindow() {
        return this.window;
    }

    public void showNativeTextScreen(TextBox uilTextBox) {
        final Command cmdDone = new Command("Done", Command.OK, 0);

        javax.microedition.lcdui.TextBox nativeTextBox = new javax.microedition.lcdui.TextBox(
                uilTextBox.getCaption(),
                uilTextBox.getText(),
                uilTextBox.getMaxLength(),
                uilTextBox.getConstraints()
                );
        nativeTextBox.addCommand(cmdDone);

        nativeTextBox.setCommandListener(new DoneCommandListener(uilTextBox));

        window.getDisplay().setCurrent(nativeTextBox);
    }

    class DoneCommandListener implements CommandListener {

        private TextBox textBox;

        public DoneCommandListener(TextBox textBox) {
            this.textBox = textBox;
        }

        public void commandAction(Command c, Displayable d) {
            textBox.setText(((javax.microedition.lcdui.TextBox)d).getString());
            window.getDisplay().setCurrent(window);
            window.repaint();
        }
    }
}
