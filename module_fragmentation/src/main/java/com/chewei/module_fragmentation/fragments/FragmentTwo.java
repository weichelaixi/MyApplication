package com.chewei.module_fragmentation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chewei.module_fragmentation.R;
import com.weiche.module_common.utils.KeepLog;

/**
 * Created by ${chewei} on 2018/7/12.
 * params:2018/7/12
 */

public class FragmentTwo extends Fragment {


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        KeepLog.e("   FragmentTwo-->onAttach");
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KeepLog.e("    FragmentTwo-->onCreate");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        KeepLog.e("    FragmentTwo-->setUserVisibleHint");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        KeepLog.e("    FragmentTwo-->onCreateView");
        View view = inflater.inflate(R.layout.fragment_two_layout,container,false);
//        TextView textView = view.findViewById(R.id.content_tv);
//        textView.setText("Two");
        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        KeepLog.e("   FragmentTwo-->onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        KeepLog.e("   FragmentTwo-->onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        KeepLog.e("   FragmentTwo-->onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        KeepLog.e("   FragmentTwo-->onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        KeepLog.e("   FragmentTwo-->onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        KeepLog.e("   FragmentTwo-->onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        KeepLog.e("   FragmentTwo-->onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        KeepLog.e("   FragmentTwo-->onDetach");
    }
}
