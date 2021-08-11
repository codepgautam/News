package com.example.news;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApiInterface {

    @GET("v2/top-headlines")
    Call<ResponseModel> getlists(@Query("country") String country, @Query("apiKey") String apiKey);
}
