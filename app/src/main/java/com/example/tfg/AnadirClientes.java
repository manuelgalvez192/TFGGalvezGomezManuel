package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.tfg.db.DbExecute;

public class AnadirClientes extends AppCompatActivity {

    DbExecute dbExecute;
    EditText cod, nombre, apellidos, dir, tlf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_clientes);

        cod = findViewById(R.id.cod);
        nombre = findViewById(R.id.nom);
        apellidos = findViewById(R.id.ape);
        dir = findViewById(R.id.dir);
        tlf = findViewById(R.id.tlf);
    }


    public void crearClie(View view)
    {
        dbExecute = new DbExecute(AnadirClientes.this);

        dbExecute.añadirClie(cod.getText().toString(), nombre.getText().toString(), apellidos.getText().toString(),
                dir.getText().toString(), tlf.getText().toString());
    }
}