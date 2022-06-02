package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.tfg.db.DbExecute;

public class Jueves extends AppCompatActivity {

    EditText multi;
    DbExecute dbExecute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jueves);

        dbExecute = new DbExecute(Jueves.this);

        multi = findViewById(R.id.tareasJueves);

        multi.setText(dbExecute.getTareas("jueves"));
    }

    public void guardar(View view)
    {
        dbExecute.guardarTareas("jueves", multi.getText().toString());

    }
}