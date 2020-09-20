package com.example.electivaiv_01.utilidades;

public class Utilidades {
    // constantes y campos de la tabla usuario
    public static final String USUARIO_ID = "id ";
    public static final String TABLA_USUARIO = "usuario";
    public static final String USUARIO_NOMBRE = "nombre";
    public static final String USUARIO_APELLIDO = "apellido";
    public static final String USUARIO_TELEFONO = "telefono";
    public static final String USUARIO_EDAD = "edad";
    public static final String USUARIO_CORREO = "correo";
    public static final String USUARIO_PASSWORD = "password";
    public static final String USUARIO_FECHA = "fecha_nacimiento";
    public static final String USUARIO_SEXO= "sexo";

    /*public static final String CREAR_TABLE_USUARIO = "CREATE TABLE" +
            ""+TABLA_USUARIO+"("+USUARIO_ID+""+"INTEGER,"
            +USUARIO_NOMBRE+"TEXT,"+USUARIO_TELEFONO+"TEXT)";*/
    //public static  final String CREATE_TABLE_USUARIO = "CREATE TABLE usuario(id INTEGER  PRIMARY KEY AUTOINCREMENT, nombre TEXT,telefono TEXT)";



    public static final String CREATE_TABLE_USUARIO="CREATE TABLE " +
            ""+TABLA_USUARIO+" ( "+USUARIO_ID+" INTEGER  PRIMARY KEY AUTOINCREMENT,"+USUARIO_NOMBRE+" TEXT,"
                +USUARIO_APELLIDO+" TEXT,"+USUARIO_TELEFONO+" TEXT,"+USUARIO_EDAD+" TEXT,"+USUARIO_CORREO+" TEXT,"+USUARIO_PASSWORD+" TEXT,"
            +USUARIO_FECHA+" TEXT,"+USUARIO_SEXO+" TEXT)";
}
