package com.example.bf.kf.common;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.bf.kf.R;
import com.example.bf.kf.net.KFNetSdk;
import com.example.bf.kf.utils.StatusBarUtil;

public class BaseActivity extends AppCompatActivity {
    private KFNetSdk kfNetSdk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBar();
        initView();
        initEvent();
        initData();
    }

    protected  void initView(){

    }

    protected void initEvent(){

    }

    protected void initData(){

    }


    protected KFNetSdk getControlCapSdk(){
        if(kfNetSdk==null){
            kfNetSdk=KFNetSdk.getInstance();
        }
        return kfNetSdk;
    }

    private void initBar(){
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0X50000000);
        }
//        QMUIStatusBarHelper.translucent(this);
    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        //判断是否有焦点
//        if(hasFocus && Build.VERSION.SDK_INT >= 19){
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            |View.SYSTEM_UI_FLAG_FULLSCREEN
//                            |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//            );
//
//        }
//    }
}