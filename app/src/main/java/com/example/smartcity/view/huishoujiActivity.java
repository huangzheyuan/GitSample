package com.example.smartcity.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.adapter.JiQiAdapter;
import com.example.smartcity.bean.JiQiData;

import java.util.ArrayList;

public class huishoujiActivity extends AppCompatActivity {
    private androidx.appcompat.widget.Toolbar toolbar;
    private android.widget.TextView tvtittle;
    private android.widget.ListView llJiqi;
    private ArrayList<JiQiData> arrayList;
    JiQiAdapter jiQiAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huishouji);
        init();
        data();
    }

    private void data() {
        arrayList = new ArrayList<>();
        for (int i = 0; i <20 ; i++) {
            JiQiData jiqo = new JiQiData();
            jiqo.setIcon(R.drawable.zhan5);
            jiqo.setSuolue("回收种类：有害垃圾与电子垃圾");
            jiqo.setTime("剩余钱币100%");
            jiqo.setTv_name("回收"+i+"号");
            jiqo.setStation("距离您："+i+"Km");
            arrayList.add(jiqo);
        }
        jiQiAdapter = new JiQiAdapter(arrayList,huishoujiActivity.this);
        llJiqi.setAdapter(jiQiAdapter);
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
        llJiqi = (ListView) findViewById(R.id.ll_jiqi);
    }
}