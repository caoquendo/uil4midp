package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.actions.ActionListener;
import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import ec.edu.epn.fis.uil4midp.components.containers.StackedContainer;
import ec.edu.epn.fis.uil4midp.components.controls.Label;
import javax.microedition.lcdui.Graphics;

public abstract class Dialog extends View {

    public static final int DIALOG_OK = 0;
    public static final int DIALOG_YES = 1;
    public static final int DIALOG_NO = 2;
    public static final int DIALOG_UNKNOWN = -1;

    private StackedContainer baseContainer;
    private TitleBar titleBar;
    private Label message;

    protected int x;
    protected int y;
    protected int dialogResult = DIALOG_UNKNOWN;
    protected boolean dismissed = false;

    //<editor-fold desc="Constructors">
    protected Dialog(String title, String message) {
        this.titleBar = new TitleBar(title);
        this.message = new Label(message);
        initializeComponent();
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    public void setWidth(int width) {
        super.setWidth(width);
        titleBar.setWidth(width);
        baseContainer.setWidth(width);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;

        titleBar.setPosition(x, y);
        baseContainer.setPosition(x, y + titleBar.getHeight());
    }

    public int getHeight() {
        return titleBar.getHeight() + baseContainer.getHeight() + 2 * tm.getViewMargin();
    }

    public int getDialogResult() {
        return dialogResult;
    }

    public boolean isDismissed()
    {
        return dismissed;
    }

    public void setDismissed(boolean dismissed) {
        this.dismissed = dismissed;
    }

    public ActionListener getDismissActionListener() {
        return actionListener;
    }

    public void setDismissActionListener(ActionListener dismissActionListener) {
        this.actionListener = dismissActionListener;
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods Implementation">
    public final void addVisualComponent(VisualComponent visualComponent) {
        baseContainer.addVisualComponent(visualComponent);
    }

    public abstract boolean keyPressed(int action, int keyCode);

    public void paint(Graphics g) {
        //Paint a background
        g.setColor(tm.getMainBackgroundColor());
        g.fillRect(x, y, width, getHeight());

        baseContainer.paint(g);
        titleBar.paint(g);

        getController().getWindow().getDisplay().vibrate(100);
    }
    //</editor-fold>

    //<editor-fold desc="Utility Methods">
    private void initializeComponent() {
        titleBar.setPadding(tm.getTitlebarPadding());

        baseContainer = new StackedContainer();
        baseContainer.setWidth(width);
        baseContainer.setView(this);
    }

    public void refreshLayout() {
        titleBar.prepareComponent();
        baseContainer.prepareComponent();
    }

    public void initialize() {
        baseContainer.addVisualComponent(message);
    }
    //</editor-fold>
}
