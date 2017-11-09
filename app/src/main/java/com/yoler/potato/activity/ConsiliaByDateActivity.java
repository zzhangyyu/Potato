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
import com.yoler.potato.adapter.RvConsiliaByDateAdapter;
import com.yoler.potato.request.PatientByDateReq;
import com.yoler.potato.util.Constant;
import com.yoler.potato.util.GsonUtil;
import com.yoler.potato.util.MyOkHttpUtil;
import com.yoler.potato.util.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ConsiliaByDateActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private UltimateRecyclerView mView;
    private List<String> mDatas = new ArrayList<>();
    private Handler handler;
    private BottomNavigationView bottomNavigationView;
    private RvConsiliaByDateAdapter mAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_consilia_by_date;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        mView = (UltimateRecyclerView) findViewById(R.id.ultimate_recycler_view);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        mView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RvConsiliaByDateAdapter(this, mDatas);
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
        getPatientByDate();

    }

    private void getPatientByDate() {
        PatientByDateReq patientByDateReq = new PatientByDateReq();
        patientByDateReq.setPageIdx("1");
        patientByDateReq.setRecordPerPage("20");
        patientByDateReq.setQueryStartDate("2016-05-10");
        patientByDateReq.setQueryEndDate("2017-11-08");
        patientByDateReq.setOs("Android");
        patientByDateReq.setPhone("15311496135");
        patientByDateReq.setVersion("V1.0");
        MyOkHttpUtil.postAsync(Constant.URL_1, GsonUtil.objectToJson(patientByDateReq), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resp = response.body().string();
                mDatas.add(resp);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.recents) {
            initData();
            mAdapter.notifyDataSetChanged();
        } else if (item.getItemId() == R.id.favourites) {
            initData2();
            mAdapter.notifyDataSetChanged();
        } else if (item.getItemId() == R.id.nearby) {
            initData3();
            mAdapter.notifyDataSetChanged();
        }
        return true;
    }

    protected void initData() {
        mDatas.clear();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    protected void initData2() {
        mDatas.clear();
        for (int i = 1; i < 20; i++) {
            mDatas.add("" + i);
        }
    }

    protected void initData3() {
        mDatas.clear();
        for (int i = 30; i < 50; i++) {
            mDatas.add("" + i);
        }
    }
}
