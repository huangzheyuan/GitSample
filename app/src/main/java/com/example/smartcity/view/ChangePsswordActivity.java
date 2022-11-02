package com.example.smartcity.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//todo 修改密码
public class ChangePsswordActivity extends AppCompatActivity {
    EditText password, newpassword, newpassord1;
    Button submit;
    String token = null;
    PrefStore prefStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pssword);
        prefStore = PrefStore.getInstance(this);
        token = prefStore.getPref("Authorization", null);
        if (token == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        init();

    }

    private void getdata() {

        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        Map<String, String> ls = new HashMap<>();
        ls.put("oldPassword", password.getText().toString());
        ls.put("newPassword", newpassword.getText().toString());
        okHttp.syncPutByurlwithToken(Connectinfo.resetPwd, ls, token, new OkHttpUtils.FuncJsonObject() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e("result", "onResponse: " + jsonObject);
                parse(jsonObject);
            }
        });


    }

    public void parse(JSONObject jo) {

        if (jo != null) {
            String msg = jo.optString("msg");
            String code = jo.optString("code");
            if (code.equals("200")) {
                Toast.makeText(this, "恭喜您，密码修改成功！" + msg, Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "对不起，密码修改不成功！" + msg, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "抱歉，登录不成功！", Toast.LENGTH_SHORT).show();
        }

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
        header.setText("修改密码");

        password = findViewById(R.id.oldpassword);
        newpassword = findViewById(R.id.newpassword1);
        newpassord1 = findViewById(R.id.newpassword2);
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String oldpassword = password.getText().toString();
                if (TextUtils.isEmpty(oldpassword)) {
                    password.setError("请输入旧密码");
                }
            }
        });
        newpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String newpass = newpassword.getText().toString();
                if (TextUtils.isEmpty(newpass)) {
                    newpassword.setError("请输入密码");
                }
            }
        });
        if (!newpassword.equals(newpassord1)) {
            Toast.makeText(getApplicationContext(), "两次输入密码不一致", Toast.LENGTH_LONG).show();
        }
        newpassord1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String newpass1 = newpassord1.getText().toString();
                if (TextUtils.isEmpty(newpass1)) {
                    newpassord1.setError("请输入密码");
                }
            }
        });

        submit = findViewById(R.id.update);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (token != null)
                    getdata();

            }
        });
    }
}