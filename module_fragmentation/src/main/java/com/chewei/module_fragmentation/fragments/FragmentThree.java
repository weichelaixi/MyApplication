package com.chewei.module_fragmentation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chewei.module_fragmentation.R;
import com.weiche.module_common.utils.KeepLog;

/**
 * Created by ${chewei} on 2018/7/12.
 * params:2018/7/12
 */

public class FragmentThree extends Fragment {


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        KeepLog.e("   FragmentThree-->onAttach");
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KeepLog.e("    FragmentThree-->onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        KeepLog.e("    FragmentThree-->onCreateView");
        View view = inflater.inflate(R.layout.fragment_layout,container,false);
        TextView textView = view.findViewById(R.id.content_tv);
        textView.setText("Three");
        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        KeepLog.e("   FragmentThree-->onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        KeepLog.e("   FragmentThree-->onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        KeepLog.e("   FragmentThree-->onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        KeepLog.e("   FragmentThree-->onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        KeepLog.e("   FragmentThree-->onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        KeepLog.e("   FragmentThree-->onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        KeepLog.e("   FragmentThree-->onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        KeepLog.e("   FragmentThree-->onDetach");
    }
}
