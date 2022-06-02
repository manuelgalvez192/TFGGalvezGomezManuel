package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.tfg.db.DbExecute;

public class Martes extends AppCompatActivity {

    EditText multi;
    DbExecute dbExecute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_martes);

        dbExecute = new DbExecute(Martes.this);

        multi = findViewById(R.id.tareasMartes);

        multi.setText(dbExecute.getTareas("martes", "t_martes"));
    }

    public void guardar(View view)
    {

        dbExecute.guardarTareas(multi.getText().toString(), "t_martes");

    }
}