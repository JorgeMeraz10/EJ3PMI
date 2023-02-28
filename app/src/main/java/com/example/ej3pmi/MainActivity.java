package com.example.ej3pmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ej3pmi.configuracion.SQLiteConexion;
import com.example.ej3pmi.transacciones.Transacciones;

public class MainActivity extends AppCompatActivity {

    //Variables Globales
    EditText nombres, apellidos, edad, correo, direccion;

    Button btnGuardar;
    Button btnVer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombres = (EditText) findViewById(R.id.nombres);
        apellidos = (EditText) findViewById(R.id.apellidos);
        edad = (EditText) findViewById(R.id.edad);
        correo = (EditText) findViewById(R.id.correo);
        direccion = (EditText) findViewById(R.id.direccion);

        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnVer = (Button) findViewById(R.id.btnVer) ;

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarPersona();
            }
        });

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(),
                        ActivityListView.class);
                startActivity(intent);


            }
        });
    }

    private void AgregarPersona()
    {
        try{
            SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatebase, null, 1);
            SQLiteDatabase db = conexion.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("nombres", nombres.getText().toString());
            valores.put("apellidos", apellidos.getText().toString());
            valores.put("edad", edad.getText().toString());
            valores.put("correo", correo.getText().toString());
            valores.put("direccion", direccion.getText().toString());

            Long Resultado = db.insert(Transacciones.tablapersonas, "id", valores);
            Toast.makeText(this, Resultado.toString(), Toast.LENGTH_SHORT).show();

            Clear();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Nose pudo insertar el dato", Toast.LENGTH_LONG).show();
        }
    }





    private void Clear(){
        nombres.setText(Transacciones.Empty);
        apellidos.setText(Transacciones.Empty);
        edad.setText(Transacciones.Empty);
        correo.setText(Transacciones.Empty);
        direccion.setText(Transacciones.Empty);
    }

    private void MostrarCliente()
    {
        String mensaje = nombres.getText().toString()+
                " | " + apellidos.getText().toString()+
                " | "+ edad.getText().toString()+
                " | "+ correo.getText().toString()+
                " | "+direccion.getText().toString();

        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}