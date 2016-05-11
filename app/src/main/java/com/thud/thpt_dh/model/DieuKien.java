package com.thud.thpt_dh.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanh on 5/11/2016.
 */
public class DieuKien {

    public static final String ID = "id";
    public static final String TENDK = "tendk";
    public static final String NOIDUNGDK = "noidungdk";

    @SerializedName("objectId")
    private String id;

    @SerializedName("TENDK")
    private String temdk;

    @SerializedName("NOIDUNGDK")
    private String noidungdk;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemdk() {
        return temdk;
    }

    public void setTemdk(String temdk) {
        this.temdk = temdk;
    }

    public String getNoidungdk() {
        return noidungdk;
    }

    public void setNoidungdk(String noidungdk) {
        this.noidungdk = noidungdk;
    }
}
