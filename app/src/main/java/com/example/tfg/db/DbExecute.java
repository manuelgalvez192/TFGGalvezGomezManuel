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

public class DbExecute extends DbHelper{//hereda de la base de datos para poder realizar ejecuciones

    Context context;


    public DbExecute(@Nullable Context context) {
        super(context);
        this.context = context;
    }



    //para insertar nuevo usuario en la app
    public void createAccount(String cod, String pass){

        //controlamos errores
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //añade los datos obtenidos de la consulta en un cursor
            Cursor c = db.rawQuery("SELECT COD FROM " + TABLE_LOGIN, null);

            //nos movemos al principio del cursor
            c.moveToFirst();

            do{
                String nombre = c.getString(0);//obtenemos la posicion del array que nos interese

                System.out.println("nombre " + nombre + " name " + cod);

                if(nombre.equals(cod))//si el valor es igual al codigo que le pasamos por parametro
                {
                    Toast.makeText(context, "Ese usuario ya existe", Toast.LENGTH_SHORT).show();//el usuario ya existe
                    return;//asi que regresamos a la llamada
                }

            }while(c.moveToNext());//mientras haya datos dentro del cursor

            //si llega aqui el usuario no existe asi que lo cremos y mandamos un mensaje para que el usuario lo seapa
            Toast.makeText(context, "Usuario creado", Toast.LENGTH_SHORT).show();
            ContentValues values = new ContentValues();//llenamos una variable con los datos que queremos
            values.put("cod", cod);//a cada valor le damos el nombre que tiene el campo en la tabla correspondiente de la base de datos
            values.put("pass", pass);

            db.insert(TABLE_LOGIN, null, values);//insertamos los datos en la tabla

        }catch (Exception e)
        {
            e.toString();
            Toast.makeText(context, "Hubo un problema, intentelo de nuevo", Toast.LENGTH_LONG).show();//mensaje de error por si no se pudo crear

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

            if(c.getString(0).equals(pass))//si es igual el usuario existe asi que le pasamos true
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

        return seguir;//regresamos el valor
    }

    //seleccionamos los cargos de la base de datos
    public ArrayList selectCargos() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList arr = new ArrayList();
        
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_CARGO, null);
        c.moveToFirst();
        do{
            arr.add(c.getString(0) + " - " +c.getString(1));//en este caso le mandamos al array dos valores de los seleccionados
            //si luego queremos solo uno de los valores separamos el String con un Split
        }while(c.moveToNext());

        return arr;
    }

    //para obtener los talleres
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

    //para obtener las ciudades
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

    //para añadir empleados
    public void añadirEmple(String nombre, String apellidos, int codEmple, int codCargo, int codTaller, int codCiu, String contra) {

        long i = 0;//variable para comprobar si se ha añadido

        try
        {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            System.out.println("INSERT INTO " + TABLE_EMPLEADO + " VALUES(" + codEmple + ", " + nombre + ", " + apellidos +
                    ", " + codCargo + ", " + codCiu + ", " + codTaller + ")");//para comprobar por consola que la consulta es como queremos

            ContentValues datos = new ContentValues();//le pasamos todos los datos
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

            i = db.insert(TABLE_EMPLEADO, null, datos);//lo añadimos

            db.insert(TABLE_LOGIN,null, log);

            if(i > 0)//si la variable es mayor que 0 se creo el empleado
            {
                Toast.makeText(context, "Empleado creado", Toast.LENGTH_SHORT).show();
            }else//sino no se creo
            {
                Toast.makeText(context, "El empleado no se ha creado", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e)
        {
            e.toString();
            Toast.makeText(context, "Hubo un error al insertar el nuevo empleado, revise los datos", Toast.LENGTH_LONG).show();
        }

    }

    //para añadir clientes
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

    //para obtener los empelados
    public ArrayList selectEmples() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList arr = new ArrayList();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_EMPLEADO, null);
        c.moveToFirst();
        do{
            arr.add(c.getString(0) + " - " +c.getString(1));
        }while(c.moveToNext());

        return arr;
    }

    //para obtener los clientes
    public ArrayList selectClie() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList arr = new ArrayList();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_CLIENTES, null);
        c.moveToFirst();
        do{
            arr.add(c.getString(0) + "-" + c.getString(1));
        }while(c.moveToNext());

        return arr;
    }

    //para obtener los fabricantes
    public ArrayList selectFab() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList arr = new ArrayList();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_FABRICANTE, null);
        c.moveToFirst();
        do{
            arr.add(c.getString(0) + " - " +c.getString(1));
        }while(c.moveToNext());

        return arr;
    }

    //para obtener los productos
    public ArrayList selectProd() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList arr = new ArrayList();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTOS, null);
        c.moveToFirst();
        do{
            arr.add(c.getString(0) + "-" + c.getString(1) + "-" + c.getString(2));
        }while(c.moveToNext());

        return arr;
    }

    //obtener precios de cada material
    public int selectPrecio(int cod){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int precio;

        Cursor c = db.rawQuery("SELECT precioUni FROM " + TABLE_PRODUCTOS + " WHERE CODIGO = '" + cod + "'", null);
        c.moveToFirst();
        do{
            precio = c.getInt(0);
        }while(c.moveToNext());

        return precio;
    }
}
