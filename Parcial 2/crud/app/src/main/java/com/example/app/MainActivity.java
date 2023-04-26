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

    String ruta = "http://192.168.1.9:8081/";
    List<Empleado> listEmpleado;
    CrudEmpleadoInterface cruempleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAll();

        //eliminar(1);

        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setNombre("Kevin Herrera");
        nuevoEmpleado.setPassword("Megusta_laempanada");
        nuevoEmpleado.setEmail("kherrera174@gmail.com");
        //crear(nuevoEmpleado);


        Empleado empleadoActua = new Empleado();
        empleadoActua.setNombre("Juan Carlos");
        empleadoActua.setPassword("123456789");
        empleadoActua.setEmail("Hola12@gmail.com");
        //actualizar(3,empleadoActua);
    }

    private void getAll(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://localhost:8080/")
                .baseUrl(ruta)
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

                Log.i("Respuesta", "Se eliminó el empleado de manera exitosa");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Throw error:", t.getMessage());

            }
        });


    }


    private void actualizar(int idP, Empleado empleado){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ruta)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        CrudEmpleadoInterface crudEmpleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<Empleado> call = crudEmpleado.actualizar(idP, empleado);
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

    private void crear(Empleado empleado){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ruta)
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