package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.tfg.db.DbExecute;

public class createAccount extends AppCompatActivity {

    DbExecute dbExecute;
    Context context;
    EditText cod, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        cod = findViewById(R.id.nombreUsuario);
        pass = findViewById(R.id.contraseñaUsuario);
    }


    public void crearCuenta(View view)
    {
        dbExecute = new DbExecute(createAccount.this);

        dbExecute.createAccount(cod.getText().toString(), pass.getText().toString());//llamas a la funcion para añadir los datos a la base de datos
    }


}