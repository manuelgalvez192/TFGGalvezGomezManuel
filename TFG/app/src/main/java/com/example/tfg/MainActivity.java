package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.tfg.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

}