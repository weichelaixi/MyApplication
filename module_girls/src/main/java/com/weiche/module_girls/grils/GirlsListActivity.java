package com.weiche.module_girls.grils;

import android.os.Bundle;

import com.weiche.module_common.BaseActivity;

/**
 * Created by ${chewei} on 2018/6/29.
 * params:2018/6/29
 */

public class GirlsListActivity extends BaseActivity {

    GirlsView mView;
    GirlsContracts.Persenter mPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new GirlsView(this);
        setContentView(mView);
        mPersenter = new GirlsPresenter();
        mPersenter.start();
    }
}
