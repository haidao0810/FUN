package com.example.bf.kf.net.response;

import java.util.List;

public class AjaxListResult<T> extends AjaxResponse {

    public AjaxListResult(int status, String message) {
        super(status, message);
    }

    String nextId;
    List<T> list;

    public String getNextId() {
        return nextId;
    }

    public void setNextId(String nextId) {
        this.nextId = nextId;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


}
