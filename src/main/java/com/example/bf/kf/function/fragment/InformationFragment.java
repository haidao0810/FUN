package com.example.bf.kf.function.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.bf.kf.R;
import com.example.bf.kf.common.BaseFragment;

/**
 * 资讯
 */
public class InformationFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.f_information_layout;
    }

    public static InformationFragment newInstance() {
        return new InformationFragment();
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