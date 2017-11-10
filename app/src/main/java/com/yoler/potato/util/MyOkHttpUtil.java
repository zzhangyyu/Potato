package com.yoler.potato.util;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Admin on 2017/11/9.
 */

public class MyOkHttpUtil {
    private static MyOkHttpUtil myOkHttpUtil;
    private static OkHttpClient okHttpClient;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private MyOkHttpUtil() {
        okHttpClient = new OkHttpClient();
        okHttpClient.newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 单例模式 获取MyOkHttpUtil实例
     *
     * @return
     */
    private static MyOkHttpUtil getInstance() {
        if (myOkHttpUtil == null) {
            myOkHttpUtil = new MyOkHttpUtil();
        }
        return myOkHttpUtil;
    }

    public static void getAsync(String reqUrl, Map<String, String> params, Callback callBack) {
        final Request request = new Request.Builder().url(reqUrl).build();
        getInstance().okHttpClient.newCall(request).enqueue(callBack);
    }

    public static void postAsync(String reqUrl, String reqJson, Callback callBack) {
        RequestBody requestBody = RequestBody.create(JSON, reqJson);
        final Request request = new Request.Builder().url(reqUrl).post(requestBody).build();
        getInstance().okHttpClient.newCall(request).enqueue(callBack);
    }

}


