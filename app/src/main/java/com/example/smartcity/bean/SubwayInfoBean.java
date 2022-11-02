package com.example.smartcity.bean;

import java.util.List;

public class SubwayInfoBean {
//"searchValue": null,
    //"createBy": null,
    //"createTime": "2018-07-23 02:28:36",
    //"updateBy": null,
    //"updateTime": "2018-07-23 02:28:36",
    //"remark": null,
    //"endTime": null,
    //"params": {},
    //"id": 1,
    //"name": "地铁 16 号线(西苑-北安河)",
    //"first": null,
    //"end": null,
    //"startTime": null,
    //"cityId": 1,
    //"stationsNumber": null,
    //"km": 20,
    //"runStationsName": "马连洼",
    //"metroStepsList": [
    //"remainingTime": 4
    private Object searchValue;
    private Object createBy;
    private String createTime;
    private Object updateBy;
    private String updateTime;
    private Object remark;
    private String endTime;
    private ParamsBean params;
    private int id;
    private String name;
    private String first;
    private String end;
    private String startTime;
    private String cityId;
    private String stationsNumber;
    private String km;
    private String runStationsName;
    List<metroStepsList> metroStepsList;
    private String remainingTime;

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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }



    public String getStationsNumber() {
        return stationsNumber;
    }

    public void setStationsNumber(String stationsNumber) {
        this.stationsNumber = stationsNumber;
    }



    public String getRunStationsName() {
        return runStationsName;
    }

    public void setRunStationsName(String runStationsName) {
        this.runStationsName = runStationsName;
    }

    public List<com.example.smartcity.bean.metroStepsList> getMetroStepsList() {
        return metroStepsList;
    }

    public void setMetroStepsList(List<com.example.smartcity.bean.metroStepsList> metroStepsList) {
        this.metroStepsList = metroStepsList;
    }

    public String getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(String remainingTime) {
        this.remainingTime = remainingTime;
    }

    @Override
    public String toString() {
        return "{" +
                "searchValue=" + searchValue +
                ", createBy=" + createBy +
                ", createTime='" + createTime + '\'' +
                ", updateBy=" + updateBy +
                ", updateTime='" + updateTime + '\'' +
                ", remark=" + remark +
                ", endTime='" + endTime + '\'' +
                ", params=" + params +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", first='" + first + '\'' +
                ", end='" + end + '\'' +
                ", startTime='" + startTime + '\'' +
                ", cityId='" + cityId + '\'' +
                ", stationsNumber='" + stationsNumber + '\'' +
                ", km='" + km + '\'' +
                ", runStationsName='" + runStationsName + '\'' +
                ", metroStepsList=" + metroStepsList +
                ", remainingTime='" + remainingTime + '\'' +
                '}';
    }
}
