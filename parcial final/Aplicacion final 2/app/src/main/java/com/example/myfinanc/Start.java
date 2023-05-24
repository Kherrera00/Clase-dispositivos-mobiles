package com.example.myfinanc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void onButtonClick7(View view) {
        // Código para cambiar de página
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void onButtonClick8(View view) {
        // Código para cambiar de página
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    public void onButtonClick9(View view) {
        // Código para cambiar de página
        Intent intent = new Intent(this, Tips.class);
        startActivity(intent);
    }

    public void onButtonClick10(View view) {
        // Código para cambiar de página
        Intent intent = new Intent(this, Myfinances.class);
        startActivity(intent);
    }

    public void onButtonClick11(View view) {
        // Código para cambiar de página
        Intent intent = new Intent(this, Learn.class);
        startActivity(intent);
    }

    public void onButtonClick12(View view) {
        // Código para cambiar de página
        Intent intent = new Intent(this, Reports.class);
        startActivity(intent);
    }

}