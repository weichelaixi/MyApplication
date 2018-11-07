package com.chewei.module_mvp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chewei.module_mvp.bean.WeatherInfoBean;
import com.chewei.module_mvp.presender.WeatherPresenterImp;
import com.chewei.module_mvp.view.WeatherView;
import com.weiche.module_common.BaseActivity;

public class MainMVPActivity extends BaseActivity implements WeatherView{

    public TextView contentTv;
    public Button button;
    public WeatherPresenterImp weatherPresenterImp;
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mvp);
        contentTv = $(R.id.textView);
        button = $(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherPresenterImp.loadWeather("098f94fc00e142d2901df4276e3342b4", "北京");
            }
        });
        inint();
    }

    private void inint() {
        weatherPresenterImp = new WeatherPresenterImp(this,this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在请求获取数据,请稍等!!!");
    }

    @Override
    public void showProgress() {
        if(progressDialog != null && !progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    @Override
    public void disimissProgress() {
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    public void loadDataSuccess(WeatherInfoBean tData) {
        String temperature = tData.getHeWeather5().get(0).getAlarms().get(0).getTxt();
        contentTv.setText("当前的气温为：" + temperature + "℃");
    }

    @Override
    public void loadDataError(Throwable throwable) {
        String errorMsg = throwable.getMessage();
        contentTv.setText(errorMsg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        weatherPresenterImp.unSubscribe();
    }
}
