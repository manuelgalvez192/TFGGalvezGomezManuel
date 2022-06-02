package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.tfg.db.DbExecute;

public class Lunes extends AppCompatActivity {

    //atributos
    EditText multi;
    DbExecute dbExecute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunes);

        dbExecute = new DbExecute(Lunes.this);

        multi = findViewById(R.id.tareasLunes);//lo referencio

        //recojo el valor que haya en la base de datos para que se obtenga lo ya guardado
        multi.setText(dbExecute.getTareas("lunes"));
    }

    public void guardar(View view)
    {

        //guardo en la base de datos lo que hayas añadido
        dbExecute.guardarTareas("lunes", multi.getText().toString());

    }
}