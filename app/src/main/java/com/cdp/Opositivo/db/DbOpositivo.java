package com.cdp.Opositivo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.cdp.Opositivo.entidades.Donante;

import java.util.ArrayList;

public class DbOpositivo extends DbHelper {

    Context context;

    public DbOpositivo(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarDonante(String Nombre, String Email, String Telefono, String Doc_identidad, String Tipo_sangre, String Direccion, String Contrasena) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Nombre", Nombre);
            values.put("Email", Email);
            values.put("Telefono", Telefono);
            values.put("Doc_identidad", Doc_identidad);
            values.put("Tipo_sangre", Tipo_sangre);
            values.put("Direccion", Direccion);
            values.put("Contrasena", Contrasena);

            id = db.insert(TABLE_DONANTES, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public long insertarUsuario(String Nombre, String Email, String Contrasena) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Nombre", Nombre);
            values.put("Email", Email);
            values.put("Contrasena", Contrasena);

            id = db.insert(TABLE_USUARIOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Donante> mostrarDonante() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Donante> listaDonante = new ArrayList<>();
        Donante donantes;
        Cursor cursorDonante;

        cursorDonante = db.rawQuery("SELECT * FROM " + TABLE_DONANTES + " ORDER BY Nombre ASC", null);

        if (cursorDonante.moveToFirst()) {
            do {
                donantes = new Donante();
                donantes.setId(cursorDonante.getInt(0));
                donantes.setNombre(cursorDonante.getString(1));
                donantes.setEmail(cursorDonante.getString(2));
                donantes.setTelefono(cursorDonante.getString(3));
                donantes.setDoc_identidad(cursorDonante.getString(4));
                donantes.setTipo_sangre(cursorDonante.getString(5));
                donantes.setDireccion(cursorDonante.getString(6));
                donantes.setContrasena(cursorDonante.getString(7));
                listaDonante.add(donantes);
            } while (cursorDonante.moveToNext());
        }

        cursorDonante.close();

        return listaDonante;
    }

    public Donante verDonante(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Donante donantes = null;
        Cursor cursorDonante;

        cursorDonante = db.rawQuery("SELECT * FROM " + TABLE_DONANTES + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorDonante.moveToFirst()) {
            donantes = new Donante();
            donantes.setId(cursorDonante.getInt(0));
            donantes.setNombre(cursorDonante.getString(1));
            donantes.setEmail(cursorDonante.getString(2));
            donantes.setTelefono(cursorDonante.getString(3));
            donantes.setDoc_identidad(cursorDonante.getString(4));
            donantes.setTipo_sangre(cursorDonante.getString(5));
            donantes.setDireccion(cursorDonante.getString(6));
            donantes.setContrasena(cursorDonante.getString(7));
        }

        cursorDonante.close();

        return donantes;
    }

    public boolean editarDonante(int id, String nombre, String email, String telefono, String doc_identidad, String tipo_sangre, String direccion){
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_DONANTES + " SET nombre = '" + nombre + "', email = '" + email + "', telefono = '" + telefono + "', doc_identidad = '" + doc_identidad + "', tipo_sangre = '" + tipo_sangre + "', direccion = '" + direccion + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarDonante(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_DONANTES + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
