package com.example.ej3pmi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ej3pmi.configuracion.SQLiteConexion;
import com.example.ej3pmi.transacciones.Personas;
import com.example.ej3pmi.transacciones.Transacciones;

import java.util.ArrayList;
import com.example.ej3pmi.MainActivity;

public class ActivityListView extends AppCompatActivity {

    //Variables Globales

    SQLiteConexion conexion;
    ListView listView;
    ArrayList<Personas> listapersonas;
    ArrayList<String> Arreglopersonas;

    Button btnActualizar;
    Button btnEliminar;
    Button btnInsertar;

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        btnActualizar = (Button)  findViewById(R.id.btnActualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnInsertar  = (Button)  findViewById(R.id.btnInsertar);


        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("nombres", Personas.getNombres());
                intent.putExtra("apellidos", Personas.getApellidos());
                intent.putExtra("edad", Personas.getEdad());
                intent.putExtra("correo", Personas.getCorreo());
                intent.putExtra("direccion", Personas.getDireccion());
                getApplicationContext().startActivity(intent);

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityListView.this);
                        builder.setMessage("¿Desea Eliminar esta Persona")
                                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        EliminarPersona();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }).show();
            }
        });

        conexion = new SQLiteConexion(this, Transacciones.NameDatebase, null, 1);
        listView = (ListView) findViewById(R.id.listView);

        ObtenerListaPersonas();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Arreglopersonas);
        listView.setAdapter(adp);
    }

    private void ActualizarPersona(){

    }



    private void EliminarPersona(){
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatebase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        try{
            db.execSQL("DELETE FROM " + Transacciones.tablapersonas + " WHERE id = '" + id + "'");

        } catch (Exception ex){
            Toast.makeText(this, "Nose pudo Eliminar la Persona", Toast.LENGTH_LONG).show();

        }finally {
            db.close();
        }

    }


    


    private void ObtenerListaPersonas(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas person = null;
        listapersonas = new ArrayList<Personas>();

        //Cursor
        Cursor cursor = db.rawQuery("SELECT * FROM personas", null);

        while (cursor.moveToNext())
        {
            person = new Personas();
            person.setId(cursor.getInt(0));
            person.setNombres(cursor.getString(1));
            person.setApellidos(cursor.getString(2));
            person.setEdad(cursor.getInt(3));
            person.setCorreo(cursor.getString(4));
            person.setDireccion(cursor.getString(5));

            listapersonas.add(person);
        }
        cursor.close();
        FillList();
    }

    private void FillList()
    {
        Arreglopersonas = new ArrayList<String>();
        for (int i = 0; i<listapersonas.size(); i++)
        {
            Arreglopersonas.add(listapersonas.get(i).getId()+ " | "+
                    listapersonas.get(i).getNombres()+ " | "+
                    listapersonas.get(i).getApellidos()+ " | "+
                    listapersonas.get(i).getEdad()+ " | "+
                    listapersonas.get(i).getCorreo()+ " | "+
                    listapersonas.get(i).getDireccion()+ " | ");
        }
    }


}