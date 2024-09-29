package com.app.usertrackers.worker;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.app.usertrackers.constant.IP2LocationConstant;
import com.app.usertrackers.constant.IPConstant;
import com.app.usertrackers.dto.IP2LocationResponse;
import com.app.usertrackers.dto.IPResponse;
import com.app.usertrackers.service.IP2LocationService;
import com.app.usertrackers.service.IPService;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationWorker extends Worker {

    // MutableLiveData to allow UI to observe changes in location
    public String ipAddress;
    public static MutableLiveData<String> locationLiveData = new MutableLiveData<>();
    public static MutableLiveData<String> ladLongLiveData = new MutableLiveData<>();

    public LocationWorker(@NonNull Context context, @NonNull WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Fetch location using current device's IP address
        getPublicIP();
        fetchLocation(ipAddress);

        return Result.success();
    }

    private void getPublicIP() {
        // Initialize Retrofit
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IPConstant.apiLink)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create the Retrofit service
        IPService ipService = retrofit.create(IPService.class);

        // Make network call to get ip address
        Call<IPResponse> call = ipService.getPublicIp();
        call.enqueue(new Callback<IPResponse>() {
            @Override
            public void onResponse(Call<IPResponse> call, Response<IPResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    ipAddress = response.body().getIpAddress();
                }
            }

            @Override
            public void onFailure(Call<IPResponse> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private void fetchLocation(String ipAddress) {
        // Initialize Retrofit
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IP2LocationConstant.IP2LocationApiLink)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IP2LocationService ip2LocationService = retrofit.create(IP2LocationService.class);

        // Make network call to get location details
        Call<IP2LocationResponse> call = ip2LocationService.getGeoLocation(ipAddress, IP2LocationConstant.IP2LocationApiKey);
        call.enqueue(new Callback<IP2LocationResponse>() {
            @Override
            public void onResponse(Call<IP2LocationResponse> call, Response<IP2LocationResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    IP2LocationResponse ip2LocationResponse = response.body();

                    String location = "Location: " +
                            ip2LocationResponse.getCityName() + ", " +
                            ip2LocationResponse.getRegionName() + ", " +
                            ip2LocationResponse.getCountryName();

                    String ladLong = "Latitude: " +
                            ip2LocationResponse.getLatitude() + ", Longitude: " +
                            ip2LocationResponse.getLongitude();

                    locationLiveData.postValue(location);
                    ladLongLiveData.postValue(ladLong);
                }
            }

            @Override
            public void onFailure(Call<IP2LocationResponse> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
