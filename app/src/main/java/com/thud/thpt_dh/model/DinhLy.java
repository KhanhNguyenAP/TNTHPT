package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class DinhLy {

    public static final String ID = "id";
    public static final String MABAIHOC = "mabaihoc";
    public static final String TENDL = "tendl";
    public static final String NOIDUNGDL = "noidungdl";

    @SerializedName("objectId")
    private String id;

    @SerializedName("MABAIHOC")
    private String mabaihoc;

    @SerializedName("TENDL")
    private String tendl;

    @SerializedName("NOIDUNGDL")
    private String noidungdl;

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

    public String getTendl() {
        return tendl;
    }

    public void setTendl(String tendl) {
        this.tendl = tendl;
    }

    public String getNoidungdl() {
        return noidungdl;
    }

    public void setNoidungdl(String noidungdl) {
        this.noidungdl = noidungdl;
    }
}
