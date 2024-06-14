package com.example.android_food_app.Model;

public class Product1 {
    private int resourceId;
    private String title, desc;
    private double priceOld, priceNew;
    private String sale, popular;

    public Product1(int resourceId, String title, String desc, double priceOld, double priceNew, String sale, String popular) {
        this.resourceId = resourceId;
        this.title = title;
        this.desc = desc;
        this.priceOld = priceOld;
        this.priceNew = priceNew;
        this.sale = sale;
        this.popular = popular;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(double priceOld) {
        this.priceOld = priceOld;
    }

    public double getPriceNew() {
        return priceNew;
    }

    public void setPriceNew(double priceNew) {
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
