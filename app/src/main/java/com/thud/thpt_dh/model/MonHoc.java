package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class MonHoc {
    public static final String TENBANG = "MONHOC";
    public static final String ID = "id";
    public static final String TENMON = "TENMON";

    @SerializedName("objectId")
    private String id;

    @SerializedName("TENMON")
    private String tenmon;

    public String getId() {
        return id;
    }

    public MonHoc(String id, String tenmon){
        this.id = id;
        this.tenmon = tenmon;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }
}
