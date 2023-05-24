package com.example.myfinanc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    public void onButtonClick1(View view) {
        // Código para cambiar de página
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void onButtonClick2(View view) {
        // Código para cambiar de página
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

}