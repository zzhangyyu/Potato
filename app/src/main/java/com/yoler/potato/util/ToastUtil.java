package com.yoler.potato.util;


import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 提示工具类
 * 
 * @author zhangyu
 */

public class ToastUtil {
	private static Toast toast;
	private static TextView toastTv;

	/**
	 * toast
	 *
	 * @param context
	 * @param stringId
	 */
	public static void showToast(Context context, int stringId) {
		showToast(context, context.getString(stringId));
	}

	/**
	 * toast
	 *
	 * @param context
	 * @param content
	 */
	public static void showToast(Context context, String content) {
		showToast(context, content, Toast.LENGTH_SHORT);
	}

	/**
	 * long toast
	 *
	 * @param context
	 * @param stringId
	 */
	public static void showLongToast(Context context, int stringId) {
		showToast(context, context.getString(stringId), Toast.LENGTH_LONG);
	}

	/**
	 * long toast
	 *
	 * @param context
	 * @param content
	 */
	public static void showLongToast(Context context, String content) {
		showToast(context, content, Toast.LENGTH_LONG);
	}

	/**
	 * toast
	 *
	 * @param context
	 * @param content
	 * @param duration
	 */
	private static void showToast(Context context, String content, int duration) {
		showNormalToast(context, content, duration);
	}

	/**
	 * toast
	 *
	 * @param context
	 * @param content
	 * @param duration
	 */
	private static void showNormalToast(Context context, String content, int duration) {
//		if (toast == null) {
//			toast = Toast.makeText(context, content, duration);
//			View view = View.inflate(context, R.layout.toast_normal, null);
//			toastTv = (TextView) view.findViewById(R.id.toast_tv);
//			toastTv.setText(content);
//			toast.setView(view);
//		} else {
//			toastTv.setText(content);
//			toast.setDuration(duration);
//		}
		toast = Toast.makeText(context, content, duration);
		toast.show();
	}
}
