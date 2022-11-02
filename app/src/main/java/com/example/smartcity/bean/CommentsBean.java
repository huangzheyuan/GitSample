package com.example.smartcity.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentsBean {
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
    @JsonProperty("appType")
    private String appType;
    @JsonProperty("newsId")
    private Integer newsId;
    @JsonProperty("content")
    private String content;
    @JsonProperty("commentDate")
    private String commentDate;
    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("likeNum")
    private Integer likeNum;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("newsTitle")
    private String newsTitle;

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

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    //"searchValue": null,
    //"createBy": null,
    //"createTime": "2020-10-27 15:57:31",
    //"updateBy": null,
    //"updateTime": null,
    //"remark": null,
    //"params": {},
    //"userId": 12,
    //"pressId": 1,
    //"content": "台媒今日援引香港《南华早报》报道指出，解放军昨天(26 日)上午朝南海发射两
    //枚导弹，其中一枚是东风 21D 航母杀手反舰导弹，此举在于要对美国提出警告。",
    //"nickName": "小张",
    //"userName": "xiaozhang",
    //"avatar": "/profile/2020/10/27/b8c38d7b-34f3-4f9c-a0ce-55e07ca35821.jpg"

}
