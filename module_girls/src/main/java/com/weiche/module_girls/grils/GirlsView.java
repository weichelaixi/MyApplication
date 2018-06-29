package com.weiche.module_girls.grils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.swipe.SwipeRefreshLayout;
import com.weiche.module_girls.grils.bean.Girls;

import java.util.List;

/**
 * Created by ${chewei} on 2018/6/29.
 * params:2018/6/29
 */

public class GirlsView extends FrameLayout implements GirlsContracts.View,RecyclerArrayAdapter.OnMoreListener,SwipeRefreshLayout.OnRefreshListener{

    private boolean mActive;
    GirlsContracts.Persenter persenter;
    private EasyRecyclerView mGirlsRecyclerView;

    public GirlsView(@NonNull Context context) {
        super(context);
        initView();
    }

    public GirlsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public GirlsView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onMoreShow() {

    }

    @Override
    public void onMoreClick() {

    }

    @Override
    public void setPresenter(GirlsContracts.Persenter presenter) {

    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void refresh(List<Girls> girlsList) {

    }

    @Override
    public void loadMore(List<Girls> girlsList) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showNormol() {

    }
}
