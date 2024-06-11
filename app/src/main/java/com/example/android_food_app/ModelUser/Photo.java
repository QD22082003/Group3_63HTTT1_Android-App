package com.example.android_food_app.ModelUser;

import java.io.Serializable;

public class Photo implements Serializable {
    private int imgID;

    public Photo(int imgID) {
        this.imgID = imgID;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }
}
