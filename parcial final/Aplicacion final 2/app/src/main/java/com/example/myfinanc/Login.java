package com.example.myfinanc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myfinanc.Interface.CrudEmpleadoInterface;
import com.example.myfinanc.Model.Empleado;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.myfinanc.Interface.CrudEmpleadoInterface;
import com.example.myfinanc.Model.Empleado;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.atomic.LongAccumulator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Login extends AppCompatActivity {

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
        setContentView(R.layout.activity_login);

        getAll();


        Button loginButton = findViewById(R.id.Start_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailEditText = findViewById(R.id.temail);
                EditText passwordEditText = findViewById(R.id.tepassword);

                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                validateLogin(email, password);
            }
        });



    }



    private void validateLogin(String email, String password) {
        boolean isValid = false;


        for (Empleado empleado : listEmpleado) {
            if (empleado.getEmail().equals(email) && empleado.getPassword().equals(password)) {
                isValid = true;
                break;
            }
        }

        if (isValid) {
            // Login válido, realizar acciones correspondientes
            Toast.makeText(Login.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login.this, Start.class);
            startActivity(intent);

            // Realiza acciones adicionales después del inicio de sesión exitoso


        } else {
            // Login inválido, mostrar mensaje de error
            Toast.makeText(Login.this, "Email o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            // Realiza acciones adicionales después de un inicio de sesión fallido
        }
    }


    private boolean validateFields(String email, String password) {
        // Validar que ambos campos tengan datos
        if (email.isEmpty() || password.isEmpty()) {
            return false;
        }

        // Validar que el email contenga una arroba
        if (!email.contains("@")) {
            return false;
        }

        // Validar que la contraseña contenga al menos un número
        boolean containsNumber = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                containsNumber = true;
                break;
            }
        }
        if (!containsNumber) {
            return false;
        }

        return true;

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

    public void onButtonClick4(View view) {
        // Código para cambiar de página
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void onButtonClick5(View view) {
        // Código para cambiar de página
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

}