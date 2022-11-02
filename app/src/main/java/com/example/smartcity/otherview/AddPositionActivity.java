package com.example.smartcity.otherview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.example.smartcity.view.LoginActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;

import okhttp3.RequestBody;

import static com.example.smartcity.baseinfo.Const.tokens;


public class AddPositionActivity extends AppCompatActivity {
    private static final String TAG = "Addposition";
    private Spinner spinneredu,spinnerposition;
    private TextView tv_file;
    private EditText tv_education,tv_address,tv_experience,tv_individualResume,tv_money;
    private Button btnChoose, btnAddPosition;
    private String path, uploadfile,fileName;
    private  String fileDir;
    private String mostEducation;
    private String address;
    private String experience;
    private String education;
    private String individualResume;
    private double money;
    private int positionId;
    private int userId=1;
    private static final int PHOTO_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private static final int PHOTO_CLIP = 3;

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
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |=View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
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
                getPicFromPhoto();
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
        Log.d("token=", token);
        File file = new File( fileDir);

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
        Log.d("getData=", uploadfile+requestBody.toString());
        okHttp.syncPostByUrlwithFile(Connectinfo.addpositionurl, requestBody, token, new OkHttpUtils.FuncJsonString() {
            @Override
            public void onResponse(String result) {
                Log.d(TAG, "onResponse: "+result);

            }
        });
    }
    //打开文件选择器
    private void showFileChooser() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File("/system/etc/help.doc"));
        intent.setDataAndType(uri, "application/msword");
        this.startActivity(intent);
    }
    public void getPicFromPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);//Intent.Action_Pick调取系统相册
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        Log.d("yann", "getPicFromPhoto");
        startActivityForResult(intent, PHOTO_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       Log.d(TAG, "返回的数据：" + data);
        switch (requestCode) {

            case PHOTO_REQUEST:
                if (data != null) {
                    Log.d("yann", "data.getData()===" + data.getData());
                    photoClip(data.getData());
                } else {
                    Log.d("yann", "wochucuole" + data.getData());
                }
                break;
            case PHOTO_CLIP:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    Log.d("yann", "onActivityResult:PHOTO_CLIP" + data.getData());
                    if (extras != null) {
                        Bitmap photo = extras.getParcelable("data");
                        Log.d(TAG, "saveBitmap");
                        saveImage(photo);
                    }
                }
                break;
            default:
                Log.d(TAG, "REQUESTCODE:" + requestCode);
                break;
        }
    }
    private void photoClip(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        //intent.putExtra("circlecrop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 256);
        intent.putExtra("outputY", 256);
        intent.putExtra("return-data", true);
        //getImageToView(intent);
        startActivityForResult(intent, PHOTO_CLIP);

    }

    public void saveImage(Bitmap bmp) {

        File appDir = new File(Environment.getExternalStorageDirectory(), "Xel_Resources");
        if (!appDir.exists()) {
            appDir.mkdir();
        }

        fileName = "resume.jpg";
        File file= new File(appDir, fileName);
        fileDir = appDir + "/" + fileName;
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            Integer i = fileDir.length();
            String uri = fileDir.substring(1, i);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static String getRealFilePath(final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
