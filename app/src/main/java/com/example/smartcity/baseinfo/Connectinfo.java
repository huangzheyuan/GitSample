package com.example.smartcity.baseinfo;

public class Connectinfo {

//	public static String contexturl="http://dasai.sdvcst.edu.cn:8080";//上下文路径
//	public static String contexturl="http://192.168.31.116:10002";//上下文路径（请修改成 服务器IP:10003）

    public static String contexturl = "http://124.93.196.45:10001";//v2 - OK

    //	public static String loginurl = contexturl+"/api/login";// 登录 1.1
    public static String loginurl = contexturl + "/prod-api/api/login";//v2 - OK

    //	public static String regurl = contexturl+"/system/user/register";// 注册 1.2
    public static String regurl = contexturl + "/prod-api/api/register";//v2 - OK

    public static String guildurl = contexturl + "/userinfo/rotation/lists";//引导页轮播图 2.1

    //	public static String mainrotationurl = contexturl+"/userinfo/rotation/list";//查询主页轮播图列表 3.1
    public static String mainrotationurl = contexturl + "/prod-api/api/rotation/list";//V2 - OK

    //	public static String queryurl = contexturl+"/service/service/list";//查询推荐服务 3.2  4.2
    public static String all_services_url = contexturl + "/prod-api/api/service/list";//V2 - 暂无推荐服务，临时处理

    //	public static String newsurl = contexturl+"/press/press/list";//查询专题新闻列表 3.3  3.5  5.1
    public static String newsurl = contexturl + "/prod-api/press/press/list";//V2 - OK

    //	public static String categoryurl = contexturl+"/system/dict/data/type/press_category";//查询所属分类下的新闻列表 3.4
    public static String categoryurl = contexturl + "/prod-api/press/category/list";//V2 - OK

    public static String queryyiurl = contexturl + "/system/dict/data/type/sys_service";//查询服务一级分类 4.1 GET

    //	public static String commentslisturl = contexturl+"/press/comments/list";  //查询评论列表 GET 5.2
    public static String commentslisturl = contexturl + "/prod-api/press/comments/list";//V2 - OK

    //	public static String addcommentsurl = contexturl+"/press/pressComment";  //新增评论POST 5.3
    public static String addcommentsurl = contexturl + "/prod-api/press/pressComment";  //V2 - OK

    public static String subwayurl = contexturl + "/metro/list";//查询地铁 6.1
    public static String querysubwayinfo = contexturl + "/metro/";//查询地铁站详情 6.2

//    public static String getinfourl = contexturl + "/getInfo";//7.1 查询用户详细信息  get
    public static String getinfourl = contexturl + "/prod-api/api/common/user/getInfo";//V2 - OK

    public static String getuser = contexturl + "/system/user/{id}";//7.2 查询个人基本信息

//    public static String updateuser = contexturl + "/system/user/updata";//7.3 修改用户基本信息 POST (带图片文件 未做)
    public static String updateuser = contexturl + "/prod-api/api/common/user";//V2 - OK（头像无法上传）

//    public static String resetPwd = contexturl + "/system/user/resetPwd";//7.4 修改用户密码
    public static String resetPwd = contexturl + "/prod-api/api/common/user/resetPwd";//V2 - OK

//    public static String queryorderslist = contexturl + "/userinfo/orders/list?";//查询订单总列表 7.5
    public static String queryorderslist = contexturl + "/prod-api/api/allorder/list";//v2 - OK

//    public static String feedbacklistlurl = contexturl + "/userinfo/feedback/list";//查询意见反馈列表 7.6
    public static String feedbacklistlurl = contexturl + "/prod-api/api/common/feedback/list";//V2 - OK

//    public static String feedbackdetail = contexturl + "/userinfo/feedback/";//查询意见反馈详情 7.7
    public static String feedbackdetail = contexturl + "/prod-api/api/common/feedback/";//V2 - OK

//    public static String addfeedback = contexturl + "/userinfo/feedback";//新增意见反馈 7.8
    public static String addfeedback = contexturl + "/prod-api/api/common/feedback";//V2 - OK

    public static String feeslisturl = contexturl + "/userinfo/feeslist/list";//缴费类型分类编号 1 为电费 2 为水费 不填该字段为查询所有 8.1
    public static String householderurl = contexturl + "/userinfo/householder/";//查询用户 生活缴费 信息 8.2
    public static String electricityurl = contexturl + "/userinfo/electricity/list";//查询缴费详情 ?doorNo=125&userId=3（数据解析）8.3
    public static String chongzhidianfeiurl = contexturl + "/userinfo/householder";//充值电费 PUT（未做） 8.4
    public static String insertrelationsurl = contexturl + "/userinfo/relations";//8.5 添加缴费类型 接口有问题（未做）

//    public static String hospitalurl = contexturl + "/userinfo/img/list";//医院详情轮播图 9.1
    public static String hospitalBannerURL = contexturl + "/prod-api/api/hospital/banner/list";//v2 - OK

//    public static String registrationurl = contexturl + "/userinfo/registration/";//9.2 根据 医院编号 查询医院信息  （数据解析）
    public static String registrationurl = contexturl + "/prod-api/api/hospital/hospital/";//V2 - OK

//    public static String hospitallisturl = contexturl + "/userinfo/registration/list";//查询医院列表 9.3
    public static String hospitallisturl = contexturl + "/prod-api/api/hospital/hospital/list";//V2 - OK

//    public static String queryhospitalurl = contexturl + "/userinfo/registration/list";//查询医院列表 9.4
    public static String queryhospitalurl = contexturl + "/prod-api/api/hospital/hospital/list";//V2 - OK

//    public static String querypatienturl = contexturl + "/userinfo/patient/list";  //查询当前用户下的就诊人卡片 9.5
    public static String querypatienturl = contexturl + "/prod-api/api/hospital/patient/list";  //V2 - OK

//    public static String patienturl = contexturl + "/userinfo/patient";//新增就诊人 POST 9.6
    public static String patienturl = contexturl + "/prod-api/api/hospital/patient";//V2 - OK

//    public static String updatepatienturl = contexturl + "/userinfo/patient";//修改就诊人卡片 9.7
    public static String updatepatienturl = contexturl + "/prod-api/api/hospital/patient";//V2 - OK

//    public static String querytypes = contexturl + "/userinfo/types/list";//查询专家与普通下的科室分类 9.8
    public static String querytypes = contexturl + "/prod-api/api/hospital/category/list";//V2 - OK

//    public static String queryyuyuelisturl = contexturl + "/userinfo/order/list";//查询用户预约列表 9.9
    public static String queryyuyuelisturl = contexturl + "/prod-api/api/hospital/reservation/list";//V2 - problem，无测试数据，需先添加

//    public static String byidqueryinfourl = contexturl + "/userinfo/order/";   // 根据预约编号查询预约详情 9.10
    public static String byidqueryinfourl = contexturl + "/prod-api/api/hospital/reservation/";   //V2 - OK

//    public static String insertyuyueurl = contexturl + "/userinfo/order/";  //生成预约单 POST 9.11
    public static String insertyuyueurl = contexturl + "/prod-api/api/hospital";  //V2 - OK

    public static String busurl = contexturl + "/userinfo/lines/list";//查询智慧巴士 10.1
    public static String bylinesurl = contexturl + "/userinfo/lines/";//10.2 根据路线编号查询路线基本信息 数据解析）
    public static String bybusStopurl = contexturl + "/userinfo/busStop/list";//10.3 根据路线编号查询站点信息 数据解析）
    public static String insertbusOrdersurl = contexturl + "/userinfo/busOrders";//10.4 新增 巴士 订单
    public static String querybusOrderssurl = contexturl + "/userinfo/busOrders/list";//10.5 查询 巴士订单列表

//    public static String queryillegalurl = contexturl + "/userinfo/illegal/list";//11.1 根据车牌
    public static String queryillegalurl = contexturl + "/prod-api/api/traffic/illegal/list";//V2 - OK

//    public static String queryillegalidurl = contexturl + "/userinfo/illegal/";//11.2 根据违章编号查询违章详情
    public static String queryillegalidurl = contexturl + "/prod-api/api/traffic/illegal/";//V2 - OK

//    public static String queryparkloturl = contexturl + "/userinfo/parklot/list";  //停车场查询 GET 12.1
    public static String queryparkloturl = contexturl + "/prod-api/api/park/lot/list";  //V2 - OK

//    public static String getparklituinfourl = contexturl + "/userinfo/parklot/";  //停车场详情 GET (数据解析问题) 12.2
    public static String getparklituinfourl = contexturl + "/prod-api/api/park/lot/";  //V2 - OK

    //预约检车  找工作 找房子
    public static String querparkrecordurl = contexturl + "/userinfo/parkrecord/list";  //查询停车记录 12.3
    public static String querparkrecordtimeurl = contexturl + "/userinfo/parkrecord/list";  //12.4 根据时间查询停车记录

//    public static String querycarNoticeurl = contexturl + "/userinfo/carNotice/grt";  //13.1 查询预约须知
    public static String querycarNoticeurl = contexturl + "/prod-api/api/traffic/checkCar/grt";  //V2 - OK

//    public static String queryplaceurl = contexturl + "/userinfo/place/list";  //13.2 查询所有检车地点
    public static String queryplaceurl = contexturl + "/prod-api/api/traffic/checkCar/place/list";  //V2 - OK

//    public static String queryplaceinfourl = contexturl + "/userinfo/place/";  //13.3 查询检车点详情
    public static String queryplaceinfourl = contexturl + "/prod-api/api/traffic/checkCar/place/";  //V2 - OK

//    public static String apturl = contexturl + "/userinfo/apt";  //13.4 预约检车
    public static String apturl = contexturl + "/prod-api/api/traffic/checkCar/apply";  //V2 - OK

//    public static String aptlisturl = contexturl + "/userinfo/apt/list";  //13.5 查询预约检车 订单
    public static String aptlisturl = contexturl + "/prod-api/api/traffic/checkCar/apply/list";  //V2 - OK

//    public static String carslisturl = contexturl + "/userinfo/cars/list";  //13.6 查询我的车辆
    public static String carslisturl = contexturl + "/prod-api/api/traffic/car/list";  //V2 - OK

//    public static String addcarsurl = contexturl + "/userinfo/cars";  //13.7 新增车辆
    public static String addcarsurl = contexturl + "/prod-api/api/traffic/car";  //V2 - OK（绑定，修改，删除）

    public static String updatecarsurl = contexturl + "/userinfo/cars";  //13.8 修改车辆
    public static String deletecarsurl = contexturl + "/userinfo/cars{id}";  //13.9 删除车辆信息

    //	public static String sourceurl = contexturl+"/userinfo/housing/{id}";  //14.2 查询房源详情
//    public static String sourcelisturl = contexturl + "/userinfo/housing/list";  //14.1 查询房源列表
    public static String sourcelisturl = contexturl + "/prod-api/api/house/housing/list";  //V2 - OK
    public static String sourceurl = contexturl + "/userinfo/housing/";  //14.2 查询房源详情

    public static String gethousenameurl = contexturl + "/userinfo/housing/list";  //14.3 根据房源名称 模糊查询
    public static String gethousetypeurl = contexturl + "/userinfo/housing/list";  //14.4 根据分类查询房源

//    public static String professionlisturl = contexturl + "/userinfo/profession/list";  //15.1 查询热门职位 - V2无对应接口
    public static String professionlisturl = contexturl + "/prod-api/api/job/profession/list";  //V2 - OK

//    public static String postlisturl = contexturl + "/userinfo/post/list";  //15.2 查询求职列表
    public static String postlisturl = contexturl + "/prod-api/api/job/post/list";  //V2 - OK

//    public static String reposturlurl = contexturl + "/userinfo/post/list";  //15.3 根据热门职位编号查询职位信息
    public static String reposturlurl = contexturl + "/prod-api/api/job/profession/list";  //V2 -OK

    public static String queryposturl = contexturl + "/userinfo/post/list";  //15.4 根据职位名称搜索职位
    public static String querypostinfourl = contexturl + "/userinfo/post/";  //15.5 查询职位详情

    //	public static String querycompanybyidurl = contexturl+"/userinfo/company/{id}";  //15.6 根据公司编号查询公司信息
    public static String querycompanybyidurl = contexturl + "/prod-api/api/job/company/";  //V2 - OK

//    public static String deliverurl = contexturl + "/userinfo/deliver";  //15.7 投简历
    public static String deliverurl = contexturl + "/prod-api/api/job/deliver";  //V2 -0K

//    public static String deliverlisturl = contexturl + "/userinfo/deliver/list";  //15.8 查询投简历历史
    public static String deliverlisturl = contexturl + "/prod-api/api/job/deliver/list";  //V2 - OK

    //	public static String byUserIdurl = contexturl+"/system/user/byUserId/{id}";  //15.9 查询用户基本信息
    public static String byUserIdurl = contexturl + "/prod-api/api/common/user/getInfo";  //V2 - OK

    //	public static String resumeurl = contexturl+"/userinfo/resume/{id}";  //15.10 查询用户求职信息
//    public static String resumeurl = contexturl + "/userinfo/resume/";  //15.10 查询用户求职信息
    public static String resumeurl = contexturl + "/prod-api/api/job/resume/queryResumeByUserId/";  //V2 - OK

    public static String updateUserurl = contexturl + "/prod-api/api/job/resume";  //15.11 修改用户求职信息

//    public static String addpositionurl = contexturl + "/userinfo/resume/";//新增用户求职信息 15.12
    public static String addpositionurl = contexturl + "/prod-api/api/job/resume";//V2 - OK 新增或更新用户简历

//    public static String querytelurl = contexturl + "/userinfo/caraction";  //16.1 查询车主电话
    public static String querytelurl = contexturl + "/prod-api/api/park/car/move";  //V2 - OK

//    public static String querymovecarurl = contexturl + "/userinfo/caraction/list";  //16.2 查询挪车记录
    public static String querymovecarurl = contexturl + "/prod-api/api/park/car/history/list";  //V2 - OK

    public static String upload_File_url = contexturl + "/prod-api/common/upload";
}
