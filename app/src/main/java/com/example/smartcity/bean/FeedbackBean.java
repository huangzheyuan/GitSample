package com.example.smartcity.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedbackBean {
    @JsonProperty("searchValue")
    private Object searchValue;
    @JsonProperty("createBy")
    private Object createBy;
    @JsonProperty("createTime")
    private String createTime;
    @JsonProperty("updateBy")
    private Object updateBy;
    @JsonProperty("updateTime")
    private Object updateTime;
    @JsonProperty("remark")
    private Object remark;
    @JsonProperty("params")
    private ParamsDTO params;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("appType")
    private String appType;
    @JsonProperty("title")
    private String title;
    @JsonProperty("content")
    private String content;
    @JsonProperty("userId")
    private Integer userId;

    public static class ParamsDTO {
    }

    public Object getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(Object searchValue) {
        this.searchValue = searchValue;
    }

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Object updateBy) {
        this.updateBy = updateBy;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public ParamsDTO getParams() {
        return params;
    }

    public void setParams(ParamsDTO params) {
        this.params = params;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    //    private Object searchValue;
//    private Object createBy;
//    private String createTime;
//    private Object updateBy;
//    private String updateTime;
//    private Object remark;
//    private ParamsBean params;
//    private int id;
//    private String content;
//    private int userId;
//    public static class ParamsBean {
//    }



}