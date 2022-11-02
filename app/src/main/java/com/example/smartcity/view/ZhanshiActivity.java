package com.example.smartcity.view;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.smartcity.R;

import com.example.smartcity.adapter.LaJiWuPinZhanShiAdapter;
import com.example.smartcity.bean.ZhanshiData;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ZhanshiActivity extends AppCompatActivity {

    private ListView llZhanshi;
    EditText search;
    private TabLayout tabsort;
    private LaJiWuPinZhanShiAdapter laJiWuPinZhanShiAdapter;
    private ArrayList<ZhanshiData> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhanshi);
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |=View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }

        llZhanshi = (ListView) findViewById(R.id.ll_zhanshi);

        search = (EditText) findViewById(R.id.search);
        tabsort = (TabLayout) findViewById(R.id.tabsort);
        arrayList = new ArrayList<>();
        for (int i = 0; i <20 ; i++) {
            ZhanshiData zhanshiData = new ZhanshiData();
            zhanshiData.setIcon(R.drawable.zhan);
            zhanshiData.setSuolue("让用户了解哪些垃圾是属于可回收垃圾，以便到垃圾回收更好选择回收类型");
            zhanshiData.setTime("发布时间：2020年12月12日");
            zhanshiData.setTv_name("其他垃圾");
            arrayList.add(zhanshiData);
        }
        laJiWuPinZhanShiAdapter = new LaJiWuPinZhanShiAdapter(arrayList,ZhanshiActivity.this);
        llZhanshi.setAdapter(laJiWuPinZhanShiAdapter);

        tabsort.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().toString().equals("可回收垃圾")){
                    arrayList.clear();
                    for (int i = 0; i <20 ; i++) {
                        ZhanshiData zhanshiData = new ZhanshiData();
                        zhanshiData.setIcon(R.drawable.zhan2);
                        zhanshiData.setSuolue("让用户了解哪些垃圾是属于可回收垃圾，以便到垃圾回收更好选择回收类型");
                        zhanshiData.setTime("发布时间：2020年12月12日");
                        zhanshiData.setTv_name("其他垃圾");
                        arrayList.add(zhanshiData);
                    }
                    laJiWuPinZhanShiAdapter.notifyDataSetChanged();
                }else {
                    arrayList.clear();

                    for (int i = 0; i <20 ; i++) {
                        ZhanshiData zhanshiData = new ZhanshiData();
                        zhanshiData.setIcon(R.drawable.zhan);
                        zhanshiData.setSuolue("让用户了解哪些垃圾是属于可回收垃圾，以便到垃圾回收更好选择回收类型");
                        zhanshiData.setTime("发布时间：2020年12月12日");
                        zhanshiData.setTv_name("其他垃圾");
                        arrayList.add(zhanshiData);
                    }
                    laJiWuPinZhanShiAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    search.setText(" ");
                    Toast.makeText(ZhanshiActivity.this, "其他垃圾", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }


}
