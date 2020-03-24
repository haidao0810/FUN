package com.example.bf.kf.function.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.bf.kf.R;
import com.example.bf.kf.common.BaseFragment;

/**
 * 圈子
 */
public class CircleFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.f_circle_layout;
    }

    public static CircleFragment newInstance() {
        return new CircleFragment();
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void inVisible() {

    }
}