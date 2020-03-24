package com.example.bf.kf.net.response;

public class AjaxObjResult<T> extends AjaxResponse {

    public AjaxObjResult(int status, String message) {
        super(status, message);
    }

    String nextId;
    T obj;

    public String getNextId() {
        return nextId;
    }

    public void setNextId(String nextId) {
        this.nextId = nextId;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
