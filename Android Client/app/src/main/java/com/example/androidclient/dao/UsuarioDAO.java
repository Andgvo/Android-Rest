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
    
    private static final String CONDICION_BY_ID = DbTables.USUARIO_ID + " = ? ";
    private static final String[] CAMPOS_RETURN_ALL = {
            DbTables.USUARIO_ID, DbTables.USUARIO_NOMBRE, DbTables.USUARIO_APELLIDO,
            DbTables.USUARIO_EMAIL, DbTables.USUARIO_PASSWORD };
    private int rowsAffected = 0;
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private SQLiteDatabase db;

    
    public UsuarioDAO(Context context){
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(context);
    }

    @Override
    public long create(Usuario usuario) {
        long idUsuarioAffected;
        try {
            db = ayudanteBaseDeDatos.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbTables.USUARIO_NOMBRE, usuario.getNombreUsuario());
            values.put(DbTables.USUARIO_APELLIDO, usuario.getApellidoUsuario());
            values.put(DbTables.USUARIO_EMAIL, usuario.getEmailUsuario());
            values.put(DbTables.USUARIO_PASSWORD, usuario.getPasswordUsuario());
            idUsuarioAffected = db.insert(DbTables.TABLE_USUARIO, null, values);
            db.close();
            return idUsuarioAffected;
        }catch ( Exception ex ) {
            ex.printStackTrace();
            db.close();
            return -1;
        }
    }

    @Override
    public int update(Usuario usuario) {
        String[] parametrosCondicion = {String.valueOf(usuario.getIdUsuario())};
        try {
            db = ayudanteBaseDeDatos.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbTables.USUARIO_NOMBRE, usuario.getNombreUsuario());
            values.put(DbTables.USUARIO_APELLIDO, usuario.getApellidoUsuario());
            values.put(DbTables.USUARIO_EMAIL, usuario.getEmailUsuario());
            values.put(DbTables.USUARIO_PASSWORD, usuario.getPasswordUsuario());
            rowsAffected = db.update(DbTables.TABLE_USUARIO,values,CONDICION_BY_ID,parametrosCondicion);
            db.close();
            return rowsAffected;
        }catch ( Exception ex ) {
            ex.printStackTrace();
            db.close();
            return -1;
        }
    }

    @Override
    public int delete(Usuario usuario) {
        try {
            db = ayudanteBaseDeDatos.getWritableDatabase();
            String[] argumentos = {String.valueOf(usuario.getIdUsuario())};
            rowsAffected = db.delete(DbTables.TABLE_USUARIO, CONDICION_BY_ID, argumentos);
            db.close();
            return  rowsAffected;
        }catch (Exception ex){
            ex.printStackTrace();
            db.close();
            return -1;
        }
    }

    @Override
    public Usuario read(Usuario usuario) throws SQLException {
        //SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        return null;
    }

    @Override
    public List<Usuario> readAll() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String[] parametros = {};
        Usuario usuario; // ObjetoAuxiliar
        try {
            db = ayudanteBaseDeDatos.getReadableDatabase();
            Cursor cursor = db.query(
                    DbTables.TABLE_USUARIO,
                    CAMPOS_RETURN_ALL,
                    null,
                    null,
                    null,
                    null,
                    null
            );
            //Hubo un error
            if (cursor == null) return null;
            // Si no hay datos, igualmente regresamos la lista vacía
            if (!cursor.moveToFirst()) return usuarios;
            do {
                usuario = new Usuario(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));
                usuarios.add( usuario );
            } while (cursor.moveToNext());
            // Fin del ciclo. Cerramos cursor y regresamos la lista de Posts :)
            cursor.close();
            db.close();
            return usuarios;
        }catch (Exception ex){
            ex.printStackTrace();
            db.close();
            return null;
        }
    }

    public Usuario login(Usuario usuario){
        db = ayudanteBaseDeDatos.getReadableDatabase();
        String[] parametrosCondicion = {usuario.getNombreUsuario(), usuario.getPasswordUsuario()};
        String condicion = DbTables.USUARIO_NOMBRE + " = ? AND " + DbTables.USUARIO_PASSWORD + " = ?" ;
        try{
            Cursor cursor = db.query(
                    DbTables.TABLE_USUARIO,
                    CAMPOS_RETURN_ALL,
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
            cursor.close();
            db.close();
            return usuario;
        }catch (Exception e) {
            e.printStackTrace();
            db.close();
            return null;
        }
    }
}