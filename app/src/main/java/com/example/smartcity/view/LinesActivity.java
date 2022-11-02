package com.example.smartcity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.bean.LinesBean;
import com.example.smartcity.bean.LinesData;
import com.example.smartcity.bean.SubwayInfoData;
import com.example.smartcity.util.OkHttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.example.smartcity.baseinfo.Connectinfo.bylinesurl;
import static com.example.smartcity.baseinfo.Connectinfo.querysubwayinfo;

public class LinesActivity extends AppCompatActivity {
    LinesData linesData;
    LinesBean linesBean;
    List<LinesBean> ls=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lines);
        init();
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |=View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }
        getdata();
    }
    private void getdata() {
        //6.2 查询地铁站详情
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        okHttp.syncJsonStringByURL(bylinesurl+1, new OkHttpUtils.FuncJsonString() {
            private static final String TAG = "HomeFragment";
            @Override
            public void onResponse(String result) {
                Log.d(TAG, "onResponse: " + result);
                linesData= new Gson().fromJson(result, LinesData.class);
                linesBean=linesData.getData();

            }
        });
    }
}