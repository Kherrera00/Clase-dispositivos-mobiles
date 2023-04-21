package com.example.app.Interface;

import com.example.app.Model.Empleado;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CrudEmpleadoInterface {

    @GET("/consultarAll")
    Call<List<Empleado>> getAll();

    @DELETE("/nombre/{id}")
    Call<Void> eliminar(@Path("id") int idEmpleado);
}
