package com.chewei.module_fragmentation;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chewei.module_fragmentation.adapter.RecycleViewItemDeleteAdapter;
import com.chewei.module_fragmentation.bean.Inventory;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.weiche.module_common.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ${chewei} on 2018/7/3.
 * params:2018/7/3
 */

public class RecycleViewSideslipDeleteItemActivity extends BaseActivity {

    public EasyRecyclerView recyclerView;
    private List<Inventory> mInventories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.easyrecycleview_layout);
        recyclerView = $(R.id.easyrecycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.divider_inset));
        recyclerView.addItemDecoration(itemDecoration);
        mInventories = new ArrayList<>();
        Inventory inventory;
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            inventory = new Inventory();
            inventory.setItemDesc("测试数据" + i);
            inventory.setQuantity(random.nextInt(100000));
            inventory.setItemCode("0120816");
            inventory.setDate("20180219");
            inventory.setVolume(random.nextFloat());
            mInventories.add(inventory);
        }
        final RecycleViewItemDeleteAdapter deleteAdapter = new RecycleViewItemDeleteAdapter(this,mInventories);
        recyclerView.setAdapter(deleteAdapter);
        deleteAdapter.setOnDeleteClickListener(new RecycleViewItemDeleteAdapter.OnDeleteClickLister() {
            @Override
            public void onDeleteClick(View view, int position) {
                mInventories.remove(position);
                deleteAdapter.notifyDataSetChanged();
            }
        });

    }
}
