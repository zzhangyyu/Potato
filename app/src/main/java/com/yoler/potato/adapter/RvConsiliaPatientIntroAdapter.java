package com.yoler.potato.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yoler.potato.R;
import com.yoler.potato.response.ConsiliaPatientIntroRespContent;

import java.util.List;

/**
 * Created by Admin on 2016/10/31.
 */

public class RvConsiliaPatientIntroAdapter extends BaseQuickAdapter<ConsiliaPatientIntroRespContent, BaseViewHolder> {

    public RvConsiliaPatientIntroAdapter(int layoutResId, @Nullable List<ConsiliaPatientIntroRespContent> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ConsiliaPatientIntroRespContent item) {
        helper.setText(R.id.item_rv_no, helper.getLayoutPosition() + 1 + "");
        helper.setText(R.id.tv_visiting_date, item.getVisitingDate());
    }
}
