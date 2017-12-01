package com.yoler.potato.fragment;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yoler.potato.Dialog.CalendarDialogFragment;
import com.yoler.potato.R;
import com.yoler.potato.activity.ConsiliaDateIntroActivity;
import com.yoler.potato.adapter.RvConsiliaDateDirAdapter;
import com.yoler.potato.request.ConsiliaDateDirReq;
import com.yoler.potato.request.ConsiliaDateDirReqContent;
import com.yoler.potato.response.DateDirResp;
import com.yoler.potato.response.DateDirRespContent;
import com.yoler.potato.util.ActivityUtil;
import com.yoler.potato.util.Constant;
import com.yoler.potato.util.DateFormatUtil;
import com.yoler.potato.util.GsonUtil;
import com.yoler.potato.util.LogUtil;
import com.yoler.potato.util.MyOkHttpUtil;
import com.yoler.potato.util.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LibraryFragment extends BaseFragment {
    private TextView tvTitle;


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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

    }

}
