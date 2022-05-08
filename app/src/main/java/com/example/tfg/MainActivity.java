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

    DbExecute dbExecute;
    EditText nombre, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.nombre);
        pass = findViewById(R.id.pass);

        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db != null){
            System.out.println("Se creó la base de datos bien");
        }else
        {
            System.out.println("No se creó la base de datos");
        }


    }

    public void createAccount(View view){

        Intent crearCuenta = new Intent(this, createAccount.class);
        startActivity(crearCuenta);
    }

    public void iniciar(View view)
    {
        boolean seguir = false;
        dbExecute = new DbExecute(MainActivity.this);
        seguir = dbExecute.iniciar(nombre.getText().toString(), pass.getText().toString(), seguir);

        System.out.println("seguir2: " + seguir);

        if(seguir)
        {
            Intent menuInicio = new Intent(this, MenuInicio.class);
            startActivity(menuInicio);
        }else
        {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

}