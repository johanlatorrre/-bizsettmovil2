package com.sena.splashscreenapp;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.sena.splashscreenapp.Adaptadores.EmpleoAdapter;
import com.sena.splashscreenapp.bizsett_data.BizsettClient;
import com.sena.splashscreenapp.bizsett_data.EmpleoApiService;
import com.sena.splashscreenapp.modelos.Empleos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Empleo extends AppCompatActivity
        implements EmpleoAdapter.RecyclerItemClick, SearchView.OnQueryTextListener ,
    NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {


        private RecyclerView recyclerView;
        private EmpleoAdapter adapter;
        private ArrayList<Empleos> empleos;
        private EmpleoApiService retrofitAPIService;
        private BizsettClient retrofitClient;
        private SearchView svSearch;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_empleo);


            recyclerView = findViewById(R.id.recyclerView);
            empleos = new ArrayList<>();
            svSearch = findViewById(R.id.svSearch);
            retrofitAPIService = BizsettClient.getApiServiceEmpleo();
            mostrarPropuestas();
        }

        private void mostrarPropuestas() {

            Call<List<Empleos>> call = retrofitAPIService.getEmpleo();
            call.enqueue(new Callback<List<Empleos>>() {
                @Override
                public void onResponse(Call<List<Empleos>> call, Response<List<Empleos>> response) {
                    if (!response.isSuccessful()) {

                        Toast.makeText(Empleo.this, "Error codigo" + response.code(), Toast.LENGTH_SHORT).show();
                    }

                    List<Empleos> empleos = response.body();
                    recyclerView.setLayoutManager(new LinearLayoutManager(Empleo.this));
                    adapter = new EmpleoAdapter((Context) Empleo.this,
                            (ArrayList<Empleos>) empleos);
                    recyclerView.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<List<Empleos>> call, Throwable t) {
                    Toast.makeText(Empleo.this, "fallo en la conexion" + t.getMessage(), Toast.LENGTH_SHORT).show();

                }

            });
        }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void itemClick(Empleos empleos) {

    }
}
