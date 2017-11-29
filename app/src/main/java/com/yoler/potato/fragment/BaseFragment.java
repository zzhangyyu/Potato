package com.yoler.potato.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/**
 * fragment父类
 *
 * @author qinweixiang
 */
public abstract class BaseFragment extends Fragment implements OnClickListener {
    protected FragmentActivity mActivity;
    protected View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity = getActivity();
        view = inflater.inflate(getLayoutResource(), container, false);
        findViews();
        return view;
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }


    /**
     * TAG
     *
     * @return
     */
    public abstract String getTagName();

    /**
     * 布局文件
     *
     * @return
     */
    protected abstract int getLayoutResource();

    protected abstract void findViews();

    @Override
    public void onClick(View v) {

    }

}
