package com.example.proyecto_grupo_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Registro1Activity extends AppCompatActivity {

    protected TextView cabeceraRegistro1;
    protected LinearLayout layoutRegistro1;
    protected EditText nombreRegistro1;
    protected EditText apellidosRegistro1;
    protected EditText ciudadRegistro1;
    protected EditText hospitalRegistro1;
    protected EditText enfermedadRegistro1;
    protected EditText breveDescripcionRegistro1;
    protected Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cabeceraRegistro1 = (TextView) findViewById(R.id.textViewCabecera_Registro1);
        layoutRegistro1 = (LinearLayout) findViewById(R.id.linearLayout_registro2);
        nombreRegistro1 = (EditText) findViewById(R.id.editTextNombre_registro1);
        apellidosRegistro1 = (EditText) findViewById(R.id.editTextApellidos_registro1);
        ciudadRegistro1 = (EditText) findViewById(R.id.editTextCiudad_registro1);
        hospitalRegistro1 = (EditText) findViewById(R.id.editTextHospital_registro1);
        enfermedadRegistro1 = (EditText) findViewById(R.id.editTextEnfermedad_registro1);
        breveDescripcionRegistro1 = findViewById(R.id.editTextBreveDescripcion_registro1);
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente_registro1);


        //PASAR A LA SIGUIENTE PANTALLA DEL REGISTRO
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pasarPantalla = new Intent(Registro1Activity.this, Registro2Activity.class);
                finish();
                startActivity(pasarPantalla);

            }
        });

    }
}