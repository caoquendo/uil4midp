package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.actions.ActionListener;
import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import ec.edu.epn.fis.uil4midp.components.containers.StackedContainer;
import ec.edu.epn.fis.uil4midp.util.Direction;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * A Form is a kind of View which can hold several UserControl instances. It has
 * a Title Bar where a title can be desplayed, as well as two optional TitleBarButtons
 * which can perform custom actions.
 * @author Carlos Andrés Oquendo
 */
public abstract class Form extends View {

    protected StackedContainer baseContainer;
    protected TitleBar titleBar;
    protected ActionListener loadActionListener;
    protected boolean loaded;
    protected int loadDelay = 0;

    //<editor-fold desc="Constructors">
    /**
     * Creates a new instance of Form
     * @param title Title of the form.
     */
    public Form(String title) {
        titleBar = new TitleBar(title);
        initializeComponent();
    }

    /**
     * Creates a new instance of Form and sets the delay for the load event.
     * @param title Title of the form.
     * @param loadDelay Time in milliseconds that must pass before executing the load event.
     */
    public Form(String title, int loadDelay) {
        this(title);
        this.loadDelay = loadDelay;
    }
    //</editor-fold>

    //<editor-fold desc="Content Management Methods">
    /**
     * Removes all the controls from the Form
     */
    public final void clearControls() {
        baseContainer.removeAllVisualComponents();
    }

    /**
     * Sets the view port height available to show content.
     * @param viewPortHeight Available height to show content.
     */
    public final void setViewPortHeight(int viewPortHeight) {
        super.setViewPortHeight(viewPortHeight);
        baseContainer.setViewPortHeight(viewPortHeight - titleBar.getHeight());
    }

    /**
     * Determines if the Form was loaded.
     * @return True if the form was loaded, otherwise, false.
     */
    public final boolean isLoaded() {
        return loaded;
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    /**
     * Sets the width of the Form
     * @param width Width of the Form
     */
    public final void setWidth(int width) {
        super.setWidth(width);
        titleBar.setWidth(width);
        baseContainer.setWidth(width);
    }

    /**
     * Sets the LeftButton of the TitleBar of the Form
     * @param caption Caption to show on the Button
     * @param actionListener ActionListener for the Button
     */
    public final void setLeftButton(String caption, ActionListener actionListener) {
        titleBar.setTitleBarButton(caption, TitleBarButton.LEFT_BUTTON, actionListener);
    }

    /**
     * Sets the LeftButton of the TitleBar of the Form
     * @param icon Icon to show on the Button
     * @param actionListener ActionListener for the Button
     */
    public final void setLeftButton(Image icon, ActionListener actionListener) {
        titleBar.setTitleBarButton(icon, TitleBarButton.LEFT_BUTTON, actionListener);
    }

    /**
     * Sets the RightButton of the TitleBar of the Form
     * @param caption Caption to show on the Button
     * @param actionListener ActionListener for the Button
     */
    public final void setRightButton(String caption, ActionListener actionListener) {
        titleBar.setTitleBarButton(caption, TitleBarButton.RIGHT_BUTTON, actionListener);
    }

    /**
     * Sets the RightButton of the TitleBar of the Form
     * @param icon Icon to show on the Button
     * @param actionListener ActionListener for the Button
     */
    public final void setRightButton(Image icon, ActionListener actionListener) {
        titleBar.setTitleBarButton(icon, TitleBarButton.RIGHT_BUTTON, actionListener);
    }

    /**
     * Sets the margin of the Form
     * @param margin Margin of the Form.
     */
    public void setMargin(int margin) {
        baseContainer.setMargin(margin);
    }

    /**
     * Sets the distance between VisualComponents when drawn on screen.
     * @param controlSeparation Distance between VisualComponents.
     */
    public void setControlSeparation(int controlSeparation) {
        baseContainer.setControlSeparation(controlSeparation);
    }

    /**
     * Gets the currently selected VisualComponent
     * @return VisualComponent selected on the Form.
     */
    public final VisualComponent getSelectedVisualComponent() {
        return baseContainer.getSelectedVisualComponent();
    }

    /**
     * Gets the LoadActionListener of the Form.
     * @return LoadActionListener of the Form
     */
    public final ActionListener getLoadActionListener() {
        return loadActionListener;
    }

    /**
     * Sets the LoadActionListener of the Form. This ActionListener is invoked
     * when the Form is being loaded (before initialization) so it can be initialized
     * without problems. This ActionListener is executed prior to display the form.
     * Also note that this ActionListener can be called to update the data of the form.
     * When writing this ActionListener, make sure to update the 'loaded' field to
     * true if the execution is successful.
     * @param actionListener ActionListener of the Form.
     */
    public final void setLoadActionListener(ActionListener loadActionListener) {
        this.loadActionListener = loadActionListener;
    }

    /**
     * Gets the delay for the load event.
     * @return Time in milliseconds that must pass before invoking the loadActionListener.
     */
    public final int getLoadDelay() {
        return loadDelay;
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods Implementation">
    /**
     * Adds a VisualComponent to the Form.
     * @param visualComponent VisualComponent to show in the Form.
     */
    public final void addVisualComponent(VisualComponent visualComponent) {
        baseContainer.addVisualComponent(visualComponent);
    }

    /**
     * Handles the key events.
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     * @return True if the key event was handled, else, False.
     */
    public boolean keyPressed(int action, int keyCode) {
        switch (action) {
            case Canvas.DOWN:
                return handleVerticalMovement(action, keyCode, Direction.DOWN);
            case Canvas.UP:
                return handleVerticalMovement(action, keyCode, Direction.UP);
            case Canvas.LEFT:
                return handleHorizontalMovement(action, keyCode);
            case Canvas.RIGHT:
                return handleHorizontalMovement(action, keyCode);
            default:
                if (baseContainer.isFocused()) {
                    return baseContainer.keyPressed(action, keyCode);
                } else {
                    return titleBar.keyPressed(action, keyCode);
                }
        }
    }

    /**
     * Paints the Form.
     * @param g Graphics object on which paint.
     */
    public final void paint(Graphics g) {
        baseContainer.prepareComponent();
        baseContainer.paint(g);

        titleBar.paint(g);
    }
    //</editor-fold>

    //<editor-fold desc="Utility Methods">
    /**
     * Initializes the internal fields and the layout of the Form
     */
    private void initializeComponent() {
        titleBar.setPadding(tm.getTitlebarPadding());
        titleBar.setWidth(width);
        titleBar.setPosition(0, 0);
        titleBar.prepareComponent();

        baseContainer = new StackedContainer();
        baseContainer.setPosition(0, titleBar.getHeight());
        baseContainer.setWidth(width);
        baseContainer.setView(this);
        baseContainer.prepareComponent();
    }

    /**
     * Navigate through UI elements horizontally
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     * @return True if the key event was handled, else, False.
     */
    private boolean handleHorizontalMovement(int action, int keyCode) {
        if (titleBar.isFocused()) {
            return titleBar.keyPressed(action, keyCode);
        } else {
            return baseContainer.keyPressed(action, keyCode);
        }
    }

    /**
     * Navigate through controls of the Form.
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     * @param direction Direction of the vertical movement.
     * @return True if the key event was handled, else, False.
     */
    private boolean handleVerticalMovement(int action, int keyCode, int direction) {
        switch (direction) {
            case Direction.DOWN:
                if (titleBar.isFocused()) {
                    if (!baseContainer.isFocused()) {
                        if (titleBar.isFocusable()) {
                            titleBar.setFocused(false);
                        }

                        baseContainer.setFocused(true);
                        return baseContainer.keyPressed(action, keyCode);
                    }
                } else {
                    if (baseContainer.isFocused()) {
                        return baseContainer.keyPressed(action, keyCode);
                    } else {
                        if (titleBar.isFocusable()) {
                            titleBar.setFocused(true);
                        } else {
                            baseContainer.setFocused(true);
                            return baseContainer.keyPressed(action, keyCode);
                        }
                    }
                }
                return true;
            case Direction.UP:
                if (titleBar.isFocused()) { //TODO: Check this implementation
                    if (baseContainer.isFocused()) {
                        //DO NOTHING
                    } else {
                        //DO NOTHING
                    }
                } else {
                    if (baseContainer.isFocused()) {
                        boolean handledByContainer = baseContainer.keyPressed(action, keyCode);

                        if (!handledByContainer) {
                            if (titleBar.isFocusable()) {
                                titleBar.setFocused(true);
                            }

                            baseContainer.setFocused(false);
                        }
                    } else {
                        baseContainer.setFocused(true);
                    }
                }
                return true;
        }
        return false;
    }
    //</editor-fold>

    //<editor-fold desc="UI Methods">
    /**
     * Shows a Dialog box.
     * @param dialog Dialog box instance to be shown.
     */
    public final void showDialog(Dialog dialog) {
        controller.getWindow().setDialog(dialog);
    }
    //</editor-fold>
}
