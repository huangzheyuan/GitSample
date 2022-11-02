package com.example.smartcity.bean;

import java.util.List;

public class PersonData {

    private int code;
    private String msg;
    private PersonBean users;
    private String idCard;

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

    public PersonBean getUsers() {
        return users;
    }

    public void setUsers(PersonBean users) {
        this.users = users;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
