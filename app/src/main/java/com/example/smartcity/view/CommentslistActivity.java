package com.example.smartcity.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.R;
import com.example.smartcity.adapter.CommentsAdapter;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.CommentsBean;
import com.example.smartcity.bean.CommentsData;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.smartcity.baseinfo.Connectinfo.commentslisturl;
import static com.example.smartcity.baseinfo.Const.newsid;
import static com.example.smartcity.baseinfo.Const.tokens;
import static com.example.smartcity.baseinfo.Const.userid;
import static com.example.smartcity.util.setListViewHeightBasedOnChildren.setListViewHeightBasedOnChildren;

public class CommentslistActivity extends AppCompatActivity {
    ListView listView;
    List<CommentsBean> ls = new ArrayList<>();
    CommentsBean commentsBean;
    CommentsData commentsData;
    CommentsAdapter adapter;
    String content;
    EditText info;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentslist);
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
        t.setText("全部评论");


        if (tokens == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }

        submit = findViewById(R.id.submit);
        info = findViewById(R.id.info);
        listView = findViewById(R.id.commentslistview);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(info.getText().toString())) {

                    Toast.makeText(CommentslistActivity.this, "请输入评论内容", Toast.LENGTH_SHORT).show();
                } else {

                    addcommview();
                }
            }
        });
    }

    private void addcommview() {
        content = info.getText().toString();
        insertdata();

    }

    //添加评论
    private void insertdata() {
        //"userId": "1",
        //"pressId": "1",
        //"content": "这个新闻是我读过最好的新闻"
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        String token = null;
        PrefStore prefStore = PrefStore.getInstance(this);
        token = prefStore.getPref("Authorization", null);
        Map<String, String> comment = new HashMap<>();
        comment.put("newsId", String.valueOf(newsid));
        comment.put("content", String.valueOf(content));
        okHttp.syncPostByUrladdHeaderToken(Connectinfo.addcommentsurl, comment, token, new OkHttpUtils.FuncJsonString() {
            @Override
            public void onResponse(String result) {
                if (result.contains("成功")) {
                    Toast.makeText(CommentslistActivity.this, "提交成功.", Toast.LENGTH_SHORT).show();
                    info.setText("");
                    getdata();
                }
            }
        });
    }

    //获取评论
    private void getdata() {
        String params2 = "?newsId=" + newsid;
        OkHttpUtils okHttp2 = OkHttpUtils.getInstance();
        okHttp2.syncJsonStringByURL(commentslisturl + params2, new OkHttpUtils.FuncJsonString() {
            @Override
            public void onResponse(String result) {
                commentsData = new Gson().fromJson(result, CommentsData.class);
                initListview();
            }
        });
    }

    private void initListview() {
        adapter = new CommentsAdapter(CommentslistActivity.this, commentsData.getRows());
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