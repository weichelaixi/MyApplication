package com.chewei.cw.myapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chewei.cw.myapplication.bean.ItemBean;
import com.chewei.cw.myapplication.R;
import com.chewei.cw.myapplication.adapter.RecycleViewAdapter;
import com.weiche.module_common.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    ArrayList<ItemBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        inintData();
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),5);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(list.get(position).type == 2){
                    Log.e("chewei", list.get(position).type+"+++++++++++++5"+"+++++"+position);
                    return 5;
                }else{
                    Log.e("chewei", list.get(position).type+"-------------1"+"------"+position);
                    return 1;
                }
            }
        });
        recycleView.setLayoutManager(layoutManager);
        RecycleViewAdapter adapter = new RecycleViewAdapter(list,this);
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position){
                    case 0:
                        //跳转module_girls模块的activity
                        ARouter.getInstance().build("/girls/list").navigation();
                        break;
                }
            }
        });
    }

    private void inintData() {
        list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            ItemBean item = new ItemBean();
            if((i<10 && i > 2) || (i<16 && i > 12)){
                item.type = 2;
                item.name = i+"我是标题";
                item.content = "我是内容";
            }else{
                item.type = 1;
                item.imageUrl = "http://images.ali213.net/picfile/pic/2015/05/25/2015052520307101.jpg";
                item.name = i+"我是biaoti";
            }
            list.add(item);
        }

    }
}
