package com.example.smartcity.bean;

import java.util.List;

public class QueryyuyueData {
    @com.fasterxml.jackson.annotation.JsonProperty("msg")
    private String msg;
    @com.fasterxml.jackson.annotation.JsonProperty("code")
    private Integer code;
    @com.fasterxml.jackson.annotation.JsonProperty("data")
    private HyuyueBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public HyuyueBean getData() {
        return data;
    }

    public void setData(HyuyueBean data) {
        this.data = data;
    }

    //    String msg;
//    int code;
//    HyuyueBean data;
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public HyuyueBean getData() {
//        return data;
//    }
//
//    public void setData(HyuyueBean data) {
//        this.data = data;
//    }


}
