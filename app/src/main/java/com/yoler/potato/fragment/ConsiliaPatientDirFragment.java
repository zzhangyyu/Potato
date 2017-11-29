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

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.yoler.potato.R;
import com.yoler.potato.adapter.RvConsiliaPatientDirAdapter;
import com.yoler.potato.request.ConsiliaPatientDirReq;
import com.yoler.potato.request.ConsiliaPatientDirReqContent;
import com.yoler.potato.response.PatientDirResp;
import com.yoler.potato.response.PatientDirRespContent;
import com.yoler.potato.util.Constant;
import com.yoler.potato.util.GsonUtil;
import com.yoler.potato.util.LogUtil;
import com.yoler.potato.util.MyOkHttpUtil;
import com.yoler.potato.util.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ConsiliaPatientDirFragment extends BaseFragment {
    private TextView tvTitle;
    private RecyclerView mRecyclerView;
    private RefreshLayout refreshView;
    private RvConsiliaPatientDirAdapter mAdapter;
    private List<PatientDirRespContent> patientDirDatas = new ArrayList<>();
    private DrawerLayout mDrawerLayout;
    private LinearLayout vDrawer;
    private ImageView ivMenu;

    @Override
    public String getTagName() {
        return null;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_consilia_patient_dir;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        findViews();
        tvTitle.setText(getResources().getText(R.string.consilia_patient_title));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new RvConsiliaPatientDirAdapter(mActivity, patientDirDatas);
        mRecyclerView.setAdapter(mAdapter);
        ivMenu.setOnClickListener(this);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        getPatientDirDatas(null, true);
        return view;
    }

    /**
     * 获取view
     */
    private void findViews() {
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        vDrawer = (LinearLayout) getActivity().findViewById(R.id.v_drawer);//主内容view
        ivMenu = (ImageView) view.findViewById(R.id.iv_menu);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        refreshView = (RefreshLayout) view.findViewById(R.id.refresh_view);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_consilia_patient_dir);
    }

    private void getPatientDirDatas(String pageIdx, final boolean needClear) {
        ConsiliaPatientDirReq consiliaPatientDirReq = new ConsiliaPatientDirReq();
        ConsiliaPatientDirReqContent consiliaPatientDirReqContent = new ConsiliaPatientDirReqContent();
        consiliaPatientDirReqContent.setPageIdx("1");
        consiliaPatientDirReqContent.setRecordPerPage("20");
        consiliaPatientDirReq.setContent(consiliaPatientDirReqContent);
        consiliaPatientDirReq.setOs("Android");
        consiliaPatientDirReq.setPhone("15311496135");
        consiliaPatientDirReq.setVersion("V1.0");
        MyOkHttpUtil.postAsync(Constant.GET_CONSILIA_PATIENT_DIR, GsonUtil.objectToJson(consiliaPatientDirReq), new Callback() {
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
                final PatientDirResp patientDirResp = GsonUtil.jsonToObject(resp, PatientDirResp.class);
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (needClear) {
                            patientDirDatas.clear();
                        }
                        patientDirDatas.addAll(patientDirResp.getContent());
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
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
