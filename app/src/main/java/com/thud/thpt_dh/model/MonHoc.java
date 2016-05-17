package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class MonHoc {
    public static final String TENBANG = "MONHOC";
    public static final String ID = "ID";
    public static final String TENMON = "TENMON";
    public static final String MAMON = "MAMON";

    @SerializedName("objectId")
    private String id;

    @SerializedName("TENMON")
    private String tenmon;

    @SerializedName("MAMON")
    private int mamon;

    public MonHoc() {
    }

    public String getId() {
        return id;
    }

    public MonHoc(String id, String tenmon, int mamon){
        this.id = id;
        this.tenmon = tenmon;
        this.mamon = mamon;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public int getMamon() {
        return mamon;
    }

    public void setMamon(int mamon) {
        this.mamon = mamon;
    }
}
