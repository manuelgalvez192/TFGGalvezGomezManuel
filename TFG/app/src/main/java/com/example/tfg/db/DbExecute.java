package com.example.tfg.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

import androidx.annotation.Nullable;

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

            ContentValues values = new ContentValues();
            values.put("nombre", name);
            values.put("pass", pass);

            db.insert(TABLE_LOGIN, null, values);

        }catch (Exception e)
        {
            e.toString();
        }

    }

}
