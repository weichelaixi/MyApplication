package com.weiche.module_girls.grils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.weiche.module_girls.R;
import com.weiche.module_girls.grils.bean.Girls;

/**
 * Created by ${chewei} on 2018/6/30.
 */
public class GirlsAdapter extends RecyclerArrayAdapter<Girls> {

    public OnMyItemClickListener mOnItemClickListener;

    public GirlsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new GirlsViewHolder(parent);
    }

    public class GirlsViewHolder extends BaseViewHolder<Girls>{
        private final ImageView image;

        public GirlsViewHolder(ViewGroup itemView) {
            super(itemView,R.layout.item_girl);
            image = $(R.id.girl_img);
        }

        @Override
        public void setData(Girls data) {
            super.setData(data);
            Glide.with(getContext())
                    .load(data.getUrl())
                    .into(image);
        }
    }

    public interface OnMyItemClickListener {
        void onItemClick(int position, BaseViewHolder holder);
    }

    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
