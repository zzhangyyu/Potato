package com.yoler.potato.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yoler.potato.R;
import com.yoler.potato.response.ConsiliaDateIntroRespPI;

import java.util.List;

/**
 * Created by Admin on 2016/10/31.
 */

public class RvConsiliaDateIntroAdapter extends BaseQuickAdapter<ConsiliaDateIntroRespPI, BaseViewHolder> {

    public RvConsiliaDateIntroAdapter(int layoutResId, @Nullable List<ConsiliaDateIntroRespPI> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ConsiliaDateIntroRespPI item) {
        helper.setText(R.id.tv_consilia_date_intro_no, helper.getLayoutPosition() + 1 + "");
        helper.setText(R.id.item_tv_patient_name, item.getPatientName());
        helper.setText(R.id.item_tv_patient_sex, item.getPatientSex());
    }
}
