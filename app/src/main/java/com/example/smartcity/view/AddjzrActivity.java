package com.example.smartcity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.smartcity.baseinfo.Const.tokens;
import static com.example.smartcity.baseinfo.Const.userid;

import org.json.JSONException;
import org.json.JSONObject;

public class AddjzrActivity extends AppCompatActivity {
    EditText name, cardId, tel, sex, birthday, address;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addjzr);
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
        t.setText("新增就诊人");
        name = findViewById(R.id.hname);
        cardId = findViewById(R.id.hcardid);
        tel = findViewById(R.id.htel);
        sex = findViewById(R.id.hsex);
        birthday = findViewById(R.id.hbirthday);
        address = findViewById(R.id.haddress);
        submit = findViewById(R.id.hsubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getdata();
            }
        });

        if (tokens == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private void getdata() {
        //"name":"张三",
        //"cardId":"112222222233333333",
        //"tel":"12345678",
        //"sex":1,
        //"birthday":"2012-06-12",
        //"adders":"大连市",

        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        PrefStore prefStore = PrefStore.getInstance(this);
        String token = prefStore.getPref("Authorization", null);
        Map<String, String> ls = new HashMap<>();
        ls.put("name", name.getText().toString());
        ls.put("cardId", cardId.getText().toString());
        ls.put("tel", tel.getText().toString());
        ls.put("sex", sex.getText().toString());
        ls.put("birthday", birthday.getText().toString());
        ls.put("address", address.getText().toString());

        okHttp.syncPostByUrladdHeaderToken(Connectinfo.patienturl, ls, token, new OkHttpUtils.FuncJsonString() {
            @Override
            public void onResponse(String result) {
                JSONObject jsonObject = null;

                try {
                    jsonObject = new JSONObject(result);
                    int code = jsonObject.getInt("code");
                    if(code == 200){
                        setResult(RESULT_OK);
                        finish();
                        Toast.makeText(AddjzrActivity.this, jsonObject.getString("msg"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}