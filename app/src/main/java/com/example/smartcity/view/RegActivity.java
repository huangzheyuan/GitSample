package com.example.smartcity.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.util.OkHttpUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegActivity extends AppCompatActivity {

    private EditText usernameEt, passwordEt, NickEt, phoneEt;
    private EditText emailView, idCardView;
    private RadioGroup sexRg;

    private String avatarPath = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |=View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }
        TextView t=findViewById(R.id.headerTitle);
        t.setText("注册新用户");
        usernameEt = findViewById(R.id.RegUserName);
        passwordEt = findViewById(R.id.RegPassWord);
        NickEt = findViewById(R.id.RegNick);
        phoneEt = findViewById(R.id.RegPhone);
        sexRg =  findViewById(R.id.RegSex);
        emailView = findViewById(R.id.editText_email);
        idCardView = findViewById(R.id.editText_idcard);

        Button regBtn =  findViewById(R.id.RegBtn);
        regBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String userName = usernameEt.getText().toString();
                String userPassword = passwordEt.getText().toString();
                String nick = NickEt.getText().toString();
                String phone = phoneEt.getText().toString();
                RadioButton rb = (RadioButton) findViewById(sexRg.getCheckedRadioButtonId());
                String s = rb.getText().toString();
                String email = emailView.getText().toString();
                String idCard = idCardView.getText().toString();

                String sex = "0";
                if (s.equals("女")) {
                    sex = "1";
                }
                Log.v("reg", userName + " " + userPassword + " " + sex);
                //创建post请求
                Map<String, String> params = new HashMap<String, String>();
                params.put("avatar", avatarPath);
                params.put("userName", userName);
                params.put("password", userPassword);
                params.put("nickName", nick);
                params.put("phonenumber", phone);
                params.put("email", email);
                params.put("idCard", idCard);
                params.put("sex", sex);
                Gson gson=new Gson();
                Log.d("params", "onClick: "+gson.toJson(params));
                OkHttpUtils okHttp = OkHttpUtils.getInstance();
                okHttp.syncPostByUrl(Connectinfo.regurl, params, new OkHttpUtils.FuncJsonObject() {
                    private static final String TAG ="regsiter" ;

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d(TAG, "onResponse: register"+jsonObject);
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
            if (code.equals("200")){
                Toast.makeText(this, "恭喜您，注册成功！"+msg, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "对不起，注册不成功！"+msg, Toast.LENGTH_SHORT).show();
            }


        } else {
            Toast.makeText(this, "抱歉，注册不成功！", Toast.LENGTH_SHORT).show();
        }
    
    }

}