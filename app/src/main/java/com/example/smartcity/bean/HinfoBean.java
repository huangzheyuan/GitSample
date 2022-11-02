package com.example.smartcity.bean;

public class HinfoBean {
    //"searchValue": null,
    //"createBy": null,
    //"createTime": null,
    //"updateBy": null,
    //"updateTime": null,
    //"remark": null,
    //"params": {},
    //"id": 1,
    //"hospitalName": "大连市儿童医院",
    //"brief": "大连市儿童医院创建于 1952 年 6 月 1 日，经过几代儿医人的艰苦奋斗，已成长为一所学
    //科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着
    //辽东半岛 18 岁以下儿童的医疗、预防、康复、保健任务。2013 年 2 月 6 日，签约成为大连医科大学附属
    //大连市儿童医院。系国家儿科、儿外科住院医师规范化培训基地，辽宁省首批儿科、儿外科住院医师规范
    //化培训基地。医院设有 36 个学科，大连市快速提升医疗软实力建设项目小儿心脏病诊疗基地 1 个，大连市
    //一级医学重点学科 6 个。",
    //"level": "3",
    //"imgUrl": "/profile/2020/10/27/h1.jpg"
    private Object searchValue;
    private Object createBy;
    private String createTime;
    private Object updateBy;
    private String updateTime;
    private Object remark;
    private ParamsBean params;
    private int id;
    private String hospitalName;
    private String brief;
    private int level;
    private String imgUrl;
    public static class ParamsBean {
    }



    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
