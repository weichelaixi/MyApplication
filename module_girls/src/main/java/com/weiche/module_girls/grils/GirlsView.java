package com.weiche.module_girls.grils;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.ViewStub;
import android.widget.FrameLayout;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.weiche.module_girls.R;
import com.weiche.module_girls.grils.bean.Girls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${chewei} on 2018/6/29.
 * params:2018/6/29
 */

public class GirlsView extends FrameLayout implements GirlsContracts.View,RecyclerArrayAdapter.OnMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private boolean mActive;
    GirlsContracts.Persenter persenter;
    private EasyRecyclerView girlsRecyclerView;
    private ViewStub networkErrorLayout;
    private GirlsAdapter adapter;
    private ArrayList<Girls> mData;

    public GirlsView(Context context) {
        super(context);
        initView();
    }

    public GirlsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public GirlsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        inflate(getContext(), R.layout.view_girls_content,this);
        networkErrorLayout = findViewById(R.id.network_error_layout);
        girlsRecyclerView = findViewById(R.id.girls_recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        girlsRecyclerView.setLayoutManager(layoutManager);
        adapter = new GirlsAdapter(getContext());
        girlsRecyclerView.setAdapterWithProgress(adapter);
        adapter.setMore(R.layout.layout_load_more, this);
        adapter.setNoMore(R.layout.layout_load_no_more);
        adapter.setError(R.layout.layout_load_error);
        adapter.setOnMyItemClickListener(new GirlsAdapter.OnMyItemClickListener() {
            @Override
            public void onItemClick(int position, BaseViewHolder holder) {

            }
        });
        girlsRecyclerView.setRefreshListener(this);

        mData = new ArrayList<>();
        mActive = true;

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mActive = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mActive = false;
    }

    @Override
    public void onMoreShow() {

    }

    @Override
    public void onMoreClick() {

    }

    @Override
    public void setPresenter(GirlsContracts.Persenter presenter) {
        this.persenter = presenter;
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void refresh(List<Girls> girlsList) {
        mData.clear();
        mData.addAll(girlsList);
        adapter.clear();
        adapter.addAll(girlsList);
    }

    @Override
    public void loadMore(List<Girls> girlsList) {
        mData.addAll(girlsList);
        adapter.addAll(girlsList);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showNormol() {

    }

    @Override
    public void onRefresh() {

    }
}
