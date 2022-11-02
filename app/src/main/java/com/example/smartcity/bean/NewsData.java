package com.example.smartcity.bean;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


// "searchValue": null,
//         "createBy": null,
//         "createTime": null,
//         "updateBy": null,
//         "updateTime": null,
//         "remark": null,
//         "params": {},
//         "id": 22,
//         "title": "中国与世卫并肩抗疫，体现责任与担当",
//         "content": " 本报讯（大连新闻传媒集团记者鹿道铭）昨日上午，由市政府外办组织成立的大连市外文公示语审核委员会举行专家委员聘任仪式暨座谈会，来自大连海事大学、大连民族大学、辽宁师范大学、大连工业大学、大连外国语大学、大连大学等高校的9名专家学者获聘为专家委员，他们将负责对我市主要街面、商铺及机场、火车站等重点涉外区域的英、日、韩公示语译审工作。",
//         "imgUrl": "/profile/zt.jpg",
//         "pressCategory": null,
//         "isRecommend": 0,
//         "likeNumber": 0,
//         "viewsNumber": 0,
//         "userId": null,
//         "pressStatus": null
public class NewsData {
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("rows")
    private List<RowsDTO> rows;
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("msg")
    private String msg;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<RowsDTO> getRows() {
        return rows;
    }

    public void setRows(List<RowsDTO> rows) {
        this.rows = rows;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class RowsDTO {
        @JsonProperty("searchValue")
        private Object searchValue;
        @JsonProperty("createBy")
        private String createBy;
        @JsonProperty("createTime")
        private String createTime;
        @JsonProperty("updateBy")
        private String updateBy;
        @JsonProperty("updateTime")
        private String updateTime;
        @JsonProperty("remark")
        private Object remark;
        @JsonProperty("params")
        private ParamsDTO params;
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("appType")
        private String appType;
        @JsonProperty("cover")
        private String cover;
        @JsonProperty("title")
        private String title;
        @JsonProperty("subTitle")
        private Object subTitle;
        @JsonProperty("content")
        private String content;
        @JsonProperty("status")
        private String status;
        @JsonProperty("publishDate")
        private String publishDate;
        @JsonProperty("tags")
        private Object tags;
        @JsonProperty("commentNum")
        private Integer commentNum;
        @JsonProperty("likeNum")
        private Integer likeNum;
        @JsonProperty("readNum")
        private Integer readNum;
        @JsonProperty("type")
        private String type;
        @JsonProperty("top")
        private String top;
        @JsonProperty("hot")
        private String hot;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
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

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(Object subTitle) {
            this.subTitle = subTitle;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public Object getTags() {
            return tags;
        }

        public void setTags(Object tags) {
            this.tags = tags;
        }

        public Integer getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(Integer commentNum) {
            this.commentNum = commentNum;
        }

        public Integer getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(Integer likeNum) {
            this.likeNum = likeNum;
        }

        public Integer getReadNum() {
            return readNum;
        }

        public void setReadNum(Integer readNum) {
            this.readNum = readNum;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTop() {
            return top;
        }

        public void setTop(String top) {
            this.top = top;
        }

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }

        public static class ParamsDTO {
        }
    }

    /**
     * total : 3
     * rows : [{"searchValue":null,"createBy":null,"createTime":"2020-10-12 16:34:02","updateBy":null,"updateTime":"2020-10-12 16:34:05","remark":null,"params":{},"id":10,"title":"看故宫与景德镇\u201c同框\u201d","content":"9月的故宫，新展连连。近日，由故宫博物院与景德镇市人民政府联合主办的\u201c御瓷新见\u2014\u2014景德镇明代御窑遗址出土与故宫博物院藏传世瓷器对比展\u201d在景仁宫展出。196件(套)文物和瓷器标本，反映了明代御窑瓷器的辉煌艺术成就，为观众提供了全面了解明代景德镇御窑瓷器品种和欣赏标准器的机会。","imgUrl":"/profile/xwn4.jpg","pressCategory":"基层","isRecommend":0,"likeNumber":456,"viewsNumber":654,"userId":1,"pressStatus":0},{"searchValue":null,"createBy":null,"createTime":"2020-10-12 16:36:32","updateBy":null,"updateTime":"2020-10-12 16:36:36","remark":null,"params":{},"id":11,"title":"全国铁路10月上旬发送煤炭5031万吨","content":"中国国家铁路集团有限公司最新数据显示，全国铁路10月上旬发送煤炭5031万吨，主要港口、电厂存煤稳中有增。","imgUrl":"/profile/cshi.jpg","pressCategory":"基层","isRecommend":1,"likeNumber":201,"viewsNumber":222,"userId":1,"pressStatus":0},{"searchValue":null,"createBy":null,"createTime":"2020-10-12 16:38:19","updateBy":null,"updateTime":"2020-10-12 16:38:22","remark":null,"params":{},"id":12,"title":"我国北方地区明显降温 北方多地启动供暖","content":"近期，受冷空气频繁影响，我国北方地区明显降温，本周局地降温幅度可达8～10℃以上。北方多地陆续启动供暖工作暖人心。","imgUrl":"/profile/xweb.jpg","pressCategory":"基层","isRecommend":0,"likeNumber":124,"viewsNumber":125,"userId":1,"pressStatus":0}]
     * code : 200
     * msg : 查询成功
     */

//    private int total;
//    private int code;
//    private String msg;
//    private List<NewBean> rows;
//
//    public int getTotal() {
//        return total;
//    }
//
//    public void setTotal(int total) {
//        this.total = total;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public List<NewBean> getRows() {
//        return rows;
//    }
//
//    public void setRows(List<NewBean> rows) {
//        this.rows = rows;
//    }


}

