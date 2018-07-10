package debug;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.weiche.module_girls.IMyAidlInterface;

/**
 * Created by ${chewei} on 2018/7/9.
 * params:2018/7/9
 */

public class TestService extends Service {

    public  TestService(){

    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    private class MyBinder extends IMyAidlInterface.Stub{
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String name() throws RemoteException {
            return "测试时使用";
        }
    }
}
