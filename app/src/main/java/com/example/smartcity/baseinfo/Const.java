package com.example.smartcity.baseinfo;


import com.example.smartcity.bean.BusBean;
import com.example.smartcity.bean.HyuyueBean;
import com.example.smartcity.bean.JiuzhenrenBean;
import com.example.smartcity.bean.KeshitypesBean;
import com.example.smartcity.bean.NewBean;
import com.example.smartcity.bean.NewsData;
import com.example.smartcity.bean.ParklotBean;

public class Const {
	
	public static final String NETWORK_ERROR = "无法连接到服务器";
	public static final String PARSE_ERROR = "数据格式错误";
	public static final String NO_CONNECTION_ERROR = "URL格式错误";
	public static final String TIMEOUT_ERROR = "网络请求超时";
	public static final String Server_ERROR = "服务器端错误，错误码为：";
	public static String tokens; //登陆标志
	public static int userid = -1; //用户ID
	public static String username; //用户名称
	public static String uphone; //用户名称
	public static BusBean uBusBean; //巴士对象
	public static int queryid; //根据预约编号查询预约详情id
	public static HyuyueBean yuyueinfo; //科室信息
	public static int parkid; //停车详情id
	public static int newsid; //新闻详情id
	public static int patientid;//就诊人id
	public static int yiyuanid;//医院id
	public static int linesId;//智慧巴士id
	public static int subwaylinesId;//地铁线路id
	public static ParklotBean Pb;
	public static JiuzhenrenBean jzr;//就诊人对象
	public static KeshitypesBean keshi;//选择的科室信息
	public static String ktype; //科室类型
	public static int illId;//违章id
	public static NewsData.RowsDTO newBeans;//新闻详情

	public  static void clear(){

		tokens = null;
		userid = -1;
		username = uphone = null;
	}
}
