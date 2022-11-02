package com.example.smartcity.otherview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;

import com.example.smartcity.bean.PersonBean;
import com.example.smartcity.util.BitmapUtil;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.example.smartcity.util.UploadFile;
import com.example.smartcity.view.LoginActivity;
import com.google.gson.JsonObject;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.example.smartcity.baseinfo.Const.username;

public class GetInfoActivity extends AppCompatActivity implements UploadFile.OnUploadOver {
    //修改用户信息
    private static final int PHOTO_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private static final int PHOTO_CLIP = 3;
    Xel_mine_modifyUserIcon_popWindow popDialog_UserIcon;
    File file;

    private ImageView img_avater;
    private EditText ed_nick, ed_phone, ed_email, ed_id_card;
    private RadioButton rb_male, rb_female;

    private Button updateButton;

    private PersonBean personBean;
    String token = null;

    private TextView header;
    private String fileName;
    private String fileDir = "DCIM/Pictures";
    private String filePath = Environment.getExternalStorageDirectory() + File.separator + fileDir + File.separator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_info);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示左边的小箭头
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });

        PrefStore prefStore = PrefStore.getInstance(this);
        token = prefStore.getPref("Authorization", null);
        if (token == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }
        initView();
        if (token != null) {
            getData();
        }

        header = findViewById(R.id.headerTitle);
        header.setText("个人信息");
    }

    private void initView() {

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }
        ed_nick = findViewById(R.id.et_nick);
        ed_phone = findViewById(R.id.et_tel);
        img_avater = findViewById(R.id.ava_img);
        img_avater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popDialog_UserIcon = new Xel_mine_modifyUserIcon_popWindow(GetInfoActivity.this, itemsOnClick_UserIcon);
                //显示窗口
                popDialog_UserIcon.showAtLocation(GetInfoActivity.this.findViewById(R.id.student_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置


            }
        });
        rb_male = findViewById(R.id.rd_male);
        rb_female = findViewById(R.id.rd_female);

        ed_id_card = findViewById(R.id.editText_id_card);
        ed_email = findViewById(R.id.editText_email);
        updateButton = findViewById(R.id.button_update_user);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(GetInfoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    Toast.makeText(getApplicationContext(), "没有权限，获取权限", Toast.LENGTH_SHORT).show();
                } else {

                    if (fileName != null) {

                        uploadAvatar(filePath, fileName, GetInfoActivity.this);
                    }else{

                        updateUser(personBean.getAvater());
                    }
                }
            }
        });
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick_UserIcon = new View.OnClickListener() {
        public void onClick(View v) {

            popDialog_UserIcon.dismiss();

            switch (v.getId()) {
                case R.id.item_popupwindows_Photo:
                    getPicFromPhoto();
                    break;
                case R.id.item_popupwindows_camera:
                    getPicFromCamera();
                    break;
                default:
                    break;
            }
        }
    };

    public void getPicFromPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);//Intent.Action_Pick调取系统相册
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, PHOTO_REQUEST);
    }


    public void getPicFromCamera() {


//        file = new File(Environment.getExternalStorageDirectory(), fileName);

        Uri uri;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            uri = FileProvider.getUriForFile(this, "com.smartcity.android.fileprovider", file);
//        } else {
//            uri = Uri.fromFile(file);
//        }
//

        File phone = new File(filePath);
        if (phone.exists()) {
            phone.delete();
        }


        fileName = Calendar.getInstance().getTimeInMillis() + ".jpg";
        ContentValues contentValues = new ContentValues();

        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, fileDir);
        } else {

            contentValues.put(MediaStore.Images.Media.DATA, filePath + File.separator + fileName);
        }
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/JPEG");
        uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, CAMERA_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case CAMERA_REQUEST:

                    try {
                        String selection = MediaStore.Images.Media.DISPLAY_NAME + "=? ";
                        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Images.Media._ID}, selection, new String[]{fileName}, null);
                        if (cursor != null && cursor.moveToFirst()) {
                            do {
                                Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cursor.getLong(0));
//                                    InputStream inputStream = getContentResolver().openInputStream(uri);
//                                    Bitmap bitmap =  BitmapFactory.decodeStream(inputStream);
                                filePath = BitmapUtil.getRealPathFromUri(GetInfoActivity.this, uri);

                                photoClip(uri);

                            } while (cursor.moveToNext());
                        } else {
                            Toast.makeText(this, "no photo", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    break;
                case PHOTO_REQUEST:
                    Uri imageUri = data.getData();
                    file = getFileFromUri(imageUri, this);

                    fileName = file.getName();
                    filePath = file.getAbsolutePath();
                    Glide.with(getApplicationContext())
                            .load("file:///" + filePath)
                            .into(img_avater);

                    break;
                case PHOTO_CLIP:
                    if (data != null) {
                        Bundle extras = data.getExtras();
                        if (extras != null) {
                            Bitmap bm = extras.getParcelable("data");
                            BitmapUtil.saveBitmap(filePath, bm);
                        }

                        Context context = GetInfoActivity.this;
                        Glide.with(context)
                                .load("file:///" + filePath)
                                .into(img_avater);


                    }
                    break;
                default:
                    break;
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
     * 通过内容解析中查询uri中的文件路径
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

    private void getData() {
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        String token = null;
        PrefStore prefStore = PrefStore.getInstance(this);
        token = prefStore.getPref("Authorization", null);
        okHttp.syncJsonGetwithTokenByURL(Connectinfo.getinfourl, token, new OkHttpUtils.FuncJsonObject() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                parse(jsonObject);
                ed_nick.setText(personBean.getNickName());
                ed_phone.setText(personBean.getPhonenumber());

                ed_email.setText(personBean.getEmail());
                ed_id_card.setText(personBean.getIdCard());

                boolean checked = personBean.getSex().equals("0");
                rb_male.setChecked(checked);
                rb_female.setChecked(!checked);

//                rb_female.setChecked(!checked);


//                if (personBean.getSex().equals("0")) {
//                    rb_male.setChecked(true);
//                } else if (personBean.getSex().equals("1")) {
//                    rb_female.setChecked(true);
//                }
                RequestOptions options = new RequestOptions()
                        .placeholder(R.drawable.icon_me)//图片加载出来前，显示的图片
                        .fallback(R.drawable.icon_me) //url为空的时候,显示的图片
                        .error(R.drawable.icon_me);//图片加载失败后，显示的图片
                options.circleCropTransform();
                options.transforms(new RoundedCorners(30));


                if(personBean.getAvater() != null && !personBean.getAvater().equals("")){
//                    String avaterUrl= personBean.getAvater().replace("http://124.93.196.45", "http://124.93.196.45:10001/prod-api");
                    String avaterUrl= personBean.getAvater().replace("http://124.93.196.45", "http://124.93.196.45:10001/prod-api");


                    Glide.with(getApplicationContext())
                            .load("http://124.93.196.45:10001/prod-api" + avaterUrl) //图片地址
                            .apply(options)
                            .into(img_avater);
                }

            }
        });
    }

    public void parse(JSONObject jo) {

        if (jo != null) {
            String msg = jo.optString("msg");
            String code = jo.optString("code");
            JSONObject user = jo.optJSONObject("user");

            if (code.equals("200")) {
                personBean = new PersonBean();
                personBean.setAvater(user.optString("avatar"));
                personBean.setNickName(user.optString("nickName"));
                personBean.setPhonenumber(user.optString("phonenumber"));
                personBean.setSex(user.optString("sex"));
                personBean.setUserName(user.optString("userName"));
                personBean.setIdCard(user.optString("idCard"));
                personBean.setUserId(user.optString("userId"));
                personBean.setEmail(user.optString("email"));
                personBean.setBalance((float) user.optDouble("balance"));
                personBean.setScore(user.optInt("score"));
                username = user.optString("userName");


                //保存个人信息
                PrefStore pref = PrefStore.getInstance(this);

                pref.removePref("usernickname");
                pref.removePref("avater");
                pref.removePref("phonenumber");
                pref.removePref("sex");
                pref.removePref("username");
                pref.removePref("idCard");
                pref.removePref("userId");
                pref.removePref("email");
                pref.removePref("balance");
                pref.removePref("score");

                pref.savePref("usernickname", personBean.getNickName());
                pref.savePref("avater", personBean.getAvater());
                pref.savePref("phonenumber", personBean.getPhonenumber());
                pref.savePref("sex", personBean.getSex());
                pref.savePref("username", personBean.getUserName());
                pref.savePref("idCard", personBean.getIdCard());
                pref.savePref("userId", personBean.getUserId());
                pref.savePref("email", personBean.getEmail());
                pref.savePref("balance", personBean.getBalance());
                pref.savePref("score", personBean.getScore());


            }

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "权限" + grantResults[0], Toast.LENGTH_SHORT).show();

                    uploadAvatar(filePath, fileName, this);
                } else {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    public void update(View view) {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(GetInfoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            Toast.makeText(getApplicationContext(), "没有权限，获取权限", Toast.LENGTH_SHORT).show();
        } else {

            uploadAvatar(filePath, fileName, this);
        }
    }

    private void uploadAvatar(String filePath, String fileName, UploadFile.OnUploadOver uploadOver) {

        UploadFile.uploadFile(filePath, fileName, uploadOver);
    }

    private void updateUser(String avatarUrl) {

        PrefStore prefStore = PrefStore.getInstance(this);
        String token = prefStore.getPref("Authorization", null);


        String cardId = ed_id_card.getText().toString();
        String nickName = ed_nick.getText().toString();
        String phonenumber = ed_phone.getText().toString();
        String email = ed_email.getText().toString();

        //验证

        if (nickName.equals("")) {
            Toast.makeText(this, "请输入昵称", Toast.LENGTH_SHORT).show();
            return;
        }

        if (phonenumber.equals("")) {
            Toast.makeText(this, "输入电话号码", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean sex = rb_male.isChecked();

        Map<String, String> params = new HashMap<String, String>();

        params.put("avatar", avatarUrl);
        params.put("nickName", nickName);
        params.put("idCard", cardId);
        params.put("phonenumber", phonenumber);
        params.put("email", email);
        params.put("sex", sex ? "0" : "1");
        OkHttpUtils okHttp = OkHttpUtils.getInstance();

        personBean.setAvater(avatarUrl);
        personBean.setNickName(nickName);
        personBean.setIdCard(cardId);
        personBean.setPhonenumber(phonenumber);
        personBean.setEmail(email);
        personBean.setSex(sex ? "0" : "1");

        okHttp.syncPutByurlwithToken(Connectinfo.updateuser, params, token, new OkHttpUtils.FuncJsonObject() {

            @Override
            public void onResponse(JSONObject jsonObject) {

                try {
                    String msg = jsonObject.getString("msg");
                    Toast.makeText(GetInfoActivity.this, msg, Toast.LENGTH_SHORT).show();

                    PrefStore pref = PrefStore.getInstance(GetInfoActivity.this);
                    pref.updateUser(personBean);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("file", file.getName(),
//                        RequestBody.create(MediaType.parse("multipart/form-data"), new File(file.getAbsolutePath())))
//                .addFormDataPart("niceName", name)
//                .addFormDataPart("idCard", cardId)
//                .addFormDataPart("userId", String.valueOf(Const.userid))
//                .addFormDataPart("phonenumber", tel)
//                .addFormDataPart("email", email)
//                .addFormDataPart("remark", remark)
//                .addFormDataPart("sex", sex ? "1" : "2")
//                .build();

//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("file", file.getName(),
//                        RequestBody.create(MediaType.parse("multipart/form-data"), new File(file.getAbsolutePath())))
//                .addFormDataPart("nickName", name)
//                .addFormDataPart("idCard", cardId)
//                .addFormDataPart("phonenumber", tel)
//                .addFormDataPart("email", email)
//                .addFormDataPart("sex", sex ? "0" : "1")
//                .build();
//
//        Request.Builder builder = new Request.Builder()
//                .header("Authorization", token)
//                .url(Connectinfo.updateuser);
//
//        builder.put(requestBody);
//
//        Request request = builder.build();
//
//        new OkHttpClient().newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//
//
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//
//
//                byte[] bytes = response.body().bytes();
//                String result = new String(bytes);
//                Gson gson = new GsonBuilder().create();
//                JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
//                onsuccessJsonObjectMethod(jsonObject);
//               /* int code = jsonObject.get("code").getAsInt();
//                String msg = jsonObject.get("msg").getAsString();
//
//                if (code == 200) {
//
//
//                    finish();
//                }*/
//
//            }
//        });
    }

    private Handler handler = new Handler();

    private void onsuccessJsonObjectMethod(final JsonObject jsonObject) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (jsonObject != null) {
                    try {
                        int code = jsonObject.get("code").getAsInt();
                        String msg = jsonObject.get("msg").getAsString();

                        if (code == 200) {


                            finish();
                            Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onSuccess( Object fileName, Object url) {
        updateUser(fileName.toString());
    }

    @Override
    public void onFailure(String error) {

        Toast.makeText(this, "上传头像失败！" + error, Toast.LENGTH_SHORT).show();
    }
}