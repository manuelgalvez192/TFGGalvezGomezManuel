package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Calendario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        Calendar calendario = Calendar.getInstance();
        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setDate(calendario.getTimeInMillis());
    }

    public void lunes(View view){

        Intent lunes = new Intent(this, Lunes.class);//lanza vista para el menu añadir empleado
        startActivity(lunes);
    }

    public void martes(View view){

        Intent martes = new Intent(this, Martes.class);//lanza vista para el menu añadir empleado
        startActivity(martes);
    }

    public void miercoles(View view){

        Intent miercoles = new Intent(this, Miercoles.class);//lanza vista para el menu añadir empleado
        startActivity(miercoles);
    }

    public void jueves(View view){

        Intent jueves = new Intent(this, Jueves.class);//lanza vista para el menu añadir empleado
        startActivity(jueves);
    }

    public void viernes(View view){

        Intent viernes = new Intent(this, Viernes.class);//lanza vista para el menu añadir empleado
        startActivity(viernes);
    }
}