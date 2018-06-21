package com.weiche.module_girls;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.weiche.module_common.BaseActivity;

/**
 * Created by ${chewei} on 2018/6/21.
 * params:2018/6/21
 */
@Route(path = "/girls/list")
public class MainGirlsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girls_main);
    }
}
