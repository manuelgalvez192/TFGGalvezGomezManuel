package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.tfg.db.DbExecute;

import java.util.ArrayList;

public class HacerPedido extends AppCompatActivity {

    DbExecute dbExecute;
    Spinner spEmples, spClie, spFab, spProd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacer_pedido);

        añadirEmples();
        añadirClie();
        añadirFab();
        añadirProd();
    }

    public void añadirEmples(){

        spEmples = findViewById(R.id.spinnerEmple);
        dbExecute = new DbExecute(HacerPedido.this);
        ArrayList<String> arr = new ArrayList<String>();

        arr = dbExecute.selectEmples();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, arr);

        spEmples.setAdapter(adapter);
    }

    public void añadirClie(){

        spClie = findViewById(R.id.spinnerClie);
        dbExecute = new DbExecute(HacerPedido.this);
        ArrayList<String> arr = new ArrayList<String>();

        arr = dbExecute.selectClie();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, arr);

        spClie.setAdapter(adapter);
    }

    public void añadirFab(){

        spFab = findViewById(R.id.spinnerFab);
        dbExecute = new DbExecute(HacerPedido.this);
        ArrayList<String> arr = new ArrayList<String>();

        arr = dbExecute.selectFab();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, arr);

        spFab.setAdapter(adapter);
    }

    public void añadirProd(){

        spProd = findViewById(R.id.spinnerProd);
        dbExecute = new DbExecute(HacerPedido.this);
        ArrayList<String> arr = new ArrayList<String>();

        arr = dbExecute.selectProd();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, arr);

        spProd.setAdapter(adapter);
    }
}