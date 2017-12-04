package com.yoler.potato.fragment;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yoler.potato.R;

public class LibraryFragment extends BaseFragment {
    private TextView tvTitle;
    private DrawerLayout mDrawerLayout;
    private LinearLayout vDrawer;
    private ImageView ivMenu;


    @Override
    public String getTagName() {
        return null;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_library;
    }

    @Override
    protected void findViews() {
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        vDrawer = (LinearLayout) getActivity().findViewById(R.id.v_drawer);//主内容view
        ivMenu = (ImageView) view.findViewById(R.id.iv_menu);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        tvTitle.setText(getResources().getText(R.string.library));
        ivMenu.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == ivMenu.getId()) {
            if (!mDrawerLayout.isDrawerOpen(vDrawer)) {
                mDrawerLayout.openDrawer(vDrawer);
            }
        }
    }

}
