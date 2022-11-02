package com.example.smartcity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.smartcity.R;

public class BaomingActivity extends AppCompatActivity {
    TextView info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baoming);
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
        TextView t=findViewById(R.id.headerTitle);
        t.setText("报名");
        info=findViewById(R.id.info);
        info.setText("市出台了《关于加快竹产业发展的实施意见》,2015年编制了《泸州市竹产业发展总体规划(2015—2025年》和《泸州竹产业园区规划》等控制性详规，明确\"主攻二产、发展三产、提升一产\"的竹区新思路,确立了“立体经营、综合开发、园区拉动、三产联动\"的竹经济新格局,描绘了依托生态竹林升级村美业兴民富的新蓝图。到2017年，全市林业总产值214.8亿元。其中竹产业产值100.28亿元，竹产业人均增收1387元。\n");
    }
}