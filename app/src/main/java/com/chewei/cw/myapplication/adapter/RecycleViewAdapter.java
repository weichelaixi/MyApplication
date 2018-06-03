package com.chewei.cw.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chewei.cw.myapplication.ItemBean;
import com.chewei.cw.myapplication.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${chewei} on 2018/6/2.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter{

    List<ItemBean> list;
    Context context;

    public RecycleViewAdapter(List<ItemBean> list,Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == 1){
            view = LayoutInflater.from(parent.getContext()).inflate( R.layout.image_news_item, parent, false);
            return new ImageViewHolder(view);
        } else if(viewType == 2){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            return new NewsViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            return new NewsViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        if(holder instanceof NewsViewHolder){
            NewsViewHolder viewHolder = (NewsViewHolder)holder;
            viewHolder.title.setText(list.get(position).name);
            viewHolder.digest.setText(list.get(position).content);
        } else if(holder instanceof ImageViewHolder){
            ImageViewHolder viewHolder = (ImageViewHolder)holder;
            viewHolder.title.setText(list.get(position).name);
            /**
             * Glide加载图片
             */
            Log.e("chewei", list.get(position).imageUrl);
            Glide.with(context).load(list.get(position).imageUrl).apply(RequestOptions.bitmapTransform(new CircleCrop())).thumbnail(0.2f).into(viewHolder.news_src);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).type;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.news_title)TextView title;
        @BindView(R.id.news_content)TextView digest;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.new_name) TextView title;
        @BindView(R.id.news_src) ImageView news_src;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
