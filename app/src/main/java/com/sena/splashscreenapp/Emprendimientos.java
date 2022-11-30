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
import com.sena.splashscreenapp.Adaptadores.Adapter;
import com.sena.splashscreenapp.Adaptadores.PublicacionesAdapter;
import com.sena.splashscreenapp.bizsett_data.BizsettApiService;
import com.sena.splashscreenapp.bizsett_data.BizsettClient;
import com.sena.splashscreenapp.modelos.Emprendimiento;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Emprendimientos extends AppCompatActivity
        implements Adapter.RecyclerItemClick, SearchView.OnQueryTextListener ,
        NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<Emprendimiento> emprendimientos;
    private BizsettApiService retrofitAPIService;
    private BizsettClient retrofitClient;
    private SearchView svSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprendimientos);


        recyclerView = findViewById(R.id.recyclerView);
        emprendimientos = new ArrayList<>();
        svSearch = findViewById(R.id.svSearch);
        retrofitAPIService = BizsettClient.getApiServiceEmp();
        mostrarEmprendimientos();
    }


    private void mostrarEmprendimientos() {

        Call<List<Emprendimiento>> call = retrofitAPIService.getEmprendimientos();
        call.enqueue(new Callback<List<Emprendimiento>>() {

            @Override
            public void onResponse(Call<List<Emprendimiento>> call, Response<List<Emprendimiento>> response) {
                if (!response.isSuccessful()) {

                    Toast.makeText(Emprendimientos.this, "Error codigo" + response.code(), Toast.LENGTH_SHORT).show();
                }

                List<Emprendimiento> emprendimientos = response.body();
                recyclerView.setLayoutManager(new LinearLayoutManager(Emprendimientos.this));
                adapter = new Adapter((Context) Emprendimientos.this,
                        (ArrayList<Emprendimiento>) emprendimientos);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Emprendimiento>> call, Throwable t) {
                Toast.makeText(Emprendimientos.this, "fallo en la conexion" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
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
    public void itemClick(Emprendimiento emprendimiento) {

    }
}
