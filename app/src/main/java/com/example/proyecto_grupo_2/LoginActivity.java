package com.example.proyecto_grupo_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    protected LinearLayout layoutLogin;
    protected ImageView imgLogin;
    protected EditText editTextUsuario;
    protected EditText editTextPassword;
    protected Button btnIniciarSesion;

    protected Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        layoutLogin = (LinearLayout) findViewById(R.id.layout_login);
        imgLogin = (ImageView) findViewById(R.id.img_login);
        editTextUsuario = (EditText) findViewById(R.id.editTextUsuario_login);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword_login);
        btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion_login);
        btnVolver = (Button) findViewById(R.id.volver_login2);

        //BTN INICIAR SESION

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //CONEXIÓN A LA BBDD PARA COMPROBAR SI EL USUARIO Y LA CONTRASEÑA SON CORRECTOS

                final String correoUsuario = editTextUsuario.getText().toString();
                final String passwordUsuario = editTextPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){

                                String nombre = jsonResponse.getString("nombre");
                                String apellidos = jsonResponse.getString("apellidos");
                                String ciudad = jsonResponse.getString("ciudad");
                                String hospital = jsonResponse.getString("hospital");
                                String enfermedad = jsonResponse.getString("enfermedad");
                                String descripcion = jsonResponse.getString("descripcion");
                                String email = jsonResponse.getString("email");
                                String telefono = jsonResponse.getString("telefono");
                                String pw = jsonResponse.getString("pw");

                                Intent pasarPantalla = new Intent(LoginActivity.this, UserMainActivity.class);
                                pasarPantalla.putExtra("nombre",nombre);
                                pasarPantalla.putExtra("apellidos",apellidos);
                                pasarPantalla.putExtra("ciudad",ciudad);
                                pasarPantalla.putExtra("hospital",hospital);
                                pasarPantalla.putExtra("enfermedad",enfermedad);
                                pasarPantalla.putExtra("descripcion",descripcion);
                                pasarPantalla.putExtra("email",email);
                                pasarPantalla.putExtra("telefono",telefono);
                                pasarPantalla.putExtra("pw",pw);
                                finish();
                                startActivity(pasarPantalla);

                            }else{

                                Toast.makeText(LoginActivity.this, getString(R.string.toastErrorLoginUsuario), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                LoginRequest loginRequest = new LoginRequest(correoUsuario,passwordUsuario,responseListener);

                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);

            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pasarpantalla = new Intent(LoginActivity.this, BienvenidaActivity.class);
                startActivity(pasarpantalla);
            }
        });

    }
}