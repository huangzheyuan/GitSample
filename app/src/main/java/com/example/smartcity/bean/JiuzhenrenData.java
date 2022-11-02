package com.example.smartcity.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class JiuzhenrenData {

    @JsonProperty("total")
    private Integer total;
    @JsonProperty("rows")
    private List<JiuzhenrenBean> rows;
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("msg")
    private String msg;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<JiuzhenrenBean> getRows() {
        return rows;
    }

    public void setRows(List<JiuzhenrenBean> rows) {
        this.rows = rows;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
