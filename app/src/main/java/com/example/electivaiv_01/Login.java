package com.example.electivaiv_01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

public class Login extends AppCompatActivity {
    EditText txt_usuario;
    EditText txt_contrasena;
    TextView txt_prueba;
    SharedPreferences p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cargarPreferencia();
        comprobarDatos();

        txt_usuario = findViewById(R.id.txt_login_user);
        txt_contrasena =findViewById(R.id.txt_login_contra);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_ingresar:
                alert();

                break;

            case R.id.btn_nuevo_usuario:

                Intent i = new Intent(Login.this,Formulario.class);
                startActivityForResult(i,0);
                break;
        }
    }

    public void validarLogin(){
        guardarPreferencia();
        if(txt_usuario.getText().toString().length() > 0 && txt_contrasena.getText().toString().length() > 0 ){
            if (txt_usuario.getText().toString().equals("Superadmin") && txt_contrasena.getText().toString().equals("123")){

                Intent intent = new Intent(Login.this,MainActivity.class);
                intent.putExtra("usuario",txt_usuario.getText().toString());
                startActivityForResult(intent,0);
            }else{
                Toast.makeText(Login.this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(Login.this,"Campos Vacios!",Toast.LENGTH_SHORT).show();
        }
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



    public void alert(){

        final CharSequence [] opciones ={"SI","NO"};
        final AlertDialog.Builder alert = new AlertDialog.Builder(Login.this);
        alert.setTitle("Desea mantener la sesión activa?");
        alert.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("SI")){
                    validarLogin();
                }else{
                    dialogInterface.dismiss();
                }
            }
        });

        alert.show();

    }
    //metodo para guardar las preferencias del usuario
    public void guardarPreferencia(){

         p = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        //String usuario_p = txt_usuario.getText().toString();
        //String contrasena_p = txt_contrasena.getText().toString();

        SharedPreferences.Editor editor = p.edit();
        editor.putString("usuario",txt_usuario.getText().toString());
        editor.putString("clave",txt_contrasena.getText().toString());
        editor.commit();

    }

    public void cargarPreferencia(){
        p = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String usuario_tmp = p.getString("usuario",null);
        String contrasena_tmp =p.getString("clave",null);

        txt_usuario =findViewById(R.id.txt_login_user);
        txt_usuario.setText(usuario_tmp);


        txt_contrasena =findViewById(R.id.txt_login_contra);
        txt_contrasena.setText(contrasena_tmp);
    }

    public void comprobarDatos(){
        p = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String usuario_tmp = p.getString("usuario",null);
        if (usuario_tmp !=null){
            Intent intent = new Intent(Login.this,MainActivity.class);
            startActivity(intent);
        }
    }
}