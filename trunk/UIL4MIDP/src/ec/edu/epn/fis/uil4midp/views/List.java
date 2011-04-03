package ec.edu.epn.fis.uil4midp.views;

import ec.edu.epn.fis.uil4midp.components.controls.ListItem;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Image;

public abstract class List extends Form {

    public List(String caption) {
        super(caption);
    }

    public void addListItem(String caption) {
        ListItem li = new ListItem(caption);
        li.setValue(caption);
        li.setPadding(3);
        addVisualComponent(li);
    }

    public void addListItem(String caption, Object value) {
        ListItem li = new ListItem(caption);
        li.setValue(value);
        li.setPadding(3);
        addVisualComponent(li);
    }

    public void addListItem(String caption, String text) {
        ListItem li = new ListItem(caption, text);
        li.setValue(caption);
        li.setPadding(3);
        addVisualComponent(li);
    }

    public void addListItem(String caption, String text, Object value) {
        ListItem li = new ListItem(caption, text);
        li.setValue(value);
        li.setPadding(3);
        addVisualComponent(li);
    }

    public void addListItem(String caption, Image image, boolean isIcon) {
        ListItem li = new ListItem(caption, image, isIcon);
        li.setValue(caption);
        li.setPadding(3);
        addVisualComponent(li);
    }

    public void addListItem(String caption, Image image, boolean isIcon, Object value) {
        ListItem li = new ListItem(caption, image, isIcon);
        li.setValue(value);
        li.setPadding(3);
        addVisualComponent(li);
    }

    public void addListItem(String caption, Image image, String text, boolean isIcon) {
        ListItem li = new ListItem(caption, text, image, isIcon);
        li.setValue(caption);
        li.setPadding(3);
        addVisualComponent(li);
    }

    public void addListItem(String caption, Image image, String text, boolean isIcon, Object value) {
        ListItem li = new ListItem(caption, text, image, isIcon);
        li.setValue(value);
        li.setPadding(3);
        addVisualComponent(li);
    }

    public boolean keyPressed(int action, int keyCode) {
        switch (action) {
            case Canvas.FIRE:
                
                return false;
            default:
                return super.keyPressed(action, keyCode);
        }
    }

    public ListItem getSelectedListItem() {
        return (ListItem)getSelectedVisualComponent();
    }

    public abstract void initialize();

    public void clearControls() {
        super.clearControls();
    }
}

