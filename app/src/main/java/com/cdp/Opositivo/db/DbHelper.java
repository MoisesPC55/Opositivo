package com.cdp.Opositivo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "Opositivo.db";
    public static final String TABLE_USUARIOS = "t_usuarios";
    public static final String TABLE_DONANTES = "t_donantes";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USUARIOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nombre TEXT NOT NULL," +
                "Email TEXT NOT NULL," +
                "Contrasena TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_DONANTES + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nombre TEXT NOT NULL," +
                "Email TEXT NOT NULL," +
                "Telefono TEXT NOT NULL," +
                "Doc_identidad TEXT NOT NULL," +
                "Tipo_sangre TEXT NOT NULL," +
                "Direccion TEXT NOT NULL," +
                "Contrasena TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USUARIOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_DONANTES);
        onCreate(sqLiteDatabase);

    }
}