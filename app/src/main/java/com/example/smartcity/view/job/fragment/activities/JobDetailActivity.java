package com.example.smartcity.view.job.fragment.activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.baseinfo.Const;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.example.smartcity.view.job.entities.CompanyEntity;
import com.example.smartcity.view.job.entities.JobListEntity;
import com.example.smartcity.view.job.entities.ResumeInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JobDetailActivity extends AppCompatActivity {

    private TextView jobNameView;
    private TextView dutyView;
    private TextView addressView;
    private TextView wageView;
    private TextView contactView;
    private TextView jobDescView;
    private TextView jobYearsView;
    private TextView companyNameView;
    private TextView companyDescView;

    private Button buttonPost;
    private ResumeInfo resumeInfo;

    private JobListEntity.RowsBean job;
    private CompanyEntity companyEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }

        TextView textView = findViewById(R.id.headerTitle);
        textView.setText("职位详情");

        job = getIntent().getParcelableExtra("job");

        jobNameView = findViewById(R.id.textView_job_name);
        dutyView = findViewById(R.id.textView_duty);
        addressView = findViewById(R.id.textView_address);
        wageView = findViewById(R.id.textView_money);
        contactView = findViewById(R.id.textView_contact);
        jobDescView = findViewById(R.id.textView_job_desc);
        jobYearsView = findViewById(R.id.textView_job_years);
        companyNameView = findViewById(R.id.textView_company_name);
        companyDescView = findViewById(R.id.textView_company_desc);

        jobNameView.setText("职位名称：" + job.getProfessionName());
        dutyView.setText("职责描述：" + job.getObligation());
        addressView.setText("工作地址：" + job.getAddress());
        wageView.setText("薪  酬：" + job.getSalary());
        contactView.setText("联系人：" + job.getContacts());
        jobDescView.setText("工作描述：" + job.getObligation());
        jobYearsView.setText("工作年限：" + job.getNeed());

        buttonPost = findViewById(R.id.button_post);
        buttonPost.setOnClickListener(view -> postResume());

        getData(job.getId());
    }

    public void getData(int id) {

        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        String url = String.format("%s%d", Connectinfo.querycompanybyidurl, id);

        okHttp.syscJsonObjectByURL(url, jsonObject -> {

            Gson gson = new Gson();
            companyEntity = gson.fromJson(jsonObject.toString(), CompanyEntity.class);
            companyNameView.setText("公司名称：" + companyEntity.getData().getCompanyName());
            companyDescView.setText("公司简介：" + companyEntity.getData().getIntroductory());
        });

    }

    private void getData() {

        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        PrefStore prefStore = PrefStore.getInstance(this);
        String token = prefStore.getPref("Authorization", null);
        String userId = prefStore.getPref("userId", "1");

        String url = String.format("%s%s", Connectinfo.resumeurl, userId);

        if (token != null) {

            okHttp.syncJsonGetwithTokenByURL(url, token, jsonObject -> {

                Gson gson = new Gson();

                resumeInfo = gson.fromJson(jsonObject.toString(), ResumeInfo.class);

                if (resumeInfo.getCode() == 200) {

                    if (resumeInfo.getData() == null) {

                        setResult(1);
                        finish();
                    } else {

                        postResume();
                    }

                }
            });
        }
    }

    private void postResume() {

        /*
        {
          "companyId": 0,
          "companyName": "",
          "id": 0,
          "money": "",
          "postId": 0,
          "postName": "",
          "remark": "",
          "satrTime": "2021-09-10",
          "userId": 0,
          "userName": ""
        }
         */
        OkHttpUtils okHttp = OkHttpUtils.getInstance();

        Map<String, String> params = new HashMap<>();

//        PrefStore prefStore = PrefStore.getInstance(this);
//        String token = prefStore.getPref("Authorization", null);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        String satrTime = simpleDateFormat.format(date);

        params.put("companyId", String.valueOf(job.getCompanyId()));
        params.put("postId", String.valueOf(job.getId()));
        params.put("postName", job.getProfessionName());
        params.put("satrTime", satrTime);
        params.put("money", job.getSalary());

        okHttp.syncRequestWithBodyAndTokenForJsonString(Connectinfo.deliverurl, params, Const.tokens, OkHttpUtils.ReqType.POST, result -> {

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
            int code = jsonObject.get("code").getAsInt();
            String msg = jsonObject.get("msg").getAsString();
            if (code == 200) {

                finish();
            }
        });
    }
}