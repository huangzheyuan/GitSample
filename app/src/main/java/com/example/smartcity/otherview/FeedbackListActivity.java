package com.example.smartcity.otherview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity.R;

import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.FeedBackData;
import com.example.smartcity.bean.FeedbackBean;

import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.example.smartcity.view.LoginActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.example.smartcity.baseinfo.Const.tokens;

public class FeedbackListActivity extends AppCompatActivity {
    private RecyclerView feedbackview;
    FeedBackData feedBackData;
    FeedbackBean feedbackBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_list);
        init();

        TextView header = findViewById(R.id.headerTitle);
        header.setText("反馈列表");
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
        feedbackview = findViewById(R.id.feedbacklist);
        //创建LinearLayoutManager 对象 这里使用
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置瀑布流
        //StaggeredGridLayoutManager layoutmanager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        //设置方向
        layoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        //  GridLayoutManager layoutmanager=new GridLayoutManager(this,3);
        //设置RecyclerView 布局
        feedbackview.setLayoutManager(layoutmanager);
        //设置Adapter
        if (tokens == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        getData(null);
    }

    private void getData(String title) {

        PrefStore prefStore = PrefStore.getInstance(this);
        String params = "?title=" + (title == null ? "" : title);
        String token = prefStore.getPref("Authorization", null);
        OkHttpUtils okHttp = OkHttpUtils.getInstance();

        okHttp.syncGetwithTokenByURL(Connectinfo.feedbacklistlurl + params, token, new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {

                feedBackData = new Gson().fromJson(result, FeedBackData.class);
                FeedbackBaseAdapter adapter = new FeedbackBaseAdapter(feedBackData.getRows(), getApplicationContext());
                feedbackview.setAdapter(adapter);
                adapter.setOnItemClickListener(new OnItemClickListener() {


                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(), FeedbaceDetailActivity.class);
                        intent.putExtra("id", feedBackData.getRows().get(position).getId());
                        startActivity(intent);

                    }

                });

                feedbackview.setItemAnimator(new DefaultItemAnimator());

            }
        });

    }
}