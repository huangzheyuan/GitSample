package com.example.smartcity.bean;

import java.util.List;

public class IllegaData {
    String total;
    List<IllegaBean> rows;
    int code;
    String msg;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<IllegaBean> getRows() {
        return rows;
    }

    public void setRows(List<IllegaBean> rows) {
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
