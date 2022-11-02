package com.example.smartcity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.baseinfo.Const;
import com.example.smartcity.bean.GuildData;
import com.example.smartcity.bean.IllegaBean;
import com.example.smartcity.bean.IllegaData;
import com.example.smartcity.bean.IllegalBean;
import com.example.smartcity.bean.IllegalData;
import com.example.smartcity.bean.QueryyuyueData;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.google.gson.Gson;

import static com.example.smartcity.baseinfo.Connectinfo.getinfourl;
import static com.example.smartcity.baseinfo.Connectinfo.queryillegalidurl;
import static com.example.smartcity.baseinfo.Const.illId;
import static com.example.smartcity.baseinfo.Const.queryid;


public class illegalinfoActivity extends AppCompatActivity {
    TextView t;
    IllegalData illegaData;
    IllegalBean illegaBean;
    private TextView trafficOffence;
    private TextView licencePlate;
    private TextView illegalSites, badTime, catType;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illegalinfo);
        id = getIntent().getIntExtra("id", -1);
        init();
        getdata();

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
        t = findViewById(R.id.headerTitle);
        t.setText("违章详情");
        trafficOffence = findViewById(R.id.trafficOffence);
        licencePlate = findViewById(R.id.licencePlate);
        illegalSites = findViewById(R.id.illegalSites);
        badTime = findViewById(R.id.badTime);
        catType = findViewById(R.id.catType);

//        trafficOffence.setText(illegaBean.getTrafficOffence());
//        licencePlate.setText(illegaBean.getLicencePlate());
//        illegalSites.setText(illegaBean.getIllegalSites());
//        badTime.setText(illegaBean.getBadTime());
//        catType.setText(illegaBean.getCatType());

    }

    private void getdata() {
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        okHttp.syncGetwithTokenByURL(queryillegalidurl + illId, Const.tokens, new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {

                illegaData = new Gson().fromJson(result, IllegalData.class);
                illegaBean = illegaData.getData();
                trafficOffence.setText(illegaBean.getDisposeState());
                licencePlate.setText("车牌号:" + illegaBean.getLicencePlate());
                illegalSites.setText("地点:" + illegaBean.getIllegalSites());
                badTime.setText("时间:" + illegaBean.getBadTime());
                catType.setText("车辆类型:" + illegaBean.getCatType());
            }
        });

    }
}