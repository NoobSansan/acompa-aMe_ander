package com.example.proyecto_grupo_2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.proyecto_grupo_2.databinding.ActivityUserMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class UserMainActivity extends AppCompatActivity {

    private ActivityUserMainBinding bind;

    protected TextView nombreUser;
    //protected TextView apellidosUser;
    protected Bundle extras;    //"ARRAY" DE PAQUETES

    protected String nombre="";
    //protected String apellidos="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        bind = ActivityUserMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        //setContentView(R.layout.activity_user_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_content_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        bind.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                boolean fragmentTransaction = false;
                Fragment fragment = null;

                int itemID = menuItem.getItemId();
                if (itemID == R.id.nav_home){
                    //Toast.makeText(getApplicationContext(),"Menú inicio", Toast.LENGTH_LONG).show();
                    //fragmentTransaction = true;
                    Intent intent = new Intent(UserMainActivity.this, MiPerfilActivity.class);
                    startActivity(intent);
                }else if (itemID == R.id.nav_fetch){
                    //Toast.makeText(getApplicationContext(),"Menú Buscar", Toast.LENGTH_LONG).show();
                    //fragmentTransaction = true;
                    Intent intent = new Intent(UserMainActivity.this, BuscarActivity.class);
                    startActivity(intent);
                }else if (itemID == R.id.nav_contacts){
                    //Toast.makeText(getApplicationContext(),"Menú Contactos", Toast.LENGTH_LONG).show();
                    //fragmentTransaction = true;
                    Intent intent = new Intent(UserMainActivity.this, ContactosActivity.class);
                    startActivity(intent);
                }else if (itemID == R.id.nav_share){
                    //Toast.makeText(getApplicationContext(),"Menú Sobre nosotros", Toast.LENGTH_LONG).show();
                    //fragmentTransaction = true;
                    Intent intent = new Intent(UserMainActivity.this, SobreNosotrosActivity.class);
                    startActivity(intent);
                }else if (itemID == R.id.nav_web) {
                    //Toast.makeText(getApplicationContext(), "Menú Guia Usuario", Toast.LENGTH_LONG).show();
                    //fragmentTransaction = true;
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://acompaname.gitbook.io/acompaname-manual-de-usuario"));
                    startActivity(intent);

                }else if (itemID == R.id.nav_close) {
                    //Toast.makeText(getApplicationContext(), "Cerrar Sesión", Toast.LENGTH_LONG).show();
                    //fragmentTransaction = true;
                    Intent intent = new Intent(UserMainActivity.this, LoginActivity.class);
                    startActivity(intent);

                }

                //RECORRE EL STACK DEL FRAGMENT MANAGER Y LOS VA ELIMINANDO PARA LIBERAR LA MEMORIA
                if (fragmentTransaction){

                    for (int i=0;i<getSupportFragmentManager().getBackStackEntryCount();i++){

                        getSupportFragmentManager().popBackStack();

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment,fragment).commit();
                    menuItem.setCheckable(true);
                    Objects.requireNonNull(getSupportActionBar()).setTitle(menuItem.getTitle());
                    bind.main.closeDrawers();
                }

                return true;
            }
        });

        bind.navView.setCheckedItem(R.id.nav_home);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Inicio");

        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                int entries = getSupportFragmentManager().getBackStackEntryCount();
                if (entries>0) {
                    getSupportFragmentManager().popBackStack();
                }else if (entries == 0 && bind.main.isDrawerOpen(GravityCompat.START)){
                    bind.main.closeDrawers();
                }else if (entries == 0){
                    bind.main.openDrawer(GravityCompat.START);
                }

            }
        };

        nombreUser = (TextView) findViewById(R.id.textViewNombre_userMain);
        //apellidosUser = (TextView) findViewById(R.id.textViewApellidos_userMain);

        extras = getIntent().getExtras();

        assert extras != null;
        nombre = extras.getString("nombre");
        //apellidos = extras.getString("apellidos");

        nombreUser.setText(getString(R.string.bienvenida, nombre));
        //apellidosUser.setText(apellidos);

        getOnBackPressedDispatcher().addCallback(onBackPressedCallback);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind = null;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            bind.main.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //ESTO SE USA PARA INICIAR EL MENU LATERAL DE LA PANTALLA PRINCIPAL DEL USUARIO
    private void init(){
        setToolbar();
    }
    private void setToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);
         Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

}