package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class HinhAnh {
    public static final String ID = "id";
    public static final String HINHANH = "hinhanh";
    public static final String MABAIGIAI = "mabaigiai";
    public static final String MACHITIETBAIHOC = "machitietbaihoc";

    @SerializedName("objectId")
    private String id;

    @SerializedName("HINHANH")
    private HinhAnhChiTiet hinhanhct;

    @SerializedName("MABAIGIAI")
    private String mabaigiai;

    @SerializedName("MACHITIETBAIHOC")
    private String mactbg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HinhAnhChiTiet getHinhanhct() {
        return hinhanhct;
    }

    public void setHinhanhct(HinhAnhChiTiet hinhanhct) {
        this.hinhanhct = hinhanhct;
    }

    public String getMabaigiai() {
        return mabaigiai;
    }

    public void setMabaigiai(String mabaigiai) {
        this.mabaigiai = mabaigiai;
    }

    public String getMactbg() {
        return mactbg;
    }

    public void setMactbg(String mactbg) {
        this.mactbg = mactbg;
    }
}
