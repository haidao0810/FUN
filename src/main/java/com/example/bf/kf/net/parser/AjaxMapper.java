package com.example.bf.kf.net.parser;


import com.example.bf.kf.exceptions.AndroidServerException;

import okhttp3.Response;

public abstract class AjaxMapper<T> {

    public abstract T resultMapper(Response response) throws AndroidServerException, AndroidServerException;

}
