package com.chewei.module_fragmentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.chewei.module_fragmentation.fragments.FragmentOne;
import com.chewei.module_fragmentation.fragments.FragmentThree;
import com.chewei.module_fragmentation.fragments.FragmentTwo;
import com.weiche.module_common.BaseActivity;
import com.weiche.module_common.utils.KeepLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${chewei} on 2018/7/12.
 * params:2018/7/12
 */

public class LifeCycleActivity extends BaseActivity {
    List<Fragment> fragments;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KeepLog.e("   Activity-->onCreate");
        setContentView(R.layout.lifecycle_activity_layout);
        Toolbar toolbar = $(R.id.toolbar);
        setupToolBar(toolbar,false);
        inintView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        KeepLog.e("   Activity-->onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        KeepLog.e("   Activity-->onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        KeepLog.e("   Activity-->onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        KeepLog.e("   Activity-->onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeepLog.e("   Activity-->onDestroy");
    }

    private void inintView() {
        TabLayout tab_main_activity = $(R.id.tab_main_activity);
        ViewPager vp_main_activity = $(R.id.vp_main_activity);
        fragments = new ArrayList<>();
        fragments.add(new FragmentOne());
        fragments.add(new FragmentTwo());
        fragments.add(new FragmentThree());
//        vp_main_activity.setOffscreenPageLimit(2);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),fragments);
        vp_main_activity.setAdapter(adapter);
        tab_main_activity.setupWithViewPager(vp_main_activity);
        for (int i = 0; i < adapter.getCount(); i++) {
            tab_main_activity.getTabAt(i).setText("tab"+(i+1));
        }
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter{

        List<Fragment> fragments;
        FragmentManager fm;

        public ViewPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
            this.fm = fm;
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

    }
}
