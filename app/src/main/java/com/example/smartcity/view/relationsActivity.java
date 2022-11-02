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
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;

import java.util.HashMap;
import java.util.Map;

import static com.example.smartcity.baseinfo.Const.tokens;
import static com.example.smartcity.baseinfo.Const.userid;

public class relationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relations);
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
        if(tokens==null){
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        getdata();
    }

    private void getdata() {
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        String token=null;
        PrefStore prefStore=PrefStore.getInstance(this);
        token= prefStore.getPref("Authorization",null);
        Log.d("token=", token);
        Map<String,String> ls=new HashMap<>();
        ls.put("obId","8");
        ls.put("userId", "2");
        ls.put("doorId","2");
        ls.put("classifyId","1");//1 为电费 2 为水费
        //}         此处是put请求
        okHttp.syncPostByUrladdHeaderToken(Connectinfo.insertrelationsurl,ls, token, new OkHttpUtils.FuncJsonString() {
            @Override
            public void onResponse(String result) {
                Log.e("sdsada","onResponse: "+result);
                TextView textView=findViewById(R.id.textView8);
                textView.setText(result);
                //todo  {"msg":"Request method 'POST' not supported","code":500}
            }
        });

    }

}