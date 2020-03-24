package com.example.bf.kf.net.parser;


import com.example.bf.kf.exceptions.AndroidServerException;

import java.io.IOException;

import okhttp3.Response;

public class AjaxTextParser extends AjaxMapper<String> {

    @Override
    public String resultMapper(Response response) throws AndroidServerException {

        try {
            String result = response.body().string();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new AndroidServerException(e);
        }

    }

}
