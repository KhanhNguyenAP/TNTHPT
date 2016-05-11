package com.thud.thpt_dh.model;

/**
 * Created by KhanhNguyen 11/5/2016
 */
public class Result<T> {
    private ResultStatus key;
    private T value;
    private String message;

    public Result(ResultStatus key, T value) {
        this.key = key;
        this.value = value;
    }

    public Result(ResultStatus key, T value, String message) {
        this.key = key;
        this.value = value;
        this.message = message;
    }

    public ResultStatus getKey() {
        return key;
    }

    public void setKey(ResultStatus key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

