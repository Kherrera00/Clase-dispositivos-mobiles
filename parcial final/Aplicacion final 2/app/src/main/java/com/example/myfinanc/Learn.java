package com.example.myfinanc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Learn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
    }

    public void onButtonClick15(View view) {
        // Código para cambiar de página
        Intent intent = new Intent(this, Example_1.class);
        startActivity(intent);
    }

    public void onButtonClick16(View view) {
        // Código para cambiar de página
        Intent intent = new Intent(this, Example_2.class);
        startActivity(intent);
    }
    public void onButtonClick17(View view) {
        // Código para cambiar de página
        Intent intent = new Intent(this, Example_3.class);
        startActivity(intent);
    }
    public void onButtonClick18(View view) {
        // Código para cambiar de página
        Intent intent = new Intent(this, Start.class);
        startActivity(intent);
    }
}