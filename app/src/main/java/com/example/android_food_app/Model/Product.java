package com.example.android_food_app.Model;

import java.io.Serializable;

public class Product implements Serializable {
    private String name, desc, priceOld, priceNew, sale;
    private String imgURL, imgURlSlider;
    private Boolean popular;
    private String imgURLOther;
    private String productType;
    private String key;

    public Product(){
    }

    public Product(String name, String desc, String priceOld, String priceNew, String sale, String imgURL, String imgURlSlider, Boolean popular, String productType, String imgURLOther) {
        this.name = name;
        this.desc = desc;
        this.priceOld = priceOld;
        this.priceNew = priceNew;
        this.sale = sale;
        this.imgURL = imgURL;
        this.imgURlSlider = imgURlSlider;
        this.popular = popular;
        this.productType = productType;
        this.imgURLOther = imgURLOther;
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

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getImgURlSlider() {
        return imgURlSlider;
    }

    public void setImgURlSlider(String imgURlSlider) {
        this.imgURlSlider = imgURlSlider;
    }

    public Boolean getPopular() {
        return popular;
    }

    public void setPopular(Boolean popular) {
        this.popular = popular;
    }
    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getImgURLOther() {
        return imgURLOther;
    }

    public void setImgURLOther(String imgURLOther) {
        this.imgURLOther = imgURLOther;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
