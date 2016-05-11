package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class ChiTietBaiHoc {
    public static final String ID = "id";
    public static final String MABAIHOC = "mabaihoc";
    public static final String TENCTBH = "tenctbh";
    public static final String NOIDUNGCTBH = "noidungctbh";

    @SerializedName("objectId")
    private String id;

    @SerializedName("MABAIHOC")
    private String mabaihoc;

    @SerializedName("TENCTBH")
    private String tenctbh;

    @SerializedName("NOIDUNGCTBH")
    private int noidungctbh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMabaihoc() {
        return mabaihoc;
    }

    public void setMabaihoc(String mabaihoc) {
        this.mabaihoc = mabaihoc;
    }

    public String getTenctbh() {
        return tenctbh;
    }

    public void setTenctbh(String tenctbh) {
        this.tenctbh = tenctbh;
    }

    public int getNoidungctbh() {
        return noidungctbh;
    }

    public void setNoidungctbh(int noidungctbh) {
        this.noidungctbh = noidungctbh;
    }
}
