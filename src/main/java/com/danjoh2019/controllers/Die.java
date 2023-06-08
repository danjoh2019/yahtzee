package com.danjoh2019.controllers;

import javafx.scene.image.Image;

public class Die {
    private Image image;
    private int value;
    private boolean selected;

    public Die() {
        this.image = null;
        this.value = 0;
        selected = false;
    }
    
    public Die(Image image, int value) {
        this.image = image;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    
}
