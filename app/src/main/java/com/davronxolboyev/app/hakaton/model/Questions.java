package com.davronxolboyev.app.hakaton.model;

import com.google.gson.annotations.SerializedName;

public class Questions {

    @SerializedName("title")
    String title;

    @SerializedName("category")
    String category;

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }
}
