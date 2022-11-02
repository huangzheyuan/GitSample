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
import com.example.smartcity.adapter.LaJiWuPinZhanShiAdapter;
import com.example.smartcity.bean.ZhanshiData;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    //todo 回收历史
    private android.widget.ListView lishi;
    private LaJiWuPinZhanShiAdapter laJiWuPinZhanShiAdapter;
    private ArrayList<ZhanshiData> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initView();
        data();
    }

    private void data() {
        arrayList = new ArrayList<>();
        for (int i = 0; i <20 ; i++) {
            ZhanshiData zhanshiData = new ZhanshiData();
            zhanshiData.setIcon(R.drawable.zhan3);
            zhanshiData.setSuolue("工作人员：张三");
            zhanshiData.setTime("上门时间：2020年12月18日");
            zhanshiData.setTv_name("回收垃圾类型：有害垃圾");
            arrayList.add(zhanshiData);
        }
        laJiWuPinZhanShiAdapter = new LaJiWuPinZhanShiAdapter(arrayList,HistoryActivity.this);
        lishi.setAdapter(laJiWuPinZhanShiAdapter);
    }


    protected void initView() {
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |=View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }
        lishi = (ListView) findViewById(R.id.lishi);
    }

}