package com.example.smartcity.bean;

import java.util.List;

public class GuildData {
    @com.fasterxml.jackson.annotation.JsonProperty("code")
    private String code;
    @com.fasterxml.jackson.annotation.JsonProperty("msg")
    private String msg;
    @com.fasterxml.jackson.annotation.JsonProperty("rows")
    private List<RowsDTO> rows;
    @com.fasterxml.jackson.annotation.JsonProperty("total")
    private String total;

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

    public List<RowsDTO> getRows() {
        return rows;
    }

    public void setRows(List<RowsDTO> rows) {
        this.rows = rows;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public static class RowsDTO {
        @com.fasterxml.jackson.annotation.JsonProperty("id")
        private Integer id;
        @com.fasterxml.jackson.annotation.JsonProperty("sort")
        private Integer sort;
        @com.fasterxml.jackson.annotation.JsonProperty("advTitle")
        private String advTitle;
        @com.fasterxml.jackson.annotation.JsonProperty("advImg")
        private String advImg;
        @com.fasterxml.jackson.annotation.JsonProperty("servModule")
        private String servModule;
        @com.fasterxml.jackson.annotation.JsonProperty("targetId")
        private Integer targetId;
        @com.fasterxml.jackson.annotation.JsonProperty("type")
        private String type;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getSort() {
            return sort;
        }

        public void setSort(Integer sort) {
            this.sort = sort;
        }

        public String getAdvTitle() {
            return advTitle;
        }

        public void setAdvTitle(String advTitle) {
            this.advTitle = advTitle;
        }

        public String getAdvImg() {
            return advImg;
        }

        public void setAdvImg(String advImg) {
            this.advImg = advImg;
        }

        public String getServModule() {
            return servModule;
        }

        public void setServModule(String servModule) {
            this.servModule = servModule;
        }

        public Integer getTargetId() {
            return targetId;
        }

        public void setTargetId(Integer targetId) {
            this.targetId = targetId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
//    private int total;
//    private int code;
//    private String msg;
//    private List<GuildBean> rows;
//
//    public int getTotal() {
//        return total;
//    }
//
//    public void setTotal(int total) {
//        this.total = total;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public List<GuildBean> getRows() {
//        return rows;
//    }
//
//    public void setRows(List<GuildBean> rows) {
//        this.rows = rows;
//    }

}
