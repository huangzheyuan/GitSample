package com.example.smartcity.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;

import android.util.Log;


import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OkHttp的封装工具类
 */
public class OkHttpUtils {
    //TAG
    private static final String TAG = OkHttpUtils.class.getSimpleName();
    //声明客户端
    private OkHttpClient client;
    //防止多个线程同时访问所造成的安全隐患
    private volatile static OkHttpUtils okHttpUtils;
    //定义提交类型Json
    private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    //定义提交类型String
    private static final MediaType STRING = MediaType.parse("text/x-markdown;charset=utf-8");
    //子线程
    private Handler handler;
    //构造方法
    private OkHttpUtils() {
        //初始化
        client = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());
    }
    //单例模式
    public static OkHttpUtils getInstance() {
//        OkHttpUtils okUtils = null;
//        if (okHttpUtils == null) {
//            //线程同步
//            synchronized (OkHttpUtils.class) {
//                if (okUtils == null) {
//                    okUtils = new OkHttpUtils();
//                    okHttpUtils = okUtils;
//                }
//            }
//        }
        OkHttpUtils okUtils = new OkHttpUtils();
        return okUtils;
    }
    //todo 不带参数的get请求

    public static void GetDataByUrl(final String url, final Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                final Request request = new Request.Builder().url(url).build();
                okHttpClient.newCall(request).enqueue(callback);
            }
        }).start();
    }

    //  向服务器提交表单 put请求 带token 1

    public void syncPutByurlwithToken(String url, Map<String, String> params, String token, final FuncJsonObject callback){
            FormBody.Builder from = new FormBody.Builder();
            if (params != null && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    from.add(entry.getKey(), entry.getValue());
                }
            }
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            Gson gson = new Gson();
            RequestBody requestBody = RequestBody.create(JSON, gson.toJson(params));
            //todo post提交
            Request request = new Request.Builder().url(url).addHeader("Authorization",token).put(requestBody).build();
            client.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                    Log.i(TAG, "解析失败");
                }
                @Override
                public void onResponse(@NotNull okhttp3.Call call, @NotNull Response response) throws IOException {
                    if (response != null && response.isSuccessful()) {
                        onsuccessJsonObjectMethod(response.body().string(), callback);
                    }
                }
            });


    }
    //todo 带参数的get请求
    public void GetDataByUrl(final String url, Map<String, String> params, final Callback callback) {
        final String u2;
        String urls=url+"?";
        //键值对不为空，他的值也不为空
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urls=urls+entry.getKey()+"="+entry.getValue()+"&";
            }
        }
        u2=urls;
        new Thread(new Runnable() {

            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                final Request request = new Request.Builder().url(u2).build();
                okHttpClient.newCall(request).enqueue(callback);
            }
        }).start();
    }
    /**
    /**
     * 请求的返回结果是json字符串
     * @param jsonValue
     * @param callBack
     */
    private void onsuccessJsonStringMethod(final String jsonValue, final FuncJsonString callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    try {
                        //解析json
                        callBack.onResponse(jsonValue);
                    } catch (Exception e) {

                        String message = e.getLocalizedMessage();
                        System.out.println(message);
                    }
                }
            }
        });
    }

    /**
     * 求的返回结果是json对象
     * @param jsonValue
     * @param callBack
     */
    private void onsuccessJsonObjectMethod(final String jsonValue, final FuncJsonObject callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    try {
                        callBack.onResponse(new JSONObject(jsonValue));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 求的返回结果是json数组
     * @param data
     * @param callBack
     */
    private void onsuccessJsonByteMethod(final byte[] data, final FuncJsonObjectByte callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onResponse(data);
                }
            }
        });
    }

    /**
     * 同步请求,不是很常用，因为会阻塞线程
     * @param url
     * @return
     */
    public String asyncGetByURL(String url) {
        //构建一个Request请求
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            //同步请求数据
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 请求指定的url，返回的结果是json字符串
     *
     * @param url
     * @param callback
     */
    public void syncJsonStringByURL(String url, final FuncJsonString callback) {
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                Log.i(TAG, "请求失败");
            }

            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    onsuccessJsonStringMethod(response.body().string(), callback);
                }
            }
        });
    }

    //todo 带token的get请求
    public void syncGetwithTokenByURL(String url, String token,final FuncJsonString callback) {
        final Request request = new Request.Builder().url(url).addHeader("Authorization",token).build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                Log.i(TAG, "请求失败");
            }

            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    onsuccessJsonStringMethod(response.body().string(), callback);
                }
            }
        });
    }
    public void syncJsonGetwithTokenByURL(String url, String token,final FuncJsonObject callback) {
        final Request request = new Request.Builder().url(url).addHeader("Authorization",token).build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                Log.i(TAG, "请求失败");
            }

            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    onsuccessJsonObjectMethod(response.body().string(), callback);
                }
            }
        });
    }

    /**
     * 请求指定的url，返回的结果是json对象
     *
     * @param url
     * @param callback
     */
    public void syscJsonObjectByURL(String url, final FuncJsonObject callback) {
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                Log.i(TAG, "解析失败");
            }

            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    onsuccessJsonObjectMethod(response.body().string(), callback);
                }
            }
        });

    }
    /**
     * 请求指定的url，返回的结果是byte字节数组
     *
     * @param url
     * @param callback
     */
    public void syscGetByteByURL(String url, final FuncJsonObjectByte callback) {
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                Log.i(TAG, "解析失败");
            }

            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    onsuccessJsonByteMethod(response.body().bytes(), callback);
                }
            }
        });


    }

    /**
     * 请求指定的url，返回的结果是Bitmap
     * @param url
     * @param callback
     */
    public void syscDownloadImageByURL(String url, final FuncJsonObjectBitmap callback) {
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                Log.i(TAG, "解析失败");
            }

            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    byte[] data = response.body().bytes();
                    Bitmap bitmap = new PicassoUtils.CropImageView().transform(BitmapFactory.decodeByteArray(data, 0, data.length));
                    callback.onResponse(bitmap);
                }
            }
        });

    }
    /**
     * 向服务器提交表单 post请求
     * @param url      提交地址
     * @param params   提交数据
     * @param callback 提交回调
     */
    public void syncPostByUrl(String url, Map<String, String> params, final FuncJsonObject callback) {
        //表单对象，包含input开始的操作
        FormBody.Builder from = new FormBody.Builder();
        //键值对不为空，他的值也不为空
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                //装载表单值
                from.add(entry.getKey(), entry.getValue());
            }
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        RequestBody body = from.build();
        Gson gson = new Gson();
        RequestBody requestBody = RequestBody.create(JSON, gson.toJson(params));


        //post提交
        Request request = new Request.Builder().url(url).post(requestBody).build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                Log.i(TAG, "解析失败");
            }

            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    onsuccessJsonObjectMethod(response.body().string(), callback);
                }
            }
        });

    }

    //todo 向服务器提交表单 post请求 带token 1
    public void syncPostByUrladdHeaderToken(String url, Map<String, String> params, String token,final FuncJsonString callback) {
        FormBody.Builder from = new FormBody.Builder();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                from.add(entry.getKey(), entry.getValue());
            }
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();
        RequestBody requestBody = RequestBody.create(JSON, gson.toJson(params));
        //todo post提交
        Request request = new Request.Builder().url(url).addHeader("Authorization",token).post(requestBody).build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                Log.i(TAG, "解析失败");
            }
            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    onsuccessJsonStringMethod(response.body().string(), callback);
                }
            }
        });
    }
    //post请求，带file文件，返回Json对象
    public void syncPostJsonByUrlwithFile(String url, RequestBody requestBody, String token,final FuncJsonObject callback) {
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();


        final okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = httpBuilder
                //设置超时
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            private static final String TAG = "upload";

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "uploadMultiFile() e=" + e);
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "uploadMultiFile() response=" + response.body().string());
                onsuccessJsonObjectMethod(response.body().string(), callback);
            }
        });

    }

//post请求带File，返回字符串
    public void syncPostByUrlwithFile(String url, RequestBody requestBody, String token,final FuncJsonString callback) {
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();


        final okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = httpBuilder
                //设置超时
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            private static final String TAG = "upload";

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "uploadMultiFile() e=" + e);
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "uploadMultiFile() response=" + response.body().string());
                onsuccessJsonStringMethod(response.body().string(), callback);
            }
        });

    }
    /**
     * 返回字符串json的接口
     */
    public interface FuncJsonString {
        //处理我们返回的结果
        void onResponse(String result);
    }
    /**
     * 返回json对象的接口
     */
    public interface FuncJsonObject {
        //处理我们返回的结果
        void onResponse(JSONObject jsonObject);
    }
    /**
     * 返回json对象的接口
     */
    interface FuncJsonObjectByte {
        //处理我们返回的结果
        void onResponse(byte[] result);
    }
    /**
     * 返回json对象的接口
     */
    interface FuncJsonObjectBitmap {
        //处理我们返回的结果
        void onResponse(Bitmap bitmap);
    }
    /*
     * yangguang
     * */

    public  enum ReqType {

        GET, POST, PUT, DELETE;
    };


    public void syncRequestWithBodyAndTokenForJsonString(String url, Map<String, String> params, String token,ReqType reqType, final FuncJsonString callback) {

        FormBody.Builder from = new FormBody.Builder();

        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                from.add(entry.getKey(), entry.getValue());
            }
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();
        RequestBody requestBody = RequestBody.create(JSON, gson.toJson(params));

        Request request = null;

        Request.Builder builder = new Request.Builder();
        builder.url(url).addHeader("Authorization", token);

        switch (reqType){

            case GET:
                request = builder.get().build();
                break;
            case PUT:
                request = builder.put(requestBody).build();
                break;
            case POST:
                request = builder.post(requestBody).build();
                break;
            case DELETE:
                request = builder.delete(requestBody).build();
                break;
        }

        if(request != null){

            client.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                    Log.i(TAG, "解析失败");
                }

                @Override
                public void onResponse(@NotNull okhttp3.Call call, @NotNull Response response) throws IOException {
                    if (response != null && response.isSuccessful()) {
                        onsuccessJsonStringMethod(response.body().string(), callback);
                    }
                }
            });
        }


    }
}
