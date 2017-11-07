package com.yoler.potato.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.yoler.potato.R;
import com.yoler.potato.adapter.RvConsiliaByDateAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private UltimateRecyclerView mView;
    private List<String> mDatas;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        handler = new Handler();
        mView = (UltimateRecyclerView) findViewById(R.id.ultimate_recycler_view);
        mView.setLayoutManager(new LinearLayoutManager(this));
        RvConsiliaByDateAdapter mAdapter = new RvConsiliaByDateAdapter(this, mDatas);
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
                },2000);
            }
        });

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {

    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }
}
