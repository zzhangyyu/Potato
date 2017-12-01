package com.yoler.potato.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yoler.potato.R;
import com.yoler.potato.response.PatientDirRespContent;

import java.util.List;

/**
 * Created by Admin on 2016/10/31.
 */

public class RvConsiliaPatientDirAdapter extends BaseQuickAdapter<PatientDirRespContent, BaseViewHolder> {

    public RvConsiliaPatientDirAdapter(int layoutResId, @Nullable List<PatientDirRespContent> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PatientDirRespContent item) {
        helper.setText(R.id.item_rv_no, helper.getLayoutPosition() + 1 + "");
        helper.setText(R.id.item_rv_patient_name, item.getPatientName());
        helper.setText(R.id.item_rv_patient_sex, item.getPatientSex());
        helper.setText(R.id.item_rv_consilia_cnt, item.getConsiliaCnt());
    }
}
