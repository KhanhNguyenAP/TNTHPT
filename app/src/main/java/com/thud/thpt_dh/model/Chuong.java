package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class Chuong {
    public static final String ID = "id";
    public static final String MAMON = "mamon";
    public static final String TENCHUONG = "tenchuong";

    @SerializedName("objectId")
    private String id;

    @SerializedName("MAMON")
    private String mamon;

    @SerializedName("TENCHUONG")
    private String tenchuong;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMamon() {
        return mamon;
    }

    public void setMamon(String mamon) {
        this.mamon = mamon;
    }

    public String getTenchuong() {
        return tenchuong;
    }

    public void setTenchuong(String tenchuong) {
        this.tenchuong = tenchuong;
    }
}
