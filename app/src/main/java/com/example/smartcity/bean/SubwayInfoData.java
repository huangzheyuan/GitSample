package com.example.smartcity.bean;

import java.util.List;

public class SubwayInfoData {
    String msg;
    String code;
    SubwayInfoBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SubwayInfoBean getData() {
        return data;
    }

    public void setData(SubwayInfoBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
