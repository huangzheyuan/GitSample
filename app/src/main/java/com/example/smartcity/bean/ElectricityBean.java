package com.example.smartcity.bean;

public class ElectricityBean {
    //"searchValue": null,
    //"createBy": null,
    //"createTime": null,
    //"updateBy": null,
    //"updateTime": null,
    //"remark": null,
    //"params": {},
    //"electricityId": 6,
    //"doorNo": 125,
    //"title": "第六季度电费",
    //"electricityMoney": "100",
    //"chargeUnit": "大连电力公司",
    //"ownerName": "赵六",
    //"balance": "10000",
    //"liveName": "电费",
    //"userId": 3,
    //"doorId": 3

    private Object searchValue;
    private Object createBy;
    private String createTime;
    private Object updateBy;
    private String updateTime;
    private Object remark;
    private ParamsBean params;
    private int electricityId;
    private int doorNo;
    private String title;
    private String electricityMoney;
    private String chargeUnit;
    private String ownerName;
    private String balance;
    private String liveName;
    private String userId;
    private String doorId;
    public static class ParamsBean {
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public int getElectricityId() {
        return electricityId;
    }

    public void setElectricityId(int electricityId) {
        this.electricityId = electricityId;
    }

    public int getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(int doorNo) {
        this.doorNo = doorNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getElectricityMoney() {
        return electricityMoney;
    }

    public void setElectricityMoney(String electricityMoney) {
        this.electricityMoney = electricityMoney;
    }

    public String getChargeUnit() {
        return chargeUnit;
    }

    public void setChargeUnit(String chargeUnit) {
        this.chargeUnit = chargeUnit;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getLiveName() {
        return liveName;
    }

    public void setLiveName(String liveName) {
        this.liveName = liveName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDoorId() {
        return doorId;
    }

    public void setDoorId(String doorId) {
        this.doorId = doorId;
    }
}
