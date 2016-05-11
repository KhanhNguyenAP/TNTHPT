package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class MonHoc {
    public static final String ID = "id";
    public static final String TENMON = "tenmon";

    @SerializedName("objectId")
    private String id;

    @SerializedName("TENMON")
    private String tenmon;

    public String getId() {
        return id;
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
