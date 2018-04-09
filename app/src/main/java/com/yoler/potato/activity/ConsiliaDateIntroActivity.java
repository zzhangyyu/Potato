package com.yoler.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yoler.potato.R;
import com.yoler.potato.adapter.RvConsiliaDateIntroAdapter;
import com.yoler.potato.request.ConsiliaDateIntroReq;
import com.yoler.potato.request.ConsiliaDateIntroReqContent;
import com.yoler.potato.response.ConsiliaDateIntroResp;
import com.yoler.potato.response.ConsiliaDateIntroRespPI;
import com.yoler.potato.util.ActivityUtil;
import com.yoler.potato.util.Host;
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

public class ConsiliaDateIntroActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    private TextView tvTitle;
    private RecyclerView mRecyclerView;
    private RvConsiliaDateIntroAdapter mAdapter;
    private List<ConsiliaDateIntroRespPI> datas = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_consilia_date_intro;
    }

    @Override
    protected void findViews() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_consilia_date_intro);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvTitle.setText(getResources().getText(R.string.consilia_date_intro_title));
        String visitingDate = ActivityUtil.getIntentStringParams(mActivity, savedInstanceState, "visitingDate");

        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new RvConsiliaDateIntroAdapter(R.layout.item_rv_consilia_date_intro, datas);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        mAdapter.setUpFetchEnable(false);
        mAdapter.setEnableLoadMore(false);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);

        getConsiliaDateIntroDatas(visitingDate);
    }

    @Override
    public void onClick(View view) {

    }

    private void getConsiliaDateIntroDatas(String visitingDate) {
        ConsiliaDateIntroReq consiliaDateIntroReq = new ConsiliaDateIntroReq();
        ConsiliaDateIntroReqContent consiliaDateIntroReqContent = new ConsiliaDateIntroReqContent();
        consiliaDateIntroReqContent.setPageIdx("1");
        consiliaDateIntroReqContent.setRecordPerPage("20");
        consiliaDateIntroReqContent.setQueryStartDate(visitingDate);
        consiliaDateIntroReqContent.setQueryEndDate(visitingDate);
        consiliaDateIntroReq.setContent(consiliaDateIntroReqContent);
        consiliaDateIntroReq.setOs("Android");
        consiliaDateIntroReq.setPhone("15311496135");
        consiliaDateIntroReq.setVersion("V1.0");
        MyOkHttpUtil.postAsync(Host.GET_CONSILIA_DATE_INTRO, GsonUtil.objectToJson(consiliaDateIntroReq), new Callback() {
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
                final ConsiliaDateIntroResp consiliaDateIntroResp = GsonUtil.jsonToObject(resp, ConsiliaDateIntroResp.class);
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        datas.addAll(consiliaDateIntroResp.getContent().get(0).getPatientInfos());
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
