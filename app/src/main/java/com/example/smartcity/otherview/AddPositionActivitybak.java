package com.example.smartcity.otherview;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.example.smartcity.view.LoginActivity;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.example.smartcity.baseinfo.Const.tokens;


public class AddPositionActivitybak extends AppCompatActivity {
    private static final String TAG = "Addposition";
    private Spinner spinneredu,spinnerposition;
    private TextView tv_file;
    private EditText tv_education,tv_address,tv_experience,tv_individualResume,tv_money;
    private Button btnChoose, btnAddPosition;
    private String docFilePath;
    private String mostEducation;
    private String address;
    private String experience;
    private String education;
    private String individualResume;
    private double money;
    private int positionId;
    private int userId=1;
    private static final int REQUEST_CODE_DOC = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_position);
        initView();

    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |=View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }
        if(tokens==null){
            Intent intent=new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        btnChoose = findViewById(R.id.choose);
        btnAddPosition = findViewById(R.id.btn_addPosition);
        tv_file = findViewById(R.id.tv_file);
        tv_experience=findViewById(R.id.experience);
        tv_experience.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                experience = tv_experience.getText().toString();
                if (TextUtils.isEmpty(experience)) {
                    tv_experience.setError("请输入工作经历");
                }
            }
        });

        Log.d(TAG, "initView: experi"+experience);
        tv_address=findViewById(R.id.address);
        tv_address.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                address= tv_address.getText().toString();
            }
        });
        tv_education=findViewById(R.id.education);
        tv_education.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                education=tv_education.getText().toString();
            }
        });

        tv_individualResume=findViewById(R.id.individualResume);
        tv_individualResume.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                individualResume=tv_individualResume.getText().toString();
                if(TextUtils.isEmpty(individualResume)){
                    tv_individualResume.setError("请输入个人简历");

                }
            }
        });

        tv_money=findViewById(R.id.money);
        tv_money.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                money=Double.parseDouble(tv_money.getText().toString());
            }
        });

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDocument();
            }
        });
        spinneredu = findViewById(R.id.spinneredu);

        spinneredu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mostEducation=spinneredu.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerposition=findViewById(R.id.positionId);
        spinnerposition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (spinnerposition.getSelectedItem().toString()){
                    case "java开发工程师":positionId=1;break;
                    case "设计":positionId=2;break;
                    case "外教":positionId=3;break;
                    case "前端工程师":positionId=4;break;
                    case "牙医":positionId=5;break;
                    case "全栈开发工程师":positionId=6;break;
                    default:positionId=0;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnAddPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: "+"education"+education+"mostEducation："+mostEducation+"\nindividualResume："+individualResume+"\nmoney："+money+"\npositionId："+positionId+"\nuserId"+userId+"\naddress："+address+"\nexperience："+experience);
               putData();

            }
        });
    }

    private void putData() {
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        String token=null;
        PrefStore prefStore=PrefStore.getInstance(this);
        token= prefStore.getPref("Authorization",null);

        Log.d("docFilePath=", docFilePath);
        File file = new File( docFilePath);

        RequestBody fileBody = RequestBody.create(file,MediaType.parse("application/octet-stream"));
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "resume.jpg", fileBody)
                .addFormDataPart("mostEducation",mostEducation)
                .addFormDataPart("education",education)
                .addFormDataPart("address",address)
                .addFormDataPart("experience",experience)
                .addFormDataPart("individualResume",individualResume)
                .addFormDataPart("money",money+"")
                .addFormDataPart("positionId",positionId+"")
                .addFormDataPart("userId",userId+"")
                .build();
        Log.d("getData=", requestBody.toString());
        okHttp.syncPostByUrlwithFile(Connectinfo.addpositionurl, requestBody, token, new OkHttpUtils.FuncJsonString() {
            @Override
            public void onResponse(String result) {
                Log.d(TAG, "onResponse: "+result);

            }
        });
    }
    private void getDocument()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/msword,application/pdf,application/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CODE_DOC);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            Uri fileuri = data.getData();
            docFilePath = getFileNameByUri(this, fileuri);
            Log.d(TAG, "onActivityResult: "+fileuri.getPath()+docFilePath);
            Toast.makeText(getApplicationContext(),"docFilePath"+fileuri,Toast.LENGTH_LONG).show();
        }


    }
// get file path

    private String getFileNameByUri(Context context, Uri uri)
    {
        String filepath = "";//default fileName
        //Uri filePathUri = uri;
        File file;
        if (uri.getScheme().toString().compareTo("content") == 0)
        {
            Cursor cursor = context.getContentResolver().query(uri, new String[] { android.provider.MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.ORIENTATION }, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            cursor.moveToFirst();

            String mImagePath = cursor.getString(column_index);
            cursor.close();
            filepath = mImagePath;

        }
        else if (uri.getScheme().compareTo("file") == 0)
        {
            try
            {
                file = new File(new URI(uri.toString()));
                if (file.exists())
                    filepath = file.getAbsolutePath();

            }
            catch (URISyntaxException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else
        {
            filepath = uri.getPath();
        }
        return filepath;
    }
}
