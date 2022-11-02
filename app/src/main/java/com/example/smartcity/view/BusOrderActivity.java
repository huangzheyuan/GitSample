package com.example.smartcity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.adapter.BusOrderAdapter;
import com.example.smartcity.adapter.NewsAdapter;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.BusOrderBean;
import com.example.smartcity.bean.BusOrderData;
import com.example.smartcity.bean.GuildData;
import com.example.smartcity.bean.OrderBean;
import com.example.smartcity.bean.QueryyuyueData;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.example.smartcity.baseinfo.Const.newsid;
import static com.example.smartcity.baseinfo.Const.queryid;
import static com.example.smartcity.baseinfo.Const.tokens;
import static com.example.smartcity.baseinfo.Const.userid;
import static com.example.smartcity.util.setListViewHeightBasedOnChildren.setListViewHeightBasedOnChildren;

public class BusOrderActivity extends AppCompatActivity {
    List<BusOrderBean> ls = new ArrayList<>();
    BusOrderData data;
    ListView listView;
    BusOrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_order);
        init();
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

        }
        TextView t = findViewById(R.id.headerTitle);
        t.setText("订单记录");
        listView = findViewById(R.id.busorderlistview);
        getdata();
        String token = null;
        PrefStore prefStore = PrefStore.getInstance(this);
        token = prefStore.getPref("Authorization", null);
        if (token == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void getdata() {
        String params = "?pageNum=1&pageSize=10&userId=" + userid;
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        System.out.println("id" + queryid);
        String token = null;
        PrefStore prefStore = PrefStore.getInstance(this);
        token = prefStore.getPref("Authorization", null);
        Log.d("token=", "getData: " + token);

        okHttp.syncGetwithTokenByURL(Connectinfo.querybusOrderssurl + params, token, new OkHttpUtils.FuncJsonString() {
            private static final String TAG = "FeedbackDetailActivity";

            @Override
            public void onResponse(String result) {
                Log.d(TAG, "onResponse: " + result);
                data = new Gson().fromJson(result, BusOrderData.class);

                for (int i = 0; i < data.getRows().size(); i++) {
                    ls.add(data.getRows().get(i));
                }
                initView();
            }
        });


    }

    private void initView() {
        adapter = new BusOrderAdapter(BusOrderActivity.this, ls);
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                newsid=ls.get(i).getId();
//                Intent intent=new Intent(getActivity(), CommentslistActivity.class);
//                startActivity(intent);
            }
        });
    }
}