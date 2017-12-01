package com.yoler.potato.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yoler.potato.R;
import com.yoler.potato.response.DateDirRespContent;
import com.yoler.potato.response.UserFavouritePatientRespContent;

import java.util.List;

/**
 * Created by Admin on 2016/10/31.
 */

public class RvUserFavouritePatientAdapter extends BaseQuickAdapter<UserFavouritePatientRespContent, BaseViewHolder> {


    public RvUserFavouritePatientAdapter(int layoutResId, @Nullable List<UserFavouritePatientRespContent> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserFavouritePatientRespContent item) {
        helper.setText(R.id.item_rv_patient_name, item.getPatientName());
        helper.setText(R.id.item_rv_patient_sex, item.getPatientSex());
        helper.setText(R.id.item_rv_consilia_cnt, item.getConsiliaCnt());
    }
}
