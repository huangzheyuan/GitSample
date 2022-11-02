package com.example.smartcity.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderBean {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("orderNo")
    private String orderNo;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("orderStatus")
    private String orderStatus;
    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("payTime")
    private String payTime;
    @JsonProperty("name")
    private String name;
    @JsonProperty("orderType")
    private String orderType;
    @JsonProperty("orderTypeName")
    private String orderTypeName;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }
}
