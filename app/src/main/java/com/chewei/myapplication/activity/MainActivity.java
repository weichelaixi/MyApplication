package com.chewei.myapplication.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chewei.myapplication.R;
import com.chewei.myapplication.adapter.RecycleViewAdapter;
import com.chewei.myapplication.bean.ItemBean;
import com.weiche.module_common.BaseActivity;
import com.weiche.module_common.utils.KeepLog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity {

    ArrayList<ItemBean> list;
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.iv_more)
    ImageView ivMore;
    @BindView(R.id.iv_search_icon)
    ImageView ivSearchIcon;
    @BindView(R.id.textview_search)
    TextView textviewSearch;
    @BindView(R.id.rl_search)
    LinearLayout rlSearch;
    @BindView(R.id.iv_more_icon)
    ImageView ivMoreIcon;
    @BindView(R.id.iv_search_iv)
    ImageView ivSearchIv;
    @BindView(R.id.textview_search_tv)
    TextView textviewSearchTv;
    @BindView(R.id.rl_search_layout)
    LinearLayout rlSearchLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar1)
    public View mToolbar1;
    @BindView(R.id.toolbar2)
    public View mToolbar2;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        inintData();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 5);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (list.get(position).type == 2) {
                    Log.e("chewei", list.get(position).type + "+++++++++++++5" + "+++++" + position);
                    return 5;
                } else {
                    Log.e("chewei", list.get(position).type + "-------------1" + "------" + position);
                    return 1;
                }
            }
        });
        recycleView.setLayoutManager(layoutManager);
        RecycleViewAdapter adapter = new RecycleViewAdapter(list, this);
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0:
                        //跳转module_girls模块的activity
                        ARouter.getInstance().build("/girls/list").navigation();
                        break;
                    case 1:
                        //Rxjava的链式操作
                        Observable.create(new ObservableOnSubscribe<Integer>() {
                            //1.创建被观察者
                            //定义发送事件
                            @Override
                            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                                e.onNext(1);
                                e.onNext(2);
                                e.onNext(3);
                                e.onNext(4);
                                e.onComplete();
                            }
                        }).subscribe(new Observer<Integer>() {
                            //2.创建观察者 & 响应事件
                            //3.通过订阅者subscribe连接观察者和被观察者
                            // 默认最先调用复写的 onSubscribe（）
                            @Override
                            public void onSubscribe(Disposable d) {
                                KeepLog.e(KeepLog.TAG,"开始使用subscribe连接");
                            }

                            @Override
                            public void onNext(Integer integer) {
                                KeepLog.e(KeepLog.TAG,"对onNext事件"+integer+"触发");
                            }

                            @Override
                            public void onError(Throwable e) {
                                KeepLog.e(KeepLog.TAG,"对onError事件触发");
                            }

                            @Override
                            public void onComplete() {
                                KeepLog.e(KeepLog.TAG,"对onComplete事件触发");
                            }
                        });
                        break;
                    case 2:

                        break;
                }
            }
        });
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    //张开
                    mToolbar1.setVisibility(View.VISIBLE);
                    mToolbar2.setVisibility(View.GONE);
                    setToolbar1Alpha(255);
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    //收缩
                    mToolbar1.setVisibility(View.GONE);
                    mToolbar2.setVisibility(View.VISIBLE);
                    setToolbar2Alpha(255);
                } else {
                    int alpha = 255 - Math.abs(verticalOffset);
                    if (alpha < 0) {
                        //收缩toolbar
                        mToolbar1.setVisibility(View.GONE);
                        mToolbar2.setVisibility(View.VISIBLE);
                        setToolbar2Alpha(Math.abs(verticalOffset));
                    } else {
                        //张开toolbar
                        mToolbar1.setVisibility(View.VISIBLE);
                        mToolbar2.setVisibility(View.GONE);
                        setToolbar1Alpha(alpha);
                    }
                }
            }
        });
    }

    /**
     * 设置展开时各控件的透明度
     */
    public void setToolbar1Alpha(int alpha) {
        rlSearch.getBackground().setAlpha(alpha);
        ivSearchIcon.getDrawable().setAlpha(alpha);
        textviewSearch.setTextColor(Color.argb(alpha, 255, 255, 255));
        ivMore.getDrawable().setAlpha(alpha);
    }

    /**
     * 设置闭合时各控件的透明度
     */
    public void setToolbar2Alpha(int alpha) {
        ivMoreIcon.getDrawable().setAlpha(alpha);
        ivSearchIv.getDrawable().setAlpha(alpha);
        rlSearchLayout.getBackground().setAlpha(alpha);
    }

    private void inintData() {
        list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            ItemBean item = new ItemBean();
            if ((i < 10 && i > 2) || (i < 16 && i > 12)) {
                item.type = 2;
                item.name = i + "我是标题";
                item.content = "我是内容";
            } else {
                item.type = 1;
                item.imageUrl = "http://images.ali213.net/picfile/pic/2015/05/25/2015052520307101.jpg";
                item.name = i + "我是biaoti";
            }
            Log.e("chewei", i + "+++++++++++++5" + "+++++" + item.name);
            list.add(item);
        }

    }
}
