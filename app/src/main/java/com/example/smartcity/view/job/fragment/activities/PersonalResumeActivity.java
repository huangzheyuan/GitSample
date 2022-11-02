package com.example.smartcity.view.job.fragment.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.method.KeyListener;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.baseinfo.Const;
import com.example.smartcity.util.ContentUriUtil;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.example.smartcity.util.UploadFile;
import com.example.smartcity.view.car.movement.FileUtil;
import com.example.smartcity.view.job.entities.HotJobListEntity;
import com.example.smartcity.view.job.entities.ResumeInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PersonalResumeActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 10;
    private EditText highestView;
    private EditText educationView;
    private EditText experienceView;
    private EditText addressView;
    private EditText descView;
    private EditText wageView;
    private EditText fileView;
    private Button okButton;
    private Button cancelButton;
    private ImageButton selectButton;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;

    private KeyListener highestKeyListener;
    private KeyListener educationKeyListener;
    private KeyListener addressKeyListener;
    private KeyListener experienceKeyListener;
    private KeyListener descKeyListener;
    private KeyListener moneyKeyListener;

    private ResumeInfo resumeInfo;

    private final String TITLE_NEW = "新增个人简历";
    //    private final String TITLE_NEW_OK = "确认新增";
    private final String TITLE_EDIT = "修改个人简历";
//    private final String TITLE_EDIT_OK = "确认修改";

//    private String items[] = new String[]{"选择职位","Java开发工程师", "设计", "外教", "前端工程师", "牙医", "全栈开发工程师"};

    private HotJobListEntity hotJobListEntity;
    private OkHttpUtils.ReqType reqType;
    private String fileName, filePath;
    private UploadFile.OnUploadOver onUploadOver = new UploadFile.OnUploadOver() {
        @Override
        public void onSuccess(Object fileName, Object url) {

            requestResume(reqType, fileName.toString());

        }

        @Override
        public void onFailure(String error) {

        }
    };

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {

            String info = msg.what == 0 ? "添加简历成功" : "修改简历成功";
            Toast.makeText(PersonalResumeActivity.this, info, Toast.LENGTH_SHORT).show();
            finish();

            super.handleMessage(msg);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_resume);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }

        TextView textView = findViewById(R.id.headerTitle);
        textView.setText("个人简历管理");

        highestView = findViewById(R.id.editText_highest);
        educationView = findViewById(R.id.editText_education);
        experienceView = findViewById(R.id.editText_experience);
        addressView = findViewById(R.id.editText_address);
        descView = findViewById(R.id.editText_desc);
        wageView = findViewById(R.id.editText_wage);
        fileView = findViewById(R.id.editText_file);

        spinner = findViewById(R.id.spinner);
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
//        spinner.setAdapter(adapter);
//        spinner.setBackground(null);


        okButton = findViewById(R.id.button_ok);
        cancelButton = findViewById(R.id.button_cancel);
        selectButton = findViewById(R.id.button_select);

        selectButton.setOnClickListener(view -> pickFile());
        okButton.setOnClickListener(view -> {

            Button button = (Button) view;
            String title = button.getText().toString();

//            if (title.equals(TITLE_EDIT_OK)) {
//
//                //请求修改
//                requestResume(OkHttpUtils.ReqType.PUT);
//            } else if (title.equals(TITLE_NEW_OK)) {
//                // 请求新增
//                requestResume(OkHttpUtils.ReqType.POST);
//            } else if (title.equals(TITLE_NEW)) {
//
//                cancelButton.setText("取消");
//                button.setText(TITLE_NEW_OK);
//                setEditable(true);
//
//            } else if (title.equals(TITLE_EDIT)) {
//                cancelButton.setText("取消");
//                button.setText(TITLE_EDIT_OK);
//                setEditable(true);
//            }

            String mostEducation = highestView.getText().toString();
            String education = educationView.getText().toString();
            String experience = experienceView.getText().toString();
            String address = addressView.getText().toString();
            String individualResume = descView.getText().toString();
            String money = wageView.getText().toString();
            String files = filePath == null ? "" : filePath;
            int positionId = (int) spinner.getSelectedItemId();

            //验证

            if (verifyInput(mostEducation, "")) {

                Toast.makeText(this, "请输入最高学历", Toast.LENGTH_SHORT).show();
                return;
            }
            if (verifyInput(education, "")) {

                Toast.makeText(this, "请输入教育经历", Toast.LENGTH_SHORT).show();
                return;
            }

            if (verifyInput(address, "")) {

                Toast.makeText(this, "请输入居住地址", Toast.LENGTH_SHORT).show();
                return;
            }
            if (verifyInput(experience, "")) {

                Toast.makeText(this, "请输入工作经验", Toast.LENGTH_SHORT).show();
                return;
            }

            if (verifyInput(individualResume, "")) {

                Toast.makeText(this, "请输入个人简介", Toast.LENGTH_SHORT).show();
                return;
            }

            if (verifyInput(money, "")) {

                Toast.makeText(this, "请输入期望薪资", Toast.LENGTH_SHORT).show();
                return;
            }
            File file = new File(files);

            if (verifyInput(files, "")) {

                Toast.makeText(this, "请选择简历文件", Toast.LENGTH_SHORT).show();
                return;
            }else if(!file.exists()){

                Toast.makeText(this, "文件不存在，请重新选择简历文件", Toast.LENGTH_SHORT).show();
                return;
            }


            if (title.equals(TITLE_NEW)) {

                reqType = OkHttpUtils.ReqType.POST;
            } else if (title.equals(TITLE_EDIT)) {

                reqType = OkHttpUtils.ReqType.PUT;
            }

            if (filePath.equals("")) {

                Toast.makeText(this, "请选择简历附件", Toast.LENGTH_SHORT).show();
            } else {

                UploadFile.uploadFile(filePath, fileName, onUploadOver);
            }
        });


        cancelButton.setOnClickListener(view -> {

            finish();

        });
        getJobTypeList();

    }

    public void getJobTypeList() {

        OkHttpUtils okHttp = OkHttpUtils.getInstance();

        okHttp.syncJsonGetwithTokenByURL(Connectinfo.professionlisturl, Const.tokens, jsonObject -> {

            Gson gson = new Gson();

            hotJobListEntity = gson.fromJson(jsonObject.toString(), HotJobListEntity.class);

            List<String> items = new ArrayList<>();

            for (HotJobListEntity.RowsBean rb :
                    hotJobListEntity.getRows()) {

                items.add(rb.getProfessionName());
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
            spinner.setAdapter(adapter);

            getData();
        });
    }

    public KeyListener setReadOnly(EditText v) {

        KeyListener keyListener = v.getKeyListener();
        v.setKeyListener(null);
        v.setTextIsSelectable(true);
        return keyListener;
    }

    public void setEditable(EditText v, KeyListener keyListener) {
        // 可编辑时弹出软键盘
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, 0);

        // 恢复KeyListener
        v.setKeyListener(keyListener);
        // 如果需要,设置文字可选
        v.setTextIsSelectable(true);

        // 恢复KeyListener后,键盘不会自动弹出,要通过代码弹出
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.showSoftInput(v, 0);
            }
        });
        // 将光标定位到最后
        v.setSelection(v.getText().length());
    }

    public void setEditable(boolean editable) {

        if (editable) {

            setEditable(highestView, highestKeyListener);
            setEditable(educationView, educationKeyListener);
            setEditable(experienceView, experienceKeyListener);
            setEditable(addressView, addressKeyListener);
            setEditable(descView, descKeyListener);
            setEditable(wageView, moneyKeyListener);
        } else {

            highestKeyListener = setReadOnly(highestView);
            educationKeyListener = setReadOnly(educationView);
            experienceKeyListener = setReadOnly(experienceView);
            addressKeyListener = setReadOnly(addressView);
            descKeyListener = setReadOnly(descView);
            moneyKeyListener = setReadOnly(wageView);
        }

    }


    // 打开系统的文件选择器
    public void pickFile() {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        intent.setType("*/*");
//        this.startActivityForResult(intent, REQUEST_CODE);

        final String DOC = "application/msword";
        final String DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        final String PDF = "application/pdf";
        String[] mimeTypes = {DOC, DOCX, PDF};

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("application/*");

        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);

        intent = Intent.createChooser(intent, "选择文件");
        startActivityForResult(intent, REQUEST_CODE);
    }

    // 获取文件的真实路径
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            // 用户未选择任何文件，直接返回
            return;
        }

        Uri uri = data.getData(); // 获取用户选择文件的URI
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            fileName = DocumentFile.fromSingleUri(PersonalResumeActivity.this, uri).getName();

            filePath = getExternalFilesDir(null).getAbsolutePath() + File.separator + fileName;

            File file = new File(filePath);
            if (!file.exists()) {

                file.createNewFile();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);

            byte[] buffer = new byte[4096];
            int length;
            while ((length = bufferedInputStream.read(buffer)) != -1) {

                fileOutputStream.write(buffer, 0, length);
            }

            fileOutputStream.close();
            inputStream.close();
            fileView.setText(fileName);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        if (uri != null && "content".equals(uri.getScheme())) {
//            Cursor cursor = this.getContentResolver().query(uri, new String[] { android.provider.MediaStore.Images.ImageColumns.DATA }, null, null, null);
//            cursor.moveToFirst();
//            filePath = cursor.getString(0);
//            cursor.close();
//        } else {
//            filePath = uri.getPath();
//        }
////        getContentResolver().openInputStream(uri);
//        String path = uri.getPath();
//        String documentId = DocumentsContract.getDocumentId(uri);
//
//        String[] projection = { MediaStore.Images.Media.DATA };
//
//        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//        cursor.moveToFirst();
//        System.out.println();
//        int index = filePath.lastIndexOf(File.separator);
//        fileName = filePath.substring(index);
//
//        Cursor cursor = ContentResolver..Query(contentURI, null, null, null, null);
//        cursor.MoveToFirst();
//        string documentId = cursor.GetString(0);
//        documentId = documentId.Split(':')[1];
//        cursor.Close();
//
//        cursor = ContentResolver.Query(
//                Android.Provider.MediaStore.Images.Media.ExternalContentUri,
//                null, MediaStore.Images.Media.InterfaceConsts.Id + " = ? ", new [] { documentId }, null);
//        cursor.MoveToFirst();
//        string path = cursor.GetString(cursor.GetColumnIndex(MediaStore.Images.Media.InterfaceConsts.Data));
//        cursor.Close();
//
//        DocumentsContract.
//
//
//
//        ContentResolver cr = getContentResolver();
//        Cursor c = cr.query(uri, null, null, null, null);
//        c.moveToFirst();
//        String documentId = c.getString(0);
//
//        Cursor c = cr.query(uri, new String[] { MediaStore.Files.FileColumns.PARENT }, null, null, null);
//        Cursor c = cr.query(uri, null, null, null, null);
////        if (c.moveToFirst()) {
////            filePath = c.getString(0);
////        }
////        c.close();
//
//        // 通过ContentProvider查询文件路径
//        ContentResolver resolver = this.getContentResolver();
//        Cursor cursor = resolver.query(uri, null, null, null, null);
//        if (cursor == null) {
//            // 未查询到，说明为普通文件，可直接通过URI获取文件路径
//            filePath = uri.getPath();
//            int index = filePath.lastIndexOf(File.separator);
//            fileName = filePath.substring(index);
//            fileView.setText(filePath);
//            return;
//        }else if(cursor.moveToFirst()) {
//            String[] names = cursor.getColumnNames();
//
//            // 多媒体文件，从数据库中获取文件的真实路径
////            String path = cursor.getString(cursor.getColumnIndex("_data"));
////            String path = cursor.getString(cursor.getColumnIndex("_display_name"));
//
//            filePath = FileUtil.getFilePathByUri(PersonalResumeActivity.this, uri);
//
//            int index = filePath.lastIndexOf(File.separator);
//            fileName = filePath.substring(index);
//            fileView.setText(filePath);
//        }
//        cursor.close();
    }

    private void getData() {

        if (Const.userid < 0) {

            return;
        }

        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        PrefStore prefStore = PrefStore.getInstance(this);
        String token = prefStore.getPref("Authorization", null);
        String url = String.format("%s%d", Connectinfo.resumeurl, Const.userid);

        if (token != null) {

            okHttp.syncJsonGetwithTokenByURL(url, token, jsonObject -> {

                Gson gson = new Gson();

                resumeInfo = gson.fromJson(jsonObject.toString(), ResumeInfo.class);

                if (resumeInfo.getCode() == 200) {

                    if (resumeInfo.getData() == null) {
                        okButton.setText(TITLE_NEW);
                    } else {
                        okButton.setText(TITLE_EDIT);

                        ResumeInfo.DataBean info = resumeInfo.getData();

                        highestView.setText(info.getMostEducation());
                        educationView.setText(info.getEducation());
                        experienceView.setText(info.getExperience());
                        addressView.setText(info.getAddress());
                        descView.setText(info.getIndividualResume());
                        wageView.setText(info.getMoney());

                        filePath = info.getFiles();
                        File resumeFile = new File(filePath);


                        int lastIndex = info.getFiles().lastIndexOf("/") + 1;


                        fileName = lastIndex >= 0 ? info.getFiles().substring(lastIndex) : info.getFiles();
                        if (!resumeFile.exists()) {

                            filePath = fileName = "";
                        }
                        fileView.setText(fileName);


                        for (int i = 0; i < hotJobListEntity.getRows().size(); i++) {

                            if (hotJobListEntity.getRows().get(i).getId() == info.getPositionId()) {

                                spinner.setSelection(i);
                                break;
                            }
                        }
                    }

                }
            });
        }
    }


    private boolean verifyInput(String text, String value) {

        return text.equals(value);
    }

    private void requestResume(OkHttpUtils.ReqType reqType, String resumeUrl) {

        if (Const.userid < 0) {

            return;
        }

//        PrefStore prefStore = PrefStore.getInstance(this);
//        String token = prefStore.getPref("Authorization", null);

        String mostEducation = highestView.getText().toString();
        String education = educationView.getText().toString();
        String experience = experienceView.getText().toString();
        String address = addressView.getText().toString();
        String individualResume = descView.getText().toString();
        String money = wageView.getText().toString();
//        String files = filePath;
        String files = filePath == null ? "" : filePath;

        int positionId = (int) spinner.getSelectedItemId();

        //验证
        if (verifyInput(mostEducation, "")) {

            Toast.makeText(this, "请输入最高学历", Toast.LENGTH_SHORT).show();
            return;
        }
        if (verifyInput(education, "")) {

            Toast.makeText(this, "请输入教育经历", Toast.LENGTH_SHORT).show();
            return;
        }

        if (verifyInput(address, "")) {

            Toast.makeText(this, "请输入居住地址", Toast.LENGTH_SHORT).show();
            return;
        }
        if (verifyInput(experience, "")) {

            Toast.makeText(this, "请输入工作经验", Toast.LENGTH_SHORT).show();
            return;
        }
        if (verifyInput(individualResume, "")) {

            Toast.makeText(this, "请输入个人简介", Toast.LENGTH_SHORT).show();
            return;
        }
        if (verifyInput(money, "")) {

            Toast.makeText(this, "请输入期望薪资", Toast.LENGTH_SHORT).show();
            return;
        }



        File file = new File(files);

        if (verifyInput(files, "")) {

            Toast.makeText(this, "请选择简历文件", Toast.LENGTH_SHORT).show();
            return;
        }else if(!file.exists()){

            Toast.makeText(this, "文件不存在，请重新选择简历文件", Toast.LENGTH_SHORT).show();
            return;
        }

//        if (verifyInput(positionId, "0")) {
//
//            Toast.makeText(this, "请选择职位", Toast.LENGTH_SHORT).show();
//            return;
//        }


        Resume resume;
        resume = new Resume(-1,
                address,
                education,
                experience,
                files,
                individualResume,
                money,
                mostEducation,
                hotJobListEntity.getRows().get(positionId).getId(),
                Const.userid
        );

        if (reqType == OkHttpUtils.ReqType.PUT) {

            resume.setId(resumeInfo.getData().getId());
        }

        String json = new Gson().toJson(resume, Resume.class);

        MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(json, jsonMediaType);


        Request.Builder builder = new Request.Builder()
                .header("Authorization", Const.tokens)
                .url(Connectinfo.addpositionurl);

        if (reqType == OkHttpUtils.ReqType.POST) {

            builder.post(body);
        } else {

            builder.put(body);
        }

        Request request = builder.build();

        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                Toast.makeText(PersonalResumeActivity.this, "操作失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {


                byte[] bytes = response.body().bytes();
                String result = new String(bytes);

                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(result, JsonObject.class);

                int code = jsonObject.get("code").getAsInt();
//                String msg = jsonObject.get("msg").getAsString();

                if (code == 200) {

                    Message message = handler.obtainMessage();

                    int what = reqType == OkHttpUtils.ReqType.POST ? 0 : 1;
                    handler.sendEmptyMessage(what);

//                    new Handler().post(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            String msg = reqType == OkHttpUtils.ReqType.POST ? "添加简历成功" : "修改简历成功";
//                            Toast.makeText(PersonalResumeActivity.this, msg, Toast.LENGTH_SHORT).show();
//                            finish();
//                        }
//                    });

                }

            }
        });

        /*
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file,
                        RequestBody.create(MediaType.parse("multipart/form-data"), new File(file)))
                .addFormDataPart(reqType == OkHttpUtils.ReqType.POST ? "userId" : "id", id)

                .addFormDataPart("mostEducation", highest)
                .addFormDataPart("education", education)
                .addFormDataPart("address", address)
                .addFormDataPart("experience", experience)
                .addFormDataPart("individualResume", desc)
                .addFormDataPart("money", wage)
                .addFormDataPart("positionId", positionId)
                .build();


        Request.Builder builder = new Request.Builder()
                .header("Authorization", token)
                .url(Connectinfo.resumeurl);
        if(reqType == OkHttpUtils.ReqType.POST){

            builder.post(requestBody);
        }else{
            builder.put(requestBody);
        }

        Request request = builder.build();

        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {


                Toast.makeText(PersonalResumeActivity.this, "操作失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {


                byte[] bytes = response.body().bytes();
                String result = new String(bytes);

                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(result, JsonObject.class);

                int code = jsonObject.get("code").getAsInt();
                String msg = jsonObject.get("msg").getAsString();

                if (code == 200) {

                    finish();
                }

            }
        });

         */
    }

    private static class Resume {

        private int id;
        private String address;
        private String education;
        private String experience;
        private String files;
        private String individualResume;
        private String money;
        private String mostEducation;
        private int positionId;
        private int userId;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Resume(int id,
                      String address,
                      String education,
                      String experience,
                      String files,
                      String individualResume,
                      String money,
                      String mostEducation,
                      int positionId,
                      int userId
        ) {

            this.id = id;
            this.address = address;
            this.education = education;
            this.experience = experience;
            this.files = files;
            this.individualResume = individualResume;
            this.money = money;
            this.mostEducation = mostEducation;
            this.positionId = positionId;
            this.userId = userId;

        }

        public Resume() {

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public String getFiles() {
            return files;
        }

        public void setFiles(String files) {
            this.files = files;
        }

        public String getIndividualResume() {
            return individualResume;
        }

        public void setIndividualResume(String individualResume) {
            this.individualResume = individualResume;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getMostEducation() {
            return mostEducation;
        }

        public void setMostEducation(String mostEducation) {
            this.mostEducation = mostEducation;
        }

        public int getPositionId() {
            return positionId;
        }

        public void setPositionId(int positionId) {
            this.positionId = positionId;
        }
    }
}