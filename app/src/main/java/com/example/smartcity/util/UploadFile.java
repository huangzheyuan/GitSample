package com.example.smartcity.util;

import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.baseinfo.Const;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UploadFile {

    public interface OnUploadOver {

        void onSuccess(Object fileName, Object url);

        void onFailure(String error);
    }

    public static void uploadFile(String filePath, String fileName, OnUploadOver onUploadOver) {


        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", fileName, RequestBody.create(MediaType.parse("multipart/form-data"), new File(filePath))).build();

        Request.Builder builder = new Request.Builder()
                .header("Authorization", Const.tokens)
                .url(Connectinfo.upload_File_url).post(requestBody);
        builder.post(requestBody);

        Request request = builder.build();

        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                if (onUploadOver != null)
                    onUploadOver.onFailure(e.getLocalizedMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {


                byte[] bytes = response.body().bytes();
                String result = new String(bytes);

                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(result, JsonObject.class);

                int code = jsonObject.get("code").getAsInt();


                if (code == 200) {

                    String fileName = jsonObject.get("fileName").getAsString();
                    if (onUploadOver != null)
                        onUploadOver.onSuccess(fileName, "http://124.93.196.45:10001/prod-api" + fileName);
                }

            }
        });
    }
}
