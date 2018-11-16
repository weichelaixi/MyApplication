package debug;

import android.content.Context;
import android.support.annotation.Keep;
import android.util.Log;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixApplication;
import com.taobao.sophix.SophixEntry;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

/**
 * Created by ${chewei} on 2018/11/14.
 * 描述:
 */

public class SophixStubApplication extends SophixApplication {

    private final String TAG = "SophixStubApplication";

    /* 此处SophixEntry应指定真正的Application，并且保证RealApplicationStub类名不被混淆。
     * 需要在progurad里面手动指定RealApplicationStub不被混淆。
     */

    @Keep
    @SophixEntry(MyApplication.class)
    static class RealApplicationStub {}

    @Override
    public void onCreate() {
        super.onCreate();
        loadNewPatch();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//         如果需要使用MultiDex，需要在此处调用。
//         MultiDex.install(this);
        initSophix();
    }

    private void initSophix() {
        String appVersion = "0.0.0";
        try {
            appVersion = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0)
                    .versionName;
        } catch (Exception e) {
            appVersion = "1.0.0";
            e.printStackTrace();
        }
        final SophixManager instance = SophixManager.getInstance();
        instance.setContext(this)
                .setAppVersion(appVersion)
                .setSecretMetaData(null, null, null)
                .setEnableDebug(true) ////默认为false，设为true即调试模式下会输出日志以及不进行补丁签名校验. 线下调试此参数可以设置为true, 它会强制不对补丁进行签名校验, 所有就算补丁未签名或者签名失败也发现可以加载成功. 但是正式发布该参数必须为false, false会对补丁做签名校验, 否则就可能存在安全漏洞风险。
                .setEnableFullLog()
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            Log.i(TAG, "sophix load patch success!");
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 如果需要在后台重启，建议此处用SharePreference保存状态。
                            Log.i(TAG, "sophix preload patch success. restart app to make effect.");
                            instance.killProcessSafely();
                        }
                    }
                }).initialize();
    }

    private void loadNewPatch() {
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
    }
}
