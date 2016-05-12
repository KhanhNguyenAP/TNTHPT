package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class DieuKien {
    public static final String TENBANG = "DIEUKIEN";
    public static final String ID = "ID";
    public static final String TENDK = "TENDK";
    public static final String NOIDUNGDK = "NOIDUNGDK";

    @SerializedName("objectId")
    private String id;

    @SerializedName("TENDK")
    private String tendk;

    @SerializedName("NOIDUNGDK")
    private String noidungdk;

    public DieuKien(String id, String tendk, String noidungdk){
        this.id = id;
        this.tendk = tendk;
        this.noidungdk = noidungdk;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTendk() {
        return tendk;
    }

    public void setTemdk(String temdk) {
        this.tendk = temdk;
    }

    public String getNoidungdk() {
        return noidungdk;
    }

    public void setNoidungdk(String noidungdk) {
        this.noidungdk = noidungdk;
    }
}
