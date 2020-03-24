package com.example.bf.kf.net;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AjaxEngine {

    OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .addInterceptor(new LoggerInterceptor())
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS);

    OkHttpClient mOkHttpClient=builder.build();

    public AjaxEngine() {
    }
    private static AjaxEngine mInstance;                 //单例
    public static AjaxEngine getInstance() {
        if (mInstance == null) {
            synchronized (AjaxEngine.class) {
                if (mInstance == null) {
                    mInstance = new AjaxEngine();
                }
            }
        }
        return mInstance;
    }


    //同步请求
    public Response doSendSync(Request request) throws IOException {

        Call call= mOkHttpClient.newCall(request);
        Response response = call.execute();

        return response;
    }

    //异步请求
    public Call doSendAsyn(Request request, Callback callback){
        Call call= mOkHttpClient.newCall(request);
        call.enqueue(callback);
        return call;
    }

    public void cancelTag(Object tag)
    {
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


}
