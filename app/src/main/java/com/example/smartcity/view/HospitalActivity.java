package com.example.smartcity.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.R;
import com.example.smartcity.adapter.HospitalListAdapter;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.BannerBean;
import com.example.smartcity.bean.HospitalBean;
import com.example.smartcity.bean.HospitalData;
import com.example.smartcity.bean.HospitalListBean;
import com.example.smartcity.bean.HospitalListData;
import com.example.smartcity.util.BannerLoader;
import com.example.smartcity.util.OkHttpUtils;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.smartcity.baseinfo.Const.yiyuanid;
import static com.example.smartcity.util.setListViewHeightBasedOnChildren.setListViewHeightBasedOnChildren;

public class HospitalActivity extends AppCompatActivity implements OnBannerListener {
    //查询
    private EditText editSubwayName;
    private Button submitSubwayName;
    //todo 主页轮播图
    private Banner banner;
    private List<String> list_img = new ArrayList<>();
    List<HospitalBean> list = new ArrayList<HospitalBean>();
    HospitalData hospitalData;
    BannerBean bannerBean; ;

    //todo 查询医院列表
//    List<HospitalListBean> hospitalListBeanList = new ArrayList<>();
    HospitalListData hospitalListData;
    HospitalListAdapter adapter;
    ListView listView;
    String Queryparams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        init();
        getBanner();
        getHospitalList("");
    }

//    private void getHospitalList() {
//
//        //todo 查询医院列表 /userinfo/registration/list?pageNum=1&pageSize=10
////        String params2 = "?pageNum=1&pageSize=10";
//        String params2 = "?hospitalName=";
//        OkHttpUtils okHttp2 = OkHttpUtils.getInstance();
//        okHttp2.syncJsonStringByURL(Connectinfo.hospitallisturl + params2, new OkHttpUtils.FuncJsonString() {
//            private static final String TAG = "HospitalActivity";
//
//            @Override
//            public void onResponse(String result) {
//                hospitalListData = new Gson().fromJson(result, HospitalListData.class);
//                for (int i = 0; i < hospitalListData.getRows().size(); i++) {
//                    hospitalListBeanList.add(hospitalListData.getRows().get(i));
//                }
//
//                initNewsListview();
//
//            }
//        });
//    }

    private void getHospitalList(String name) {

        //todo 查询医院列表 /userinfo/registration/list?pageNum=1&pageSize=10
        String params2 = "?hospitalName=";
        OkHttpUtils okHttp2 = OkHttpUtils.getInstance();
        okHttp2.syncJsonStringByURL(Connectinfo.hospitallisturl + params2 + name, new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {
                hospitalListData = new Gson().fromJson(result, HospitalListData.class);
                initNewsListview();
            }
        });
    }

    private void getBanner() {
        //todo 主页轮播图
        String params = "?hospitalId=1";
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        String url = Connectinfo.hospitalBannerURL + params;
        okHttp.syncJsonStringByURL(url, new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {

                BannerBean bannerBean = new Gson().fromJson(result, BannerBean.class);

                for (int i = 0; i < bannerBean.getData().size(); i++) {

                    list_img.add(Connectinfo.contexturl + bannerBean.getData().get(i).getImgUrl());
                }
                initBanner();
            }
        });


    }


    private void init() {
        TextView t = findViewById(R.id.headerTitle);
        t.setText("医院");
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }
        banner = (Banner) findViewById(R.id.HospitalBanner);
        listView = findViewById(R.id.hospitalListview);
        editSubwayName = findViewById(R.id.editSubwayName);
        submitSubwayName = findViewById(R.id.submitSubwayName);
        editSubwayName.setText("大连市医大二院");

        submitSubwayName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editSubwayName.getText().toString() != null) {

                    Queryparams = "?hospitalName=" + editSubwayName.getText().toString();
                    queryData(Queryparams);
                    editSubwayName.setText("");
                    editSubwayName.setHint("点击搜索所有医院");

                } else {
//                    getBanner();
                }


            }
        });

    }

    private void queryData(String params) {
        OkHttpUtils okHttp2 = OkHttpUtils.getInstance();
        okHttp2.syncJsonStringByURL(Connectinfo.queryhospitalurl + params, new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {

                hospitalListData = new Gson().fromJson(result, HospitalListData.class);
                initNewsListview();

            }
        });
    }

    private void initBanner() {

        //todo 轮播图

        banner.setImageLoader(new BannerLoader());
        banner.setImages(list_img);
        banner.setBannerAnimation(Transformer.Default);
        banner.setDelayTime(2000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER)
                .setOnBannerListener(this)
                .start();

    }

    private void initNewsListview() {

        adapter = new HospitalListAdapter(HospitalActivity.this, hospitalListData.getRows());
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                yiyuanid = hospitalListData.getRows().get(i).getId();
                Intent intent = new Intent(HospitalActivity.this, JiuzhenrenActivity.class);
                startActivity(intent);
            }
        });

    }

    //轮播图的监听方法
    @Override
    public void OnBannerClick(int position) {
        Log.i("tag", "你点了第" + position + "张轮播图");
    }
}