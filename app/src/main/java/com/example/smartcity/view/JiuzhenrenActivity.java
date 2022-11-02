package com.example.smartcity.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity.R;
import com.example.smartcity.adapter.JzrAdapter;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.JiuzhenrenBean;
import com.example.smartcity.bean.JiuzhenrenData;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.example.smartcity.baseinfo.Const.jzr;
import static com.example.smartcity.baseinfo.Const.tokens;
import static com.example.smartcity.baseinfo.Const.userid;
import static com.example.smartcity.util.setListViewHeightBasedOnChildren.setListViewHeightBasedOnChildren;
import static com.example.smartcity.baseinfo.Const.patientid;

public class JiuzhenrenActivity extends AppCompatActivity {
    ListView listView;
    JiuzhenrenData jiuzhenrenData;
    JzrAdapter adapter;
    Button addjzr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiuzhenren);
        init();
        getdata();
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
        TextView t = findViewById(R.id.headerTitle);
        t.setText("就诊人");
        if (tokens == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        listView = findViewById(R.id.jzrlistview);
        addjzr = findViewById(R.id.addjzr);

        addjzr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JiuzhenrenActivity.this, AddjzrActivity.class);
                startActivityForResult(intent, 100);
            }
        });
    }



    private void getdata() {


        if (userid < 0) {

            finish();
            return;
        }
        String params = "?pageNum=1&pageSize=10&userId=" + userid;

        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        String token = null;
        PrefStore prefStore = PrefStore.getInstance(this);
        token = prefStore.getPref("Authorization", null);
        okHttp.syncGetwithTokenByURL(Connectinfo.querypatienturl, token, new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {

                TextView t = findViewById(R.id.headerTitle);
                jiuzhenrenData = new Gson().fromJson(result, JiuzhenrenData.class);
                t.setText(jiuzhenrenData.getMsg());

                initListview();
            }
        });
    }

    private void initListview() {
        adapter = new JzrAdapter(JiuzhenrenActivity.this, jiuzhenrenData.getRows()) {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                final View view = super.getView(position, convertView, parent);
                final Button guahao = view.findViewById(R.id.guahao);
                final Button update = view.findViewById(R.id.update);



                guahao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jzr = jiuzhenrenData.getRows().get(position);
                        patientid = jiuzhenrenData.getRows().get(position).getId();
                        Intent intent = new Intent(JiuzhenrenActivity.this, HospitalInfoActivity.class);
                        startActivity(intent);
                    }
                });

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        jzr = jiuzhenrenData.getRows().get(position);
                        patientid = jiuzhenrenData.getRows().get(position).getId();

                        Intent intent = new Intent(JiuzhenrenActivity.this, UpdatePatientCardActivity.class);
                        startActivityForResult(intent, 100);

                    }
                });
                return view;
            }
        };
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {

            getdata();
        }
    }
}