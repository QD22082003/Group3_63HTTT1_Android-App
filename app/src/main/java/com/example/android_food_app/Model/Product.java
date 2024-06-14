package com.example.android_food_app.Model;

public class Product {
    private int id;
    private String name, desc, priceOld, priceNew, sale;
    private byte[] imgUrl, imgUrlSlide;
    private Boolean popular;
    private byte[] imgUrlOther;
    public Product(){

    }

    public Product(int id, String name, String desc, String priceOld, String priceNew, String sale, byte[] imgUrl, byte[] imgUrlSlide, Boolean popular, byte[] imgUrlOther) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.priceOld = priceOld;
        this.priceNew = priceNew;
        this.sale = sale;
        this.imgUrl = imgUrl;
        this.imgUrlSlide = imgUrlSlide;
        this.popular = popular;
        this.imgUrlOther = imgUrlOther;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public byte[] getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(byte[] imgUrl) {
        this.imgUrl = imgUrl;
    }

    public byte[] getImgUrlSlide() {
        return imgUrlSlide;
    }

    public void setImgUrlSlide(byte[] imgUrlSlide) {
        this.imgUrlSlide = imgUrlSlide;
    }

    public Boolean getPopular() {
        return popular;
    }

    public void setPopular(Boolean popular) {
        this.popular = popular;
    }

    public byte[] getImgUrlOther() {
        return imgUrlOther;
    }

    public void setImgUrlOther(byte[] imgUrlOther) {
        this.imgUrlOther = imgUrlOther;
    }
}
