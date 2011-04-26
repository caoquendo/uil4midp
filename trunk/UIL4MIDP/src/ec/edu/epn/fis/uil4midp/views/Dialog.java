package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.actions.ActionListener;
import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import ec.edu.epn.fis.uil4midp.components.containers.StackedContainer;
import ec.edu.epn.fis.uil4midp.components.controls.Label;
import javax.microedition.lcdui.Graphics;

/**
 * Defines the structure of a Dialog
 * @author Carlos Andrés Oquendo
 */
public abstract class Dialog extends View {

    public static final int DIALOG_OK = 0;
    public static final int DIALOG_YES = 1;
    public static final int DIALOG_NO = 2;
    public static final int DIALOG_UNKNOWN = -1;

    private StackedContainer baseContainer;
    private TitleBar titleBar;
    private Label message;
    private boolean vibrated = false;

    protected int x;
    protected int y;
    protected int dialogResult = DIALOG_UNKNOWN;
    protected boolean dismissed = false;

    //<editor-fold desc="Constructors">
    /**
     * Initializes internal fields
     * @param title Title of the Dialog.
     * @param message Main message of the Dialog
     */
    protected Dialog(String title, String message) {
        this.titleBar = new TitleBar(title);
        this.message = new Label(message);
        initializeComponent();
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    /**
     * Sets the width for the Dialog
     * @param width Width for the Dialog
     */
    public void setWidth(int width) {
        super.setWidth(width);
        titleBar.setWidth(width);
        baseContainer.setWidth(width);
    }

    /**
     * Sets the position on which the Dialog will be shown
     * @param x X-coordinate of the position of the Dialog.
     * @param y Y-coordinate of the position of the Dialog.
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;

        titleBar.setPosition(x, y);
        baseContainer.setPosition(x, y + titleBar.getHeight());
    }

    /**
     * Gets the height of the Dialog
     * @return Actual height of the Dialog.
     */
    public int getHeight() {
        return titleBar.getHeight() + baseContainer.getHeight() + 2 * tm.getViewMargin();
    }

    /**
     * Gets the Dialog result value
     * @return Dialog result value
     */
    public int getDialogResult() {
        return dialogResult;
    }

    /**
     * Checks if the Dialog has been dismissed.
     * @return True if the dialog is dismissed, otherwise, False.
     */
    public boolean isDismissed()
    {
        return dismissed;
    }

    /**
     * Sets the dismissed status of the Dialog.
     * @param dismissed
     */
    public void setDismissed(boolean dismissed) {
        this.dismissed = dismissed;
    }

    /**
     * Gets the action listener associated to the dismiss of the dialog.
     * @return An ActionListener instance. If there is no action listener, null.
     */
    public ActionListener getDismissActionListener() {
        return actionListener;
    }

    /**
     * Sets the ActionListener that will be called when the Dialog is dismissed.
     * @param dismissActionListener ActionListener object.
     */
    public void setDismissActionListener(ActionListener dismissActionListener) {
        this.actionListener = dismissActionListener;
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods Implementation">
    /**
     * Adds a visual component to the Dialog.
     * @param visualComponent VisualComponent to be added to the Dialog.
     */
    public final void addVisualComponent(VisualComponent visualComponent) {
        baseContainer.addVisualComponent(visualComponent);
    }

    /**
     * Paints the Dialog on the screen.
     * @param g Graphics object on which paint.
     */
    public void paint(Graphics g) {
        if (!vibrated){
            getController().getWindow().getDisplay().vibrate(100);
            vibrated = true;
        }

        //Paint a background
        g.setColor(tm.getMainBackgroundColor());
        g.fillRect(x, y, width, getHeight());

        baseContainer.paint(g);
        titleBar.paint(g);
    }
    //</editor-fold>

    //<editor-fold desc="Utility Methods">
    /**
     * Finishes the initialization of the Dialog.
     */
    private void initializeComponent() {
        titleBar.setPadding(tm.getTitlebarPadding());

        baseContainer = new StackedContainer();
        baseContainer.setWidth(width);
        baseContainer.setView(this);
    }

    /**
     * Updates the layout values of the internal components of the Dialog.
     */
    public void refreshLayout() {
        titleBar.prepareComponent();
        baseContainer.prepareComponent();
    }

    /**
     * Initializes additional components of the Dialog.
     */
    public void initialize() {
        baseContainer.addVisualComponent(message);
    }
    //</editor-fold>
}
