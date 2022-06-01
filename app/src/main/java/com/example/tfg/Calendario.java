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

    CalendarView calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        calendario = findViewById(R.id.calendarView);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,Calendar.getInstance().getActualMinimum(Calendar.DATE));
        long date = calendar.getTime().getTime();

        calendario.setMinDate(date);
    }

    public void lunes(View view){

        Intent lunes = new Intent(this, Lunes.class);//lanza vista para el menu añadir empleado
        startActivity(lunes);
    }

    public void martes(View view){

    }

    public void miercoles(View view){

    }

    public void jueves(View view){

    }

    public void viernes(View view){

    }
}