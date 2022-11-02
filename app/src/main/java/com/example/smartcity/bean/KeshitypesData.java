package com.example.smartcity.bean;

import java.util.List;

public class KeshitypesData {
    private int total;
    private List<KeshitypesBean> rows;
    private int code;
    private String msg;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<KeshitypesBean> getRows() {
        return rows;
    }

    public void setRows(List<KeshitypesBean> rows) {
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
