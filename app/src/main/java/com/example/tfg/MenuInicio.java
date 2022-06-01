package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.tfg.db.DbExecute;
import com.example.tfg.db.DbHelper;

public class MenuInicio extends AppCompatActivity {

    DbExecute db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);
    }

    public void añadirEmple(View v)
    {
        Intent addEmple = new Intent(this, AnadirEmpleado.class);//lanza vista para el menu añadir empleado
        startActivity(addEmple);

    }

    public void añadirCliente(View v)
    {
        Intent crearCuenta = new Intent(this, AnadirClientes.class);
        startActivity(crearCuenta);
    }

    public void hacerPedido(View v)
    {
        Intent hacerPed = new Intent(this, HacerPedido.class);
        startActivity(hacerPed);
    }

    public void calendario(View v)
    {
        Intent calen= new Intent(this, Calendario.class);
        startActivity(calen);
    }

}