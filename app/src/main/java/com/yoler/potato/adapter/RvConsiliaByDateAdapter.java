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
import com.yoler.potato.util.ToastUtil;

import java.util.List;

/**
 * @author zhangyu
 */
public class RvConsiliaByDateAdapter extends UltimateViewAdapter<RvConsiliaByDateAdapter.MyViewHolder> {
    protected Context mContext;
    protected List<String> mDatas;
    private LayoutInflater mLayoutInflater;

    public RvConsiliaByDateAdapter(Context context, List<String> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RvConsiliaByDateAdapter.MyViewHolder newFooterHolder(View view) {
        return new MyViewHolder(view);
    }

    @Override
    public RvConsiliaByDateAdapter.MyViewHolder newHeaderHolder(View view) {
        return new MyViewHolder(view);
    }

    @Override
    public RvConsiliaByDateAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent) {
        View v = mLayoutInflater.inflate(R.layout.item_rv_consilia_by_date, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View v = mLayoutInflater.inflate(R.layout.header_rv_consilia_by_date, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public int getAdapterItemCount() {
        return mDatas == null ? 0 : mDatas.size();
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
    public void onBindViewHolder(final RvConsiliaByDateAdapter.MyViewHolder holder, int position) {
        if (position < getItemCount() && (customHeaderView != null ? position <= mDatas.size() : position < mDatas.size()) && (customHeaderView != null ? position > 0 : true)) {
            position -= customHeaderView == null ? 0 : 1;
            holder.tv.setText(mDatas.get(position));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getPosition();
                String str = holder.tv.getText().toString();
                ToastUtil.showToast(mContext, "you click " + str + " at " + pos);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int pos = holder.getPosition();
                String str = holder.tv.getText().toString();
                ToastUtil.showToast(mContext, "you long click " + str + " at " + pos);
                return true;
            }
        });
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (customHeaderView != null) {
            position -= 1;
        }
        ((MyViewHolder)holder).tv.setText("header  "+(position+"").charAt(0));
    }

    class MyViewHolder extends UltimateRecyclerviewViewHolder {
        public TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_rv_consilia_date);
        }
    }
}
