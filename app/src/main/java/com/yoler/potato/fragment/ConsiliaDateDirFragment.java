package com.yoler.potato.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yoler.potato.Dialog.CalendarDialogFragment;
import com.yoler.potato.R;
import com.yoler.potato.adapter.RvConsiliaDateDirAdapter;
import com.yoler.potato.request.ConsiliaDateDirReq;
import com.yoler.potato.request.ConsiliaDateDirReqContent;
import com.yoler.potato.response.DateDirResp;
import com.yoler.potato.response.DateDirRespContent;
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

public class ConsiliaDateDirFragment extends BaseFragment implements CalendarDialogFragment.OnEnsureDateSelectListener {
    private RecyclerView mRecyclerView;
    private RefreshLayout refreshView;
    private RvConsiliaDateDirAdapter mAdapter;
    private List<DateDirRespContent> dateDirDatas = new ArrayList<>();
    private TextView tvCalendar;

    @Override
    public String getTagName() {
        return null;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_consilia_date_dir;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        tvCalendar = (TextView) getActivity().findViewById(R.id.tv_calendar);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_consilia_date_dir);
        refreshView = (RefreshLayout) view.findViewById(R.id.refresh_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new RvConsiliaDateDirAdapter(mActivity, dateDirDatas);
        mRecyclerView.setAdapter(mAdapter);
        tvCalendar.setOnClickListener(this);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        getDateDirDatas(null, null, "1", true);

        refreshView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getDateDirDatas(null, null, "1", true);
                refreshlayout.finishRefresh();
            }
        });
        refreshView.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
            }
        });
        return view;
    }

    private void getDateDirDatas(String startDate, String endDate, String pageIdx, final boolean needClear) {
        ConsiliaDateDirReq consiliaDateDirReq = new ConsiliaDateDirReq();
        ConsiliaDateDirReqContent consiliaDateDirReqContent = new ConsiliaDateDirReqContent();
        consiliaDateDirReqContent.setPageIdx("1");
        consiliaDateDirReqContent.setRecordPerPage("20");
        consiliaDateDirReqContent.setQueryStartDate(startDate);
        consiliaDateDirReqContent.setQueryEndDate(endDate);
        consiliaDateDirReq.setContent(consiliaDateDirReqContent);
        consiliaDateDirReq.setOs("Android");
        consiliaDateDirReq.setPhone("15311496135");
        consiliaDateDirReq.setVersion("V1.0");
        MyOkHttpUtil.postAsync(Constant.GET_CONSILIA_DATE_DIR, GsonUtil.objectToJson(consiliaDateDirReq), new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(mActivity, getResources().getString(R.string.load_fail));
                        LogUtil.e(e.getMessage(), e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String resp = response.body().string();
                final DateDirResp dateDirResp = GsonUtil.jsonToObject(resp, DateDirResp.class);
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (needClear) {
                            dateDirDatas.clear();
                        }
                        dateDirDatas.addAll(dateDirResp.getContent());
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }

    @Override
    public void onEnsureDateSelect(List<Date> selectedDates) {
        if (selectedDates == null || selectedDates.size() == 0) {
            return;
        }
        String startDate = DateFormatUtil.formatDate(selectedDates.get(0));
        String endDate = DateFormatUtil.formatDate(selectedDates.get(selectedDates.size() - 1));
        getDateDirDatas(startDate, endDate, "1", true);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == tvCalendar.getId()) {
            CalendarDialogFragment calendarDialogFragment = CalendarDialogFragment.getInstance(mActivity, "请选择日期", this);
            calendarDialogFragment.setCancelable(true);
            calendarDialogFragment.show();
        }
    }
}
