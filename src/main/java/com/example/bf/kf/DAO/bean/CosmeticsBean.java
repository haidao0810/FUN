package com.example.bf.kf.DAO.bean;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Comparator;

/**
 * 数据对象
 */
public class CosmeticsBean  implements Serializable, Comparator {
    private String brandId;
    private String brandLocation;//品牌属于哪个地区
    private String brandName;
    private String bLogoUrl;//品牌logo
    private String allYP;//全拼
    private String firstYP;//首字母

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandLocation() {
        return brandLocation;
    }

    public void setBrandLocation(String brandLocation) {
        this.brandLocation = brandLocation;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getAllYP() {
        return allYP;
    }

    public void setAllYP(String allYP) {
        this.allYP = allYP;
    }

    public String getFirstYP() {
        return firstYP;
    }

    public void setFirstYP(String firstYP) {
        this.firstYP = firstYP;
    }

    public String getbLogoUrl() {
        return bLogoUrl;
    }

    public void setbLogoUrl(String bLogoUrl) {
        this.bLogoUrl = bLogoUrl;
    }

    @Override
    public   int compare(Object o, Object t1) {
        CosmeticsBean b1= (CosmeticsBean) o;
        CosmeticsBean b2= (CosmeticsBean) t1;
        return b1.getFirstYP().compareTo(b2.getFirstYP());
    }
}
