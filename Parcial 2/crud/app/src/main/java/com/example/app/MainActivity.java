package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.app.Interface.CrudEmpleadoInterface;
import com.example.app.Model.Empleado;

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
    }

    private void getAll(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhots:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<List<Empleado>> call = cruempleado.getAll();

        call.enqueue(new Callback<list<Empleado>>(){

        @Override
            public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {
                if(!response.isSuccessful()) {
                    System.out.println(response.message());
                    return;
                }
                listEmpleado = response.body();
                listEmpleado.forEach(p-> System.out.println(p.toString()));
            }

            @Override
            public void onFailure(Call<List<Empleado>> call, Throwable t){

                System.out.println(t.getMessage());
            }
        });

    }
}