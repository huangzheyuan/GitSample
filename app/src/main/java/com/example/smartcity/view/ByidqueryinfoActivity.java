package com.example.smartcity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.FeedBackDetail;
import com.example.smartcity.bean.QueryyuyueData;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.google.gson.Gson;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import static com.example.smartcity.baseinfo.Const.jzr;
import static com.example.smartcity.baseinfo.Const.keshi;
import static com.example.smartcity.baseinfo.Const.ktype;
import static com.example.smartcity.baseinfo.Const.queryid;
import static com.example.smartcity.baseinfo.Const.tokens;
import static com.example.smartcity.baseinfo.Const.userid;
import static com.example.smartcity.baseinfo.Const.username;
import static com.example.smartcity.baseinfo.Const.yuyueinfo;
import static com.example.smartcity.util.disposeTime.disposeTime;

public class ByidqueryinfoActivity extends AppCompatActivity {
    QueryyuyueData queryyuyueData;
    TextView t1, t2, t3, t4, time2;
    Button submit;
    private DatePicker datePicker;
    static int year, month, day;

    private int categoryId, money;
    private String type, categoryName;

    private SwitchDateTimeDialogFragment dateTimeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byidqueryinfo);

        Intent intent = getIntent();
        categoryId = intent.getIntExtra("categoryId", -1);
        money = intent.getIntExtra("money", -1);
        type = intent.getStringExtra("type");
        categoryName = intent.getStringExtra("categoryName");
        init();

        initDateTimePicker();
//        getdata();
    }

    private void initDateTimePicker() {

        // Construct SwitchDateTimePicker
        dateTimeFragment = (SwitchDateTimeDialogFragment) getSupportFragmentManager().findFragmentByTag("TAG_DATETIME_FRAGMENT");
        if (dateTimeFragment == null) {
            dateTimeFragment = SwitchDateTimeDialogFragment.newInstance("选择时间日期", "确定", "取消");
        }

        // Optionally define a timezone
        dateTimeFragment.setTimeZone(TimeZone.getDefault());

        // Init format
        final SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault());
        // Assign unmodifiable values
        dateTimeFragment.set24HoursMode(false);
        dateTimeFragment.setHighlightAMPMSelection(false);
        dateTimeFragment.setMinimumDateTime(new GregorianCalendar(2015, Calendar.JANUARY, 1).getTime());
        dateTimeFragment.setMaximumDateTime(new GregorianCalendar(2025, Calendar.DECEMBER, 31).getTime());

        // Define new day and month format
        try {
            dateTimeFragment.setSimpleDateMonthAndDayFormat(new SimpleDateFormat("MMMM dd", Locale.getDefault()));
        } catch (SwitchDateTimeDialogFragment.SimpleDateMonthAndDayFormatException e) {
        }

        // Set listener for date
        // Or use dateTimeFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonClickListener() {
        dateTimeFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonWithNeutralClickListener() {
            @Override
            public void onPositiveButtonClick(Date date) {
                time2.setText(myDateFormat.format(date));
            }

            @Override
            public void onNegativeButtonClick(Date date) {
                // Do nothing
            }

            @Override
            public void onNeutralButtonClick(Date date) {
                // Optional if neutral button does'nt exists
                time2.setText("");
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
        t.setText("预约单");
        if (tokens == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }

        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        time2 = findViewById(R.id.time2);

//        int year = Calendar.getInstance().get(Calendar.YEAR);
//        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
//        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
//
//        String date = String.format("%d-%2d-%2d",year, month, day);
//        time2.setText(date);
        submit = findViewById(R.id.ysubmit);

        t1.setText(jzr.getName());
        t2.setText(categoryName);
        if (ktype.equals("2")) {
            t3.setText("普通门诊");
        } else {
            t3.setText("专家门诊");
        }
        t4.setText(money + "元");
//
//        t4.setText(keshi.getMoney() + "元");
        time2.setText(disposeTime());
        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
//                calendar.add(Calendar.DAY_OF_MONTH, 1);

                int year = Calendar.getInstance().get(Calendar.YEAR);
                int month = Calendar.getInstance().get(Calendar.MONTH);
                int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

                dateTimeFragment.startAtCalendarView();
                dateTimeFragment.setDefaultDateTime(new GregorianCalendar(year, month, day, 8, 0).getTime());
                dateTimeFragment.show(getSupportFragmentManager(), "TAG_DATETIME_FRAGMENT");

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getyueyedata();
            }
        });
    }

    private void getyueyedata() {
        //"patientName":"张三",
        //"divisionId":5,
        //"typesId":"1",
        //"moeny":"5",
        //"startime":"2020-10-24 18:38:48",
        //"userId":"1"


        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        PrefStore prefStore = PrefStore.getInstance(this);
        String token = prefStore.getPref("Authorization", null);
        Map<String, String> ls = new HashMap<>();


        ls.put("categoryId", String.valueOf(categoryId));
        ls.put("money", String.valueOf(money));
        ls.put("patientName", jzr.getName());
        ls.put("reserveTime", time2.getText().toString());
        ls.put("type", type);

        okHttp.syncPostByUrladdHeaderToken(Connectinfo.insertyuyueurl, ls, token, new OkHttpUtils.FuncJsonString() {
            @Override
            public void onResponse(String result) {
                if (result.contains("成功")) {
                    Toast.makeText(ByidqueryinfoActivity.this, "已生成预约单.", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });


    }

    private void getdata() {
        OkHttpUtils okHttp = OkHttpUtils.getInstance();

        PrefStore prefStore = PrefStore.getInstance(this);
        String token = prefStore.getPref("Authorization", null);

        okHttp.syncGetwithTokenByURL(Connectinfo.byidqueryinfourl + queryid, token, new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {
                queryyuyueData = new Gson().fromJson(result, QueryyuyueData.class);

                t1.setText(queryyuyueData.getData().getPatientName());
                t2.setText("科室:" + queryyuyueData.getData().getCategoryName());
                t3.setText("时间:" + queryyuyueData.getData().getReserveTime());
                t4.setText("费用:" + queryyuyueData.getData().getMoney() + "元");

            }
        });
    }

}