package com.example.smartcity.otherview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.example.smartcity.view.MainActivity;


import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MoveCarActivity extends AppCompatActivity {
    private static final String TAG = "moveCar";
    private EditText et_phone, et_plates, et_address;
    private Button btn_upload, btn_queryPhone;
    private String cardId;
    private String names;
    private String userId;
    private String tel;
    private String address;
    private String plates;
    private static final int PHOTO_REQUEST = 1;
    File file;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "??????"+grantResults[0], Toast.LENGTH_SHORT).show();
                    openFile();
                } else {
                    Toast.makeText(this, "?????????????????????????????????", Toast.LENGTH_SHORT).show();
                    //finish();
                }
                break;
            default:
        }
    }
    private void openFile() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);//Intent.Action_Pick??????????????????
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        Log.d("yann", "getPicFromPhoto");
        startActivityForResult(intent, PHOTO_REQUEST);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_car);
        initView();
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MoveCarActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    Toast.makeText(getApplicationContext(), "???????????????????????????", Toast.LENGTH_SHORT).show();
                }else {
                    openFile();
                }

            }
        });
        btn_queryPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "??????????????????" + data);
        if (requestCode == PHOTO_REQUEST) {
            //??????
            if (resultCode == RESULT_OK) {
                Uri imageUri = data.getData();
                //??????uri,7.0?????????fileProvider ???URI ???content provider ?????? ???????????????????????????
                file = getFileFromUri(imageUri, this);
                Toast.makeText(this, "" + file.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public File getFileFromUri(Uri uri, Context context) {
        if (uri == null) {
            return null;
        }
        switch (uri.getScheme()) {
            case "content":
                return getFileFromContentUri(uri, context);
            case "file":
                return new File(uri.getPath());
            default:
                return null;
        }
    }

    /**
     * ???????????????????????????uri??????????????????
     */
    private File getFileFromContentUri(Uri contentUri, Context context) {
        if (contentUri == null) {
            return null;
        }
        File file = null;
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(contentUri, filePathColumn, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            filePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
            cursor.close();
            if (!TextUtils.isEmpty(filePath)) {
                file = new File(filePath);
            }
        }
        return file;
    }

    //????????????
    private void showImage(String imaePath) {
        Bitmap bm = BitmapFactory.decodeFile(imaePath);
//        ((ImageView)findViewById(R.id.image)).setImageBitmap(bm);
    }


    private void getData() {
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        String token = null;
        PrefStore prefStore = PrefStore.getInstance(this);
        token = prefStore.getPref("Authorization", null);
        cardId = prefStore.getPref("idCard", "423016199812141526");
        names = prefStore.getPref("username", "??????");
        userId = prefStore.getPref("userId", "1");

        showImage(file.getAbsolutePath());

        //?????????

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(MediaType.parse("multipart/form-data"), new File(file.getAbsolutePath())))
                .addFormDataPart("names", names)
                .addFormDataPart("cardId", cardId)
                .addFormDataPart("userId", userId)
                .addFormDataPart("tel", tel)
                .addFormDataPart("address", address)
                .addFormDataPart("plates", plates)
                .build();

        Request request = new Request.Builder()
                .header("Authorization", token)
                .url(Connectinfo.querymovecarurl)
                .post(requestBody)
                .build();

        Response response = null;
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("TAG", "onResponse: " + e.getMessage());


            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d("TAG", "onResponse: " + response.body().string());


            }
        });


        //?????????
//
//        RequestBody requestBody = RequestBody.create(file, MediaType.parse("application/octet-stream"));
//        RequestBody muiltipartBody = new MultipartBody.Builder()
//                //?????????????????????
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("names", names)
//                .addFormDataPart("cardId", cardId)
//                .addFormDataPart("userId", userId)
//                .addFormDataPart("tel", tel)
//                .addFormDataPart("address", address)
//                .addFormDataPart("plates", plates)
//                .addFormDataPart("file", file.getName(), requestBody)
//                .build();
//
//        Log.d("requestbody", muiltipartBody.toString());
//
//        okHttp.syncPostJsonByUrlwithFile(Connectinfo.querymovecarurl, muiltipartBody, token, new OkHttpUtils.FuncJsonObject() {
//            @Override
//            public void onResponse(JSONObject jsonObject) {
//                Log.d("TAG", "onResponse: " + jsonObject);
//            }
//
//
//        });


    }

    private void initView() {
        et_phone = findViewById(R.id.et_phones);
        et_address = findViewById(R.id.et_address);
        et_plates = findViewById(R.id.et_plates);
        btn_queryPhone = findViewById(R.id.btn_queryPhone);
        btn_upload = findViewById(R.id.btn_upload);
        et_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                tel = et_phone.getText().toString();
                if (TextUtils.isEmpty(tel)) {
                    et_phone.setError("??????????????????");
                }
            }
        });
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tel = et_phone.getText().toString();
            }
        });
        et_address.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                address = et_address.getText().toString();
                if (TextUtils.isEmpty(address)) {
                    et_address.setError("???????????????");
                }
            }
        });
        et_address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                address = et_address.getText().toString();
            }
        });
        et_plates.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                plates = et_plates.getText().toString();
                if (TextUtils.isEmpty(plates)) {
                    et_plates.setError("??????????????????");
                }
            }
        });
        et_plates.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                plates = et_plates.getText().toString();
            }
        });


    }
}