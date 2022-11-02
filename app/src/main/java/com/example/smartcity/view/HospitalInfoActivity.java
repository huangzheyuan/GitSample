package com.example.smartcity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.adapter.HtypesAdapter;
import com.example.smartcity.adapter.HyuyueAdapter;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.HinfoBean;
import com.example.smartcity.bean.HinfoData;
import com.example.smartcity.bean.HyuyueBean;
import com.example.smartcity.bean.HyuyueData;
import com.example.smartcity.bean.KeshitypesBean;
import com.example.smartcity.bean.KeshitypesData;
import com.example.smartcity.bean.SubwayInfoData;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.example.smartcity.baseinfo.Connectinfo.querysubwayinfo;
import static com.example.smartcity.baseinfo.Connectinfo.registrationurl;
import static com.example.smartcity.baseinfo.Const.keshi;
import static com.example.smartcity.baseinfo.Const.ktype;
import static com.example.smartcity.baseinfo.Const.tokens;
import static com.example.smartcity.baseinfo.Const.userid;
import static com.example.smartcity.baseinfo.Const.yiyuanid;
import static com.example.smartcity.baseinfo.Const.yuyueinfo;
import static com.example.smartcity.baseinfo.Const.queryid;
import static com.example.smartcity.util.setListViewHeightBasedOnChildren.setListViewHeightBasedOnChildren;

public class HospitalInfoActivity extends AppCompatActivity {
    KeshitypesData keshitypesData;
    HtypesAdapter adapter;
    ListView listView;
    Button zhuanjia, putong, yuyue;
    //    String params = "?pageNum=1&pageSize=10&did=1";
    //todo 预约
    HyuyueData hyuyueData;
    HyuyueAdapter adapter2;
    //医院详情
    HinfoData hinfoData;
    HinfoBean hinfoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_info);
        //专家号
        ktype = "1";
        init();
        getdata(ktype);
        getdata3();
//        initListview();
    }

    //科室分类
    private void getdata(String type) {
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        PrefStore prefStore = PrefStore.getInstance(this);
        String token = prefStore.getPref("Authorization", null);
        okHttp.syncGetwithTokenByURL(Connectinfo.querytypes + "?type=" + type, token, new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {
                keshitypesData = new Gson().fromJson(result, KeshitypesData.class);
                initListview();
            }
        });
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }
        if (tokens == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        TextView t = findViewById(R.id.headerTitle);
        t.setText("挂号");
        listView = findViewById(R.id.htypeslistview);
        zhuanjia = findViewById(R.id.zhuanjia);//专家
        putong = findViewById(R.id.putong);//普通
        yuyue = findViewById(R.id.queryyuyue);//预约

        zhuanjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ktype = "1";
                getdata(ktype);
            }
        });

        putong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ktype = "2";
                getdata(ktype);
            }
        });

        yuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                params = "?pageNum=1&pageSize=10&userId=" + userid;
                getdata2();
            }
        });
    }

    //预约查询
    private void getdata2() {
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        PrefStore prefStore = PrefStore.getInstance(this);
        String token = prefStore.getPref("Authorization", null);

        okHttp.syncGetwithTokenByURL(Connectinfo.queryyuyuelisturl, token, new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {
                hyuyueData = new Gson().fromJson(result, HyuyueData.class);
                initListview2();
            }
        });
    }


    private void getdata3() {
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        okHttp.syncJsonStringByURL(registrationurl + yiyuanid, new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {
                hinfoData = new Gson().fromJson(result, HinfoData.class);
                hinfoBean = hinfoData.getData();
                TextView t = findViewById(R.id.headerTitle);
                t.setText(result);
                t.setText(hinfoData.getMsg());
            }
        });
    }

    private void initListview() {

        adapter = new HtypesAdapter(HospitalInfoActivity.this, keshitypesData.getRows());
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int categoryId = keshitypesData.getRows().get(i).getId();
                String categoryName = keshitypesData.getRows().get(i).getCategoryName();
                int money = keshitypesData.getRows().get(i).getMoney();
                String type = keshitypesData.getRows().get(i).getType();

                Intent intent = new Intent(HospitalInfoActivity.this, ByidqueryinfoActivity.class);
                intent.putExtra("categoryId", categoryId);
                intent.putExtra("categoryName", categoryName);
                intent.putExtra("money", money);
                intent.putExtra("type", type);


                startActivity(intent);
            }
        });
    }

    private void initListview2() {
        adapter2 = new HyuyueAdapter(HospitalInfoActivity.this, hyuyueData.getRows());
        listView.setAdapter(adapter2);
        setListViewHeightBasedOnChildren(listView);

    }
}