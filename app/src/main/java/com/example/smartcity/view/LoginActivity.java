package com.example.smartcity.view;

import android.content.Intent;
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
import com.example.smartcity.baseinfo.Const;
import com.example.smartcity.bean.PersonBean;

import com.example.smartcity.otherview.GetInfoActivity;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.smartcity.baseinfo.Const.uphone;
import static com.example.smartcity.baseinfo.Const.userid;
import static com.example.smartcity.baseinfo.Const.username;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText usernameEd, passwordEd;
    private TextView tv_register;
    private String userName, userPassword;
    private PersonBean personBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btn_login);
        usernameEd = this.findViewById(R.id.et_username);
        passwordEd = this.findViewById(R.id.et_password);
        tv_register = findViewById(R.id.tv_newUser);
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegActivity.class);
                startActivity(intent);
            }
        });
        usernameEd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String username = usernameEd.getText().toString();
                if (TextUtils.isEmpty(username)) {
//                    usernameEd.setError("请输入用户名");
                }
            }
        });
        passwordEd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String password = passwordEd.getText().toString();
                if (TextUtils.isEmpty(password)) {
//                    passwordEd.setError("请输入密码");
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获得用户名和密码
                userName = usernameEd.getText().toString();
                userPassword = passwordEd.getText().toString();
                //创建post请求
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", userName);
                params.put("password", userPassword);
                OkHttpUtils okHttp = OkHttpUtils.getInstance();
                okHttp.syncPostByUrl(Connectinfo.loginurl, params, new OkHttpUtils.FuncJsonObject() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        parse(jsonObject);

                    }
                });
            }
        });
    }

    public void parse(JSONObject jo) {

        if (jo != null) {
            String msg = jo.optString("msg");
            String code = jo.optString("code");
            String token = jo.optString("token");
            if (code.equals("200")) {

                Toast.makeText(this, "恭喜您，登录成功！" + msg, Toast.LENGTH_SHORT).show();
                //登录成功保存
                PrefStore pref = PrefStore.getInstance(this);
                pref.savePref("Authorization", token);

                Const.tokens = token;
                save();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(this, "对不起，登录不成功！" + msg, Toast.LENGTH_SHORT).show();
            }


        } else {
            Toast.makeText(this, "抱歉，登录不成功！", Toast.LENGTH_SHORT).show();
        }

    }

    class RefreshThread implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void save() {
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        String token = null;
        PrefStore prefStore = PrefStore.getInstance(this);
        token = prefStore.getPref("Authorization", null);
        okHttp.syncJsonGetwithTokenByURL(Connectinfo.getinfourl, token, new OkHttpUtils.FuncJsonObject() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                parse2(jsonObject);

            }
        });
    }

    public void parse2(JSONObject jo) {

        if (jo != null) {
            String msg = jo.optString("msg");
            String code = jo.optString("code");
            JSONObject user = jo.optJSONObject("user");

            if (code.equals("200")) {

                personBean = new PersonBean();
                personBean.setAvater(user.optString("avatar"));
                personBean.setNickName(user.optString("nickName"));
                personBean.setPhonenumber(user.optString("phonenumber"));
                personBean.setSex(user.optString("sex"));
                personBean.setUserName(user.optString("userName"));
                personBean.setIdCard(jo.optString("idCard"));
                personBean.setUserId(user.optString("userId"));
                userid = Integer.parseInt(personBean.getUserId());
                username = personBean.getUserName();
                uphone = personBean.getPhonenumber();
                //保存个人信息
                PrefStore pref = PrefStore.getInstance(this);

                pref.updateUser(personBean);
//                pref.savePref("usernickname", personBean.getNickName());
//                pref.savePref("avater", personBean.getAvater());
//                pref.savePref("phonenumber", personBean.getPhonenumber());
//                pref.savePref("sex", personBean.getSex());
//                pref.savePref("username", personBean.getUserName());
//                pref.savePref("idCard", personBean.getIdCard());
//                pref.savePref("userId", personBean.getUserId());
            }

        }
    }


}


