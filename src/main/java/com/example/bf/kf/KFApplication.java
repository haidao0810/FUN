package com.example.bf.kf;

import android.app.Application;
import android.content.Context;

public class KFApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {

        super.onCreate();
//        initPhotoError();

        init();
    }
    public static Context getContext() {
        return mContext;
    }

    private void init(){
        mContext = this.getApplicationContext();
    }


}
