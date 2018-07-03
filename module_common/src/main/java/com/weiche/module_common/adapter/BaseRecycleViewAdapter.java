package com.weiche.module_common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ${chewei} on 2018/7/3.
 * params:2018/7/3
 */

public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<RecycleViewHolder> implements View.OnClickListener{

    private Context mContext;
    private List<T> mData;
    private int mLayoutId;
    private OnItemClickListener mListener;

    public BaseRecycleViewAdapter(Context mContext, List<T> mData, int mLayoutId) {
        this.mContext = mContext;
        this.mData = mData;
        this.mLayoutId = mLayoutId;
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutId,parent,false);
        view.setOnClickListener(this);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        holder.itemView.setTag(position);
        T bean = mData.get(position);
        onBindData(holder,bean,position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onItemClick(this, v, (Integer) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
    }

    /**
     * item点击监听器
     */
    public interface OnItemClickListener {
        /**
         * item点击回调
         *
         * @param adapter  The Adapter where the click happened.
         * @param v        The view that was clicked.
         * @param position The position of the view in the adapter.
         */
        void onItemClick(RecyclerView.Adapter adapter, View v, int position);
    }

    /**
     * 数据绑定，由实现类实现
     *
     * @param holder   The reference of the all view within the item.
     * @param bean     The data bean related to the position.
     * @param position The position to bind data.
     */
    protected abstract void onBindData(RecycleViewHolder holder, T bean, int position);

}
