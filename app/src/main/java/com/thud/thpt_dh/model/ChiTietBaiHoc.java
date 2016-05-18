package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;
import com.parse.Parse;
import com.parse.ParseFile;

/**
 * Created by khanh on 5/11/2016.
 */
public class ChiTietBaiHoc {
    public static final String TENBANG = "CHITIETBAIHOC";
    public static final String ID = "ID";
    public static final String MABAIHOC = "MABAIHOC";
    public static final String TENCTBH = "TENCTBH";
    public static final String NOIDUNGCTBH = "NOIDUNGCTBH";
    public static final String NOIDUNGCT = "NOIDUNGCT";

    @SerializedName("objectId")
    private String id;

    @SerializedName("MABAIHOC")
    private String mabaihoc;

    @SerializedName("TENCTBH")
    private String tenctbh;

    @SerializedName("NOIDUNGCTBH")
    private String noidungctbh;

    @SerializedName("NOIDUNGCT")
    private String noidungct;


    public ChiTietBaiHoc(String id, String mabaihoc, String tenctbh, String noidungctbh, String noidungct){
        this.id = id;
        this.mabaihoc = mabaihoc;
        this.tenctbh = tenctbh;
        this.noidungctbh = noidungctbh;
        this.noidungct = noidungct;
    }

    public ChiTietBaiHoc(){
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

    public String getTenctbh() {
        return tenctbh;
    }

    public void setTenctbh(String tenctbh) {
        this.tenctbh = tenctbh;
    }

    public String getNoidungctbh() {
        return noidungctbh;
    }

    public void setNoidungctbh(String noidungctbh) {
        this.noidungctbh = noidungctbh;
    }

    public String getNoidungct() {
        return noidungct;
    }

    public void setNoidungct(String noidungct) {
        this.noidungct = noidungct;
    }
}
