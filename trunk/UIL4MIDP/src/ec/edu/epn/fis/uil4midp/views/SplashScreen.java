package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.components.VisualComponent;
import ec.edu.epn.fis.uil4midp.components.containers.StackedContainer;
import ec.edu.epn.fis.uil4midp.components.controls.AnimatedImageBox;
import ec.edu.epn.fis.uil4midp.components.controls.Label;
import ec.edu.epn.fis.uil4midp.util.FramesManager;
import ec.edu.epn.fis.uil4midp.util.GradientManager;
import ec.edu.epn.fis.uil4midp.util.ThemeManager;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class SplashScreen extends View {

    private TitleBar titleBar;
    private boolean progressIndicatorVisible;
    private AnimatedImageBox progressIndicator;
    private StackedContainer baseContainer;
    private int[] backgroundColors = new int[]{tm.getMainBackgroundColor()};
    private int splashScreenDelay = 1000;
    private Image logo;
    private int maxBackgroundHeight;
    private int[] objectsPos;
    private Label lblProgressMessage;

    //<editor-fold desc="Constructors">
    public SplashScreen(String title, Image logo, boolean progressIndicatorVisible) {
        titleBar = new TitleBar(title);
        baseContainer = new StackedContainer();
        this.logo = logo;
        this.progressIndicatorVisible = progressIndicatorVisible;

        if (progressIndicatorVisible) {
            String[] vrs = ThemeManager.getInstance().getProgressAnimationFrames();
            FramesManager frmMgr = new FramesManager(vrs[0], vrs[1], vrs[2], Integer.parseInt(vrs[3]));
            progressIndicator = new AnimatedImageBox(frmMgr, 100);
        }

        initializeComponent();
    }

    public SplashScreen(String title, Image logo, String progressMessage, boolean progressIndicatorVisible) {
        this(title, logo, progressIndicatorVisible);
        if (progressMessage != null || progressMessage.length() > 0){
            lblProgressMessage = new Label(progressMessage);
            lblProgressMessage.setTextAlignment(Label.LABEL_CENTER);
            baseContainer.addVisualComponent(lblProgressMessage);
        }
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods Implementations">
    public final void addVisualComponent(VisualComponent visualComponent) {
        // This method does nothing
    }

    /**
     * Handles the key events.
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     * @return True if the key event was handled, else, False.
     */
    public final boolean keyPressed(int action, int keyCode) {
        return false;
    }

    /**
     * Paints the SplashScreen
     * @param g Graphics object on which paint
     */
    public final void paint(Graphics g) {
        maxBackgroundHeight = progressIndicatorVisible ? viewPortHeight - baseContainer.getHeight() - tm.getViewMargin() : viewPortHeight;

        objectsPos = new int[]{width / 2, maxBackgroundHeight / 2};

        if (backgroundColors.length > 1) {
            // Gradient
            GradientManager.paintGradient(g, backgroundColors[0], backgroundColors[1], 0, 0, width, maxBackgroundHeight, GradientManager.VERTICAL);
        } else {
            // Solid color
            g.setColor(backgroundColors[0]);
            g.fillRect(0, 0, width, maxBackgroundHeight);
        }

        g.drawImage(logo, objectsPos[0], objectsPos[1], Graphics.HCENTER | Graphics.VCENTER);

        // Progress Indicator
        if (progressIndicatorVisible) {
            // Clean the indicator area screen
            g.setColor(0xFFFFFF);
            g.fillRect(0, maxBackgroundHeight, width, viewPortHeight);

            // Paint progressIndicator
            baseContainer.setPosition(0, maxBackgroundHeight);
            baseContainer.paint(g);
        }
        //Title Bar
        titleBar.paint(g);
    }

    /**
     * Initializes some internal fields
     */
    public final void initialize() {
        baseContainer.prepareComponent();
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    public final void setWidth(int width) {
        super.setWidth(width);
        titleBar.setWidth(width);
        baseContainer.setWidth(width);
    }

    /**
     * Sets the background colors for the splash screen.
     * @param backgroundColors Array containing the integer value of the background
     * colors. If there is only one value in the array, the screen will be painted
     * with a solid color. If there are two or more values in the array, the screen
     * will be filled with a gradient between the two first colors.
     */
    public final void setBackgroundColors(int[] backgroundColors) {
        this.backgroundColors = backgroundColors;
    }

    /**
     * Gets the time in millisenconds that the SplashScreen should remain painted.
     * @return Splash screen delay.
     */
    public final int getSplashScreenDelay() {
        return splashScreenDelay;
    }

    /**
     * Sets the time in milliseconds that the SplashScreen should be visible.
     * @param splashScreenDelay Time which the SplashScreen should be visible.
     */
    public final void setSplashScreenDelay(int splashScreenDelay) {
        this.splashScreenDelay = splashScreenDelay;
    }
    //</editor-fold>

    //<editor-fold desc="Utility Methods">
    private void initializeComponent() {
        titleBar.setPadding(tm.getTitlebarPadding());
        titleBar.setPosition(0, 0);
        titleBar.prepareComponent();

        if (progressIndicatorVisible) {
            baseContainer.setWidth(width);
            baseContainer.setView(this);

            baseContainer.addVisualComponent(progressIndicator);
        }
    }

    /**
     * Dismisses the SplashScreen. This method must be called only by the Window
     * class.
     */
    public void close() {
        if (progressIndicatorVisible) {
            progressIndicator.cancelAnimation();
        }
    }
    //</editor-fold>
}
