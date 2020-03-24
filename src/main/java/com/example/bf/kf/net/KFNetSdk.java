package com.example.bf.kf.net;

import com.example.bf.kf.DAO.bean.CosmeticsBean;
import com.example.bf.kf.DAO.bean.DeviceBean;
import com.example.bf.kf.KFApplication;
import com.example.bf.kf.common.KFXmlInfo;
import com.example.bf.kf.net.response.AjaxListResult;
import com.example.bf.kf.net.response.AjaxObjResult;

import java.lang.ref.WeakReference;

import okhttp3.Call;

public class KFNetSdk extends NetSdk {

    private static WeakReference<KFNetSdk> sWeakReferenceInstance;

    protected KFNetSdk() {
        super();
    }

    public static KFNetSdk getInstance() {
        if (sWeakReferenceInstance == null || sWeakReferenceInstance.get() == null) {
            sWeakReferenceInstance = new WeakReference(new KFNetSdk());
        }
        return sWeakReferenceInstance.get();
    }

    @Override
    public Call doPost(AjaxParams params, AjaxCallBack callback) {
        if(params!=null){
            KFXmlInfo jcXmlInfo = new KFXmlInfo(params.getContext()!=null?params.getContext(): KFApplication.getContext());
            params.put("deviceId",jcXmlInfo.getDeviceId());
        }
        return super.doPost(params, callback);
    }

    @Override
    public Call doGet(AjaxParams params, AjaxCallBack callback) {
        if(params!=null){
            KFXmlInfo jcXmlInfo = new KFXmlInfo(params.getContext()!=null?params.getContext(): KFApplication.getContext());
            params.put("deviceId",jcXmlInfo.getDeviceId());
        }
        return super.doGet(params, callback);
    }

    /**
     * 上传设备信息
     */
    public void sendDeviceMessage(AjaxParams params,AjaxCallBack<AjaxObjResult<DeviceBean>>callBack){
        params.setActionUrl(KFSdkConfigs.POST_DEVICE_MESSAGE_URL);
        super.doPost(params,callBack);
    }

    /**
     * 获取品牌数据列表
     */
    public void getLookCosmeticsList(AjaxParams params, AjaxCallBack<AjaxListResult<CosmeticsBean>> callBack){
        params.setActionUrl(KFSdkConfigs.GET_LOOK_COSMETICS_LIST_URL);
        doGet(params,callBack);
    }

}