package com.example.smartcity.bean;

import java.util.List;

public class HospitalData {
    List<HospitalListBean> rows;
    int code;
    String msg;

    public List<HospitalListBean> getRows() {
        return rows;
    }

    public void setRows(List<HospitalListBean> rows) {
        this.rows = rows;
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
