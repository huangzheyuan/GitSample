package com.example.smartcity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.adapter.ParklotAdapter;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.IllegaData;
import com.example.smartcity.bean.ParkinfoData;
import com.example.smartcity.bean.ParklotBean;
import com.example.smartcity.bean.QueryyuyueData;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PicassoUtils;
import com.example.smartcity.util.PrefStore;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.example.smartcity.baseinfo.Const.parkid;
import static com.example.smartcity.baseinfo.Const.queryid;
import static com.example.smartcity.baseinfo.Const.Pb;
import static com.example.smartcity.baseinfo.Const.tokens;

public class ParklotinfoActivity extends AppCompatActivity {
    ParkinfoData parkinfoData;
    ImageView pimgUrl;
    private TextView parkName, vacancy, priceCaps, rates, distance, address, allPark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parklotinfo);
        init();
        getdata();
        initview();
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }
        if (tokens == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        TextView t = findViewById(R.id.headerTitle);
        t.setText("停车场详情");
    }

    private void getdata() {

        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        String token = null;
        PrefStore prefStore = PrefStore.getInstance(this);
        token = prefStore.getPref("Authorization", null);

        okHttp.syncGetwithTokenByURL(Connectinfo.getparklituinfourl + parkid, token, new OkHttpUtils.FuncJsonString() {
            @Override
            public void onResponse(String result) {
                parkinfoData = new Gson().fromJson(result, ParkinfoData.class);
                initview();
            }
        });
    }

    private void initview() {
        TextView t = findViewById(R.id.headerTitle);
        t.setText("停车场详情");
        pimgUrl = findViewById(R.id.pimgUrl);
        parkName = findViewById(R.id.parkName);
        vacancy = findViewById(R.id.vacancy);
        priceCaps = findViewById(R.id.priceCaps);
        rates = findViewById(R.id.rates);
        distance = findViewById(R.id.distance);
        address = findViewById(R.id.address);
        allPark = findViewById(R.id.allPark);
        ParklotBean bean = Pb;
        parkName.setText(bean.getParkName());
        vacancy.setText("空缺:" + bean.getVacancy());
        parkName.setText(bean.getParkName());
        priceCaps.setText("价格上限:" + bean.getPriceCaps());
        rates.setText("价格:" + bean.getRates());
        distance.setText("距离:" + bean.getDistance());
        address.setText("地址:" + bean.getAddress());
        allPark.setText("所有空位:" + bean.getAllPark());
        PicassoUtils.loadImageViewSize(ParklotinfoActivity.this, Connectinfo.contexturl + bean.getImgUrl(), 150, 100, pimgUrl);
    }
}