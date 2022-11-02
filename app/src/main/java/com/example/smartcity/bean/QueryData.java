package com.example.smartcity.bean;


import java.util.List;

public class QueryData {
    private int total;
    private List<QueryBean> rows;
    private int code;
    private String msg;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public List<QueryBean> getRows() {
        return rows;
    }

    public void setRows(List<QueryBean> rows) {
        this.rows = rows;
    }
}
