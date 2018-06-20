package com.weiche.module_girls;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.weiche.module_common.BaseActivity;

@Route(path = "/girls/list")
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girls_main);
    }
}
