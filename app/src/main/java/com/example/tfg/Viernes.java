package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.tfg.db.DbExecute;

public class Viernes extends AppCompatActivity {

    EditText multi;
    DbExecute dbExecute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viernes);

        dbExecute = new DbExecute(Viernes.this);

        multi = findViewById(R.id.tareasViernes);

        String tarea = dbExecute.getTareas("viernes");

        multi.setText(tarea);
    }

    public void guardar(View view)
    {
        dbExecute.guardarTareas("viernes", multi.getText().toString());

    }
}