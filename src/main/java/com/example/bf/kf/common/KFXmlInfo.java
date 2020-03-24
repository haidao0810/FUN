package com.example.bf.kf.common;

import android.content.Context;
import android.content.SharedPreferences;

public class KFXmlInfo {
    public static final String PREFERENCE_NAME = "control_cap_app_info";
    private Context mContext;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private final String LOGIN_TAG="login_state_tag";//登录状态

    private final String DEVICE_ID_TAG="device_id_tag";//设备id
    public KFXmlInfo(Context context){
        mContext=context;
        sharedPreferences=context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    /**
     * 设置登录状态
     */
    public void setLoginState(boolean login){
        editor.putBoolean(LOGIN_TAG,login);
        editor.commit();
    }

    /**
     * 获取登录状态
     */
    public boolean getLoginState(){
       return sharedPreferences.getBoolean(LOGIN_TAG,false);
    }

    /**
     *
     */
    public void setDeviceId(String id){
        editor.putString(DEVICE_ID_TAG,id);
        editor.commit();
    }

    public String getDeviceId(){
        return sharedPreferences.getString(DEVICE_ID_TAG,"");
    }

    /**
     * 清除所有信息
     */
    public void clear(){
        editor.clear();
        editor.commit();
    }
}