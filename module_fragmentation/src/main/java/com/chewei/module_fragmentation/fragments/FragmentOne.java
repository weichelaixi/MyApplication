package com.chewei.module_fragmentation.fragments;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chewei.module_fragmentation.R;
import com.weiche.module_common.utils.KeepLog;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by ${chewei} on 2018/7/12.
 * params:2018/7/12
 */

public class FragmentOne extends Fragment implements EasyPermissions.PermissionCallbacks{

    public int CAMERA_CODE = 1001;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        KeepLog.e("   FragmentOne-->onAttach");
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KeepLog.e("    FragmentOne-->onCreate");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        KeepLog.e("    FragmentOne-->setUserVisibleHint");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        KeepLog.e("    FragmentOne-->onCreateView");
        View view = inflater.inflate(R.layout.fragment_one_layout,container,false);
        TextView textView = view.findViewById(R.id.content_tv);
        textView.setText("one");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission();

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        KeepLog.e("   FragmentOne-->onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        KeepLog.e("   FragmentOne-->onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        KeepLog.e("   FragmentOne-->onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        KeepLog.e("   FragmentOne-->onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        KeepLog.e("   FragmentOne-->onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        KeepLog.e("   FragmentOne-->onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        KeepLog.e("   FragmentOne-->onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        KeepLog.e("   FragmentOne-->onDetach");
    }

    public void checkPermission(){
        if (Build.VERSION.SDK_INT >= 23) {
            //打电话的权限
            String[] mPermissionList = new String[]{Manifest.permission.CAMERA};
            if (EasyPermissions.hasPermissions(getActivity(), mPermissionList)) {
                //已经同意过
            } else {
                //未同意过,或者说是拒绝了，再次申请权限
                EasyPermissions.requestPermissions(
                        this,  //上下文
                        "需要读取系统相册的权限", //提示文言
                        CAMERA_CODE, //请求码
                        mPermissionList //权限列表
                );
            }
        } else {
            //6.0以下，不需要授权
        }

    }
        @Override
        public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
            KeepLog.e("permission","------------onPermissionsGranted");
        }

        //拒绝授权
        @Override
        public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
            if (EasyPermissions.somePermissionPermanentlyDenied(getActivity(), perms)) {
                new AppSettingsDialog.Builder(getActivity()).setTitle("提醒")
                        .setRationale("此app需要这些权限才能正常使用").build().show();
            }
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            KeepLog.e("permission","------------onRequestPermissionsResult");
            EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
        }


}
