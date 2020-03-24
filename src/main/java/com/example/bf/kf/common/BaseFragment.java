package com.example.bf.kf.common;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bf.kf.net.KFNetSdk;

/**
 * 文件描述：pos机 基本Fragment
 * 作者：CCX
 * 创建时间：2019/1/3
 */
public  abstract  class BaseFragment extends Fragment {
    protected Activity mActivity;
    protected KFNetSdk kfNetSdk;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity)context;
    }

    protected KFNetSdk getControlCapSdk(){
        if(kfNetSdk==null){
            kfNetSdk=KFNetSdk.getInstance();
        }
        return kfNetSdk;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= LayoutInflater.from(mActivity).inflate(getLayoutId(),null);
        initView(view,savedInstanceState);
        return view;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            inVisible();
        }else{
            onVisible();

        }
    }
    /**
     * fragment 布局id
     * @return
     */
    protected  abstract int  getLayoutId() ;

    protected  abstract void initView(View view,Bundle savedInstanceState);

    /**
     * fragment 显示时候执行方法
     */
    protected abstract void onVisible();

    /**
     *  fragment hint 时候执行方法
     */

    protected  abstract void inVisible();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mActivity!=null){
            mActivity=null;
        }
    }


}
