package com.chewei.module_fragmentation;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chewei.module_fragmentation.observer_pattern.ObserverUser;
import com.chewei.module_fragmentation.observer_pattern.ObserverableBean;
import com.weiche.module_common.BaseActivity;
import com.weiche.module_girls.IMyAidlInterface;

@Route(path = "/module/fragmenttation")
public class MainFirstActivity extends BaseActivity implements View.OnClickListener{

    Button button;
    Button button2;
    Button button3;
    Button button4;
    public IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindService(new Intent("debug.action"), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        },BIND_AUTO_CREATE);
        button = $(R.id.button);
        button2 = $(R.id.button2);
        button3 = $(R.id.button3);
        button4 = $(R.id.button4);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.button) {
            ObserverableBean observerable = new ObserverableBean();

            ObserverUser user1 = new ObserverUser("张三");
            ObserverUser user2 = new ObserverUser("李四");
            ObserverUser user3 = new ObserverUser("王五");
            ObserverUser user4 = new ObserverUser("赵六");

            observerable.registerObser(user1);
            observerable.registerObser(user2);
            observerable.registerObser(user3);
            observerable.registerObser(user4);

            observerable.setInformation("大家早上好");

            System.out.println("----------------------------------------------");
            observerable.deleteObserver(user2);
            observerable.setInformation("JAVA是世界上最好用的语言！");
        }else if (i == R.id.button2) {
            startActivity(new Intent(this,RecycleViewSideslipDeleteActivity.class));
        }else if (i == R.id.button3) {
            startActivity(new Intent(this,RecycleViewSideslipDeleteItemActivity.class));
        }else if (i == R.id.button4) {
            try {
                Toast.makeText(MainFirstActivity.this,iMyAidlInterface.name(),Toast.LENGTH_LONG).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
