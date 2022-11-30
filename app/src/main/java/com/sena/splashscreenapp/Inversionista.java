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
import com.sena.splashscreenapp.Adaptadores.InvertirAdapter;
import com.sena.splashscreenapp.bizsett_data.BizsettClient;
import com.sena.splashscreenapp.bizsett_data.InvertirApiService;
import com.sena.splashscreenapp.modelos.Invertir;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inversionista extends AppCompatActivity
        implements InvertirAdapter.RecyclerItemClick, SearchView.OnQueryTextListener ,
        NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {


    private RecyclerView recyclerView;
    private InvertirAdapter adapter;
    private ArrayList<Invertir> invertirs;
    private InvertirApiService retrofitAPIService;
    private BizsettClient retrofitClient;
    private SearchView svSearch;

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invertir);


        recyclerView = findViewById(R.id.recyclerView);
        invertirs = new ArrayList<>();
        svSearch = findViewById(R.id.svSearch);
        retrofitAPIService = BizsettClient.getApiServiceInv();
        mostrarInversion();
    }

    private void mostrarInversion() {

        Call<List<Invertir>> call = retrofitAPIService.getInvertir();
        call.enqueue(new Callback<List<Invertir>>() {
            @Override
            public void onResponse(Call<List<Invertir>> call, Response<List<Invertir>> response) {
                if (!response.isSuccessful()) {

                    Toast.makeText(Inversionista.this, "Error codigo" + response.code(), Toast.LENGTH_SHORT).show();
                }

                List<Invertir> invertirs = response.body();
                recyclerView.setLayoutManager(new LinearLayoutManager(Inversionista.this));
                adapter = new InvertirAdapter((Context) Inversionista.this,
                        (ArrayList<Invertir>) invertirs);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Invertir>> call, Throwable t) {
                Toast.makeText(Inversionista.this, "fallo en la conexion" + t.getMessage(), Toast.LENGTH_SHORT).show();

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
    public void itemClick(Invertir invertir) {

    }
}