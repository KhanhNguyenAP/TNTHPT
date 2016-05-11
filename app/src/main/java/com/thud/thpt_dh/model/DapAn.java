package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class DapAn {
    public static final String ID = "id";
    public static final String DUNGSAI = "dungsai";
    public static final String MACAUHOI = "macauhoi";
    public static final String NOIDUNGDA = "noidungda";

    @SerializedName("objectId")
    private String id;

    @SerializedName("DUNGSAI")
    private int dung;

    @SerializedName("MACAUHOI")
    private String macauhoi;

    @SerializedName("NOIDUNGDA")
    private String noidungda;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDung() {
        return dung;
    }

    public void setDung(int dung) {
        this.dung = dung;
    }

    public String getMacauhoi() {
        return macauhoi;
    }

    public void setMacauhoi(String macauhoi) {
        this.macauhoi = macauhoi;
    }

    public String getNoidungda() {
        return noidungda;
    }

    public void setNoidungda(String noidungda) {
        this.noidungda = noidungda;
    }
}
