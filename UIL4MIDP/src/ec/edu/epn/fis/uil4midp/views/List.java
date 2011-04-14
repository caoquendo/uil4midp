package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.actions.ActionListener;
import ec.edu.epn.fis.uil4midp.components.controls.ListItem;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Image;

public abstract class List extends Form {

    public List(String caption) {
        super(caption);
        super.setMargin(0); // List does not have a margin
        super.setControlSeparation(0); // List does not have ControlSeparation
    }

    public void addListItem(String caption) {
        ListItem li = new ListItem(caption);
        li.setValue(caption);
        li.setPadding(tm.getListitemPadding());
        addVisualComponent(li);
    }

    public void addListItem(String caption, Object value) {
        ListItem li = new ListItem(caption);
        li.setValue(value);
        li.setPadding(tm.getListitemPadding());
        addVisualComponent(li);
    }

    public void addListItem(String caption, String text) {
        ListItem li = new ListItem(caption, text);
        li.setValue(caption);
        li.setPadding(tm.getListitemPadding());
        addVisualComponent(li);
    }

    public void addListItem(String caption, String text, Object value) {
        ListItem li = new ListItem(caption, text);
        li.setValue(value);
        li.setPadding(tm.getListitemPadding());
        addVisualComponent(li);
    }

    public void addListItem(String caption, Image image, boolean isIcon) {
        ListItem li = new ListItem(caption, image, isIcon);
        li.setValue(caption);
        li.setPadding(tm.getListitemPadding());
        addVisualComponent(li);
    }

    public void addListItem(String caption, Image image, boolean isIcon, Object value) {
        ListItem li = new ListItem(caption, image, isIcon);
        li.setValue(value);
        li.setPadding(tm.getListitemPadding());
        addVisualComponent(li);
    }

    public void addListItem(String caption, Image image, String text, boolean isIcon) {
        ListItem li = new ListItem(caption, text, image, isIcon);
        li.setValue(caption);
        li.setPadding(tm.getListitemPadding());
        addVisualComponent(li);
    }

    public void addListItem(String caption, Image image, String text, boolean isIcon, Object value) {
        ListItem li = new ListItem(caption, text, image, isIcon);
        li.setValue(value);
        li.setPadding(tm.getListitemPadding());
        addVisualComponent(li);
    }

    public boolean keyPressed(int action, int keyCode) {
        switch (action) {
            case Canvas.FIRE:
                if (actionListener != null) {
                    actionListener.execute();
                    return true;
                }
                return super.keyPressed(action, keyCode);
            default:
                return super.keyPressed(action, keyCode);
        }
    }

    public abstract void initialize();


    public void clearControls() {
        super.clearControls();
    }

    //<editor-fold desc="Getters & Setters">
    /**
     * Sets the ActionListener of the List. This ActionListener may be used to
     * handle the keypress events based on the elements of the List.
     * @param actionListener ActionListener of the List.
     */
    public void setActionListener(ActionListener actionListener) {
        super.setActionListener(actionListener);
    }

    /**
     * Gets the selected ListItem on the List.
     * @return SelectedListItem on the List.
     */
    public ListItem getSelectedListItem() {
        return (ListItem)getSelectedVisualComponent();
    }

    //</editor-fold>

    public void setMargin(int margin) {
        super.setMargin(0);
    }

    public void setControlSeparation(int controlSeparation) {
        super.setControlSeparation(0);
    }
}

