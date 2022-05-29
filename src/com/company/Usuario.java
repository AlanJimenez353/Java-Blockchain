package com.company;

import java.io.Serializable;

public class Usuario implements Serializable {

    private static int generalId;
    private int id;
    private String mail;
    private String contraseña;
    private String dni;
    private String nombre;


    public Usuario( String nombre,String mail, String contraseña, String dni) {
        setGeneralId();
        this.id = generalId;
        this.mail = mail;
        this.contraseña = contraseña;
        this.dni = dni;
        this.nombre=nombre;
    }
    public Usuario(){

    }

    public static int getGeneralId() {
        return generalId;
    }

    public static void setGeneralId() {
        generalId = generalId+1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
