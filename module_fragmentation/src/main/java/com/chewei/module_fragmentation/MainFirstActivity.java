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
import com.weiche.module_common.utils.KeepLog;
import com.weiche.module_girls.IMyAidlInterface;
import com.weiche.module_girls.UserServer;
import com.weiche.module_girls.grils.User;

import java.util.List;

@Route(path = "/module/fragmenttation")
public class MainFirstActivity extends BaseActivity implements View.OnClickListener{

    Button button;
    Button button2;
    Button button3;
    Button button4,button5,button6,button7;
    public IMyAidlInterface iMyAidlInterface;
    public UserServer userServer;
    private boolean connected;

    List<User> userList;
    int userI = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClassLoader classLoader = getClassLoader();
        if(classLoader != null){
            KeepLog.e(KeepLog.TAG,"classLoader" + classLoader.toString());
            while (classLoader.getParent() != null){
                classLoader = classLoader.getParent();
                KeepLog.e(KeepLog.TAG,"classLoader" + classLoader.toString());
            }
        }

        bindService(new Intent().setPackage("com.weiche.module_girls").setAction("debug.action"), serviceConnection,BIND_AUTO_CREATE);
        bindService(new Intent().setPackage("com.weiche.module_girls").setAction("debug.UserService"),userServiceConnection,BIND_AUTO_CREATE);
        button = $(R.id.button);
        button2 = $(R.id.button2);
        button3 = $(R.id.button3);
        button4 = $(R.id.button4);
        button5 = $(R.id.button5);
        button6 = $(R.id.button6);
        button7 = $(R.id.button7);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    ServiceConnection userServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            userServer = UserServer.Stub.asInterface(iBinder);
            connected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            connected = false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(connected){
            unbindService(userServiceConnection);
        }
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
        }else if(i == R.id.button5){
            if(connected){
                try {
                    userList = userServer.getBookList();
                    for (User user:
                         userList) {
                        KeepLog.e(KeepLog.TAG,user.toString());
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }else if(i == R.id.button6){
            if(connected){
                User user = new User("张三"+userI);
                try {
                    userServer.addUserInOut(user);
                    KeepLog.e(KeepLog.TAG,user.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                userI++;
            }
        }else if(i == R.id.button7){
            startActivity(new Intent(MainFirstActivity.this,LifeCycleActivity.class));
        }
    }
}
