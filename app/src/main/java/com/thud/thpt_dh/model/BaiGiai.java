package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class BaiGiai {
    public static final String TENBANG = "BAIGIAI";
    public static final String ID = "ID";
    public static final String TENBAIGIAI = "TENBAIGIAI";
    public static final String NOIDUNGBG = "NOIDUNGBAIGIAI";

    @SerializedName("objectId")
    private String id;

    @SerializedName("TENBAIGIAI")
    private String tenbaigiai;

    @SerializedName("NOIDUNGBAIGIAI")
    private String noidungbg;

    public BaiGiai(String id, String tenbaigiai, String noidungbg){
        this.id = id;
        this.tenbaigiai = tenbaigiai;
        this.noidungbg = noidungbg;
    }

    public BaiGiai(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenbaigiai() {
        return tenbaigiai;
    }

    public void setTenbaigiai(String tenbaigiai) {
        this.tenbaigiai = tenbaigiai;
    }

    public String getNoidungbg() {
        return noidungbg;
    }

    public void setNoidungbg(String noidungbg) {
        this.noidungbg = noidungbg;
    }
}
