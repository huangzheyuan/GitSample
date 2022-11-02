package com.example.smartcity.otherview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.widget.ListView;


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

    private ListView ListView;
    private NewsAdapter adapter;
    List<NewBean> ls=new ArrayList<NewBean>();
    NewsData getnews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news);
        ListView = findViewById(R.id.news_listview);
        getData();


    }

    //todo /press/press/list?pageNum=1&pageSize=10&pressCategory=48
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
//                adapter = new NewsAdapter(getApplicationContext(), ls);
//                ListView.setAdapter(adapter);


            }
        });

    }




}