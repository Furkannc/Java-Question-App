package com.example.sorubankapp.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient
{
    public static final String url="https://opentdb.com";
    public static Retrofit retrofit=null;

    public static Retrofit getRetrofit() {
        if (retrofit==null){
            retrofit=new Retrofit.Builder()//Retrofit creat
                    .baseUrl(url).//To base url
                    addConverterFactory(GsonConverterFactory.create())//Gson data converter created
                    .build();//build retrofit

        }
        return  retrofit;
    }
}
