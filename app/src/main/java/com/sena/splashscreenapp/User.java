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
import com.sena.splashscreenapp.Adaptadores.UsersAdapter;
import com.sena.splashscreenapp.bizsett_data.BizsettClient;
import com.sena.splashscreenapp.bizsett_data.UsersApiService;
import com.sena.splashscreenapp.modelos.Users;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User extends AppCompatActivity
        implements UsersAdapter.RecyclerItemClick, SearchView.OnQueryTextListener ,
        NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {


    private RecyclerView recyclerView;
    private UsersAdapter adapter;
    private ArrayList<Users> users;
    private UsersApiService retrofitAPIService;
    private BizsettClient retrofitClient;
    private SearchView svSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        recyclerView = findViewById(R.id.recyclerView);
        users = new ArrayList<>();
        svSearch = findViewById(R.id.svSearch);
        retrofitAPIService = BizsettClient.getApiServiceUsers();
        mostrarUsers();
    }

    private void mostrarUsers() {

        Call<List<Users>> call = retrofitAPIService.getUsers();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if (!response.isSuccessful()) {

                    Toast.makeText(User.this, "Error codigo" + response.code(), Toast.LENGTH_SHORT).show();
                }

                List<Users> users = response.body();
                recyclerView.setLayoutManager(new LinearLayoutManager(User.this));
                adapter = new UsersAdapter((Context) User.this,
                        (ArrayList<Users>) users);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(User.this, "fallo en la conexion" + t.getMessage(), Toast.LENGTH_SHORT).show();

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
    public void itemClick(Users users) {

    }


}
