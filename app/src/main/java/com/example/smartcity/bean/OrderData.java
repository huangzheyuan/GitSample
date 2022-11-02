package com.example.smartcity.bean;

import java.util.List;

public class OrderData {
    private String msg;
    private int code;
    private List<OrderBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<OrderBean> getData() {
        return data;
    }


    public void setData(List<OrderBean> data) {
        this.data = data;
    }
}
