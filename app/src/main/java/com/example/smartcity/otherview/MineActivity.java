package com.example.smartcity.otherview;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.view.MainActivity;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MineActivity extends Activity implements View.OnClickListener {

    private String filePath = null;
    public static final String TAG = "xel_mine_studentmypage";
    int queryCase = 0;// 0 无修改状态 / 1 用户修改文字信息 / 2 用户修改个人头像
    LinearLayout userIcon_tag;
    ProgressDialog uploadDialog;
    Xel_mine_modifyUserIcon_popWindow popDialog_UserIcon;
    Xel_mine_modifyInfo_popWindow popDialog_Info;
    ImageView img_userIcon;
    private String fileName = null;
    private Uri muri;
    private static final int PHOTO_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private static final int PHOTO_CLIP = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        init();
        Ini_view_image();
        setViewContent();
    }

    private void Ini_view_image() {

    }

    private void setViewContent() {

    }

    private void init() {
        userIcon_tag = (LinearLayout) findViewById(R.id.userIcon_tag);
        userIcon_tag.setOnClickListener(this);
        uploadDialog = new ProgressDialog(this);
        img_userIcon = (ImageView) findViewById(R.id.img_userIcon);
        uploadDialog.setTitle("更新信息");
        uploadDialog.setMessage("正在上传用户信息，请稍后~");
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (MineActivity.this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    MineActivity.this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                } else {
                    //这里就是权限打开之后自己要操作的逻辑
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        popDialog_UserIcon = new Xel_mine_modifyUserIcon_popWindow(MineActivity.this, itemsOnClick_UserIcon);
        //显示窗口
        popDialog_UserIcon.showAtLocation(MineActivity.this.findViewById(R.id.student_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

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
        Log.d("yann", "getPicFromPhoto");
        startActivityForResult(intent, PHOTO_REQUEST);
    }


    public void getPicFromCamera() {
        Log.d("yann", "getPicFromCamera");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 下面这句指定调用相机拍照后的照片存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                Environment.getExternalStorageDirectory(), "test.jpg")));
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("yann", "requestCode" + requestCode);
        switch (requestCode) {
            case CAMERA_REQUEST:
                switch (resultCode) {
                    case -1:// -1表示拍照成功
                        File file = new File(Environment.getExternalStorageDirectory()
                                + "/test.jpg");
                        if (file.exists()) {
                            Log.d("yann", "Uri.fromFile(file)===" + Uri.fromFile(file));
                            photoClip(Uri.fromFile(file));
                        } else {
                            Log.d(TAG, "!file.exists()");
                        }
                        break;
                    default:
                        Log.d(TAG, "RESULTCODE:" + resultCode);
                        break;
                }
                break;
            case PHOTO_REQUEST:
                if (data != null) {
                    Log.d("yann", "data.getData()===" + data.getData());
                    photoClip(data.getData());
                } else {
//                    Log.d("yann", "wochucuole" + data.getData());
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

    public void saveImage(Bitmap bmp) {
        //SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd,hh:mm:ss");
        //String date = sDateFormat.format(new java.util.Date());

        File appDir = new File(Environment.getExternalStorageDirectory(), "Xel_Resources");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        fileName = "logo.jpg";
        File file = new File(appDir, fileName);
        final String fileDir = appDir + "/" + fileName;
        Log.d(TAG, "fileDir:" + fileDir);//服务器端返回的结果 100 失败 200 成功
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            Integer i = fileDir.length();
            String uri = fileDir.substring(1, i);
            // MsharedPrefrence.SetUserTempImgDir(this,phoneNum,uri);
            //转换为JPEG格式,质量为100
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                //                    Log.d(TAG, "FilePath:" + fileDir + "UniqueID:" + cUserUniqueID);
//                    recode = Post_UserInfo.getStringCha(Constant.post_image, fileDir, cUserUniqueID);//向服务器提交用户头像上传请求
                int recode = 22222222;
//                    queryCase = 2;  //说明用户选择了头像上传
                Log.d(TAG, "Post Img result:" + recode);//服务器端返回的结果 100 失败 200 成功
                Message msg = new Message();//消息处理机制
                Bundle bundle = new Bundle();
                bundle.putString("response", String.valueOf(recode));
//                    Log.d("用户请求上传图片返回的结果", String.valueOf(bundle));
//                    msg.setData(bundle);
//                    handler.sendMessage(msg);
            }
        }).start();
//        getHandlerMSG();//调用消息处理结果方法

    }

    private void photoClip(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        muri = uri;
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

    private View.OnClickListener itemsOnClick_UserInfo = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            popDialog_Info.dismiss();
            switch (v.getId()) {
                case R.id.modify_edit_confirm:
                    //  selectFlag = 1;
                    TextView tv = (TextView) Xel_mine_modifyInfo_popWindow.popView.findViewById(R.id.modify_edit);
                    String textContent = null;
                    if (tv != null) {
                        textContent = tv.getText().toString();
                        tv.setText("");
                    } else {
                        Log.d("1111111111", "代码有异常");
                    }
                    Log.d("1111111111", "用户输入的信息:" + textContent);
                    //          getTagToChnageText(1, textContent);
                    break;
                case R.id.modify_edit_cancel:
                    //  flag = 0;
                    break;
            }

        }
    };
}
