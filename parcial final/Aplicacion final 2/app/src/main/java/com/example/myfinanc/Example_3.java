package com.example.myfinanc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Example_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example3);
    }

    public void onButtonClick21(View view) {
        // Código para cambiar de página
        Intent intent = new Intent(this, Learn.class);
        startActivity(intent);
    }

}