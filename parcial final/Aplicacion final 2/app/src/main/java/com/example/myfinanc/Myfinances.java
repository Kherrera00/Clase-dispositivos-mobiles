package com.example.myfinanc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Myfinances extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfinances);
    }

    public void onButtonClick6(View view) {
        // Código para cambiar de página
        Intent intent = new Intent(this, Start.class);
        startActivity(intent);
    }
}