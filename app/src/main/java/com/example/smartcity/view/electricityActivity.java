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
import com.example.smartcity.bean.ElectricityBean;
import com.example.smartcity.bean.ElectricityData;
import com.example.smartcity.bean.FeeslistData;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.example.smartcity.baseinfo.Const.tokens;

public class electricityActivity extends AppCompatActivity {
    String params="?doorNo=125&userId=3";
    ElectricityData electricityData;
    ElectricityBean electricityBean;
    List<ElectricityBean> ls=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity);
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
        Log.d("token=", "getData: "+token);
        okHttp.syncGetwithTokenByURL(Connectinfo.electricityurl+params ,  token,new OkHttpUtils.FuncJsonString() {
            private static final String TAG = "Fees";
            @Override
            public void onResponse(String result) {
                Log.d(TAG, "onResponse: " + result);
                electricityData = new Gson().fromJson(result, ElectricityData.class);
                for(int i=0;i< electricityData.getRows().size();i++){
                    ls.add(electricityData.getRows().get(i));
                }
                TextView t=findViewById(R.id.textView7);
                t.setText("22");
                t.setText(ls.get(0).getOwnerName());
            }
        });
    }
}