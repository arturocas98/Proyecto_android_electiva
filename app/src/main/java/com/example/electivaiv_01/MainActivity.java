package com.example.electivaiv_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int score = 0;
    public Preguntas[] pregunta_test = {
                    new Preguntas("¿Estamos solos en el universo?", "falso"),
                    new Preguntas("¿Pasamos la materia?", "verdadero")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}