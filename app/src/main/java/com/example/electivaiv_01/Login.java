package com.example.electivaiv_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn_aceptar = (Button)findViewById(R.id.btn_ingresar);
        Button btn_nuevo_usuario = (Button)findViewById(R.id.btn_nuevo_usuario);
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txt_usuario =(EditText) findViewById(R.id.txt_usuario);
                EditText txt_contrasena =(EditText) findViewById(R.id.txt_contrasena);
                if(!txt_usuario.equals("") && !txt_contrasena.equals("") ){
                    if (!txt_usuario.equals("admin") && !txt_contrasena.equals("123")){
                        Intent i = new Intent(Login.this,MainActivity.class);
                        i.putExtra("usuario",txt_usuario.getText().toString());
                        startActivityForResult(i,0);
                    }else{
                        Toast.makeText(Login.this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Login.this,"Campos Vacios!",Toast.LENGTH_SHORT);
                }
            }
        });
        btn_nuevo_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,Formulario.class);
                startActivityForResult(i,0);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu m){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,m);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.settings :
                Toast.makeText(this,"Configuración selected",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.salir :
                Toast.makeText(this,"Cerrando sesión espere un momento ....",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.acerca_de:
                Intent i = new Intent(Login.this,acerca_de.class);
                startActivityForResult(i,0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}