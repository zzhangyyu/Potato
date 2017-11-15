package com.yoler.potato.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zhangyu on 2017/11/15.
 */

public class ActivityUtil {

    /**
     * 跳转Activity
     *
     * @param context
     * @param cls
     */
    public static void startActivity(Context context, Class cls) {
        startActivity(context, cls, null);
    }

    /**
     * 跳转Activity
     *
     * @param context
     * @param cls
     * @param extras
     */
    public static void startActivity(Context context, Class cls, Bundle extras) {
        Intent intent = new Intent(context, cls);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }

    /**
     * 跳转Activity(FLAG_ACTIVITY_NEW_TASK)
     *
     * @param context
     * @param cls
     */
    public static void startActivityWithNewTask(Context context, Class cls) {
        startActivityWithNewTask(context, cls, null);
    }

    /**
     * 跳转Activity(FLAG_ACTIVITY_NEW_TASK)
     *
     * @param context
     * @param cls
     * @param extras
     */
    public static void startActivityWithNewTask(Context context, Class cls, Bundle extras) {
        Intent intent = new Intent(context, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }

    /**
     * 跳转Activity
     *
     * @param activity
     * @param cls
     * @param requestCode
     */
    public static void startActivityForResult(Activity activity, Class cls, int requestCode) {
        startActivityForResult(activity, cls, requestCode, null);
    }

    /**
     * 跳转Activity
     *
     * @param activity
     * @param cls
     * @param requestCode
     * @param extras
     */
    public static void startActivityForResult(Activity activity, Class cls, int requestCode, Bundle extras) {
        Intent intent = new Intent(activity, cls);
        if (extras != null) {
            intent.putExtras(extras);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 获取intent string参数
     *
     * @param mActivity
     * @param savedInstanceState
     * @param paramName
     * @return
     */
    public static String getIntentStringParams(Activity mActivity, Bundle savedInstanceState, String paramName) {
        return savedInstanceState != null ? savedInstanceState.getString(paramName)
                : mActivity.getIntent().getStringExtra(paramName);
    }

    /**
     * 获取intent boolean参数
     *
     * @param mActivity
     * @param savedInstanceState
     * @param paramName
     * @return
     */
    public static boolean getIntentBooleanParams(Activity mActivity, Bundle savedInstanceState, String paramName,
                                                 boolean defaultValue) {
        return savedInstanceState != null ? savedInstanceState.getBoolean(paramName)
                : mActivity.getIntent().getBooleanExtra(paramName, defaultValue);
    }

    /**
     * 获取intent int参数
     *
     * @param mActivity
     * @param savedInstanceState
     * @param paramName
     * @return
     */
    public static int getIntentIntParams(Activity mActivity, Bundle savedInstanceState, String paramName,
                                         int defaultValue) {
        return savedInstanceState != null ? savedInstanceState.getInt(paramName)
                : mActivity.getIntent().getIntExtra(paramName, defaultValue);
    }

    /**
     * 获取intent double参数
     *
     * @param mActivity
     * @param savedInstanceState
     * @param paramName
     * @param defaultValue
     * @return
     */
    public static double getIntentDoubleParams(Activity mActivity, Bundle savedInstanceState, String paramName,
                                               double defaultValue) {
        return savedInstanceState != null ? savedInstanceState.getDouble(paramName)
                : mActivity.getIntent().getDoubleExtra(paramName, defaultValue);
    }

    /**
     * 获取intent参数
     *
     * @param mActivity
     * @param savedInstanceState
     * @param paramName
     * @return
     */
    public static ArrayList<String> getIntentStringArrayList(Activity mActivity, Bundle savedInstanceState,
                                                             String paramName) {
        return savedInstanceState != null ? savedInstanceState.getStringArrayList(paramName)
                : mActivity.getIntent().getStringArrayListExtra(paramName);
    }

    /**
     * 获取intent参数
     *
     * @param mActivity
     * @param savedInstanceState
     * @param paramName
     * @return
     */
    public static ArrayList<Integer> getIntentIntegerArrayList(Activity mActivity, Bundle savedInstanceState,
                                                               String paramName) {
        return savedInstanceState != null ? savedInstanceState.getIntegerArrayList(paramName)
                : mActivity.getIntent().getIntegerArrayListExtra(paramName);
    }

    /**
     * 获取intent参数
     *
     * @param mActivity
     * @param savedInstanceState
     * @param paramName
     * @return
     */
    public static String[] getIntentStringArray(Activity mActivity, Bundle savedInstanceState, String paramName) {
        return savedInstanceState != null ? savedInstanceState.getStringArray(paramName)
                : mActivity.getIntent().getStringArrayExtra(paramName);
    }

    /**
     * 获取intent参数
     *
     * @param mActivity
     * @param savedInstanceState
     * @param paramName
     * @return
     */
    public static int[] getIntentIntArray(Activity mActivity, Bundle savedInstanceState, String paramName) {
        return savedInstanceState != null ? savedInstanceState.getIntArray(paramName)
                : mActivity.getIntent().getIntArrayExtra(paramName);
    }

    /**
     * 获取intent Serializable参数
     *
     * @param mActivity
     * @param savedInstanceState
     * @param paramName
     * @return
     */
    public static Serializable getIntentSerializableParams(Activity mActivity, Bundle savedInstanceState,
                                                           String paramName) {
        return savedInstanceState != null ? savedInstanceState.getSerializable(paramName)
                : mActivity.getIntent().getSerializableExtra(paramName);
    }

    /**
     * 获取intent Parcelable参数
     *
     * @param mActivity
     * @param savedInstanceState
     * @param paramName
     * @return
     */
    public static Parcelable getIntentParcelableParams(Activity mActivity, Bundle savedInstanceState,
                                                       String paramName) {
        return savedInstanceState != null ? savedInstanceState.getParcelable(paramName)
                : mActivity.getIntent().getParcelableExtra(paramName);
    }
}
