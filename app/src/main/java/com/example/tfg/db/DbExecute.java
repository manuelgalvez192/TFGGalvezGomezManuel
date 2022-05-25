package com.example.tfg.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.tfg.createAccount;

import java.util.ArrayList;

public class DbExecute extends DbHelper{

    Context context;


    public DbExecute(@Nullable Context context) {
        super(context);
        this.context = context;
    }



    //para insertar nuevo usuario en la app
    public void createAccount(String name, String pass){

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            Cursor c = db.rawQuery("SELECT NOMBRE FROM " + TABLE_LOGIN, null);

            c.moveToFirst();

            do{
                String nombre = c.getString(0);

                System.out.println("nombre " + nombre + " name " + name);

                if(nombre.equals(name))
                {
                    Toast.makeText(context, "Ese usuario ya existe", Toast.LENGTH_SHORT).show();
                    return;
                }

            }while(c.moveToNext());

            Toast.makeText(context, "Usuario creado", Toast.LENGTH_SHORT).show();
            ContentValues values = new ContentValues();
            values.put("nombre", name);
            values.put("pass", pass);

            db.insert(TABLE_LOGIN, null, values);

        }catch (Exception e)
        {
            e.toString();
            Toast.makeText(context, "Hubo un problema, intentelo de nuevo", Toast.LENGTH_LONG).show();

        }

    }

    //comprobar que el usuario existe y contraseña esta bien
    public boolean iniciar(String name, String pass, boolean seguir){


        try
        {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            Cursor c = db.rawQuery("SELECT PASS FROM " + TABLE_LOGIN + " WHERE NOMBRE = '" + name + "'", null);

            c.moveToFirst();

            System.out.println("C: " + c.getString(0) + " pass: " + pass);

            if(c.getString(0).equals(pass))
            {
                seguir = true;
            }else
            {
                seguir = false;
            }

        }catch (Exception e)
        {
            e.toString();
        }

        System.out.println("seguir: " + seguir);

        return seguir;
    }

    public ArrayList selectCargos()
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList arr = new ArrayList();
        
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_CARGO, null);
        c.moveToFirst();
        do{
            arr.add(c.getString(0) + " - " +c.getString(1));
        }while(c.moveToNext());

        return arr;
    }

    public ArrayList selectTalleres()
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList arr = new ArrayList();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_TALLER, null);
        c.moveToFirst();
        do{
            arr.add(c.getString(0) + " - " +c.getString(1));
        }while(c.moveToNext());

        return arr;
    }

    public ArrayList selectCiudades()
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList arr = new ArrayList();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_CIUDAD, null);
        c.moveToFirst();
        do{
            arr.add(c.getString(0) + " - " +c.getString(1));
        }while(c.moveToNext());

        return arr;
    }

}
