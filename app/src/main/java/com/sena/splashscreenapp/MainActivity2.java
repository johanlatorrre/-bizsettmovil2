package com.sena.splashscreenapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sena.splashscreenapp.Adaptadores.PostAdapter;
import com.sena.splashscreenapp.modelos.Post;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity{

    DrawerLayout drawerLayout;

    private RecyclerView reciclador;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        List items = new ArrayList();

        items.add(new Post(R.drawable.fondo55, "Hola mundo1"));
        items.add(new Post(R.drawable.fondo55, "Hola mundo2"));
        items.add(new Post(R.drawable.fondo55, "Hola mundo3"));
        items.add(new Post(R.drawable.fondo55, "Hola mundo4"));
        items.add(new Post(R.drawable.fondo55, "Hola mundo5"));
        items.add(new Post(R.drawable.fondo55, "Hola mundo6"));
        items.add(new Post(R.drawable.fondo55, "Hola mundo7"));
        items.add(new Post(R.drawable.fondo55, "Hola mundo8"));
        items.add(new Post(R.drawable.fondo55, "Hola mundo9"));


        // Obtener el Recycler
        reciclador = (RecyclerView) findViewById(R.id.recicladorP);
        reciclador.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);reciclador.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new PostAdapter(items);
        reciclador.setAdapter(adapter);


    }

}