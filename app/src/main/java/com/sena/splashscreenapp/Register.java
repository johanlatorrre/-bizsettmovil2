package com.sena.splashscreenapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sena.splashscreenapp.bizsett_data.BizsettClient;
import com.sena.splashscreenapp.bizsett_data.PublicacionesApiService;
import com.sena.splashscreenapp.bizsett_data.UsersApiService;
import com.sena.splashscreenapp.modelos.RegisterRequest;
import com.sena.splashscreenapp.modelos.RegisterResponse;
import com.sena.splashscreenapp.modelos.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    EditText nombre, email, password, password_confirmation;
    Button btnRegistrarse;
    UsersApiService usersApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nombre = findViewById(R.id.nombre);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password_confirmation = findViewById(R.id.password_confirmation);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(nombre.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) || TextUtils.isEmpty(password_confirmation.getText().toString())) {

                    String message = "todos los campos requeridos";
                    Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();

                } else {
                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setName(nombre.getText().toString());
                    registerRequest.setEmail(email.getText().toString());
                    registerRequest.setPassword(password.getText().toString());
                    registerRequest.setPassword_confirmation(password_confirmation.getText().toString());
                    registerUser(registerRequest);
                }
            }
        });

    }

    public void registerUser(RegisterRequest registerRequest) {

        Call<RegisterResponse> registerResponseCall = BizsettClient.getApiServiceUsers().registerUsers(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if (response.isSuccessful()) {

                    String message = "successfull";
                    Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(Register.this, Home.class));
                    finish();

                } else {

                    String message = "ocurrio un error intenta de nuevo";
                    Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }


}

