package com.sena.splashscreenapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.sena.splashscreenapp.modelos.Invertir;

public class Perfilemp extends AppCompatActivity implements
        View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfilemp);

        findViewById(R.id.btn_invertir).setOnClickListener(this);
        findViewById(R.id.btn_postularse).setOnClickListener(this);
        findViewById(R.id.maps).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_invertir:
                Intent invertir = new Intent(Perfilemp.this, Inversionista.class);
                startActivity(invertir);
                break;

                case R.id.btn_postularse:
                    Intent empleo = new Intent(Perfilemp.this, Empleo.class);
                    startActivity(empleo);
                    break;

                    case R.id.maps:
                        Intent maps = new Intent(Perfilemp.this, Maps.class);
                        startActivity(maps);
                    break;
            }

        }


}
