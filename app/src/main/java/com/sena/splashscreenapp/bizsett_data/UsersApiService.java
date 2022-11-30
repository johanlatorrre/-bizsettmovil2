package com.sena.splashscreenapp.bizsett_data;

import com.sena.splashscreenapp.modelos.RegisterRequest;
import com.sena.splashscreenapp.modelos.RegisterResponse;
import com.sena.splashscreenapp.modelos.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsersApiService {

    @GET("v1/users")
    Call<List<Users>> getUsers();

    @POST("v1/users")
    Call<RegisterResponse>registerUsers(@Body RegisterRequest registerRequest);
}
