package com.app.usertrackers.service;

import com.app.usertrackers.dto.IPResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IPService {

    @GET("/")
    Call<IPResponse> getPublicIp();
}
