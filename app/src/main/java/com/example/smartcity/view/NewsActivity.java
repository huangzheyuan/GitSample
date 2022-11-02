package com.example.smartcity.view;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.R;
import com.example.smartcity.adapter.NewsAdapter;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.NewBean;
import com.example.smartcity.bean.NewsData;
import com.example.smartcity.util.OkHttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    android.widget.ListView ListView;
    private NewsAdapter adapter;
    List<NewBean> ls=new ArrayList<NewBean>();
    NewsData getnews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |=View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }
        ListView=findViewById(R.id.newsActivityList);
        getData();


    }

    private void getData() {
        String params = "?pageNum=1&pageSize=10&pressCategory=48";
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        okHttp.syncJsonStringByURL(Connectinfo.newsurl , new OkHttpUtils.FuncJsonString() {
            private static final String TAG = "NewsActivity";

            @Override
            public void onResponse(String result) {
                Log.d(TAG, "onResponse: " + result);

//                getnews = new Gson().fromJson(result, NewsData.class);
//                for(int i=0;i<getnews.getRows().size();i++){
//                    ls.add(getnews.getRows().get(i));
//                }
//                adapter = new NewsAdapter(NewsActivity.this, ls);
//                ListView.setAdapter(adapter);


            }
        });

    }
}