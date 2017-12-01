package com.yoler.potato.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yoler.potato.R;
import com.yoler.potato.response.DateDirRespContent;

import java.util.List;

/**
 * Created by Admin on 2016/10/31.
 */

public class RvConsiliaDateDirAdapter extends BaseQuickAdapter<DateDirRespContent, BaseViewHolder> {

    public RvConsiliaDateDirAdapter(int layoutResId, @Nullable List<DateDirRespContent> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DateDirRespContent item) {
        helper.setText(R.id.item_rv_consilia_no, helper.getLayoutPosition() + 1 + "");
        helper.setText(R.id.item_rv_consilia_date, item.getVisitingDate());
        helper.setText(R.id.item_rv_consilia_cnt, item.getPatientCnt());
    }
}
