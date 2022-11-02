package com.example.smartcity.bean;

import java.util.List;

public class BusOrderData {
    String total;
    List<BusOrderBean> rows;
    String code;
    String msg;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<BusOrderBean> getRows() {
        return rows;
    }

    public void setRows(List<BusOrderBean> rows) {
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
