package com.example.android_food_app.Model;

import java.io.Serializable;

public class Product1 implements Serializable {
    private int resourceId;
    private String name, desc;
    private String priceOld, priceNew;
    private String sale, popular;

    public Product1(int resourceId, String name, String desc, String priceOld, String priceNew, String sale, String popular) {
        this.resourceId = resourceId;
        this.name = name;
        this.desc = desc;
        this.priceOld = priceOld;
        this.priceNew = priceNew;
        this.sale = sale;
        this.popular = popular;
    }
    public Product1(int resourceId, String name, String priceOld, String priceNew) {
        this.resourceId = resourceId;
        this.name = name;
        this.priceOld = priceOld;
        this.priceNew = priceNew;
    }

    public Product1(int resourceId, String name, String priceOld, String priceNew) {
        this.resourceId = resourceId;
        this.name = name;
        this.priceOld = priceOld;
        this.priceNew = priceNew;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(String priceOld) {
        this.priceOld = priceOld;
    }

    public String getPriceNew() {
        return priceNew;
    }

    public void setPriceNew(String priceNew) {
        this.priceNew = priceNew;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getPopular() {
        return popular;
    }

    public void setPopular(String popular) {
        this.popular = popular;
    }

}
