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

public class RvConsiliaDateIntroAdapter extends RecyclerView.Adapter<RvConsiliaDateIntroAdapter.DateIntroViewHolder> {
    protected Context mContext;
    protected List<DateDirRespContent> dateDirDatas;
    private LayoutInflater mLayoutInflater;

    public RvConsiliaDateIntroAdapter(Context context, List<DateDirRespContent> dateDirDatas) {
        this.mContext = context;
        this.dateDirDatas = dateDirDatas;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public DateIntroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_rv_consilia_date_dir, parent, false);
        return new DateIntroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final DateIntroViewHolder dateIntroViewHolder, int position) {

        dateIntroViewHolder.tvConsiliaNo.setText(position + 1 + "");
        dateIntroViewHolder.tvConsiliaDate.setText(dateDirDatas.get(position).getVisitingDate());
        dateIntroViewHolder.tvConsiliaCnt.setText(dateDirDatas.get(position).getPatientCnt());

        dateIntroViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int pos = dateDirViewHolder.getPosition();
//                String str = dateDirViewHolder.tvConsiliaDate.getText().toString();
//                ToastUtil.showToast(mContext, "you click " + str + " at " + pos);
                ActivityUtil.startActivity(mContext, ConsiliaDateIntroActivity.class);
            }
        });

        dateIntroViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int pos = dateIntroViewHolder.getPosition();
                String str = dateIntroViewHolder.tvConsiliaDate.getText().toString();
                ToastUtil.showToast(mContext, "you long click " + str + " at " + pos);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return dateDirDatas == null ? 0 : dateDirDatas.size();
    }


    class DateIntroViewHolder extends RecyclerView.ViewHolder {
        public TextView tvConsiliaDate;
        public TextView tvConsiliaCnt;
        public TextView tvConsiliaNo;

        public DateIntroViewHolder(View itemView) {
            super(itemView);
            tvConsiliaDate = (TextView) itemView.findViewById(R.id.item_rv_consilia_date);
            tvConsiliaCnt = (TextView) itemView.findViewById(R.id.item_rv_consilia_cnt);
            tvConsiliaNo = (TextView) itemView.findViewById(R.id.item_rv_consilia_no);
        }
    }
}
