package com.yoler.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yoler.potato.R;
import com.yoler.potato.adapter.RvConsiliaPatientIntroAdapter;
import com.yoler.potato.request.ConsiliaPatientIntroReq;
import com.yoler.potato.request.ConsiliaPatientIntroReqContent;
import com.yoler.potato.response.ConsiliaPatientIntroResp;
import com.yoler.potato.response.ConsiliaPatientIntroRespContent;
import com.yoler.potato.util.ActivityUtil;
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

public class ConsiliaPatientIntroActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private RvConsiliaPatientIntroAdapter mAdapter;
    private List<ConsiliaPatientIntroRespContent> datas = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_consilia_patient_intro;
    }

    @Override
    protected void findViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_consilia_patient_intro);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String patientInfoId = ActivityUtil.getIntentStringParams(mActivity, savedInstanceState, "patientInfoId");

        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new RvConsiliaPatientIntroAdapter(R.layout.item_rv_consilia_patient_intro, datas);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        mAdapter.setUpFetchEnable(false);
        mAdapter.setEnableLoadMore(false);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);

        getConsiliaDateIntroDatas(patientInfoId);
    }

    @Override
    public void onClick(View view) {

    }

    private void getConsiliaDateIntroDatas(String patientInfoId) {
        ConsiliaPatientIntroReq consiliaPatientIntroReq = new ConsiliaPatientIntroReq();
        ConsiliaPatientIntroReqContent consiliaPatientIntroReqContent = new ConsiliaPatientIntroReqContent();
        consiliaPatientIntroReqContent.setPatientInfoId(patientInfoId);
        consiliaPatientIntroReq.setContent(consiliaPatientIntroReqContent);
        consiliaPatientIntroReq.setOs("Android");
        consiliaPatientIntroReq.setPhone("15311496135");
        consiliaPatientIntroReq.setVersion("V1.0");

        MyOkHttpUtil.postAsync(Constant.GET_CONSILIA_PATIENT_INTRO, GsonUtil.objectToJson(consiliaPatientIntroReq), new Callback() {
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
                final ConsiliaPatientIntroResp consiliaPatientIntroResp = GsonUtil.jsonToObject(resp, ConsiliaPatientIntroResp.class);
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        datas.addAll(consiliaPatientIntroResp.getContent());
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle extras = new Bundle();
        extras.putString("patientConditionId", datas.get(position).getPatientConditionId());
        ActivityUtil.startActivity(mActivity, ConsiliaDetailActivity.class, extras);
    }
}
