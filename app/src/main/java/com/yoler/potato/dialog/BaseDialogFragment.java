package com.yoler.potato.dialog;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * dialog父类
 *
 * @author zhangyu
 */
public abstract class BaseDialogFragment extends DialogFragment implements View.OnClickListener {
    protected FragmentActivity mActivity;

    /**
     * 设置对话框宽高
     *
     * @param width
     */
    protected void setDialogWH(double width) {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout((int) (dm.widthPixels * width),
                getDialog().getWindow().getAttributes().height);
    }

    /**
     * 设置对话框宽高
     *
     * @param width
     * @param height
     */
    protected void setDialogWH(double width, double height) {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout((int) (dm.widthPixels * width), (int) (dm.heightPixels * height));
    }

    protected void show(String tag) {
        show(mActivity.getSupportFragmentManager(), tag);
    }

    public abstract void show();

    @Override
    public void onClick(View v) {

    }

}
