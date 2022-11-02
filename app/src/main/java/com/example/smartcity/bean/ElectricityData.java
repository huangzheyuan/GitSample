package com.example.smartcity.bean;

import java.util.List;

public class ElectricityData {
    String total;
    List<ElectricityBean> rows;
    String code;
    String msg;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ElectricityBean> getRows() {
        return rows;
    }

    public void setRows(List<ElectricityBean> rows) {
        this.rows = rows;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
