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
import com.yoler.potato.response.ConsiliaPatientIntroRespContent;
import com.yoler.potato.util.ActivityUtil;
import com.yoler.potato.util.ToastUtil;

import java.util.List;

/**
 * Created by Admin on 2016/10/31.
 */

public class RvConsiliaPatientIntroAdapter extends RecyclerView.Adapter<RvConsiliaPatientIntroAdapter.PatientIntroViewHolder> {
    protected Context mContext;
    protected List<ConsiliaPatientIntroRespContent> datas;
    private LayoutInflater mLayoutInflater;

    public RvConsiliaPatientIntroAdapter(Context context, List<ConsiliaPatientIntroRespContent> datas) {
        this.mContext = context;
        this.datas = datas;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public PatientIntroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_rv_consilia_patient_intro, parent, false);
        return new PatientIntroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PatientIntroViewHolder patientIntroViewHolder, final int position) {
        patientIntroViewHolder.tvNo.setText(position + 1 + "");
        patientIntroViewHolder.tvVisitingDate.setText(datas.get(position).getVisitingDate());
        patientIntroViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = new Bundle();
                extras.putString("patientConditionId", datas.get(position).getPatientConditionId());
                ActivityUtil.startActivity(mContext, ConsiliaDetailActivity.class, extras);
            }
        });

        patientIntroViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
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

    class PatientIntroViewHolder extends RecyclerView.ViewHolder {
        public TextView tvVisitingDate;
        public TextView tvNo;

        public PatientIntroViewHolder(View itemView) {
            super(itemView);
            tvNo = (TextView) itemView.findViewById(R.id.item_rv_no);
            tvVisitingDate = (TextView) itemView.findViewById(R.id.tv_visiting_date);
        }
    }
}
