package com.example.bf.kf.net;


import com.example.bf.kf.log.LogManager;
import com.example.bf.kf.log.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LoggerInterceptor implements Interceptor {

    private final String TAG = this.getClass().getName();
    Logger LOGGER = LogManager.getLogger();

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Response response = chain.proceed(request);

        LOGGER.i(TAG, "==>" + request.toString());
        LOGGER.i(TAG, "==>" + response.toString());

        return response;
    }
}
