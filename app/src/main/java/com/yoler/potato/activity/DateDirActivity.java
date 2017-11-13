package com.yoler.potato.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.yoler.potato.R;
import com.yoler.potato.adapter.RvDateDirAdapter;
import com.yoler.potato.request.DateDirReq;
import com.yoler.potato.request.DateDirReqContent;
import com.yoler.potato.response.DateDirResp;
import com.yoler.potato.response.DateDirRespContent;
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

public class DateDirActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private List<DateDirRespContent> dateDirDatas = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RvDateDirAdapter mAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_consilia_by_date;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //底部导航栏
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.v_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_date_dir);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RvDateDirAdapter(mActivity, dateDirDatas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        getDateDirDatas();
    }

    private void getDateDirDatas() {
        DateDirReq dateDirReq = new DateDirReq();
        DateDirReqContent dateDirReqContent = new DateDirReqContent();
        dateDirReqContent.setPageIdx("1");
        dateDirReqContent.setRecordPerPage("20");
        dateDirReq.setContent(dateDirReqContent);
        dateDirReq.setOs("Android");
        dateDirReq.setPhone("15311496135");
        dateDirReq.setVersion("V1.0");
        MyOkHttpUtil.postAsync(Constant.GET_DATE_DIR, GsonUtil.objectToJson(dateDirReq), new Callback() {
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
//                        dateDirDatas.clear();
                        dateDirDatas.addAll(dateDirResp.getContent());
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.recents) {
            ToastUtil.showToast(mActivity, "click1");
        } else if (item.getItemId() == R.id.favourites) {
            ToastUtil.showToast(mActivity, "click2");
        } else if (item.getItemId() == R.id.nearby) {
            ToastUtil.showToast(mActivity, "click3");
        }
        return true;
    }

}
