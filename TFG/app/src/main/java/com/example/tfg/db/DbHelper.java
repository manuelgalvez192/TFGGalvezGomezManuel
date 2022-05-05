package com.example.tfg.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NOMBRE = "tfg.db";
    public static final String TABLE_EMPLEADO = "t_empleado";
    public static final String TABLE_LOGIN = "t_login";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_LOGIN + "(" +
                "nombre TEXT NOT NULL," +
                "pass TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_EMPLEADO + "(" +
                "codigo INTEGER PRIMARY KEY," +
                "nombre TEXT NOT NULL," +
                "apellidos TEXT NOT NULL," +
                "codCargo INTEGER NOT NULL," +
                "codCiudad INTEGER NOT NULL," +
                "codTaller INTEGER NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_LOGIN);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_EMPLEADO);
        onCreate(sqLiteDatabase);
    }
}
