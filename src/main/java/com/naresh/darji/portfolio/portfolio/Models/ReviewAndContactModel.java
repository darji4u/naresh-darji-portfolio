package com.naresh.darji.portfolio.portfolio.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class ReviewAndContactModel {

    String type;
    String companyName;
    String designation;
    String message;
    int rating;


    public ReviewAndContactModel(String type, String companyName, String designation, String message, int rating) {
        this.type = type;
        this.companyName = companyName;
        this.designation = designation;
        this.message = message;
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
