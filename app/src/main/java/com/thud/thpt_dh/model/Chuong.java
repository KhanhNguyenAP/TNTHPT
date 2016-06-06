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
    public static final String MALINHVUC = "MALINHVUC";

    @SerializedName("objectId")
    private String id;

    @SerializedName("MAMON")
    private String mamon;

    @SerializedName("TENCHUONG")
    private String tenchuong;

    @SerializedName("MALINHVUC")
    private String malinhvuc;

    public Chuong(String id, String mamon, String tenchuong, String malinhvuc){
        this.id = id;
        this.mamon = mamon;
        this.tenchuong = tenchuong;
        this.malinhvuc = malinhvuc;
    }

    public Chuong() {
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

    public String getMalinhvuc() {
        return malinhvuc;
    }

    public void setMalinhvuc(String malinhvuc) {
        this.malinhvuc = malinhvuc;
    }
}
