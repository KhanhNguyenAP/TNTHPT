package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class ViDu {
    public static final String ID = "id";
    public static final String MACONGTHUC = "macongthuc";
    public static final String NOIDUNGVD = "noidungvd";
    public static final String BAIGIAIVD = "baigiaivd";

    @SerializedName("objectId")
    private String id;

    @SerializedName("MACONGTHUC")
    private String macongthuc;

    @SerializedName("NOIDUNGVD")
    private String noidungvd;

    @SerializedName("BAIGIAIVD")
    private String baigiaivd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMacongthuc() {
        return macongthuc;
    }

    public void setMacongthuc(String macongthuc) {
        this.macongthuc = macongthuc;
    }

    public String getNoidungvd() {
        return noidungvd;
    }

    public void setNoidungvd(String noidungvd) {
        this.noidungvd = noidungvd;
    }

    public String getBaigiaivd() {
        return baigiaivd;
    }

    public void setBaigiaivd(String baigiaivd) {
        this.baigiaivd = baigiaivd;
    }
}
