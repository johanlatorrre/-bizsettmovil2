package com.sena.splashscreenapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.sena.splashscreenapp.Adaptadores.Adapter;
import com.sena.splashscreenapp.Adaptadores.PublicacionesAdapter;

import com.sena.splashscreenapp.bizsett_data.BizsettClient;
import com.sena.splashscreenapp.bizsett_data.PublicacionesApiService;
import com.sena.splashscreenapp.modelos.Publicaciones;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity
        implements PublicacionesAdapter.RecyclerItemClick, SearchView.OnQueryTextListener ,
        NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{

    private RecyclerView recyclerView;
    private PublicacionesAdapter adapter;
    private ArrayList<Publicaciones> publicaciones;
    private PublicacionesApiService retrofitAPIService;
    private BizsettClient retrofitClient;
    private SearchView svSearch;
    private Button button;


    /////////////////////////////////////////////
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout;
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        MenuItem menuItem = navigationView.getMenu().getItem(0);
        //onNavigationItemSelected(menuItem);
        menuItem.setChecked(true);

        drawerLayout.addDrawerListener(this);



        View header = navigationView.getHeaderView(0);
        header.findViewById(R.id.header_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(this, getString(R.string.title_click),Toast.LENGTH_SHORT).show();
            }
        });






        recyclerView = findViewById(R.id.recyclerView);
        publicaciones = new ArrayList<>();
        svSearch = findViewById(R.id.svSearch);
        retrofitAPIService = BizsettClient.getApiService();
        mostrarPublicaciones();

    }



    public void add(View view) {
        Intent login = new Intent(this, PublicacionesCrud.class);
        login.putExtra("id", "");
        login.putExtra("descripcion", "");
        login.putExtra("emprendimiento_id", "");
        startActivity(login);

    }


//    public void delete(View view) {
//        Intent login = new Intent(this, PublicacionesDelete.class);
//        login.putExtra("id", "");
//        login.putExtra("descripcion", "");
//        login.putExtra("emprendimiento_id", "");
//        startActivity(login);
//    }

    private void mostrarPublicaciones() {

        Call<List<Publicaciones>> call = retrofitAPIService.getPublicaciones();
        call.enqueue(new Callback<List<Publicaciones>>() {

            @Override
            public void onResponse(Call<List<Publicaciones>> call, Response<List<Publicaciones>> response) {
                if (!response.isSuccessful()){

                    Toast.makeText(Home.this, "Error codigo" +response.code(),Toast.LENGTH_SHORT).show();
                }

                List<Publicaciones> publicaciones= response.body();
                recyclerView.setLayoutManager(new LinearLayoutManager(Home.this));
                adapter= new PublicacionesAdapter((Context) Home.this,
                        (ArrayList<Publicaciones>) publicaciones);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Publicaciones>> call, Throwable t) {
                Toast.makeText(Home.this, "fallo en la conexion" + t.getMessage(), Toast.LENGTH_SHORT).show();

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





//    public void profile(View view){
//        Intent profile = new Intent(this, Perfil.class);
//        startActivity(profile);
//    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
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
        int title;
        switch (item.getItemId()) {
            case R.id.nav_perfil:
                Intent profile = new Intent(Home.this, Perfil.class);
                startActivity(profile);
                title = R.string.perfil;
                break;
            case R.id.nav_emprendimiento:
                Intent perfilemp = new Intent(Home.this, Perfilemp.class);
                startActivity(perfilemp);
                title = R.string.emprendimiento;
                break;
            case R.id.nav_chat:
                Intent intent = new Intent(Home.this, MainActivity2.class);
                startActivity(intent);
                title = R.string.chat;
                break;
            case R.id.nav_settings:
                title = R.string.configuraci_n;
                break;
            case R.id.nav_salir:
                title = R.string.log_out;
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(Home.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.nav_dash:
                Intent dashboard = new Intent(Home.this, Dashboard.class);
                startActivity(dashboard);
                title = R.string.dash;
                break;
            default:
                throw new IllegalArgumentException("menu option not implemented!!");
        }



        Fragment fragment = HomeContentFragment.newInstance(getString(title));
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.nav_enter, R.anim.nav_exit)
                .replace(R.id.lyt2, fragment)
                .commit();

        setTitle(getString(title));
        //drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void itemClick(Publicaciones publicaciones) {

    }

}



//    private RecyclerView recyclerView;
//    private Adapter adapter;
//    private ArrayList<Emprendimiento> emprendimientos;
//    private BizsettApiService retrofitAPIService;
//    private BizsettClient retrofitClient;
//    private SearchView svSearch;
//
//
//
//
//
//    private void mostrarEmprendimientos() {
//
//        Call<List<Emprendimiento>> call = retrofitAPIService.getEmprendimientos();
//        call.enqueue(new Callback<List<Emprendimiento>>() {
//
//            @Override
//            public void onResponse(Call<List<Emprendimiento>> call, Response<List<Emprendimiento>> response) {
//                if (!response.isSuccessful()){
//
//                    Toast.makeText(Home.this, "Error codigo" +response.code(),Toast.LENGTH_SHORT).show();
//                }
//
//                List<Emprendimiento> emprendimientos= response.body();
//                recyclerView.setLayoutManager(new LinearLayoutManager(Home.this));
//                adapter= new Adapter((Context) Home.this,
//                        (ArrayList<Emprendimiento>) emprendimientos);
//                recyclerView.setAdapter(adapter);
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Emprendimiento>> call, Throwable t) {
//                Toast.makeText(Home.this, "fallo en la conexion" + t.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//
//        });
//    }
//
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        adapter.filter(newText);
//        return false;
//    }









