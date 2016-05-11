package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class BaiHoc {
    public static final String ID = "id";
    public static final String MACHUONG = "machuong";
    public static final String TENBH = "tenbh";

    @SerializedName("objectId")
    private String id;

    @SerializedName("MACHUONG")
    private String machuong;

    @SerializedName("TENBH")
    private String tenbh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMachuong() {
        return machuong;
    }

    public void setMachuong(String machuong) {
        this.machuong = machuong;
    }

    public String getTenbh() {
        return tenbh;
    }

    public void setTenbh(String tenbh) {
        this.tenbh = tenbh;
    }
}
