package com.example.smartcity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.adapter.BusLineAdapter;
import com.example.smartcity.adapter.NewsAdapter;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.BusStopBean;
import com.example.smartcity.bean.BusStopData;
import com.example.smartcity.bean.GuildData;
import com.example.smartcity.util.OkHttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.example.smartcity.baseinfo.Const.newsid;
import static com.example.smartcity.util.setListViewHeightBasedOnChildren.setListViewHeightBasedOnChildren;

public class BusStopActivity extends AppCompatActivity {
    String params = "?pageNum=1&pageSize=10&linesId=1";
    BusStopData busStopData;
    List<BusStopBean> ls=new ArrayList<>();
    BusLineAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_stop);
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
        listView=findViewById(R.id.linlistview);
        getdata();
    }

    private void getdata() {
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        okHttp.syncJsonStringByURL(Connectinfo.bybusStopurl + params, new OkHttpUtils.FuncJsonString() {
            private static final String TAG = "HomeFragment";

            @Override
            public void onResponse(String result) {
                Log.d(TAG, "onResponse: " + result);

                busStopData = new Gson().fromJson(result,BusStopData.class);

                for (int i = 0; i < busStopData.getRows().size(); i++) {
                    ls.add(busStopData.getRows().get(i));
                }
                ListviewInit();
            }
        });
    }

    private void ListviewInit() {
        adapter = new BusLineAdapter(BusStopActivity.this, ls);
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}