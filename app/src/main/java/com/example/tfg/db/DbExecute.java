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
    public void createAccount(String cod, String pass){

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            Cursor c = db.rawQuery("SELECT COD FROM " + TABLE_LOGIN, null);

            c.moveToFirst();

            do{
                String nombre = c.getString(0);

                System.out.println("nombre " + nombre + " name " + cod);

                if(nombre.equals(cod))
                {
                    Toast.makeText(context, "Ese usuario ya existe", Toast.LENGTH_SHORT).show();
                    return;
                }

            }while(c.moveToNext());

            Toast.makeText(context, "Usuario creado", Toast.LENGTH_SHORT).show();
            ContentValues values = new ContentValues();
            values.put("cod", cod);
            values.put("pass", pass);

            db.insert(TABLE_LOGIN, null, values);

        }catch (Exception e)
        {
            e.toString();
            Toast.makeText(context, "Hubo un problema, intentelo de nuevo", Toast.LENGTH_LONG).show();

        }

    }

    //comprobar que el usuario existe y contraseña esta bien
    public boolean iniciar(String cod, String pass, boolean seguir){


        try
        {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            Cursor c = db.rawQuery("SELECT PASS FROM " + TABLE_LOGIN + " WHERE COD = '" + cod + "'", null);

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

    public ArrayList selectCargos() {
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

    public ArrayList selectTalleres() {
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

    public ArrayList selectCiudades() {
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

    public void añadirEmple(String nombre, String apellidos, int codEmple, int codCargo, int codTaller, int codCiu, String contra) {

        long i = 0;

        try
        {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            System.out.println("INSERT INTO " + TABLE_EMPLEADO + " VALUES(" + codEmple + ", " + nombre + ", " + apellidos +
                    ", " + codCargo + ", " + codCiu + ", " + codTaller + ")");

            ContentValues datos = new ContentValues();
            datos.put("codigo", codEmple);
            datos.put("nombre", nombre);
            datos.put("apellidos", apellidos);
            datos.put("contraseña", contra);
            datos.put("codCargo", codCargo);
            datos.put("codCiudad", codCiu);
            datos.put("codTaller", codTaller);

            ContentValues log = new ContentValues();
            log.put("cod", codEmple);
            log.put("pass", contra);

            i = db.insert(TABLE_EMPLEADO, null, datos);

            db.insert(TABLE_LOGIN,null, log);

            if(i > 0)
            {
                Toast.makeText(context, "Empleado creado", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(context, "El empleado no se ha creado", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e)
        {
            e.toString();
            Toast.makeText(context, "Hubo un error al insertar el nuevo empleado, revise los datos", Toast.LENGTH_LONG).show();
        }

    }

    public void añadirClie(String cod, String nom, String ape, String dir, int tlf){

        long i = 0;

        try
        {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            System.out.println("INSERT INTO " + TABLE_CLIENTES + " VALUES(" + cod + ", " + nom + ", " + ape +
                    ", " + dir + ", " + tlf + ")");

            ContentValues datos = new ContentValues();
            datos.put("codigo", cod);
            datos.put("nombre", nom);
            datos.put("apellidos", ape);
            datos.put("direccion", dir);
            datos.put("telefono", tlf);

            i = db.insert(TABLE_CLIENTES, null, datos);

            if(i > 0)
            {
                Toast.makeText(context, "Cliente creado", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(context, "El cliente no se ha creado", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e)
        {
            e.toString();
            Toast.makeText(context, "Hubo un error al insertar el nuevo cliente, revise los datos", Toast.LENGTH_LONG).show();
        }
    }
}
