package com.example.smartcity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity.R;
import com.example.smartcity.adapter.BusLineAdapter;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.BusStopBean;
import com.example.smartcity.bean.BusStopData;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.smartcity.baseinfo.Const.linesId;
import static com.example.smartcity.baseinfo.Const.tokens;
import static com.example.smartcity.baseinfo.Const.uBusBean;
import static com.example.smartcity.baseinfo.Const.uphone;
import static com.example.smartcity.baseinfo.Const.userid;
import static com.example.smartcity.baseinfo.Const.username;
import static com.example.smartcity.util.setListViewHeightBasedOnChildren.setListViewHeightBasedOnChildren;

public class InsertbusOrdersActivity extends AppCompatActivity {
    TextView shang,xia,phone,uname;
    Button submit;
    String params = "?pageNum=1&pageSize=10&linesId="+linesId;
    BusStopData busStopData;
    List<BusStopBean> ls=new ArrayList<>();
    BusLineAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertbus_orders);
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |=View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }
        init();
    }

    private void init() {
        TextView t=findViewById(R.id.headerTitle);
        t.setText("乘车信息");
        if(tokens==null){
            Intent intent=new Intent(InsertbusOrdersActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        listView=findViewById(R.id.linlistview);
        shang=findViewById(R.id.shang);
        xia=findViewById(R.id.xia);
        uname=findViewById(R.id.uname);
        phone=findViewById(R.id.phone);
        uname.setText(username);
        phone.setText(uphone);
        submit=findViewById(R.id.b5);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(shang.getText().toString().contains("未选择")||xia.getText().toString().contains("未选择")){
                    Toast.makeText(InsertbusOrdersActivity.this,"请选择上车站和下车站",Toast.LENGTH_SHORT).show();
                }else {
                    submitInit();
                }
            }
        });
        getdata();
    }

    private void submitInit() {
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        String token=null;
        PrefStore prefStore=PrefStore.getInstance(this);
        token= prefStore.getPref("Authorization",null);
        Log.d("token=", token);
        Map<String,String> ls=new HashMap<>();
        ls.put("start",shang.getText().toString());
        ls.put("end",xia.getText().toString());
        ls.put("userName",username);
        ls.put("userTel",uphone);
        ls.put("price", String.valueOf(uBusBean.getPrice()));
        ls.put("path",uBusBean.getName());
        ls.put("status","1");
        ls.put("userId", String.valueOf(userid));
        Toast.makeText(InsertbusOrdersActivity.this,String.valueOf(userid)+shang.getText().toString()+xia.getText().toString(),Toast.LENGTH_LONG).show();
        //}         此处是put请求
        okHttp.syncPostByUrladdHeaderToken(Connectinfo.insertbusOrdersurl,ls, token, new OkHttpUtils.FuncJsonString() {
            @Override
            public void onResponse(String result) {
                Log.e("sdsada1","onResponse: "+result);
                //todo  {"msg":"操作成功","code":200}
                if(result.contains("成功")){
                    finish();
                    Toast.makeText(InsertbusOrdersActivity.this,"提交成功",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(InsertbusOrdersActivity.this, BusOrderActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void getdata() {
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        okHttp.syncJsonStringByURL(Connectinfo.bybusStopurl + params, new OkHttpUtils.FuncJsonString() {
            private static final String TAG = "HomeFragment";

            @Override
            public void onResponse(String result) {
                Log.d(TAG, "onResponse: " + result);

                busStopData = new Gson().fromJson(result,BusStopData.class);

                for (int i = 0; i < busStopData.getRows().size(); i++) {
                    ls.add(busStopData.getRows().get(i));
                }
                ListviewInit();
            }
        });
    }

    private void ListviewInit() {
        adapter = new BusLineAdapter(InsertbusOrdersActivity.this, ls){
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                final View view=super.getView(position, convertView, parent);
                final Button s=view.findViewById(R.id.btns);
                final Button x=view.findViewById(R.id.btnx);
                s.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        shang.setText(ls.get(position).getName());
                    }
                });
                x.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        xia.setText(ls.get(position).getName());

                    }
                });
                return view;
            }
        };
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}