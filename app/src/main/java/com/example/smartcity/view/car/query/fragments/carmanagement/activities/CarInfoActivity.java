package com.example.smartcity.view.car.query.fragments.carmanagement.activities;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.smartcity.view.car.query.entities.CarInfoListEntity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class CarInfoActivity extends AppCompatActivity {

    private EditText plateView, engineNoView, typeView, milesView, phoneView;
    private Button okButton, removeButton, cancelButton;

    private CarInfoListEntity.RowsBean row;

    private final String TITLE_ADD = "添加";
    private final String TITLE_MODIFY = "修改";
    private String token, userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }

        TextView textView = findViewById(R.id.headerTitle);
        textView.setText(row == null ? "添加车辆信息" : "修改车辆信息");

        PrefStore prefStore = PrefStore.getInstance(CarInfoActivity.this);
        token = prefStore.getPref("Authorization", null);
        userId = prefStore.getPref("userId", "1");

        row = getIntent().getParcelableExtra("car");


        plateView = findViewById(R.id.editText_plate_number);
        engineNoView = findViewById(R.id.editText_main_number);
        typeView = findViewById(R.id.editText_car_type);
//        milesView = findViewById(R.id.editText_miles);
//        phoneView = findViewById(R.id.editText_phone_number);

        okButton = findViewById(R.id.button_ok);
        removeButton = findViewById(R.id.button_remove);
        cancelButton = findViewById(R.id.button_cancel);

        okButton.setText(row == null ? TITLE_ADD : TITLE_MODIFY);

        if (row != null) {

            plateView.setText(row.getPlateNo());
            engineNoView.setText(row.getEngineNo());
            typeView.setText(row.getType());
//            milesView.setText(row.getMileage());
//            phoneView.setText(row.getPhone());
            removeButton.setVisibility(View.VISIBLE);
        } else {

            removeButton.setVisibility(View.GONE);
        }


        okButton.setOnClickListener(view -> {

            Button button = (Button) view;

            OkHttpUtils.ReqType reqType = button.getText().equals(TITLE_ADD) ? OkHttpUtils.ReqType.POST : OkHttpUtils.ReqType.PUT;

            String plateNumber = plateView.getText().toString();
            String engineNo = engineNoView.getText().toString();
            String type = typeView.getText().toString();
//            String miles = milesView.getText().toString();
//            String phone = phoneView.getText().toString();

            if (plateNumber.trim().equals("")) {

                return;
            }

            if (engineNo.trim().equals("")) {

                return;
            }
            if (type.trim().equals("")) {//选择类型

                return;
            }
//            if (miles.trim().equals("")) {
//
//                return;
//            }
//            if (phone.trim().equals("")) {
//
//                return;
//            }


            OkHttpUtils okHttp = OkHttpUtils.getInstance();

            Map<String, String> params = new HashMap<>();

            if (button.getText().equals(TITLE_ADD)) {

                params.put("userId", userId);
            }else {

                params.put("id", row.getId().toString());
            }
            params.put("plateNo", plateNumber);
            params.put("engineNo", engineNo);
            params.put("type", type);
//            params.put("mileage", miles);
//            params.put("phone", phone);

            okHttp.syncRequestWithBodyAndTokenForJsonString(Connectinfo.addcarsurl, params, token, reqType, result -> {

                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
                int code = jsonObject.get("code").getAsInt();
                String msg = jsonObject.get("msg").getAsString();
                if (code == 200) {

                    Toast.makeText(CarInfoActivity.this, msg, Toast.LENGTH_SHORT).show();
                    finish();
                }
            });


        });

        cancelButton.setOnClickListener(view -> finish());

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id = row.getId();
                OkHttpUtils okHttp = OkHttpUtils.getInstance();

                okHttp.syncRequestWithBodyAndTokenForJsonString(Connectinfo.addcarsurl + "/" + id, null, token, OkHttpUtils.ReqType.DELETE, result -> {

                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
                    int code = jsonObject.get("code").getAsInt();
                    String msg = jsonObject.get("msg").getAsString();
                    if (code == 200) {

                        Toast.makeText(CarInfoActivity.this, msg, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });


    }


}