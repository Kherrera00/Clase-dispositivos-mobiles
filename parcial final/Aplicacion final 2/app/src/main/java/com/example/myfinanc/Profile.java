package com.example.myfinanc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myfinanc.Interface.CrudEmpleadoInterface;
import com.example.myfinanc.Model.Empleado;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Profile extends AppCompatActivity {

    String ruta = "http://10.10.20.106:8081/";
    List<Empleado> listEmpleado;
    CrudEmpleadoInterface cruempleado;
    private String nombre;
    private String password;
    private String email;
    private String numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Button miBoton = findViewById(R.id.actualizarBtn);
        miBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Empleado empleadoActua = new Empleado();


                EditText nombreEditText = findViewById(R.id.nombre);
                EditText passwordEditText = findViewById(R.id.password);
                EditText emailEditText = findViewById(R.id.email);
                EditText numeroEditText = findViewById(R.id.numero);

                String nombre = nombreEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String numero = numeroEditText.getText().toString();

                empleadoActua.setNombre(nombre);
                empleadoActua.setPassword(password);
                empleadoActua.setEmail(email);
                empleadoActua.setNumero(numero);
                actualizar(15,empleadoActua);

                // Limpiar los campos de texto
                nombreEditText.setText("");
                passwordEditText.setText("");
                emailEditText.setText("");
                numeroEditText.setText("");

            }
        });


        Button botonRedireccionarEliminar = findViewById(R.id.elimiarBtn);
        botonRedireccionarEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar(15); // Llama al método eliminar con el parámetro deseado

                // Redirige a la clase Home
                Intent ntent = new Intent(getApplicationContext(), Home.class);
                startActivity(ntent);
            }
        });



    }

    private void actualizar(int id, Empleado empleado){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ruta)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        CrudEmpleadoInterface crudEmpleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<Empleado> call = crudEmpleado.actualizar(id, empleado);
        call.enqueue(new Callback<Empleado>(){

            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if(response.isSuccessful()) {
                    Empleado empleadoActu = response.body();
                    Log.i("actualizarEmpleado", "Se realizo la actualizacion con exito: " + empleadoActu.toString());
                } else {
                    Log.e("actualizarEmpleado", "Response err: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Log.e("actualizarEmpleado", "Error al realizar la actualizacion: " + t.getMessage());
            }
        });
    }


    public void onButtonClick13(View view) {
        // Código para cambiar de página
        Intent intent = new Intent(this, Start.class);
        startActivity(intent);
    }

    //////////eliminar


    private void eliminar(int idP) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ruta)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<Void> call = cruempleado.eliminar(idP);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response err:, ", response.message());
                    return;
                }

                Log.i("Respuesta", "Se eliminó el usuario de manera exitosa");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Throw error:", t.getMessage());

            }
        });


    }



}

//////emilinar






