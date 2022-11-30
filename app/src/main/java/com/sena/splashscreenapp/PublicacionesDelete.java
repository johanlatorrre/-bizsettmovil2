package com.sena.splashscreenapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sena.splashscreenapp.bizsett_data.BizsettClient;
import com.sena.splashscreenapp.bizsett_data.PublicacionesApiService;
import com.sena.splashscreenapp.modelos.Publicaciones;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicacionesDelete extends AppCompatActivity {
    PublicacionesApiService publicacionesApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletep);

        TextView idper = (TextView) findViewById(R.id.id);
        final TextView descripcion = (TextView) findViewById(R.id.descipcion);
        final TextView emprendimiento_id = (TextView) findViewById(R.id.idemp);

        Button eliminar = (Button) findViewById(R.id.eliminar);


        Bundle bundle = getIntent().getExtras();
        final String id = bundle.getString("id");
        String descr = bundle.getString("descripcion");
        String empid = bundle.getString("emprendimiento_id");


        idper.setText(id);
        descripcion.setText(descr);
        emprendimiento_id.setText(empid);


        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deletePublicacion(Integer.valueOf(id));

                Intent intent = new Intent(PublicacionesDelete.this, Home.class);
                startActivity(intent);
            }
        });
    }




    public void deletePublicacion(int id){
        publicacionesApiService = BizsettClient.getApiService();
        Call<Publicaciones>call=publicacionesApiService.deletePublicacion(id);
        call.enqueue(new Callback<Publicaciones>() {
            @Override
            public void onResponse(Call<Publicaciones> call, Response<Publicaciones> response) {
                if (response.isSuccessful()){
                    Toast.makeText(PublicacionesDelete.this, "Se eliminó con éxito",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Publicaciones> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });

        Intent intent = new Intent(PublicacionesDelete.this, Home.class);
        startActivity(intent);
    }
}