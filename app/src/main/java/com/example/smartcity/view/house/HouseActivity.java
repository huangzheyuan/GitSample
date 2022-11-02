package com.example.smartcity.view.house;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.example.smartcity.view.car.query.entities.AddressListEntity;
import com.example.smartcity.view.house.adapter.HouseListAdpater;
import com.example.smartcity.view.house.entities.HouseListEntity;
import com.google.gson.Gson;

import java.util.List;

public class HouseActivity extends AppCompatActivity {

    private EditText searchView;
    private Spinner typeView;
    private ImageButton searchButton;
    private RecyclerView recyclerView;

    private HouseListAdpater adapter;
    private long type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }

        TextView textView = findViewById(R.id.headerTitle);
        textView.setText("找房子");
        searchView = findViewById(R.id.editText_search);
        typeView = findViewById(R.id.spinner_type);
        searchButton = findViewById(R.id.button_search);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new HouseListAdpater();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration decoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);

        final String[] types = new String[]{"不限", "二手", "楼盘", "出租", "中介"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, types);
        typeView.setAdapter(arrayAdapter);
        typeView.setSelection(0);

        searchButton.setOnClickListener(view -> {

//            type = typeView.getSelectedItemId();
            String houseType = typeView.getSelectedItem().toString();
            String searchName = searchView.getText().toString();
            if(houseType.equals("不限")){
                houseType = "";
            }
            getData(searchName, houseType);

        });

        getData("", "");
    }


    /**
     * houseType
     * 二手
     * 中介
     * 楼盘
     * 租房
     */
    private void getData(String sourceName, String houseType) {

        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        String url;


        url = String.format("%s?sourceName=%s&houseType=%s", Connectinfo.sourcelisturl, sourceName, houseType);
        okHttp.syscJsonObjectByURL(url, jsonObject -> {

            Gson gson = new Gson();

            HouseListEntity houseListEntity = gson.fromJson(jsonObject.toString(), HouseListEntity.class);
            List<HouseListEntity.RowsBean> houseList = houseListEntity.getRows();
            adapter.setData(houseList);
        });

    }
}