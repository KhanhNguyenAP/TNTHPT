package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;
import com.parse.ParseFile;

/**
 * Created by khanh on 5/11/2016.
 */
public class HinhAnh {
    public static final String ID = "id";
    public static final String TENBANG = "HINHANH";
    public static final String HINHANH = "HINHANH";
    public static final String MABAIGIAI = "MABAIGIAI";
    public static final String MACHITIETBAIHOC = "MACHITIETBAIHOC";

    @SerializedName("objectId")
    private String id;

    @SerializedName("HINHANH")
    private ParseFile hinhanh;

    @SerializedName("MABAIGIAI")
    private String mabaigiai;

    @SerializedName("MACHITIETBAIHOC")
    private String mactbg;

    private String img;

    public HinhAnh(String id, ParseFile hinhanh, String mabaigiai, String mactbg){
        this.id = id;
        this.hinhanh = hinhanh;
        this.mabaigiai = mabaigiai;
        this.mactbg = mactbg;
    }

    public HinhAnh(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ParseFile getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(ParseFile hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
