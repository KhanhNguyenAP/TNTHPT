package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class DeThiThu {

    public static final String ID = "id";
    public static final String MAMONHOC = "mamonhoc";
    public static final String SOLUONG = "soluong";
    public static final String TENDE = "tende";

    @SerializedName("objectId")
    private String id;

    @SerializedName("MAMONHOC")
    private String manonhoc;

    @SerializedName("SOLUONG")
    private int soluong;

    @SerializedName("TENDE")
    private String tende;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManonhoc() {
        return manonhoc;
    }

    public void setManonhoc(String manonhoc) {
        this.manonhoc = manonhoc;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getTende() {
        return tende;
    }

    public void setTende(String tende) {
        this.tende = tende;
    }
}
