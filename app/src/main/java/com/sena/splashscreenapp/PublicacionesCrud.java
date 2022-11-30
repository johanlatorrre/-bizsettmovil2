package com.sena.splashscreenapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sena.splashscreenapp.bizsett_data.BizsettClient;
import com.sena.splashscreenapp.bizsett_data.PublicacionesApiService;
import com.sena.splashscreenapp.modelos.Publicaciones;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicacionesCrud extends AppCompatActivity {
    PublicacionesApiService publicacionesApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modal_publicaciones);


        final EditText descripcion = (EditText) findViewById(R.id.descipcion);
        final EditText emprendimiento_id = (EditText)  findViewById(R.id.idemp);

        Button save = (Button) findViewById(R.id.save);


        Bundle bundle=getIntent().getExtras();
        String descr= bundle.getString("descripcion");
        String empid= bundle.getString("emprendimiento_id");


        descripcion.setText(descr);
        emprendimiento_id.setText(empid);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Publicaciones p=new Publicaciones();
                p.setDescripcion(descripcion.getText().toString());
                p.setEmprendimiento_id(emprendimiento_id.getText().toString());
                addPublicacion(p);

                Intent intent= new Intent(PublicacionesCrud.this, Home.class);
                startActivity(intent);
                }
        });
    }

//    public void edit(View view) {
//        Intent login = new Intent(this, PublicacionesEdit.class);
//        startActivity(login);
//
//    }


    public void addPublicacion(Publicaciones p){
        publicacionesApiService = BizsettClient.getApiService();

        Call<Publicaciones>call=publicacionesApiService.addPublicacion(p);
        call.enqueue(new Callback<Publicaciones>() {
            @Override
            public void onResponse(Call<Publicaciones> call, Response<Publicaciones> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(PublicacionesCrud.this, "Se agrego conéxito", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Publicaciones> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });

        Intent intent= new Intent(PublicacionesCrud.this, Home.class);
        startActivity(intent);
    }
}


