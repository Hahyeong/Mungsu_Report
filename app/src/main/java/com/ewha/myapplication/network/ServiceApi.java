package com.ewha.myapplication.network;

import com.ewha.myapplication.data.movementdata;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceApi {
    @GET("/movement")
    Call<movementdata> getPost();
}
