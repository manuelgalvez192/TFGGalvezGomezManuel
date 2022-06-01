package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class Lunes extends AppCompatActivity {

    EditText multi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunes);

        multi = findViewById(R.id.tareasLunes);

    }
}