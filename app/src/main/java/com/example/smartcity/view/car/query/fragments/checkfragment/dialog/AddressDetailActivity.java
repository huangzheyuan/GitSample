package com.example.smartcity.view.car.query.fragments.checkfragment.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.example.smartcity.view.car.query.entities.AddressDetail;
import com.example.smartcity.view.car.query.entities.AddressListEntity;
import com.example.smartcity.view.car.query.entities.CarInfoListEntity;
import com.google.gson.Gson;

public class AddressDetailActivity extends AppCompatActivity {

    private TextView headerTitleView, placeNameView, remarksView, addressView, phoneView;
    private Button cancelButton;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        id = getIntent().getIntExtra("id", -1);
        setContentView(R.layout.activity_address_detail);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }

        headerTitleView = findViewById(R.id.headerTitle);
        placeNameView = findViewById(R.id.textView_place_name);
        remarksView = findViewById(R.id.textView_remarks);
        addressView = findViewById(R.id.textView_address);
        phoneView = findViewById(R.id.textView_phone);
        cancelButton = findViewById(R.id.button_cancel);

        headerTitleView.setText("地点详情");

        cancelButton.setOnClickListener(view -> finish());

        if (id >= 0) {

            getData(id);
        }
    }


    void getData(int id) {

        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        PrefStore prefStore = PrefStore.getInstance(this);
        String token = prefStore.getPref("Authorization", null);
        String url = String.format("%s%d", Connectinfo.queryplaceinfourl, id);

        if (token != null) {

            okHttp.syncJsonGetwithTokenByURL(url, token, jsonObject -> {

                Gson gson = new Gson();

                AddressDetail addressDetail = gson.fromJson(jsonObject.toString(), AddressDetail.class);

                if (addressDetail.getCode() == 200) {

                    placeNameView.setText("检车地点：" + addressDetail.getData().getPlaceName());
                    remarksView.setText("检车说明：" + addressDetail.getData().getRemarks());
                    addressView.setText("地址：" + addressDetail.getData().getAddress());
                    phoneView.setText("电话：" + addressDetail.getData().getPhone());
                }

            });
        }
    }
}