package com.sena.splashscreenapp.bizsett_data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BizsettClient {

    private static Retrofit retrofit;
    public static final String URL_BASE = "http://10.0.2.2:8000/";

    public static BizsettApiService getApiServiceEmp(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(BizsettApiService.class);
    }


    public static PublicacionesApiService getApiService(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(PublicacionesApiService.class);
    }



    public static InvertirApiService getApiServiceInv(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(InvertirApiService.class);
    }

    public static EmpleoApiService getApiServiceEmpleo(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(EmpleoApiService.class);
    }

    public static UsersApiService getApiServiceUsers(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(UsersApiService.class);
    }




}
