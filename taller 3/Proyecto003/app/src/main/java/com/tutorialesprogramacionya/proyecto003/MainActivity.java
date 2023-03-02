package com.tutorialesprogramacionya.proyecto003;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    private EditText et1,et2;
    private TextView tv1;
    private RadioButton r1,r2,r3,r4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        tv1=findViewById(R.id.tv1);
        r1=findViewById(R.id.r1);
        r2=findViewById(R.id.r2);
        r3=findViewById(R.id.r3);
        r4=findViewById(R.id.r4);
    }

    //Este método se ejecutará cuando se presione el botón
    public void operar(View view) {
        String valor1=et1.getText().toString();
        String valor2=et2.getText().toString();
        int nro1=Integer.parseInt(valor1);
        int nro2=Integer.parseInt(valor2);

        if (nro1 > 0 && nro2 > 0 && r1.isChecked()==true) {
            int suma=nro1+nro2;
            String resu=String.valueOf(suma);
            tv1.setText(resu);
        } if (nro1 == 0 || nro2 == 0) {
            Toast.makeText(getApplicationContext(), "Coloca valores diferentes a cero, gracias", Toast.LENGTH_SHORT).show();
        }
        if (nro1 > 0 && nro2 > 0 && r2.isChecked()==true) {
            int resta=nro1-nro2;
            String resu=String.valueOf(resta);
            tv1.setText(resu);
        } if (nro1 == 0 || nro2 == 0) {
            Toast.makeText(getApplicationContext(), "Coloca valores diferentes a cero, gracias", Toast.LENGTH_SHORT).show();
        }
        if (nro1 > 0 && nro2 > 0 &&  r3.isChecked()==true) {
            int multiplicar=nro1*nro2;
            String resu=String.valueOf(multiplicar);
            tv1.setText(resu);
        } if (nro1 == 0 || nro2 == 0) {
            Toast.makeText(getApplicationContext(), "Coloca valores diferentes a cero, gracias", Toast.LENGTH_SHORT).show();
        }
        if (nro1 > 0 && nro2 > 0 &&  r4.isChecked()==true) {
            float dividir=nro2/nro1;
            String resu=String.valueOf(dividir);
            tv1.setText(resu);
        } if (nro1 == 0 || nro2 == 0) {
            Toast.makeText(getApplicationContext(), "Coloca valores diferentes a cero, gracias", Toast.LENGTH_SHORT).show();
        }
 }
}