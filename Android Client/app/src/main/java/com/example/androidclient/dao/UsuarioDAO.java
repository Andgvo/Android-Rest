package com.example.androidclient.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidclient.dto.Usuario;
import com.example.androidclient.utilerias.AyudanteBaseDeDatos;
import com.example.androidclient.utilerias.DbTables;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements DAO<Usuario> {
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;

    public UsuarioDAO(Context context){
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(context);
    }

    @Override
    public void create(Usuario usuario) {
        SQLiteDatabase db;
        try {
            db = ayudanteBaseDeDatos.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbTables.USUARIO_NOMBRE, usuario.getNombreUsuario());
            values.put(DbTables.USUARIO_APELLIDO, usuario.getApellidoUsuario());
            values.put(DbTables.USUARIO_EMAIL, usuario.getEmailUsuario());
            values.put(DbTables.USUARIO_PASSWORD, usuario.getPasswordUsuario());
            db.insert(DbTables.TABLE_USUARIO, null, values);
            db.close();
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Usuario usuario) {
        SQLiteDatabase db;
        String[] parametrosCondicion = {String.valueOf(usuario.getIdUsuario())};
        String condicion = DbTables.USUARIO_ID + " = ? ";
        try {
            db = ayudanteBaseDeDatos.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbTables.USUARIO_NOMBRE, usuario.getNombreUsuario());
            values.put(DbTables.USUARIO_APELLIDO, usuario.getApellidoUsuario());
            values.put(DbTables.USUARIO_EMAIL, usuario.getEmailUsuario());
            values.put(DbTables.USUARIO_PASSWORD, usuario.getPasswordUsuario());
            int affected = db.update(DbTables.TABLE_USUARIO,values,condicion,parametrosCondicion);
            db.close();
        }catch ( Exception ex ) {
            ex.printStackTrace();
            //return false;
        }
    }

    @Override
    public void delete(Usuario usuario) {
        SQLiteDatabase db;
        try {
            db = ayudanteBaseDeDatos.getWritableDatabase();
            String[] argumentos = {String.valueOf(usuario.getIdUsuario())};
            String condicion = DbTables.USUARIO_ID + " = ?";
            db.delete(DbTables.TABLE_USUARIO, condicion, argumentos);
            db.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Usuario read(Usuario usuario) throws SQLException {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        return null;
    }

    @Override
    public List<Usuario> readAll() throws SQLException {
        ArrayList<Usuario> Usuarios = new ArrayList<>();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();
        // SELECT nombre, edad, id
        String[] columnasAConsultar = {"idUsuario", "nombreUsuario", "apellidoUsuario", "emailUsuario", "passwordUsuario"};
        Cursor cursor = baseDeDatos.query(
                DbTables.TABLE_USUARIO,//from Usuarios
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null) {
            /*
                Salimos aquí porque hubo un error, regresar
                lista vacía
             */
            return Usuarios;

        }
        // Si no hay datos, igualmente regresamos la lista vacía
        if (!cursor.moveToFirst()) return Usuarios;

        // En caso de que sí haya, iteramos y vamos agregando los
        // datos a la lista de Usuarios
        do {
            // El 0 es el número de la columna, como seleccionamos
            // nombre, edad,id entonces el nombre es 0, edad 1 e id es 2
            int idUsuario = cursor.getInt(0);
            String nombreUsuario = cursor.getString(1);
            String apelldioUsuario = cursor.getString(2);
            String emailUsuario = cursor.getString(3);
            String passwordUsuario = cursor.getString(4);

            Usuario UsuarioObtenidaDeBD = new Usuario(
                    idUsuario, nombreUsuario, apelldioUsuario, emailUsuario, passwordUsuario);
            Usuarios.add(UsuarioObtenidaDeBD);
        } while (cursor.moveToNext());

        // Fin del ciclo. Cerramos cursor y regresamos la lista de Usuarios :)
        cursor.close();
        return Usuarios;
    }

    public Usuario login(Usuario usuario){
        SQLiteDatabase db = ayudanteBaseDeDatos.getReadableDatabase();
        String[] parametrosCondicion = {usuario.getNombreUsuario(), usuario.getPasswordUsuario()};
        String[] camposRetorno = {
                DbTables.USUARIO_ID, DbTables.USUARIO_NOMBRE, DbTables.USUARIO_APELLIDO, DbTables.USUARIO_EMAIL, DbTables.USUARIO_PASSWORD};
        String condicion = DbTables.USUARIO_NOMBRE + " = ? AND " + DbTables.USUARIO_PASSWORD + " = ?" ;
        try{
            Cursor cursor = db.query(
                    DbTables.TABLE_USUARIO,
                    camposRetorno,
                    condicion,
                    parametrosCondicion,
                    null,
                    null,
                    null
            );
            cursor.moveToFirst();
            usuario = new Usuario(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));
            return usuario;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}