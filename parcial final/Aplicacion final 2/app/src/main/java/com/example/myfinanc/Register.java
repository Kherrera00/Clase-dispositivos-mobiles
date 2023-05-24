package com.example.myfinanc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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

public class Register extends AppCompatActivity {

    String ruta = "http://192.168.1.8:8081/";
    List<Empleado> listEmpleado;
    CrudEmpleadoInterface cruempleado;
    private String nombre;
    private String password;
    private String email;
    private String numero;

    //String idnombre,idpassword,idemail,idnumero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button miBoton = findViewById(R.id.registerBtn); // Reemplaza "miBoton" con el ID de tu botón
        miBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Empleado nuevoEmpleado = new Empleado();

                EditText nombreEditText = findViewById(R.id.nombre);
                EditText passwordEditText = findViewById(R.id.password);
                EditText emailEditText = findViewById(R.id.email);
                EditText numeroEditText = findViewById(R.id.numero);

                String nombre = nombreEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String numero = numeroEditText.getText().toString();

                // Validar campos vacíos
                if (nombre.isEmpty() || password.isEmpty() || email.isEmpty() || numero.isEmpty()) {
                    Toast.makeText(Register.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar formato de email
                if (!email.contains("@")) {
                    Toast.makeText(Register.this, "Ingrese un email válido", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar formato de nombre (solo letras)
                if (!nombre.matches("[a-z or A-Z]+")) {
                    Toast.makeText(Register.this, "Ingrese un nombre válido", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar formato de contraseña (al menos un número)
                if (!password.matches(".*\\d.*")) {
                    Toast.makeText(Register.this, "La contraseña debe contener al menos un número", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar formato de número (solo números)
                if (!numero.matches("\\d+")) {
                    Toast.makeText(Register.this, "Ingrese un número válido", Toast.LENGTH_SHORT).show();
                    return;
                }


                nuevoEmpleado.setNombre(nombre);
                nuevoEmpleado.setPassword(password);
                nuevoEmpleado.setEmail(email);
                nuevoEmpleado.setNumero(numero);
                crear(nuevoEmpleado);

                // Limpiar los campos de texto
                nombreEditText.setText("");
                passwordEditText.setText("");
                emailEditText.setText("");
                numeroEditText.setText("");

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

    public void onButtonClick3(View view) {
        // Código para cambiar de página
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }


}