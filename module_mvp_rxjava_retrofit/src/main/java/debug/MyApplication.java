package debug;

import android.content.Context;

import com.chewei.module_mvp.util.AppContextUtil;
import com.weiche.module_common.BaseApplication;

/**
 * Created by ${chewei} on 2018/11/6.
 * params:2018/11/6
 */

public class MyApplication extends BaseApplication {

    public static Context applicationContext;


    public static Context getContext() {
        return applicationContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppContextUtil.init(this);
        applicationContext = this;
    }
}
