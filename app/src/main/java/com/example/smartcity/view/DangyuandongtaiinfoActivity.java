package com.example.smartcity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.smartcity.R;


public class DangyuandongtaiinfoActivity extends AppCompatActivity {
    LinearLayout ls1,ls2,ls3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangyuandongtaiinfo);
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
        ls1=findViewById(R.id.ls1);
        ls2=findViewById(R.id.ls2);
        ls3=findViewById(R.id.ls3);
        ls1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DangyuandongtaiinfoActivity.this, DangjiandongtaiActivity.class);
                startActivity(intent);
            }
        });
        ls2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DangyuandongtaiinfoActivity.this, DangjiandongtaiActivity.class);
                startActivity(intent);
            }
        });
        ls3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DangyuandongtaiinfoActivity.this, DangjiandongtaiActivity.class);
                startActivity(intent);
            }
        });
    }
}