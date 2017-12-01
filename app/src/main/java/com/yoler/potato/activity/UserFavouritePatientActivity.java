package com.yoler.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yoler.potato.R;
import com.yoler.potato.adapter.RvUserFavouritePatientAdapter;
import com.yoler.potato.request.UserFavouritePatientReq;
import com.yoler.potato.request.UserFavouritePatientReqContent;
import com.yoler.potato.response.UserFavouritePatientResp;
import com.yoler.potato.response.UserFavouritePatientRespContent;
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

public class UserFavouritePatientActivity extends BaseActivity {
    private TextView tvTitle;
    private RecyclerView mRecyclerView;
    private RvUserFavouritePatientAdapter mAdapter;
    private List<UserFavouritePatientRespContent> datas = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_user_favourite_patient;
    }

    @Override
    protected void findViews() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_user_favourite_patient);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvTitle.setText(getResources().getText(R.string.user_favourite_patient_title));
        LinearLayoutManager layoutManager = new GridLayoutManager(mActivity, 3);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new RvUserFavouritePatientAdapter(R.layout.item_rv_user_favourite_patient, datas);
        mAdapter.setUpFetchEnable(false);
        mAdapter.setEnableLoadMore(false);
        mRecyclerView.setAdapter(mAdapter);

        getUserFavouritePatietnDatas("1", true);

    }

    @Override
    public void onClick(View view) {

    }

    private void getUserFavouritePatietnDatas(String userId, final boolean needClear) {
        UserFavouritePatientReq userFavouritePatientReq = new UserFavouritePatientReq();
        UserFavouritePatientReqContent userFavouritePatientReqContent = new UserFavouritePatientReqContent();
        userFavouritePatientReqContent.setUserId(userId);
        userFavouritePatientReq.setContent(userFavouritePatientReqContent);
        userFavouritePatientReq.setOs("Android");
        userFavouritePatientReq.setPhone("15311496135");
        userFavouritePatientReq.setVersion("V1.0");
        MyOkHttpUtil.postAsync(Constant.GET_USER_FAVOURITE_PATIENT, GsonUtil.objectToJson(userFavouritePatientReq), new Callback() {
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
                final UserFavouritePatientResp userFavouritePatientResp = GsonUtil.jsonToObject(resp, UserFavouritePatientResp.class);
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (needClear) {
                            datas.clear();
                        }
                        datas.addAll(userFavouritePatientResp.getContent());
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

}
