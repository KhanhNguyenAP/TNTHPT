package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class CauHoi {
    public static final  String TENBANG = "CAUHOI";
    public static final  String ID = "id";
    public static final  String LOAI = "LOAI";
    public static final  String MADETHI = "MADETHI";
    public static final  String NOIDUNGCH = "NOIDUNGCH";

    @SerializedName("objectId")
    private String id;

    @SerializedName("LOAI")
    private int loai;

    @SerializedName("MADETHI")
    private String madethi;

    @SerializedName("NOIDUNGCH")
    private String noidungch;

    public CauHoi(String id, int loai, String madethi, String noidungch){
        this.id = id;
        this.loai = loai;
        this.madethi = madethi;
        this.noidungch = noidungch;
    }

    public CauHoi(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }

    public String getMadethi() {
        return madethi;
    }

    public void setMadethi(String madethi) {
        this.madethi = madethi;
    }

    public String getNoidungch() {
        return noidungch;
    }

    public void setNoidungch(String noidungch) {
        this.noidungch = noidungch;
    }
}
