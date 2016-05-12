package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class HinhAnhChiTiet {
    public static final String TYPE = "type";
    public static final String NAME = "name";
    public static final String URL = "url";

    @SerializedName("__type")
    private String type;

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    public HinhAnhChiTiet(String type, String name, String url){
        this.type = type;
        this.name = name;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
