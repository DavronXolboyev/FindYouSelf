package com.davronxolboyev.app.hakaton.model;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface JsonPlaceHolder {

    @GET("?format=json")
    Call<List<Questions>> getQuestions();

}
