package com.example.proyecto_grupo_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class UserMainActivity extends AppCompatActivity {

    protected TextView nombreUser;
    protected TextView apellidosUser;
    protected Bundle extras;    //"ARRAY" DE PAQUETES

    protected String nombre="";
    protected String apellidos="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nombreUser = (TextView) findViewById(R.id.textViewNombre_userMain);
        apellidosUser = (TextView) findViewById(R.id.textViewApellidos_userMain);

        extras = getIntent().getExtras();

        nombre = extras.getString("nombre");
        apellidos = extras.getString("apellidos");

        nombreUser.setText(nombre);
        apellidosUser.setText(apellidos);

    }
}