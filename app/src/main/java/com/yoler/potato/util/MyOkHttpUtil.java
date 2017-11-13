package com.yoler.potato.util;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

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
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .sslSocketFactory(initSSLSocketFactory(), initTrustManager())
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


    /**
     * TrustManager
     *
     * @return
     */
    public static X509TrustManager initTrustManager() {
        X509TrustManager mTrustManager = new X509TrustManager() {

            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        return mTrustManager;
    }

    /**
     * SSLSocketFactory
     *
     * @return
     */
    public static SSLSocketFactory initSSLSocketFactory() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            X509TrustManager[] xTrustArray = new X509TrustManager[]{initTrustManager()};
            sslContext.init(null, xTrustArray, new SecureRandom());
        } catch (Exception e) {
            LogUtil.e(e.getMessage(),e);
        }
        return sslContext.getSocketFactory();
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

}




