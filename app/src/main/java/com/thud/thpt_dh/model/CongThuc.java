package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class CongThuc {
    public static final String TENBANG = "CONGTHUC";
    public static final String ID = "ID";
    public static final String MACTBH = "MACTBH";
    public static final String NOIDUNGCT = "NOIDUNGCT";
    public static final String TENCT = "TENCT";

    @SerializedName("objectId")
    private String id;

    @SerializedName("MACTBH")
    private String mactbh;

    @SerializedName("NOIDUNGCT")
    private String noidungct;

    @SerializedName("TENCT")
    private String tenct;

    public CongThuc(String id, String mactbh, String tenct, String noidungct){
        this.id = id;
        this.mactbh = mactbh;
        this.tenct = tenct;
        this.noidungct = noidungct;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMactbh() {
        return mactbh;
    }

    public void setMactbh(String mactbh) {
        this.mactbh = mactbh;
    }

    public String getNoidungct() {
        return noidungct;
    }

    public void setNoidungct(String noidungct) {
        this.noidungct = noidungct;
    }

    public String getTenct() {
        return tenct;
    }

    public void setTenct(String tenct) {
        this.tenct = tenct;
    }
}
