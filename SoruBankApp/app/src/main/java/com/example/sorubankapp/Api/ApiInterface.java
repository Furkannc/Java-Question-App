package com.example.sorubankapp.Api;

import com.example.sorubankapp.Quiz;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/api.php?amount=20&type=multiple")
    Call<Quiz> getQuizzes();

}
