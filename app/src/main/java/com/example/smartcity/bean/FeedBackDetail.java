package com.example.smartcity.bean;

public class FeedBackDetail {
    private FeedBackDetailBean data;
    private int code;
    private String msg;

    public FeedBackDetailBean getData() {
        return data;
    }

    public void setData(FeedBackDetailBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
