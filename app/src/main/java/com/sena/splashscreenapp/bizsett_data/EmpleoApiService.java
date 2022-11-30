package com.sena.splashscreenapp.bizsett_data;

import com.sena.splashscreenapp.modelos.Empleos;
import com.sena.splashscreenapp.modelos.Emprendimiento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmpleoApiService{

    @GET("v1/empleos")
    Call<List<Empleos>> getEmpleo();
}
