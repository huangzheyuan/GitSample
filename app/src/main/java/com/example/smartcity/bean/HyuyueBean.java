package com.example.smartcity.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HyuyueBean {
    @JsonProperty("searchValue")
    private String searchValue;
    @JsonProperty("createBy")
    private String createBy;
    @JsonProperty("createTime")
    private String createTime;
    @JsonProperty("updateBy")
    private String updateBy;
    @JsonProperty("updateTime")
    private String updateTime;
    @JsonProperty("remark")
    private String remark;
    @JsonProperty("params")
    private ParamsDTO params;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("orderNo")
    private String orderNo;
    @JsonProperty("patientName")
    private String patientName;
    @JsonProperty("categoryId")
    private Integer categoryId;
    @JsonProperty("type")
    private String type;
    @JsonProperty("money")
    private Double money;
    @JsonProperty("reserveTime")
    private String reserveTime;
    @JsonProperty("status")
    private String status;
    @JsonProperty("categoryName")
    private String categoryName;
    @JsonProperty("userId")
    private Integer userId;

    public static class ParamsDTO {
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(String reserveTime) {
        this.reserveTime = reserveTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    //    //"searchValue": null,
//    //"createBy": null,
//    //"createTime": null,
//    //"updateBy": null,
//    //"updateTime": null,
//    //"remark": null,
//    //"params": {},
//    //"id": 17,
//    //"orderNo": "1656342556761",
//    //"patientName": "李四",
//    //"divisionId": null,
//    //"typesId": "2",
//    //"moeny": "37",
//    //"startime": "2020-10-27 12:01",
//    //"reservedStatus": null,
//    //"categoryName": "消化内科",
//    //"userId": null
//
//    private Object searchValue;
//    private Object createBy;
//    private String createTime;
//    private Object updateBy;
//    private String updateTime;
//    private Object remark;
//    private ParamsBean params;
//    private int id;
//    private int doorNo;
//    private String patientName;
//    private int divisionId;
//    private int typesId;
//    private int moeny;
//    private String startime;
//    private String reservedStatus;
//    private String categoryName;
//    private int userId;
//    public static class ParamsBean {
//    }
//
//    public Object getSearchValue() {
//        return searchValue;
//    }
//
//    public void setSearchValue(Object searchValue) {
//        this.searchValue = searchValue;
//    }
//
//    public Object getCreateBy() {
//        return createBy;
//    }
//
//    public void setCreateBy(Object createBy) {
//        this.createBy = createBy;
//    }
//
//    public String getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(String createTime) {
//        this.createTime = createTime;
//    }
//
//    public Object getUpdateBy() {
//        return updateBy;
//    }
//
//    public void setUpdateBy(Object updateBy) {
//        this.updateBy = updateBy;
//    }
//
//    public String getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(String updateTime) {
//        this.updateTime = updateTime;
//    }
//
//    public Object getRemark() {
//        return remark;
//    }
//
//    public void setRemark(Object remark) {
//        this.remark = remark;
//    }
//
//    public ParamsBean getParams() {
//        return params;
//    }
//
//    public void setParams(ParamsBean params) {
//        this.params = params;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getDoorNo() {
//        return doorNo;
//    }
//
//    public void setDoorNo(int doorNo) {
//        this.doorNo = doorNo;
//    }
//
//    public String getPatientName() {
//        return patientName;
//    }
//
//    public void setPatientName(String patientName) {
//        this.patientName = patientName;
//    }
//
//    public int getDivisionId() {
//        return divisionId;
//    }
//
//    public void setDivisionId(int divisionId) {
//        this.divisionId = divisionId;
//    }
//
//    public int getTypesId() {
//        return typesId;
//    }
//
//    public void setTypesId(int typesId) {
//        this.typesId = typesId;
//    }
//
//    public int getMoeny() {
//        return moeny;
//    }
//
//    public void setMoeny(int moeny) {
//        this.moeny = moeny;
//    }
//
//    public String getStartime() {
//        return startime;
//    }
//
//    public void setStartime(String startime) {
//        this.startime = startime;
//    }
//
//    public String getReservedStatus() {
//        return reservedStatus;
//    }
//
//    public void setReservedStatus(String reservedStatus) {
//        this.reservedStatus = reservedStatus;
//    }
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public String getCategoryName() {
//        return categoryName;
//    }
//
//    public void setCategoryName(String categoryName) {
//        this.categoryName = categoryName;
//    }


}
