package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.tfg.db.DbExecute;
import com.example.tfg.db.DbHelper;

import java.util.ArrayList;

public class AnadirEmpleado extends AppCompatActivity {

    DbExecute dbExecute;
    Spinner spCargo, spTaller, spCiu;
    String cargo, ciu, taller;
    EditText nombre, apellidos, codEmple, contraseña;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_empleado);

        añadirCargos();
        añadirTalleres();
        añadirCiudades();

        spCargo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cargo = (String) spCargo.getSelectedItem();
                System.out.println("item selec " + cargo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //nada
            }
        });

        spTaller.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                taller = (String) spTaller.getSelectedItem();
                System.out.println("item selec " + taller);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //nada
            }
        });

        spCiu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ciu = (String) spCiu.getSelectedItem();
                System.out.println("item selec " + ciu);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //nada
            }
        });

        nombre = findViewById(R.id.editTextTextPersonName);
        apellidos = findViewById(R.id.editTextTextPersonApellidos);
        codEmple = findViewById(R.id.editTextTextPersonCodEmple);
        contraseña = findViewById(R.id.editTextTextPassword);
    }


    public void añadirCargos(){

        spCargo = findViewById(R.id.spinnerCargos);
        dbExecute = new DbExecute(AnadirEmpleado.this);
        ArrayList<String> arr = new ArrayList<String>();

        arr = dbExecute.selectCargos();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, arr);

        spCargo.setAdapter(adapter);



    }

    public void añadirTalleres(){

        spTaller = findViewById(R.id.spinnerTaller);
        dbExecute = new DbExecute(AnadirEmpleado.this);
        ArrayList<String> arr = new ArrayList<String>();

        arr = dbExecute.selectTalleres();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, arr);

        spTaller.setAdapter(adapter);

    }

    public void añadirCiudades(){

        spCiu = findViewById(R.id.spinnerCiu);
        dbExecute = new DbExecute(AnadirEmpleado.this);
        ArrayList<String> arr = new ArrayList<String>();

        arr = dbExecute.selectCiudades();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, arr);

        spCiu.setAdapter(adapter);

    }

    public void crearEmple(View view){
        spTaller = findViewById(R.id.spinnerTaller);
        dbExecute = new DbExecute(AnadirEmpleado.this);

        cargo = cargo.substring(0, 1);
        ciu = ciu.substring(0, 1);
        taller = taller.substring(0, 1);


        dbExecute.añadirEmple(nombre.getText().toString(), apellidos.getText().toString(), Integer.parseInt(codEmple.getText().toString()),
                Integer.parseInt(cargo), Integer.parseInt(ciu), Integer.parseInt(taller), contraseña.getText().toString());
    }

}