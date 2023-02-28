package com.example.ej3pmi.transacciones;

public class Personas {

    private Integer id;
    private static String nombres;
    private static String apellidos;
    private static Integer edad;
    private static String correo;
    private static String direccion;

    public Personas(Integer id, String nombres, String apellidos, Integer edad, String correo, String direccion) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.correo = correo;
        this.direccion = direccion;
    }

    public Personas(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public static String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public static Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public static String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public static String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }





}
