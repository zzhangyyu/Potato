package com.yoler.potato.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yoler.potato.R;
import com.yoler.potato.activity.ConsiliaPatientIntroActivity;
import com.yoler.potato.response.PatientDirRespContent;
import com.yoler.potato.util.ActivityUtil;
import com.yoler.potato.util.ToastUtil;

import java.util.List;

/**
 * Created by Admin on 2016/10/31.
 */

public class RvConsiliaPatientDirAdapter extends RecyclerView.Adapter<RvConsiliaPatientDirAdapter.PatientDirViewHolder> {
    protected Context mContext;
    protected List<PatientDirRespContent> dateDirDatas;
    private LayoutInflater mLayoutInflater;

    public RvConsiliaPatientDirAdapter(Context context, List<PatientDirRespContent> dateDirDatas) {
        this.mContext = context;
        this.dateDirDatas = dateDirDatas;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public PatientDirViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_rv_consilia_patient_dir, parent, false);
        return new PatientDirViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PatientDirViewHolder patientDirViewHolder, final int position) {

        patientDirViewHolder.tvNo.setText(position + 1 + "");
        patientDirViewHolder.tvPatientName.setText(dateDirDatas.get(position).getPatientName());
        patientDirViewHolder.tvPatientSex.setText(dateDirDatas.get(position).getPatientSex());
        patientDirViewHolder.tvConsiliaCnt.setText(dateDirDatas.get(position).getConsiliaCnt());
        patientDirViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = new Bundle();
                extras.putString("patientInfoId", dateDirDatas.get(position).getPatientInfoId());
                ActivityUtil.startActivity(mContext, ConsiliaPatientIntroActivity.class, extras);
            }
        });

        patientDirViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int pos = patientDirViewHolder.getPosition();
                String str = patientDirViewHolder.tvPatientName.getText().toString();
                ToastUtil.showToast(mContext, "you long click " + str + " at " + pos);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return dateDirDatas == null ? 0 : dateDirDatas.size();
    }


    class PatientDirViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNo;
        public TextView tvPatientName;
        public TextView tvPatientSex;
        public TextView tvConsiliaCnt;

        public PatientDirViewHolder(View itemView) {
            super(itemView);
            tvNo = (TextView) itemView.findViewById(R.id.item_rv_no);
            tvPatientName = (TextView) itemView.findViewById(R.id.item_rv_patient_name);
            tvPatientSex = (TextView) itemView.findViewById(R.id.item_rv_patient_sex);
            tvConsiliaCnt = (TextView) itemView.findViewById(R.id.item_rv_consilia_cnt);
        }
    }
}
