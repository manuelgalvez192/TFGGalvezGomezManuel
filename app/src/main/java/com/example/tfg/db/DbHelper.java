package com.example.tfg.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.tfg.MainActivity;

public class DbHelper extends SQLiteOpenHelper {

    //estableces variables de los nombres para crear la base de datos
    private static final int DATABASE_VERSION = 12;
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

    //creas el constructor con el super para llamar
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //ejecucion con el metodo integrado de SQLite para crear la base de datos
        //a cada campo se le da un tipo de dato y que no se pueda dejar vacio
        //ademas de si es clave primaria
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_EMPLEADO + "(" +
                "codigo INTEGER PRIMARY KEY," +
                "nombre TEXT NOT NULL," +
                "apellidos TEXT NOT NULL," +
                "contraseña TEXT NOT NULL," +
                "codCargo INTEGER NOT NULL," +
                "codCiudad INTEGER NOT NULL," +
                "codTaller INTEGER NOT NULL)");

        //añades datos en la base de datos
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EMPLEADO + " VALUES('1', 'Sam', 'Fernandez', '1234', '2', '1', '1')");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PAIS + "(" +
                "codigo TEXT PRIMARY KEY," +
                "nombre TEXT NOT NULL)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PAIS + " VALUES('ESP', 'España')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PAIS + " VALUES('FRA', 'Francia')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PAIS + " VALUES('POR', 'Portugal')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PAIS + " VALUES('ITL', 'Italia')");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CIUDAD + "(" +
                "codigo TEXT PRIMARY KEY," +
                "nombre TEXT NOT NULL," +
                "codPais TEXT NOT NULL)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CIUDAD + " VALUES('1', 'Madrid', 'ESP')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CIUDAD + " VALUES('2', 'Barcelonaa', 'ESP')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CIUDAD + " VALUES('3', 'Sevilla', 'ESP')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CIUDAD + " VALUES('4', 'Toledo', 'ESP')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CIUDAD + " VALUES('5', 'Valencia', 'ESP')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CIUDAD + " VALUES('6', 'Córdoba', 'ESP')");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CLIENTES + "(" +
                "codigo TEXT PRIMARY KEY," +
                "nombre TEXT NOT NULL," +
                "apellidos TEXT NOT NULL," +
                "direccion TEXT NOT NULL," +
                "telefono INTEGER NOT NULL)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CLIENTES + " VALUES('1', 'Juan', 'Gomez', 'C/ veinticuatro', '+34 697421568')");

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

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_TALLER + " VALUES('1', 'Taller Madrid', 'Calle Madrid', '0123', '5', '1')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_TALLER + " VALUES('2', 'Taller Barcelona', 'Calle Barcelona', '1234', '8', '2')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_TALLER + " VALUES('3', 'Taller Sevilla', 'Calle Sevilla', '6542', '4', '3')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_TALLER + " VALUES('4', 'Taller Toledo', 'Calle Toledo', '9878', '6', '4')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_TALLER + " VALUES('5', 'Taller Valencia', 'Calle Valencia', '1564', '7', '5')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_TALLER + " VALUES('6', 'Taller Córdoba', 'Calle Córdoba', '7485', '8', '6')");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_FABRICANTE + "(" +
                "codigo TEXT PRIMARY KEY," +
                "nombre TEXT NOT NULL," +
                "codCiu TEXT NOT NULL)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FABRICANTE + " VALUES('1', 'Bosch', '1')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FABRICANTE + " VALUES('2', 'A.B.S', '1')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FABRICANTE + " VALUES('3', 'Ridex', '2')");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PRODUCTOS + "(" +
                "codigo TEXT PRIMARY KEY," +
                "nombre TEXT NOT NULL," +
                "precioUni INTEGER NOT NULL," +
                "codFab TEXT NOT NULL," +
                "codCat TEXT NOT NULL)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PRODUCTOS + " VALUES('1', 'Bugia', '50', '1', '3')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PRODUCTOS + " VALUES('2', 'Neumáticos', '20', '3', '3')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PRODUCTOS + " VALUES('3', 'Tubos de escape', '40', '2', '3')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PRODUCTOS + " VALUES('4', 'Limpiador', '5', '1', '1')");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PEDIDO + "(" +
                "codigo TEXT PRIMARY KEY," +
                "codClie TEXT NOT NULL," +
                "fechaPed DATE NOT NULL," +
                "direccion TEXT NOT NULL," +
                "codEmple TEXT NOT NULL," +
                "material TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CATEGORIA + "(" +
                "codigo TEXT PRIMARY KEY," +
                "nombre TEXT NOT NULL," +
                "descripcion TEXT NOT NULL)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CATEGORIA + " VALUES('1', 'Limpieza', 'Articulos relacionados con el mantenimiento y limpieza del vehículo')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CATEGORIA + " VALUES('2', 'Cristl', 'Articulos de material de cristal, o frágil')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CATEGORIA + " VALUES('3', 'Mecánica', 'Articulos necesarios para el funcionamiento del vehículo')");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_LOGIN + "(" +
                "cod TEXT PRIMARY KEY," +
                "pass TEXT NOT NULL)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_LOGIN + " VALUES('Admin', 'Admin')");


    }


    //cada vez que se cambie la version de la app se ejecutara este metodo
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //borra las tablas para que al ejecutarse el otro metodo no de conflictos de que la tabla ya existe
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
        onCreate(sqLiteDatabase);//lanza el metodo para crear las tablas de nuevo
    }
}
