package com.chewei.module_fragmentation.adapter;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.chewei.module_fragmentation.R;
import com.chewei.module_fragmentation.bean.Inventory;
import com.weiche.module_common.adapter.BaseRecycleViewAdapter;
import com.weiche.module_common.adapter.RecycleViewHolder;
import com.weiche.module_common.view.MyRecyclerViewItem;

import java.util.List;

/**
 * Created by ${chewei} on 2018/7/3.
 * params:2018/7/3
 */

public class RecycleViewItemDeleteAdapter extends BaseRecycleViewAdapter<Inventory> {
    private OnDeleteClickLister mDeleteClickListener;

    public RecycleViewItemDeleteAdapter(Context mContext, List mData) {
        super(mContext, mData, R.layout.item_inventroynew);
    }

    @Override
    protected void onBindData(RecycleViewHolder holder, Inventory bean, int position) {
        View view = holder.getView(R.id.tv_delete);
        view.setTag(position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            if (!view.hasOnClickListeners()) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mDeleteClickListener != null) {
                            mDeleteClickListener.onDeleteClick(v, (Integer) v.getTag());
                        }
                    }
                });
            }
        }
        ((TextView) holder.getView(R.id.tv_item_desc)).setText(bean.getItemDesc());
        String quantity = bean.getQuantity() + "箱";
        ((TextView) holder.getView(R.id.tv_quantity)).setText(quantity);
        String detail = bean.getItemCode() + "/" + bean.getDate();
        ((TextView) holder.getView(R.id.tv_detail)).setText(detail);
        String volume = bean.getVolume() + "方";
        ((TextView) holder.getView(R.id.tv_volume)).setText(volume);
        //恢复状态
        ((MyRecyclerViewItem)holder.getView(R.id.scroll_item)).reset();
    }

    public void setOnDeleteClickListener(OnDeleteClickLister listener) {
        this.mDeleteClickListener = listener;
    }

    public interface OnDeleteClickLister {
        void onDeleteClick(View view, int position);
    }

}
