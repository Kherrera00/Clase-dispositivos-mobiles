package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.app.Interface.CrudEmpleadoInterface;
import com.example.app.Model.Empleado;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Empleado> listEmpleado;
    CrudEmpleadoInterface cruempleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAll();

        eliminar(4);


        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setNombre("Nuevo nombre");
        nuevoEmpleado.setPassword("Nuevo password");
        nuevoEmpleado.setEmail("Nuevo email");
        crear(nuevoEmpleado);


        actualizarR(4, nuevoEmpleado);


    }



    private void getAll(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://localhost:8080/")
                .baseUrl("http://192.168.1.8:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<List<Empleado>> call = cruempleado.getAll();

        call.enqueue(new Callback<List<Empleado>>(){

            @Override
            public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {
                if(!response.isSuccessful()) {
                    //System.out.println(response.message());
                    Log.e("Response err:, " , response.message());
                    return;
                }
                listEmpleado = response.body();
                //listEmpleado.forEach(p-> System.out.println(p.toString()));
                listEmpleado.forEach(p-> Log.i("Empleados: ", p.toString()));

            }

            @Override
            public void onFailure(Call<List<Empleado>> call, Throwable t){
                Log.e("Throw error:" , t.getMessage());
                //System.out.println(t.getMessage());
            }
        });

    }



    private void eliminar(int idP) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.8:8081")
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

                Log.i("Respuesta", "Se elimino el empleado");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Throw error:", t.getMessage());

            }
        });


    }


    private void actualizarR(int idP, Empleado empleado){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.8:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<Empleado> call = cruempleado.actualizarR(idP, empleado);
        call.enqueue(new Callback<Empleado>(){

            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if(!response.isSuccessful()) {
                    Log.e("Response err:, ", response.message());
                    return;
                }
                Empleado Actualizado = response.body();
                Log.i("Se realizo la actualizacion con exito ", Actualizado.toString());

            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t){
                Log.e("Throw error:" , t.getMessage());
            }
        });

    }

    private void crear(Empleado empleado){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.8:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<Empleado> call = cruempleado.crear(empleado);
        call.enqueue(new Callback<Empleado>(){

            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if(!response.isSuccessful()) {
                    Log.e("Response err:, " , response.message());
                    return;
                }
                Empleado empleadoR = response.body();
                Log.i("se creo con exito ", empleadoR.toString());

            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t){
                Log.e("Throw error:" , t.getMessage());
            }
        });

    }

}