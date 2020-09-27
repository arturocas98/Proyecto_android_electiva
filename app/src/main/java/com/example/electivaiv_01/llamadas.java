package com.example.electivaiv_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.telecom.Call;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class llamadas extends AppCompatActivity {



    private static final int CODIGO_SOLICITUD_PERMISO = 1;
    private Activity activity;
    Button btn_llamadas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamadas);
        activity = this;
        btn_llamadas = (Button) findViewById(R.id.btn_mostrar_llamadas);
        btn_llamadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarLlamadas(v);
            }
        });
    }

    public void mostrarLlamadas(View v)
    {
        //Verificar si ya estan dado los permisos
        //Sino se solicitan
        if (chequearStatusPermiso()){
            consultarCPLlamadas();
        } else{
            solicitarPermiso();
        }
    }

    public void solicitarPermiso()
    {


        boolean solicitarPermisoRCL = ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CALL_LOG);
        boolean solicitarPermisoWCL = ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_CALL_LOG);

        if (solicitarPermisoRCL && solicitarPermisoWCL) {
            Toast.makeText(llamadas.this, "Los permisos fueron otorgados", Toast.LENGTH_SHORT).show();
        }
        else{
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG}, CODIGO_SOLICITUD_PERMISO);
        }

    }

    public boolean chequearStatusPermiso()
    {
        boolean permisoRealCallLog = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED;
        boolean permisoWriteCallLog = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALL_LOG) == PackageManager.PERMISSION_GRANTED;

        if (permisoRealCallLog && permisoWriteCallLog){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CODIGO_SOLICITUD_PERMISO:
                if (chequearStatusPermiso()){
                    Toast.makeText(llamadas.this, "Ya está activo el permiso", Toast.LENGTH_SHORT).show();
                    consultarCPLlamadas();
                }else{
                    Toast.makeText(llamadas.this, "No se activó el permiso", Toast.LENGTH_SHORT).show();
                }
        }
    }

    public void consultarCPLlamadas()
    {
        TextView tvLlamadas = (TextView) findViewById(R.id.tvLlamadas);
        tvLlamadas.setText("");
        Uri direccionUriLlamadas = CallLog.Calls.CONTENT_URI;

        //Numero, fecha, tipo, duración
        String[] campos = {
                CallLog.Calls.CACHED_NAME,
                CallLog.Calls.NUMBER,
                CallLog.Calls.DATE,
                CallLog.Calls.TYPE,
                CallLog.Calls.DURATION
        };

        ContentResolver contentResolver = getContentResolver();
        Cursor registros = contentResolver.query(direccionUriLlamadas,campos,null,null, CallLog.Calls.DATE + " DESC");

        while (registros.moveToNext()){
            //Se obtiene los datos a partir del indice de la columna.
            String nombre = registros.getString(registros.getColumnIndex(campos[0]));
            String numero   = registros.getString(registros.getColumnIndex(campos[1]));
            Long fecha      = registros.getLong(registros.getColumnIndex(campos[2]));
            int tipo        = registros.getInt(registros.getColumnIndex(campos[3]));
            String duracion = registros.getString(registros.getColumnIndex(campos[4]));;

            String tipoLlamada = "";
            //Validacion del tipo de llamada
            switch (tipo){
                case CallLog.Calls.INCOMING_TYPE:
                    tipoLlamada = "Llamada Entrada";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    tipoLlamada = "Llamada Perdida";
                    break;
                case CallLog.Calls.OUTGOING_TYPE:
                    tipoLlamada = "Llamada Salida";
                    break;
                default:
                    tipoLlamada = "Llamada Desconocida";

            }

            String detalle = "Nombre:"+nombre+"\n"+ "Numero: " + numero + "\n" +
                    "Fecha: "  +  DateFormat.format("dd/mm/yy k:mm",fecha) + "\n" +
                    "Tipo: " + tipoLlamada + "\n" +
                    "Duración: " + duracion +"s. \n\n";

            tvLlamadas.append(detalle);
        }

    }
}