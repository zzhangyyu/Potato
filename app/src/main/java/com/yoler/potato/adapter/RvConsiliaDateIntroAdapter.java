package com.yoler.potato.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yoler.potato.R;
import com.yoler.potato.activity.ConsiliaDetailActivity;
import com.yoler.potato.response.ConsiliaDateIntroRespPI;
import com.yoler.potato.util.ActivityUtil;
import com.yoler.potato.util.ToastUtil;

import java.util.List;

/**
 * Created by Admin on 2016/10/31.
 */

public class RvConsiliaDateIntroAdapter extends RecyclerView.Adapter<RvConsiliaDateIntroAdapter.DateIntroViewHolder> {
    protected Context mContext;
    protected List<ConsiliaDateIntroRespPI> datas;
    private LayoutInflater mLayoutInflater;

    public RvConsiliaDateIntroAdapter(Context context, List<ConsiliaDateIntroRespPI> datas) {
        this.mContext = context;
        this.datas = datas;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public DateIntroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_rv_consilia_date_intro, parent, false);
        return new DateIntroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final DateIntroViewHolder dateIntroViewHolder, final int position) {
        dateIntroViewHolder.tvPatientName.setText(datas.get(position).getPatientName());
        dateIntroViewHolder.tvPatientSex.setText(datas.get(position).getPatientSex());
        dateIntroViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = new Bundle();
                extras.putString("patientConditionId", datas.get(position).getPatientConditionId());
                ActivityUtil.startActivity(mContext, ConsiliaDetailActivity.class, extras);
            }
        });

        dateIntroViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ToastUtil.showToast(mContext, "you long click ");
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class DateIntroViewHolder extends RecyclerView.ViewHolder {
        public TextView tvPatientName;
        public TextView tvPatientSex;
        public TextView tvTotalTimes;

        public DateIntroViewHolder(View itemView) {
            super(itemView);
            tvPatientName = (TextView) itemView.findViewById(R.id.item_tv_patient_name);
            tvPatientSex = (TextView) itemView.findViewById(R.id.item_tv_patient_sex);
            tvTotalTimes = (TextView) itemView.findViewById(R.id.item_tv_total_times);
        }
    }
}
