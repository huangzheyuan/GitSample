package com.example.smartcity.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class IllegaBean implements Parcelable {

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
    @JsonProperty("licencePlate")
    private String licencePlate;
    @JsonProperty("disposeState")
    private String disposeState;
    @JsonProperty("badTime")
    private String badTime;
    @JsonProperty("money")
    private String money;
    @JsonProperty("deductMarks")
    private String deductMarks;
    @JsonProperty("illegalSites")
    private String illegalSites;
    @JsonProperty("noticeNo")
    private String noticeNo;
    @JsonProperty("engineNumber")
    private String engineNumber;
    @JsonProperty("trafficOffence")
    private String trafficOffence;
    @JsonProperty("catType")
    private String catType;
    @JsonProperty("performOffice")
    private String performOffice;
    @JsonProperty("performDate")
    private String performDate;
    @JsonProperty("imgUrl")
    private String imgUrl;
    @JsonProperty("plateNoList")
    private List<?> plateNoList;

    protected IllegaBean(Parcel in) {
        searchValue = in.readString();
        createBy = in.readString();
        createTime = in.readString();
        updateBy = in.readString();
        updateTime = in.readString();
        remark = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        licencePlate = in.readString();
        disposeState = in.readString();
        badTime = in.readString();
        money = in.readString();
        deductMarks = in.readString();
        illegalSites = in.readString();
        noticeNo = in.readString();
        engineNumber = in.readString();
        trafficOffence = in.readString();
        catType = in.readString();
        performOffice = in.readString();
        performDate = in.readString();
        imgUrl = in.readString();
    }

    public static final Creator<IllegaBean> CREATOR = new Creator<IllegaBean>() {
        @Override
        public IllegaBean createFromParcel(Parcel in) {
            return new IllegaBean(in);
        }

        @Override
        public IllegaBean[] newArray(int size) {
            return new IllegaBean[size];
        }
    };

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

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getDisposeState() {
        return disposeState;
    }

    public void setDisposeState(String disposeState) {
        this.disposeState = disposeState;
    }

    public String getBadTime() {
        return badTime;
    }

    public void setBadTime(String badTime) {
        this.badTime = badTime;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDeductMarks() {
        return deductMarks;
    }

    public void setDeductMarks(String deductMarks) {
        this.deductMarks = deductMarks;
    }

    public String getIllegalSites() {
        return illegalSites;
    }

    public void setIllegalSites(String illegalSites) {
        this.illegalSites = illegalSites;
    }

    public String getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getTrafficOffence() {
        return trafficOffence;
    }

    public void setTrafficOffence(String trafficOffence) {
        this.trafficOffence = trafficOffence;
    }

    public String getCatType() {
        return catType;
    }

    public void setCatType(String catType) {
        this.catType = catType;
    }

    public String getPerformOffice() {
        return performOffice;
    }

    public void setPerformOffice(String performOffice) {
        this.performOffice = performOffice;
    }

    public String getPerformDate() {
        return performDate;
    }

    public void setPerformDate(String performDate) {
        this.performDate = performDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<?> getPlateNoList() {
        return plateNoList;
    }

    public void setPlateNoList(List<?> plateNoList) {
        this.plateNoList = plateNoList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(searchValue);
        dest.writeString(createBy);
        dest.writeString(createTime);
        dest.writeString(updateBy);
        dest.writeString(updateTime);
        dest.writeString(remark);
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(licencePlate);
        dest.writeString(disposeState);
        dest.writeString(badTime);
        dest.writeString(money);
        dest.writeString(deductMarks);
        dest.writeString(illegalSites);
        dest.writeString(noticeNo);
        dest.writeString(engineNumber);
        dest.writeString(trafficOffence);
        dest.writeString(catType);
        dest.writeString(performOffice);
        dest.writeString(performDate);
        dest.writeString(imgUrl);
    }

    public static class ParamsDTO {
    }
}
