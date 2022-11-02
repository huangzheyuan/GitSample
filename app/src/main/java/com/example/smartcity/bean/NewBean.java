package com.example.smartcity.bean;

public class NewBean {
    /**
     * searchValue : null
     * createBy : null
     * createTime : 2020-10-12 16:34:02
     * updateBy : null
     * updateTime : 2020-10-12 16:34:05
     * remark : null
     * params : {}
     * id : 10
     * title : 看故宫与景德镇“同框”
     * content : 9月的故宫，新展连连。近日，由故宫博物院与景德镇市人民政府联合主办的“御瓷新见——景德镇明代御窑遗址出土与故宫博物院藏传世瓷器对比展”在景仁宫展出。196件(套)文物和瓷器标本，反映了明代御窑瓷器的辉煌艺术成就，为观众提供了全面了解明代景德镇御窑瓷器品种和欣赏标准器的机会。
     * imgUrl : /profile/xwn4.jpg
     * pressCategory : 基层
     * isRecommend : 0
     * likeNumber : 456
     * viewsNumber : 654
     * userId : 1
     * pressStatus : 0
     */

    private Object searchValue;
    private Object createBy;
    private String createTime;
    private Object updateBy;
    private String updateTime;
    private Object remark;
    private ParamsBean params;
    private int id;
    private String title;
    private String content;
    private String imgUrl;
    private String pressCategory;
    private int isRecommend;
    private int likeNumber;
    private int viewsNumber;
    private int userId;
    private int pressStatus;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPressCategory() {
        return pressCategory;
    }

    public void setPressCategory(String pressCategory) {
        this.pressCategory = pressCategory;
    }

    public int getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(int isRecommend) {
        this.isRecommend = isRecommend;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    public int getViewsNumber() {
        return viewsNumber;
    }

    public void setViewsNumber(int viewsNumber) {
        this.viewsNumber = viewsNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPressStatus() {
        return pressStatus;
    }

    public void setPressStatus(int pressStatus) {
        this.pressStatus = pressStatus;
    }

    public static class ParamsBean {
    }

}
