package com.sena.splashscreenapp.bizsett_data;

import com.sena.splashscreenapp.modelos.Emprendimiento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BizsettApiService {

    @GET("v1/emprendimientos")
    Call<List<Emprendimiento>> getEmprendimientos();


//    @GET("Publicacione.php")
//    Call<List<Publicaciones>> getPublicaciones(@Query("idemprendimiento") int idemprendimiento);
}
