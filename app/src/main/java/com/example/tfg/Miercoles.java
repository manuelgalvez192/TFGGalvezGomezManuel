package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.tfg.db.DbExecute;

public class Miercoles extends AppCompatActivity {

    EditText multi;
    DbExecute dbExecute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miercoles);

        dbExecute = new DbExecute(Miercoles.this);

        multi = findViewById(R.id.tareasMiercoles);

        multi.setText(dbExecute.getTareas("miercoles"));
    }

    public void guardar(View view)
    {
        dbExecute.guardarTareas("miercoles", multi.getText().toString());

    }
}