package com.thud.thpt_dh.model;

import android.widget.ListView;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KhanhNguyen on 6/6/2016.
 */
public class LinhVuc {
    public static final String TENBANG = "LINHVUC";
    public static final String ID = "ID";
    public static final String MAMON = "MAMON";
    public static final String TENLINHVUC = "TENLINHVUC";

    @SerializedName("objectId")
    private String id;

    @SerializedName("MAMON")
    private String mamon;

    @SerializedName("TENLINHVUC")
    private String tenlinhvuc;

    public LinhVuc(){
    }

    public LinhVuc(String id, String mamon, String tenlinhvuc){
        this.id = id;
        this.mamon = mamon;
        this.tenlinhvuc = tenlinhvuc;
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

    public String getTenlinhvuc() {
        return tenlinhvuc;
    }

    public void setTenlinhvuc(String tenlinhvuc) {
        this.tenlinhvuc = tenlinhvuc;
    }
}
