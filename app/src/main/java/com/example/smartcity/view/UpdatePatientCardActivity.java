package com.example.smartcity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.example.smartcity.baseinfo.Const.jzr;
import static com.example.smartcity.baseinfo.Const.patientid;
import static com.example.smartcity.baseinfo.Const.tokens;
import static com.example.smartcity.baseinfo.Const.userid;

public class UpdatePatientCardActivity extends AppCompatActivity {
    private static final String TAG = "update";
    private EditText et_name, et_cardId, et_tel, et_address;
    private DatePicker datePicker;
    private RadioButton radio_male, radio_female;
    private RadioGroup rb;
    private TextView txtDate;
    private String name, cardId, tel, sex, birthday, adders;
    private String userId = String.valueOf(userid);
    private String id = String.valueOf(patientid);
    static int year, month, day;
    private Button btn;
    private LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient_card);
        initView();
    }

    private void getData() {
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        String token = null;
        PrefStore prefStore = PrefStore.getInstance(this);
        token = prefStore.getPref("Authorization", null);
        Map<String, String> map = new HashMap<>();
        birthday = txtDate.getText().toString();

        map.put("name", name);
        map.put("cardId", cardId);
        map.put("tel", tel);
        map.put("sex", sex);
        map.put("birthday", birthday);
        map.put("adders", adders);
        map.put("id", id);

        okHttp.syncPutByurlwithToken(Connectinfo.updatepatienturl, map, token, new OkHttpUtils.FuncJsonObject() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                parse(jsonObject);
            }
        });
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }
        TextView t = findViewById(R.id.headerTitle);
        t.setText("修改就诊人信息");
        if (tokens == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        et_name = findViewById(R.id.et_name);
        et_name.setText(jzr.getName());
        et_tel = findViewById(R.id.et_tel);
        et_tel.setText(jzr.getTel());

        et_cardId = findViewById(R.id.et_cardId);
        et_cardId.setText(jzr.getCardId());
        et_address = findViewById(R.id.et_address);
        et_address.setText(jzr.getAddress());
        radio_male = findViewById(R.id.rb_male);
        radio_female = findViewById(R.id.rb_female);
        if (jzr.getSex().equals("1")) {
            radio_male.setChecked(true);
        } else {
            radio_female.setChecked(true);
        }
        rb = findViewById(R.id.rg_sex);

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DATE);
        txtDate = findViewById(R.id.tv_date);
        txtDate.setText(year + "-" + (month + 1) + "-" + day);
        btn = findViewById(R.id.btn_update);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = et_name.getText().toString();
                tel = et_tel.getText().toString();
                cardId = et_cardId.getText().toString();
                adders = et_address.getText().toString();
                if (radio_male.isChecked()) {
                    sex = "0";
                } else if (radio_female.isChecked()) {
                    sex = "1";
                }
                getData();
            }
        });

    }

    public void set(View view) {
        datePicker = findViewById(R.id.dp_birthday);
        linear = findViewById(R.id.linear);
        datePicker.setVisibility(View.VISIBLE);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {

            public void onDateChanged(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                UpdatePatientCardActivity.year = year;
                UpdatePatientCardActivity.month = monthOfYear;
                UpdatePatientCardActivity.day = dayOfMonth;
                txtDate.setText(year + "-" + (month + 1) + "-" + day);
                datePicker.setVisibility(View.GONE);
            }
        });
    }

    public void parse(JSONObject jo) {

        if (jo != null) {
            String msg = jo.optString("msg");
            String code = jo.optString("code");

            if (code.equals("200")) {
                Toast.makeText(this, "恭喜您，修改就诊人卡片信息成功！" + msg, Toast.LENGTH_SHORT).show();

                setResult(RESULT_OK);
                finish();

            } else {
                Toast.makeText(this, "对不起，修改就诊人卡片信息失败！" + msg, Toast.LENGTH_SHORT).show();
            }


        } else {
            Toast.makeText(this, "抱歉，登录不成功！", Toast.LENGTH_SHORT).show();
        }

    }
}