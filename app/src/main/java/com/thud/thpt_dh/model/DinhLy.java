package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class DinhLy {
    public static final String TENBANG = "DINHLY";
    public static final String ID = "id";
    public static final String MABAIHOC = "MABAIHOC";
    public static final String TENDL = "TENDL";
    public static final String NOIDUNGDL = "NOIDUNGDL";

    @SerializedName("objectId")
    private String id;

    @SerializedName("MABAIHOC")
    private String mabaihoc;

    @SerializedName("TENDL")
    private String tendl;

    @SerializedName("NOIDUNGDL")
    private String noidungdl;

    public DinhLy(String id, String mabaihoc, String tendl, String noidungdl){
        this.id = id;
        this.mabaihoc = mabaihoc;
        this.tendl = tendl;
        this.noidungdl = noidungdl;
    }

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
