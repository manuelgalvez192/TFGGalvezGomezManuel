package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tfg.db.DbExecute;
import com.example.tfg.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    //declaras variables
    DbExecute dbExecute;
    EditText cod, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cod = findViewById(R.id.nombre);//inicias variables
        pass = findViewById(R.id.pass);

        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();//inicias la base de datos
        if(db != null){
            System.out.println("Se creó la base de datos bien");//para saber si se ha creado o no
        }else
        {
            System.out.println("No se creó la base de datos");
        }


    }

    public void createAccount(View view){

        Intent crearCuenta = new Intent(this, createAccount.class);//lanza actividad crear empleado
        startActivity(crearCuenta);
    }

    public void iniciar(View view)
    {
        boolean seguir = false;//variable para comprobar si lanzar actividad
        dbExecute = new DbExecute(MainActivity.this);//instancias clase para hacer ejecuciones de base da datos
        seguir = dbExecute.iniciar(cod.getText().toString(), pass.getText().toString(), seguir);//lanza metodo que devuelve valor a boolean

        System.out.println("seguir: " + seguir);

        if(seguir)//si true
        {
            Intent menuInicio = new Intent(this, MenuInicio.class);//lanza actividad
            startActivity(menuInicio);
        }else//si false mensaje de error
        {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

}