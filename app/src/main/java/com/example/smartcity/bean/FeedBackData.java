package com.example.smartcity.bean;


import java.util.List;

public class FeedBackData {

    private int total;
    private int code;
    private String msg;
    private List<FeedbackBean> rows;

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

    public List<FeedbackBean> getRows() {
        return rows;
    }

    public void setRows(List<FeedbackBean> rows) {
        this.rows = rows;
    }
}

