package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class Chuong {
    public static final String TENBANG = "CHUONG";
    public static final String ID = "ID";
    public static final String MAMON = "MAMON";
    public static final String TENCHUONG = "TENCHUONG";

    @SerializedName("objectId")
    private String id;

    @SerializedName("MAMON")
    private String mamon;

    @SerializedName("TENCHUONG")
    private String tenchuong;

    public Chuong(String id, String mamon, String tenchuong){
        this.id = id;
        this.mamon = mamon;
        this.tenchuong = tenchuong;
    }

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
