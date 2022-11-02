package com.example.smartcity.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.R;
import com.example.smartcity.adapter.OrderAdapter;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.baseinfo.Const;
import com.example.smartcity.bean.OrderBean;
import com.example.smartcity.bean.OrderData;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.example.smartcity.baseinfo.Const.tokens;
import static com.example.smartcity.baseinfo.Const.userid;
import static com.example.smartcity.util.setListViewHeightBasedOnChildren.setListViewHeightBasedOnChildren;

//todo 订单列表
public class OrderListActivity extends AppCompatActivity {
    ListView listView;
    private OrderAdapter adapter;
    List<OrderBean> orderList = new ArrayList<OrderBean>();
    OrderData orderData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
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
        TextView header = findViewById(R.id.headerTitle);
        header.setText("订单列表");
        if (tokens == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        listView = findViewById(R.id.orderlistview);
        getData();
    }

    private void getData() {
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        okHttp.syncGetwithTokenByURL(Connectinfo.queryorderslist, tokens, new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {
                orderData = new Gson().fromJson(result, OrderData.class);

                adapter = new OrderAdapter(OrderListActivity.this, orderData.getData());
                listView.setAdapter(adapter);
                setListViewHeightBasedOnChildren(listView);
            }
        });


    }

}