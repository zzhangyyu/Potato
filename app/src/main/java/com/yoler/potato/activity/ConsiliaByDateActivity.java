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

import java.util.ArrayList;
import java.util.List;

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
        initData();
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
