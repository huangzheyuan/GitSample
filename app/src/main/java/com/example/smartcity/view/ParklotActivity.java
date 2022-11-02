package com.example.smartcity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity.R;
import com.example.smartcity.adapter.NewsAdapter;
import com.example.smartcity.adapter.ParklotAdapter;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.GuildData;
import com.example.smartcity.bean.ParklotBean;
import com.example.smartcity.bean.ParklotData;
import com.example.smartcity.util.OkHttpUtils;
import com.google.gson.Gson;

import static com.example.smartcity.baseinfo.Const.parkid;
import static com.example.smartcity.baseinfo.Const.Pb;

import java.util.ArrayList;
import java.util.List;

import static com.example.smartcity.util.setListViewHeightBasedOnChildren.setListViewHeightBasedOnChildren;

public class ParklotActivity extends AppCompatActivity {
    ParklotData parklotData;
    ParklotAdapter adapter;
    ListView listView;

    Button searchButton;
    EditText searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parklot);
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

        searchButton = findViewById(R.id.button_search);
        searchView = findViewById(R.id.editText_search);

        TextView t = findViewById(R.id.headerTitle);
        t.setText("停车场");

        listView = findViewById(R.id.parklistview);
        getdata();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = searchView.getText().toString();
                getdata(name);
            }
        });
    }

    private void getdata() {
        //todo 主页轮播图
        String params = "?pageNum=1&pageSize=10";
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
//        okHttp.syncJsonStringByURL(Connectinfo.queryparkloturl + params, new OkHttpUtils.FuncJsonString() {
        okHttp.syncJsonStringByURL(Connectinfo.queryparkloturl, new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {

                parklotData = new Gson().fromJson(result, ParklotData.class);

                initNewsListview();
            }
        });
    }

    private void getdata(String name) {
        //todo 主页轮播图
        String params = "?pageNum=1&pageSize=10";
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        okHttp.syncJsonStringByURL(Connectinfo.queryparkloturl + "?parkName=" + name, new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {


                parklotData = new Gson().fromJson(result, ParklotData.class);

                initNewsListview();
            }
        });
    }

    private void initNewsListview() {
        adapter = new ParklotAdapter(ParklotActivity.this, parklotData.getRows());
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                parkid = parklotData.getRows().get(i).getId();
                Pb = parklotData.getRows().get(i);
                Intent intent = new Intent(ParklotActivity.this, ParklotinfoActivity.class);
                startActivity(intent);
            }
        });

        if (parklotData.getRows().size() == 0) {

            Toast.makeText(ParklotActivity.this, "没有符合条件的记录！", Toast.LENGTH_SHORT).show();
        }
    }
}