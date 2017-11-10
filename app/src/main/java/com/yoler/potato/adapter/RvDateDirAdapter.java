package com.yoler.potato.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.yoler.potato.R;
import com.yoler.potato.response.DateDirRespContent;

import java.util.List;

/**
 * @author zhangyu
 */
public class RvDateDirAdapter extends UltimateViewAdapter<RvDateDirAdapter.DateDirViewHolder> {
    protected Context mContext;
    protected List<DateDirRespContent> dateDirDatas;
    private LayoutInflater mLayoutInflater;

    public RvDateDirAdapter(Context context, List<DateDirRespContent> dateDirDatas) {
        this.mContext = context;
        this.dateDirDatas = dateDirDatas;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public DateDirViewHolder newFooterHolder(View view) {
        return new DateDirViewHolder(view);
    }

    @Override
    public DateDirViewHolder newHeaderHolder(View view) {
        return new DateDirViewHolder(view);
    }

    @Override
    public DateDirViewHolder onCreateViewHolder(ViewGroup parent) {
        View v = mLayoutInflater.inflate(R.layout.item_rv_consilia_by_date, parent, false);
        return new DateDirViewHolder(v);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View v = mLayoutInflater.inflate(R.layout.header_rv_consilia_by_date, parent, false);
        return new DateDirViewHolder(v);
    }

    @Override
    public int getAdapterItemCount() {
        return dateDirDatas == null ? 0 : dateDirDatas.size();
    }

    @Override
    public long generateHeaderId(int position) {
        if (customHeaderView != null) {
            position -= 1;
        }
        String s = position + "";
        return s.charAt(0);
    }

    @Override
    public void onBindViewHolder(final DateDirViewHolder holder, int position) {
//        if (position < getItemCount() && (customHeaderView != null ? position <= dateDirDatas.size() : position < dateDirDatas.size()) && (customHeaderView != null ? position > 0 : true)) {
//            position -= customHeaderView == null ? 0 : 1;
//            holder.tvConsiliaNo.setText(position + "");
//            holder.tvConsiliaDate.setText(dateDirDatas.get(position).getVisitingDate());
//            holder.tvConsiliaCnt.setText(dateDirDatas.get(position).getPatientCnt());
//        }
        holder.tvConsiliaNo.setText(position + "");
        holder.tvConsiliaDate.setText(dateDirDatas.get(position).getVisitingDate());
        holder.tvConsiliaCnt.setText(dateDirDatas.get(position).getPatientCnt());
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (customHeaderView != null) {
            position -= 1;
        }
    }

    class DateDirViewHolder extends UltimateRecyclerviewViewHolder {
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
