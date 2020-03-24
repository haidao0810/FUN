package com.example.bf.kf.DAO.bean;

import java.io.Serializable;

/**
 * Created by CCX on 2020/3/5.
 * 分类类型对象
 */
public class ClassIficationBean implements Serializable {
    private String className;//分类名称
    private String uuid;//分类uuid
    private boolean select;//被选择


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
