package com.example.tfg.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.tfg.MainActivity;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NOMBRE = "tfg.db";
    public static final String TABLE_EMPLEADO = "t_empleado";
    public static final String TABLE_PAIS = "t_pais";
    public static final String TABLE_CIUDAD = "t_ciudad";
    public static final String TABLE_CLIENTES = "t_clientes";
    public static final String TABLE_CARGO = "t_cargo";
    public static final String TABLE_TALLER = "t_taller";
    public static final String TABLE_PRODUCTOS = "t_productos";
    public static final String TABLE_PEDIDO = "t_pedido";
    public static final String TABLE_CATEGORIA = "t_categoria";
    public static final String TABLE_FABRICANTE = "t_fabricante";
    public static final String TABLE_LOGIN = "t_login";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_EMPLEADO + "(" +
                "codigo INTEGER PRIMARY KEY," +
                "contraseña TEXT NOT NULL," +
                "nombre TEXT NOT NULL," +
                "apellidos TEXT NOT NULL," +
                "codCargo INTEGER NOT NULL," +
                "codCiudad INTEGER NOT NULL," +
                "codTaller INTEGER NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PAIS + "(" +
                "codigo TEXT PRIMARY KEY," +
                "nombre TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CIUDAD + "(" +
                "codigo TEXT PRIMARY KEY," +
                "nombre TEXT NOT NULL," +
                "codPais TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CLIENTES + "(" +
                "codigo TEXT PRIMARY KEY," +
                "nombre TEXT NOT NULL," +
                "apellidos TEXT NOT NULL," +
                "direccion TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CARGO + "(" +
                "codigo TEXT PRIMARY KEY," +
                "nombre TEXT NOT NULL)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CARGO + " VALUES('1', 'Director')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CARGO + " VALUES('2', 'Mecanico')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CARGO + " VALUES('3', 'Reponedor')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CARGO + " VALUES('4', 'Limpiador')");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_TALLER + "(" +
                "codigo TEXT PRIMARY KEY," +
                "nombre TEXT NOT NULL," +
                "direccion TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "numEmples INTEGER NOT NULL," +
                "codCiu TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_FABRICANTE + "(" +
                "codigo TEXT PRIMARY KEY," +
                "nombre TEXT NOT NULL," +
                "codCiu TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PRODUCTOS + "(" +
                "codigo TEXT PRIMARY KEY," +
                "nombre TEXT NOT NULL," +
                "precioUni INTEGER NOT NULL," +
                "codFab TEXT NOT NULL," +
                "codCat TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PEDIDO + "(" +
                "codigo TEXT PRIMARY KEY," +
                "codClie TEXT NOT NULL," +
                "fechaPed DATE NOT NULL," +
                "fechaEntr DATE NOT NULL," +
                "direccion TEXT NOT NULL," +
                "codEmple TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CATEGORIA + "(" +
                "codigo TEXT PRIMARY KEY," +
                "nombre TEXT NOT NULL," +
                "descripcion TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_LOGIN + "(" +
                "nombre TEXT PRIMARY KEY," +
                "pass TEXT NOT NULL)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_LOGIN + " VALUES('Admin', 'Admin')");


    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_EMPLEADO);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PAIS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CIUDAD);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CLIENTES);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CARGO);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_TALLER);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_FABRICANTE);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PRODUCTOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PEDIDO);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CATEGORIA);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_LOGIN);
        onCreate(sqLiteDatabase);
    }
}
