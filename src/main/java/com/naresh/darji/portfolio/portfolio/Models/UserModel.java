package com.naresh.darji.portfolio.portfolio.Models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserModel {

    int userID;
    String userEmail;
    String userProfile;
    String userName;




    public UserModel(int userID, String userEmail, String userProfile, String userName) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.userProfile = userProfile;
        this.userName = userName;
    }

    public UserModel() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
