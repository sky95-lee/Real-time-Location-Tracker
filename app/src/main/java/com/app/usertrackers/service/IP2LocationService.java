package com.app.usertrackers.service;

import com.app.usertrackers.dto.IP2LocationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IP2LocationService {

    @GET("json/")
    Call<IP2LocationResponse> getGeoLocation(@Query("ip") String ipAddress
            , @Query("key") String apiKey);
}
