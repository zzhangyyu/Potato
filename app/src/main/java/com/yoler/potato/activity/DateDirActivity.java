package com.yoler.potato.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
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

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DateDirActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private UltimateRecyclerView mView;
    private List<DateDirRespContent> dateDirDatas;
    private Handler handler;
    private BottomNavigationView bottomNavigationView;
    private RvDateDirAdapter mAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_consilia_by_date;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        handler = new Handler();
        mView = (UltimateRecyclerView) findViewById(R.id.ultimate_recycler_view);

        getDateDirDatas();
        LogUtil.d(GsonUtil.objectToJson(dateDirDatas));
        mView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RvDateDirAdapter(this, dateDirDatas);
        mView.setAdapter(mAdapter);
        //StickyRecyclerHeadersDecoration stickyRecyclerHeadersDecoration = new StickyRecyclerHeadersDecoration(mAdapter);
        //mView.addItemDecoration(stickyRecyclerHeadersDecoration);
        mView.enableDefaultSwipeRefresh(true);//开启下拉刷新
        mView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mView.setRefreshing(false);
                    }
                }, 2000);
            }
        });

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
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resp = response.body().string();
                DateDirResp dateDirResp = GsonUtil.jsonToObject(resp, DateDirResp.class);
                dateDirDatas = dateDirResp.getContent();
            }
        });

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.recents) {

        } else if (item.getItemId() == R.id.favourites) {

        } else if (item.getItemId() == R.id.nearby) {

        }
        return true;
    }

}
