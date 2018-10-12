package com.example.moviles.bienestaricesi.model;

public class Usuario {
    private String uid;
    private String usuario;
    private String correo;
    private String nombre;


    public Usuario() {
    }

    public Usuario(String uid, String usuario, String correo, String nombre) {

        this.uid = uid;
        this.usuario = usuario;
        this.correo = correo;
        this.nombre = nombre;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
