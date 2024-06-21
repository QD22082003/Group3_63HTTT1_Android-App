package com.example.android_food_app.Model;

public class Feedback {
    private String ID;
    private String userID;
    private String comment;

    public Feedback(String ID,String userID, String comment) {
        this.ID = ID;
        this.userID = userID;
        this.comment = comment;
    }
    public Feedback() {
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
