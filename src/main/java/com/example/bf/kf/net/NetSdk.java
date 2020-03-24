package com.example.bf.kf.net;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;




public abstract class NetSdk {

    public static Handler handler = new Handler(Looper.getMainLooper());

    OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .addInterceptor(new LoggerInterceptor())
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS);

    OkHttpClient mOkHttpClient = builder.build();

    public NetSdk() {

    }

    public Call doGet(AjaxParams params, AjaxCallBack callback){
        if ( null != callback){
            callback.onStart();
        }

        Request request = params.getRequest4Get();

        return excute(request, callback);
    }

    /**
     * 给的路径很完整
     * @param params
     * @param callback
     * @return
     */
    public Call doGetNoPutParam(AjaxParams params, AjaxCallBack callback){

        if ( null != callback){
            callback.onStart();
        }

        Request request = params.getRequest5Get();

        return excute(request, callback);
    }


    public Call doPost(AjaxParams params, AjaxCallBack callback) {
        if ( null != callback){
            callback.onStart();
        }

        Request request = params.getRequest4Post();

        return excute(request, callback);
    }

    public Call doFilePost(String name,String url,AjaxParams params, AjaxCallBack callback) {
        if ( null != callback){
            callback.onStart();
        }

        Request request = params.getRequest4PostImage(name,url);

        return excute(request, callback);
    }

    private Call excute(Request request , Callback callback){
        Call call= mOkHttpClient.newCall(request);
        call.enqueue(callback);
        return call;
    }

    public void cancel( Object tag){

        for (Call call : mOkHttpClient.dispatcher().queuedCalls())
        {
            if (tag.equals(call.request().tag()))
            {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls())
        {
            if (tag.equals(call.request().tag()))
            {
                call.cancel();
            }
        }
    }

    public void callAll(){
        for (Call call : mOkHttpClient.dispatcher().queuedCalls())
        {
                call.cancel();
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls())
        {
                call.cancel();
        }
    }

}
