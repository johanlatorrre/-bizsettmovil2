package com.sena.splashscreenapp.bizsett_data;


import com.sena.splashscreenapp.modelos.Publicaciones;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PublicacionesApiService {

    @GET("v1/publicaciones")
    Call<List<Publicaciones>> getPublicaciones();

    @POST ("v1/publicaciones")
    Call<Publicaciones>addPublicacion(@Body Publicaciones publicaciones);

    @PUT("v1/publicaciones/{id}")
    Call<Publicaciones>updatePublicacion(@Path("id") int id, @Body Publicaciones publicaciones);

    @DELETE("v1/publicaciones/{id}")
    Call<Publicaciones>deletePublicacion(@Path("id") int id);

}

