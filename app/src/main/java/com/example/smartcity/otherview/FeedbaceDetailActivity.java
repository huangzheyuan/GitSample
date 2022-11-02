package com.example.smartcity.otherview;

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
import com.example.smartcity.bean.FeedBackDetail;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.example.smartcity.view.LoginActivity;
import com.google.gson.Gson;

import static com.example.smartcity.baseinfo.Const.tokens;
import static com.example.smartcity.baseinfo.Const.userid;

public class FeedbaceDetailActivity extends AppCompatActivity {
    private FeedBackDetail feedBackDetail;
    private TextView tv_feedbackDetail;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbace_detail);
        id = getIntent().getIntExtra("id", -1);
        init();

        TextView header = findViewById(R.id.headerTitle);
        header.setText("反馈详情");

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
        tv_feedbackDetail = findViewById(R.id.tv_feedbackdetail);

        if (id != -1) {
            getData(id);
        }
    }

    private void getData(int id) {
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        PrefStore prefStore = PrefStore.getInstance(this);
        String token = prefStore.getPref("Authorization", null);

        okHttp.syncGetwithTokenByURL(Connectinfo.feedbackdetail + id, token, new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {

                feedBackDetail = new Gson().fromJson(result, FeedBackDetail.class);
                tv_feedbackDetail.setText(tv_feedbackDetail.getText() + "\n反馈内容" +
                        feedBackDetail.getData().getContent() +
                        "\n反馈时间" + feedBackDetail.getData().getCreateTime());
            }
        });


    }

}