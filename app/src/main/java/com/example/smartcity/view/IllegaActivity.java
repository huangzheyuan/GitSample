package com.example.smartcity.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.R;
import com.example.smartcity.adapter.IllegaAdapter;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.baseinfo.Const;
import com.example.smartcity.bean.IllegaData;
import com.example.smartcity.util.OkHttpUtils;
import com.google.gson.Gson;

import static com.example.smartcity.baseinfo.Const.illId;
import static com.example.smartcity.util.setListViewHeightBasedOnChildren.setListViewHeightBasedOnChildren;

import org.json.JSONObject;

public class IllegaActivity extends AppCompatActivity {
    //查询
    private Button submitSubwayName;
    String params;
    IllegaData illegaData;
    ListView listView;
    IllegaAdapter adapter;
    EditText editType,editNumber, editEngineNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illega);
        init();
    }

    private void getData() {

        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        String url = Connectinfo.queryillegalurl + params;

        okHttp.syncGetwithTokenByURL(url, Const.tokens, new OkHttpUtils.FuncJsonString() {
            @Override
            public void onResponse(String result) {
                illegaData = new Gson().fromJson(result, IllegaData.class);

                if (illegaData.getRows().size() == 0)
                    Toast.makeText(IllegaActivity.this, "无违章.", Toast.LENGTH_LONG).show();
                initListview();
            }
        });
    }

    private void initListview() {
        adapter = new IllegaAdapter(IllegaActivity.this, illegaData.getRows());
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                illId = illegaData.getRows().get(i).getId();
                Intent intent = new Intent(IllegaActivity.this, illegalinfoActivity.class);
                intent.putExtra("id", illId);
                startActivity(intent);
            }
        });
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
        t.setText("违章查询");
        listView = findViewById(R.id.illegaListview);
        submitSubwayName = findViewById(R.id.submit);
        editType = findViewById(R.id.editText_type);
        editNumber = findViewById(R.id.editText_number);
        editEngineNumber = findViewById(R.id.editText_engine_number);
        submitSubwayName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                params = "?catType=" + editType.getText().toString() + "&engineNumber=" + editEngineNumber.getText().toString() + "&licencePlate=" + editNumber.getText().toString();
                getData();
//                String[] CarData=editSubwayName.getText().toString().split("/");
//                if(CarData.length<3){
//                    editSubwayName.setText("");
//                    Toast.makeText(IllegaActivity.this,"输入格式错误.",Toast.LENGTH_LONG).show();
//                    initListview();
//                }else{
//
//                }

            }
        });
    }


}