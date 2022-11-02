package com.example.smartcity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.util.PicassoUtils;

import static com.example.smartcity.baseinfo.Const.newBeans;
import static com.example.smartcity.baseinfo.Const.newsid;


public class NewsInfo extends AppCompatActivity {
    ImageView pic;
    TextView title, info;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
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
        TextView t = findViewById(R.id.headerTitle);
        t.setText("新闻详情");
        pic = findViewById(R.id.pic);
        title = findViewById(R.id.title);
        info = findViewById(R.id.info);
        button = findViewById(R.id.pl);
        title.setText(newBeans.getTitle());
        info.setText(Html.fromHtml(newBeans.getContent()));
        PicassoUtils.loadImageViewSize(this, Connectinfo.contexturl + newBeans.getCover(), 150, 100, pic);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newsid = newBeans.getId();
                Intent intent = new Intent(NewsInfo.this, CommentslistActivity.class);
                startActivity(intent);
            }
        });


    }
}