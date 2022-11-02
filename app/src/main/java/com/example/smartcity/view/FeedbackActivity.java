package com.example.smartcity.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.otherview.FeedbackListActivity;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;

import java.util.HashMap;
import java.util.Map;

import static com.example.smartcity.baseinfo.Const.tokens;
import static com.example.smartcity.baseinfo.Const.userid;

//todo 意见反馈
public class FeedbackActivity extends AppCompatActivity {
    Button btn_feed, submit;
    EditText editText_content, editText_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
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
        t.setText("反馈");
        if (tokens == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        btn_feed = findViewById(R.id.feedbackList);
        btn_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FeedbackActivity.this, FeedbackListActivity.class);
                startActivity(intent);
            }
        });
        editText_content = findViewById(R.id.feedback_content);
        editText_title = findViewById(R.id.feedback_title);
        submit = findViewById(R.id.button2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = editText_title.getText().toString();
                String content = editText_content.getText().toString();
                putData(title, content);
            }
        });
    }

    private void putData(String title, String content) {

        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        PrefStore prefStore = PrefStore.getInstance(this);
        String token = prefStore.getPref("Authorization", null);
        Map<String, String> param = new HashMap<>();
        param.put("content", content);
        param.put("title", title);
        okHttp.syncPostByUrladdHeaderToken(Connectinfo.addfeedback, param, token, new OkHttpUtils.FuncJsonString() {
            @Override
            public void onResponse(String result) {

                if (result.contains("成功")) {
                    Toast.makeText(FeedbackActivity.this, "反馈成功", Toast.LENGTH_SHORT).show();
                    editText_content.setText("");
                    editText_title.setText("");
                }
            }
        });
    }
}