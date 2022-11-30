package com.sena.splashscreenapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {


    Button dash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        dash=findViewById(R.id.dash);
//        dash.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent dash = new Intent(MainActivity.this, Dashboard.class);
//                startActivity(dash);
//            }
//        });

    }

    public void registrar(View view) {
        Intent login = new Intent(this, Register.class);
        startActivity(login);

    }

    public void login(View view){
        Intent login = new Intent(this, login.class);
        startActivity(login);
    }

    public void logingoogle(View view){
        Intent login = new Intent(this, login.class);
        startActivity(login);
    }

//    public void dash(View view) {
//        Intent dash = new Intent(this, Dashboard.class);
//        startActivity(dash);
//
//    }

}