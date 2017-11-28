package com.yoler.potato.util;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhangyu on 2017/11/28.
 */

public class MeasureUtil {

    /**
     * 获取View的尺寸（单位px）
     * 在调用该方法后，使用View对象的getMessuredHeight获取高度、getMeasuredWidth获取宽度
     *
     * @param child 需要测量高度和宽度的View对象
     */
    public static void measureView(View child) {
        ViewGroup.LayoutParams params = child.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0,
                params.width);
        int lpHeight = params.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight,
                    View.MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }
}
