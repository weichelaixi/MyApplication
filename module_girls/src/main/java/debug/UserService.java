package debug;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.weiche.module_common.utils.KeepLog;
import com.weiche.module_girls.UserServer;
import com.weiche.module_girls.grils.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${chewei} on 2018/7/10.
 * params:2018/7/10
 */

public class UserService extends Service {
    List<User> userList;

    private void inintData() {
        User user = new User("chewei");
        User user1 = new User("chewei1");
        User user2 = new User("chewei2");
        User user3 = new User("chewei3");
        User user4 = new User("chewei4");
        userList.add(user);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
    }

    private IBinder iBinder = new UserServer.Stub(){

        @Override
        public List<User> getBookList() throws RemoteException {
            return userList;
        }

        @Override
        public void addUserInOut(User user) throws RemoteException {
            if(user != null){
                userList.add(user);
            }else{
                KeepLog.e(KeepLog.TAG,"接收到了一个空对象 InOut");
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        userList = new ArrayList<>();
        inintData();
        return iBinder;
    }
}
