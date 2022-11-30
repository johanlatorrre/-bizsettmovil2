package com.sena.splashscreenapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }


    public void publicaciones(View view) {
        Intent login = new Intent(this, Home.class);
        startActivity(login);

    }

    public void emprendimientos(View view) {
        Intent login = new Intent(this, Emprendimientos.class);
        startActivity(login);

    }

    public void users(View view) {
        Intent login = new Intent(this, User.class);
        startActivity(login);

    }
}
