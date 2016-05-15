package com.thud.thpt_dh.model;

import android.graphics.drawable.Drawable;

/**
 * Created by KhanhNguyen on 13/05/2016.
 * Contact phone: 0975 282 877
 */
public class DrawerSetting {
    /**
     * type setting
     */
    private String title;

    /**
     * icon show on background
     */
    private Drawable icon;

    /**
     * background of item
     */
    private Drawable background;

    /**
     * Contructor     *
     * @return
     * @param charSequence
     */

    public DrawerSetting(CharSequence charSequence){

    }

    public DrawerSetting(String title, Drawable icon, Drawable background) {
        this.title = title;
        this.icon = icon;
        this.background = background;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public Drawable getBackground() {
        return background;
    }

    public void setBackground(Drawable background) {
        this.background = background;
    }
}
