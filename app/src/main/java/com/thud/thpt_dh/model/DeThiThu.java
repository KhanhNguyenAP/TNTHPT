package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class DeThiThu {
    public static final String TENBANG = "DETHITHU";
    public static final String ID = "ID";
    public static final String MAMONHOC = "MAMONHOC";
    public static final String SOLUONG = "SOLUONG";
    public static final String TENDE = "TENDE";
    public static final String LOAI = "LOAI";

    @SerializedName("objectId")
    private String id;

    @SerializedName("MAMONHOC")
    private String manonhoc;

    @SerializedName("SOLUONG")
    private int soluong;

    @SerializedName("TENDE")
    private String tende;

    @SerializedName("LOAI")
    private int loai;

    public DeThiThu(String id, String manonhoc, String tende, int soluong, int loai){
        this.id = id;
        this.manonhoc = manonhoc;
        this.tende = tende;
        this.soluong = soluong;
        this.loai = loai;
    }

    public DeThiThu(){
    }

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

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }

    public void setTende(String tende) {
        this.tende = tende;
    }


}
