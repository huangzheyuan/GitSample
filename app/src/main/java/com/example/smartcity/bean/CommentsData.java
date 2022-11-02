package com.example.smartcity.bean;

import java.util.List;

public class CommentsData {
    int total;
    List<CommentsBean> rows;
    int code;
    String msg;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CommentsBean> getRows() {
        return rows;
    }

    public void setRows(List<CommentsBean> rows) {
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
