package com.example.electivaiv_01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.electivaiv_01.utilidades.Utilidades;

import java.util.Calendar;

public class Formulario extends AppCompatActivity {
    private int dia,mes,anio;
    EditText txt_nombre;
    EditText txt_apellido;
    EditText txt_telefono;
    EditText txt_edad;
    EditText txt_correo;
    EditText txt_usuario;
    EditText txt_contrasena;
    EditText txt_calendar;
    Button btn_consultar;
    Button btn_calendar;
    Button btn_registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);


        final Button btn_calendar = (Button)findViewById(R.id.btn_calendar);
        txt_calendar = (EditText) findViewById(R.id.txt_calendar);
        final Spinner sp_lugar = (Spinner) findViewById(R.id.sp_lugar);
        String[]opciones ={"Guayaquil","Quito","Cuenca"};
        ArrayAdapter<String> a = new ArrayAdapter<String>(Formulario.this,android.R.layout.simple_spinner_item,opciones);
        sp_lugar.setAdapter(a);

        btn_registrar = (Button)findViewById(R.id.btn_registrar);
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txt_nombre =(EditText) findViewById(R.id.txt_nombre);
                txt_apellido =(EditText) findViewById(R.id.txt_apellido);
                txt_telefono =(EditText) findViewById(R.id.txt_telefono);
                txt_edad =(EditText) findViewById(R.id.txt_edad);
                txt_correo =(EditText) findViewById(R.id.txt_correo);
                txt_usuario =(EditText) findViewById(R.id.txt_usuario);
                txt_contrasena =(EditText) findViewById(R.id.txt_contrasena);
//                Toast toast = Toast.makeText(getApplicationContext(), "Nombre: " + txt_nombre.getText().toString()+""+", Apellido:"
//                        +txt_apellido.getText().toString(), Toast.LENGTH_SHORT);
                registrarUsuariosSql();
                Toast toast = Toast.makeText(getApplicationContext(),"Registro Exitoso!",Toast.LENGTH_SHORT);
                toast.show();

            }
        });



        Button btn_cancelar = (Button)findViewById(R.id.btn_cancelar);
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Cancelando el registro ....... " , Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(Formulario.this,Login.class);
                startActivityForResult(intent,0);
            }
        });

        btn_consultar = (Button) findViewById(R.id.btn_consultar);
        btn_consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Formulario.this,consultar.class);
                startActivityForResult(i,0);
            }
        });


    }

    /*public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_registrar:
                txt_nombre =(EditText) findViewById(R.id.txt_nombre);
                txt_apellido =(EditText) findViewById(R.id.txt_apellido);
                txt_telefono =(EditText) findViewById(R.id.txt_telefono);
                txt_edad =(EditText) findViewById(R.id.txt_edad);
                txt_correo =(EditText) findViewById(R.id.txt_correo);
                txt_usuario =(EditText) findViewById(R.id.txt_usuario);
                txt_contrasena =(EditText) findViewById(R.id.txt_contrasena);
//                Toast toast = Toast.makeText(getApplicationContext(), "Nombre: " + txt_nombre.getText().toString()+""+", Apellido:"
//                        +txt_apellido.getText().toString(), Toast.LENGTH_SHORT);
                registrarUsuariosSql();
                Toast toast = Toast.makeText(getApplicationContext(),"Registro Exitoso!",Toast.LENGTH_SHORT);
                toast.show();
                break;

            case R.id.btn_calendar:

                if (view == btn_calendar){
                    final Calendar c= Calendar.getInstance();
                    dia = c.get(Calendar.DAY_OF_MONTH);
                    mes = c.get(Calendar.MONTH);
                    anio= c.get(Calendar.YEAR);

                    DatePickerDialog dp = new DatePickerDialog(Formulario.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                            txt_calendar.setText(d+"/"+(m+1)+"/"+y);
                        }
                    },dia,mes,anio);
                    dp.show();
                }
                break;
            case R.id.btn_cancelar:
                Toast t = Toast.makeText(getApplicationContext(), "Cancelando el registro ....... " , Toast.LENGTH_SHORT);
                t.show();
                Intent intent = new Intent(Formulario.this,Login.class);
                startActivityForResult(intent,0);
                break;
            case R.id.btn_consultar:
                Intent i = new Intent(Formulario.this,Login.class);
                startActivityForResult(i,0);

                break;
        }
    }*/

    public void registrarUsuarioBD(){
        MyOpenHelper conn = new MyOpenHelper(this,"bd_usuarios",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.USUARIO_NOMBRE,txt_nombre.getText().toString());
        values.put(Utilidades.USUARIO_TELEFONO,txt_telefono.getText().toString());
        Long usuario_res = db.insert(Utilidades.TABLA_USUARIO,Utilidades.USUARIO_NOMBRE,values);

        Toast.makeText(this, "ID de usuario registrado:"+usuario_res, Toast.LENGTH_SHORT).show();
        db.close();
    }

    private void registrarUsuariosSql() {
        MyOpenHelper conn=new MyOpenHelper(this,"bd_usuarios",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        /*String insert="INSERT INTO "+Utilidades.TABLA_USUARIO
                +" ( " +Utilidades.CAMPO_ID+","+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+")" +
                " VALUES ("+campoId.getText().toString()+", '"+campoNombre.getText().toString()+"','"
                +campoTelefono.getText().toString()+"')";*/

        String insert="INSERT INTO "+Utilidades.TABLA_USUARIO
                +" ( " +Utilidades.USUARIO_NOMBRE+","+Utilidades.USUARIO_APELLIDO+","+Utilidades.USUARIO_TELEFONO+" ," +
                ""+Utilidades.USUARIO_EDAD+","+Utilidades.USUARIO_CORREO+" ,"+Utilidades.USUARIO_PASSWORD+","+Utilidades.USUARIO_FECHA+")" +
                " VALUES ('"+txt_nombre.getText().toString()+"', '"+txt_apellido.getText().toString()+"','"+txt_telefono.getText().toString()+"'," +
                "'"+txt_edad.getText().toString()+"','"+txt_correo.getText().toString()+"','"+txt_contrasena.getText().toString()+"','"+txt_calendar.getText().toString()+"')";

        db.execSQL(insert);
        Toast.makeText(this, "USUARIO REGISTRADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();

        db.close();
    }
}