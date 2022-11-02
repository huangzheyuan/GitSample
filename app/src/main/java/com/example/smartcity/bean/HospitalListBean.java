package com.example.smartcity.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HospitalListBean {


    @JsonProperty("searchValue")
    private Object searchValue;
    @JsonProperty("createBy")
    private Object createBy;
    @JsonProperty("createTime")
    private Object createTime;
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
    @JsonProperty("hospitalName")
    private String hospitalName;
    @JsonProperty("brief")
    private String brief;
    @JsonProperty("level")
    private Integer level;
    @JsonProperty("imgUrl")
    private String imgUrl;

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

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
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

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public static class ParamsDTO {
    }
}
