package ec.edu.epn.fis.uil4midp.util;

import java.io.IOException;

/**
 * Manages the usage of colors and spaces for all the components of the solution.
 * @author Carlos Andrés Oquendo
 */
public class ThemeManager {

    private static ThemeManager instance;
    // Colors
    private int primaryFontColor;
    private int secondaryFontColor;
    private int invertedFontColor;
    private int mainBackgroundColor;
    private int[] titlebarNormalBackground;
    private int[] titlebarButtonNormalBackground;
    private int[] titlebarButtonHoverBackground;
    private int[] tabbarNormalBackground;
    private int[] tabbarSelectedBackground;
    private int[] tabbarHoverBackground;
    private int[] textboxActiveBackground;
    private int textboxActiveBorder;
    private int textboxActiveBorderinside;
    private int[] textboxInactiveBackground;
    private int textboxInactiveBorder;
    private int[] buttonActiveBackground;
    private int buttonActiveBorder;
    private int[] buttonInactiveBackground;
    private int buttonInactiveBorder;
    private int[] switchActiveBackground;
    private int switchActiveBorder;
    private int[] switchActiveSelector;
    private int[] switchInactiveBackground;
    private int switchInactiveBorder;
    private int[] switchInactiveSelector;
    private int listitemDivider;
    //Dimensions
    private int usercontrolPadding;
    private int listitemPadding;
    private int titlebarPadding;
    private int tabbarPadding;
    private int containerControlSeparation;
    private int viewMargin;
    //Graphic Resources
    private String backIconPath;
    private String okYesIconPath;
    private String cancelNoIconPath;
    private String[] progressAnimationFrames;
    private Properties theme;

    /**
     * Creates an instance of ThemeManager which initializes using the default
     * theme.
     */
    private ThemeManager() {
        // Load default theme
        loadTheme(null);
    }

    /**
     * Loads a theme definition file.
     * @param filename String containing the path of the properties file to be loaded.
     * Is the value passed in this parameter is null, the ThemeManager loads the
     * default theme.
     */
    public void loadTheme(String filename) {
        try {
            theme = new Properties();
            theme.load(filename == null ? "/ec/edu/epn/fis/uil4midp/resources/default.properties" : filename);

            // Colors
            primaryFontColor = convertValue(theme.get("primary-font-color").toString());
            secondaryFontColor = convertValue(theme.get("secondary-font-color").toString());
            invertedFontColor = convertValue(theme.get("inverted-font-color").toString());
            mainBackgroundColor = convertValue(theme.get("main-background-color").toString());
            titlebarNormalBackground = convertValues(theme.get("titlebar-normal-background").toString());
            titlebarButtonNormalBackground = convertValues(theme.get("titlebar-button-normal-background").toString());
            titlebarButtonHoverBackground = convertValues(theme.get("titlebar-button-hover-background").toString());
            tabbarNormalBackground = convertValues(theme.get("tabbar-normal-background").toString());
            tabbarSelectedBackground = convertValues(theme.get("tabbar-selected-background").toString());
            tabbarHoverBackground = convertValues(theme.get("tabbar-hover-background").toString());
            textboxActiveBackground = convertValues(theme.get("textbox-active-background").toString());
            textboxActiveBorder = convertValue(theme.get("textbox-active-border").toString());
            textboxActiveBorderinside = convertValue(theme.get("textbox-active-border-inside").toString());
            textboxInactiveBackground = convertValues(theme.get("textbox-inactive-background").toString());
            textboxInactiveBorder = convertValue(theme.get("textbox-inactive-border").toString());
            buttonActiveBackground = convertValues(theme.get("button-active-background").toString());
            buttonActiveBorder = convertValue(theme.get("button-active-border").toString());
            buttonInactiveBackground = convertValues(theme.get("button-inactive-background").toString());
            buttonInactiveBorder = convertValue(theme.get("button-inactive-border").toString());
            switchActiveBackground = convertValues(theme.get("switch-active-background").toString());
            switchActiveBorder = convertValue(theme.get("switch-active-border").toString());
            switchActiveSelector = convertValues(theme.get("switch-active-selector").toString());
            switchInactiveBackground = convertValues(theme.get("switch-inactive-background").toString());
            switchInactiveBorder = convertValue(theme.get("switch-inactive-border").toString());
            switchInactiveSelector = convertValues(theme.get("switch-inactive-selector").toString());
            listitemDivider = convertValue(theme.get("listitem-divider").toString());

            // Dimensions
            usercontrolPadding = convertValue(theme.get("usercontrol-padding").toString());
            listitemPadding = convertValue(theme.get("listitem-padding").toString());
            titlebarPadding = convertValue(theme.get("titlebar-padding").toString());
            tabbarPadding = convertValue(theme.get("tabbar-padding").toString());
            containerControlSeparation = convertValue(theme.get("container-control-separation").toString());
            viewMargin = convertValue(theme.get("view-margin").toString());

            // Graphic Resources
            backIconPath = theme.get("icon-back").toString();
            okYesIconPath = theme.get("icon-ok-yes").toString();
            cancelNoIconPath = theme.get("icon-cancel-no").toString();
            progressAnimationFrames = prepareAnimationDefinition(theme.get("frames-progress-animation").toString());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Converts a string representing an hexadecimal value (Eg. FFA0B3) to its integer value.
     * @param value String with an hexadecimal value.
     * @return Integer value of String.
     */
    private int convertValue(String value) {
        return Integer.valueOf(value, 16).intValue();
    }

    /**
     * Converts comma separaded string hexadecimal values to an array containing
     * their corresponding integer values.
     * @param value String with comma separated hexadecimal values.
     * @return Array with the integer values of the String.
     */
    private int[] convertValues(String value) {
        String[] values = TextManager.split(value, ',');
        int[] intVals = new int[values.length];

        for (int i = 0; i < values.length; i++) {
            intVals[i] = convertValue(values[i]);
        }

        return intVals;
    }

    /**
     * Parsed the animation definition for the progress animation.
     * @param value Animation definition string value
     * @return Array with tha animation definition values.
     */
    private String[] prepareAnimationDefinition(String value) {
        String[] values = TextManager.split(value, ',');
        String[] finalValues = new String[4];

        try {
            finalValues[0] = 0 < values.length ? values[0] : ""; // Path
            finalValues[1] = 1 < values.length ? values[1] : ""; // File Name Prefix
            finalValues[2] = 2 < values.length ? values[2] : ""; // Extension
            finalValues[3] = 3 < values.length ? values[3] : "0"; // Frames Count

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return finalValues;
    }

    /**
     * Gets an instance of themeManager.
     * @return Instance of ThemeManager.
     */
    public static ThemeManager getInstance() {
        if (instance == null) {
            instance = new ThemeManager();
        }

        return instance;
    }

    public int[] getButtonActiveBackground() {
        return buttonActiveBackground;
    }

    public int getButtonActiveBorder() {
        return buttonActiveBorder;
    }

    public int[] getButtonInactiveBackground() {
        return buttonInactiveBackground;
    }

    public int getButtonInactiveBorder() {
        return buttonInactiveBorder;
    }

    public int getContainerControlSeparation() {
        return containerControlSeparation;
    }

    public int getInvertedFontColor() {
        return invertedFontColor;
    }

    public int getListitemDivider() {
        return listitemDivider;
    }

    public int getListitemPadding() {
        return listitemPadding;
    }

    public int getMainBackgroundColor() {
        return mainBackgroundColor;
    }

    public int getPrimaryFontColor() {
        return primaryFontColor;
    }

    public int getSecondaryFontColor() {
        return secondaryFontColor;
    }

    public int[] getSwitchActiveBackground() {
        return switchActiveBackground;
    }

    public int getSwitchActiveBorder() {
        return switchActiveBorder;
    }

    public int[] getSwitchActiveSelector() {
        return switchActiveSelector;
    }

    public int[] getSwitchInactiveBackground() {
        return switchInactiveBackground;
    }

    public int getSwitchInactiveBorder() {
        return switchInactiveBorder;
    }

    public int[] getSwitchInactiveSelector() {
        return switchInactiveSelector;
    }

    public int[] getTabbarHoverBackground() {
        return tabbarHoverBackground;
    }

    public int[] getTabbarNormalBackground() {
        return tabbarNormalBackground;
    }

    public int getTabbarPadding() {
        return tabbarPadding;
    }

    public int[] getTabbarSelectedBackground() {
        return tabbarSelectedBackground;
    }

    public int[] getTextboxActiveBackground() {
        return textboxActiveBackground;
    }

    public int getTextboxActiveBorder() {
        return textboxActiveBorder;
    }

    public int getTextboxActiveBorderinside() {
        return textboxActiveBorderinside;
    }

    public int[] getTextboxInactiveBackground() {
        return textboxInactiveBackground;
    }

    public int getTextboxInactiveBorder() {
        return textboxInactiveBorder;
    }

    public int[] getTitlebarButtonHoverBackground() {
        return titlebarButtonHoverBackground;
    }

    public int[] getTitlebarButtonNormalBackground() {
        return titlebarButtonNormalBackground;
    }

    public int[] getTitlebarNormalBackground() {
        return titlebarNormalBackground;
    }

    public int getTitlebarPadding() {
        return titlebarPadding;
    }

    public int getUsercontrolPadding() {
        return usercontrolPadding;
    }

    public int getViewMargin() {
        return viewMargin;
    }

    public String getBackIconPath() {
        return backIconPath;
    }

    public String getOkYesIconPath() {
        return okYesIconPath;
    }

    public String getCancelNoIconPath() {
        return cancelNoIconPath;
    }

    public String[] getProgressAnimationFrames() {
        return progressAnimationFrames;
    }
}
