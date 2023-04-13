package com.example.app.Interface;

import com.example.app.Model.Empleado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CrudEmpleadoInterface {

    @GET("/consultarAll")
    Call<List<Empleado>> getAll();
}
