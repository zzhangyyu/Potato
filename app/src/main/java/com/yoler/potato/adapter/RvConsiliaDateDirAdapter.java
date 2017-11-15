package com.yoler.potato.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.yoler.potato.R;
import com.yoler.potato.activity.ConsiliaDateIntroActivity;
import com.yoler.potato.response.DateDirRespContent;
import com.yoler.potato.util.ActivityUtil;
import com.yoler.potato.util.ToastUtil;

import java.util.List;

/**
 * Created by Admin on 2016/10/31.
 */

public class RvConsiliaDateDirAdapter extends RecyclerView.Adapter<RvConsiliaDateDirAdapter.DateDirViewHolder> {
    protected Context mContext;
    protected List<DateDirRespContent> dateDirDatas;
    private LayoutInflater mLayoutInflater;

    public RvConsiliaDateDirAdapter(Context context, List<DateDirRespContent> dateDirDatas) {
        this.mContext = context;
        this.dateDirDatas = dateDirDatas;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public DateDirViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_rv_consilia_date_dir, parent, false);
        return new DateDirViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final DateDirViewHolder dateDirViewHolder, int position) {

        dateDirViewHolder.tvConsiliaNo.setText(position + 1 + "");
        dateDirViewHolder.tvConsiliaDate.setText(dateDirDatas.get(position).getVisitingDate());
        dateDirViewHolder.tvConsiliaCnt.setText(dateDirDatas.get(position).getPatientCnt());

        dateDirViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int pos = dateDirViewHolder.getPosition();
//                String str = dateDirViewHolder.tvConsiliaDate.getText().toString();
//                ToastUtil.showToast(mContext, "you click " + str + " at " + pos);
                ActivityUtil.startActivity(mContext, ConsiliaDateIntroActivity.class);
            }
        });

        dateDirViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int pos = dateDirViewHolder.getPosition();
                String str = dateDirViewHolder.tvConsiliaDate.getText().toString();
                ToastUtil.showToast(mContext, "you long click " + str + " at " + pos);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return dateDirDatas == null ? 0 : dateDirDatas.size();
    }


    class DateDirViewHolder extends RecyclerView.ViewHolder {
        public TextView tvConsiliaDate;
        public TextView tvConsiliaCnt;
        public TextView tvConsiliaNo;

        public DateDirViewHolder(View itemView) {
            super(itemView);
            tvConsiliaDate = (TextView) itemView.findViewById(R.id.item_rv_consilia_date);
            tvConsiliaCnt = (TextView) itemView.findViewById(R.id.item_rv_consilia_cnt);
            tvConsiliaNo = (TextView) itemView.findViewById(R.id.item_rv_consilia_no);
        }
    }
}
