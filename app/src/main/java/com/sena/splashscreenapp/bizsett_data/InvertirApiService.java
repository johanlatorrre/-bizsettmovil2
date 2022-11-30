package com.sena.splashscreenapp.bizsett_data;

import com.sena.splashscreenapp.modelos.Invertir;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InvertirApiService {
    @GET("v1/inversionistas")
    Call<List<Invertir>> getInvertir();
}
