package com.example.electivaiv_01;

public class Preguntas {

    public String preguntas;
    public String respuestas;

    public Preguntas(String p, String r){
        this.preguntas = p;
        this.respuestas = r;
    }

    public String getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(String preguntas) {
        this.preguntas = preguntas;
    }

    public String getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String respuestas) {
        this.respuestas = respuestas;
    }
}
