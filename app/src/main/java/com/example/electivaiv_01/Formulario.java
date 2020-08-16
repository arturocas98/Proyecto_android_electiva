package com.example.electivaiv_01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class Formulario extends AppCompatActivity {
    private int dia,mes,anio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Button btn_aceptar = (Button)findViewById(R.id.btn_ingresar);
        final Button btn_calendar = (Button)findViewById(R.id.btn_calendar);
        final EditText txt_calendar = (EditText) findViewById(R.id.txt_calendar);
        final Spinner sp_lugar = (Spinner) findViewById(R.id.sp_lugar);
        String[]opciones ={"Guayaquil","Quito","Cuenca"};
        ArrayAdapter<String> a = new ArrayAdapter<String>(Formulario.this,android.R.layout.simple_spinner_item,opciones);
        sp_lugar.setAdapter(a);
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txt_nombre =(EditText) findViewById(R.id.txt_nombre);
                EditText txt_apellido =(EditText) findViewById(R.id.txt_apellido);
                EditText txt_telefono =(EditText) findViewById(R.id.txt_telefono);
                EditText txt_edad =(EditText) findViewById(R.id.txt_edad);
                EditText txt_correo =(EditText) findViewById(R.id.txt_correo);
                EditText txt_usuario =(EditText) findViewById(R.id.txt_usuario);
                EditText txt_contrasena =(EditText) findViewById(R.id.txt_contrasena);
//                Toast toast = Toast.makeText(getApplicationContext(), "Nombre: " + txt_nombre.getText().toString()+""+", Apellido:"
//                        +txt_apellido.getText().toString(), Toast.LENGTH_SHORT);


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
            }
        });

        btn_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });
    }
}