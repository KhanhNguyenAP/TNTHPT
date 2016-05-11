package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class CongThuc {
    public static final String ID = "id";
    public static final String MACTBH = "mactbh";
    public static final String NOIDUNGCT = "noidungct";
    public static final String TENCT = "tenct";

    @SerializedName("objectId")
    private String id;

    @SerializedName("MACTBH")
    private String mactbh;

    @SerializedName("NOIDUNGCT")
    private String noidungct;

    @SerializedName("TENCT")
    private String tenct;

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
