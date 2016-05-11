package com.thud.thpt_dh.utils.interfaces;

/**
 * Created by KhanhNguyen on 11/5/2016
 * Contact phone: 0975 282 877
 */
public interface ActivityInterface {
    /**
     * Init all variable for Flags
     */
    public void initFlags();

    /**
     * Init all control of View
     */
    public void initControl();

    /**
     * set all event if have for control
     */
    public void setEventForControl();

    /**
     * get Data to show on the view
     */
    public void getData(String... params);

    /**
     * set Data for every control if have
     */
    public void setData();
}
