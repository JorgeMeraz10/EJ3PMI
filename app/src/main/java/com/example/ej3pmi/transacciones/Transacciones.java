package com.example.ej3pmi.transacciones;

public class Transacciones {

    //Nombre de la base de datos

    public static final String NameDatebase = "E3PM";

    //Tabla de la base de datos
    public static final String tablapersonas = "personas";

    //Transacciones de la base de datos E3PM//
    public static final String CreateTBPersonas=
            "CREATE TABLE personas (id INTEGER PRIMARY KEY AUTOINCREMENT, nombres TEXT, "+
                    "apellidos TEXT, edad INTEGER, correo TEXT, direccion TEXT)";

    public static final String DropTablePersonas = "DROP TABLE IF EXISTS personas";

    //Helpers
    public  static final String Empty = "";
}
