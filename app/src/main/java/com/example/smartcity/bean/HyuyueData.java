package com.example.smartcity.bean;

import java.util.List;

public class HyuyueData {
    String total;
    List<HyuyueBean> rows;
    int code;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<HyuyueBean> getRows() {
        return rows;
    }

    public void setRows(List<HyuyueBean> rows) {
        this.rows = rows;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
