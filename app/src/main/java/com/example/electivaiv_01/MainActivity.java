package com.example.electivaiv_01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txt_usuario;
    EditText txt_contrasena;
    public int score = 0;
    SharedPreferences sharedPreferences;
    public Preguntas[] pregunta_test = {
                    new Preguntas("¿Estamos solos en el universo?", "falso"),
                    new Preguntas("¿Pasamos la materia?", "verdadero")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SHARED PREFERENCES
        sharedPreferences = getSharedPreferences("credenciales",MODE_PRIVATE);
        txt_usuario = findViewById(R.id.txt_login_user);
        txt_contrasena =findViewById(R.id.txt_login_contra);

        Button btnVerdadero = (Button)findViewById(R.id.btn_verdadero);
        Button btnFalso = (Button)findViewById(R.id.btn_falso);

        TextView txtpregunta = (TextView)findViewById(R.id.txt_pregunta);
        final TextView txtpuntaje = (TextView)findViewById(R.id.txt_puntaje);

        Preguntas pl = pregunta_test[0];
        txtpregunta.setText(pl.getPreguntas());

        btnVerdadero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = score + 100;
                txtpuntaje.setText(String.valueOf(score));
            }
        });

        btnFalso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score = score - 100;
                txtpuntaje.setText(String.valueOf(score));
            }
        });
    }

    //metodo para llamar a un menu
    public boolean onCreateOptionsMenu(Menu m){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,m);
        return true;
    }
    //metodo para decirle q accion realize cada item
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.settings :
                Toast.makeText(this,"Configuración selected",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.salir :
                alertLogout();
                return true;

            case R.id.acerca_de:
                Intent i = new Intent(MainActivity.this,acerca_de.class);
                startActivityForResult(i,0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void logout(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        Toast.makeText(this,"Cerrando sesión espere un momento ....",Toast.LENGTH_SHORT).show();
        finish();

    }

    public void alertLogout(){

        final CharSequence [] opciones ={"SI","NO"};
        final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Desea salir de la sesión?");
        alert.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("SI")){
                    logout();
                }else{
                    dialogInterface.dismiss();
                }
            }
        });

        alert.show();

    }
}