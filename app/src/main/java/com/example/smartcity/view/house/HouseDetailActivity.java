package com.example.smartcity.view.house;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.view.house.entities.HouseListEntity;
import com.squareup.picasso.Picasso;

public class HouseDetailActivity extends AppCompatActivity {

    private ImageView houseImageView;
    private TextView houseView, addressView, areaView, priceView, telView, desView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_detail);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }

        TextView textView = findViewById(R.id.headerTitle);
        textView.setText("房子详情");


        houseView = findViewById(R.id.textView_house);
        addressView = findViewById(R.id.textView_address);
        areaView = findViewById(R.id.textView_area);
        priceView = findViewById(R.id.textView_price);
        telView = findViewById(R.id.textView_phone);
        desView = findViewById(R.id.textView_des);
        houseImageView = findViewById(R.id.imageView_house);

        HouseListEntity.RowsBean house = getIntent().getParcelableExtra("house");
        if (house != null) {

            String url = Connectinfo.contexturl + house.getPic();

            Picasso.get().load(url).into(houseImageView);

            houseView.setText("房源：" + house.getSourceName());
            addressView.setText("地址：" + house.getAddress());
            areaView.setText("面积：" + house.getAreaSize().toString());
            priceView.setText("价格：" + "" + house.getPrice());
            telView.setText("电话：" + house.getTel());
            desView.setText("介绍：" + house.getDescription());
        }

    }
}