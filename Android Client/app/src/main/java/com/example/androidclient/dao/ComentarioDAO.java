package com.example.androidclient.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidclient.dto.Comentario;
import com.example.androidclient.dto.Post;
import com.example.androidclient.dto.Usuario;
import com.example.androidclient.utilerias.AyudanteBaseDeDatos;
import com.example.androidclient.utilerias.DbTables;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComentarioDAO implements DAO<Comentario>{

    private static final String CONDICION_BY_ID = DbTables.COMENTARIO_ID + " = ? ";
    private static final String QUERY_BY_ID_POST =
            "SELECT c.idComentario, c.fechaComentario, c.contenidoComentario, u.idUsuario, u.nombreUsuario, c.idPost FROM Comentario c INNER JOIN Usuario u ON c.idUsuario = u.idUsuario WHERE c.idPost = ?;";

    private static final String[] CAMPOS_RETURN_ALL = {
            DbTables.COMENTARIO_ID,
            DbTables.COMENTARIO_FECHA,
            DbTables.COMENTARIO_CONTENIDO,
            DbTables.COMENTARIO_ID_USUARIO,
            DbTables.COMENTARIO_ID_POST
    };
    private static final SimpleDateFormat FORMATO = new SimpleDateFormat("yyyy-MM-dd");
    private int rowsAffected = 0;
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private SQLiteDatabase db;


    public ComentarioDAO(Context context){
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(context);
    }

    @Override
    public long create(Comentario comentario) {
        long idComentarioAffected;
        try {
            db = ayudanteBaseDeDatos.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbTables.COMENTARIO_FECHA, FORMATO.format(comentario.getFechaComentario()) );
            values.put(DbTables.COMENTARIO_CONTENIDO, comentario.getContenidoComentario());
            values.put(DbTables.COMENTARIO_ID_USUARIO, comentario.getIdUsuario().getIdUsuario());
            values.put(DbTables.COMENTARIO_ID_POST, comentario.getIdPost().getIdPost());
            idComentarioAffected = db.insert(DbTables.TABLE_COMENTARIO, null, values);
            db.close();
            return idComentarioAffected;
        }catch ( Exception ex ) {
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public int update(Comentario comentario) {
        String[] parametrosCondicion = {String.valueOf(comentario.getIdComentario())};
        try {
            db = ayudanteBaseDeDatos.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbTables.COMENTARIO_FECHA, FORMATO.format(comentario.getFechaComentario()) );
            values.put(DbTables.COMENTARIO_CONTENIDO, comentario.getContenidoComentario());
            values.put(DbTables.COMENTARIO_ID_USUARIO, comentario.getIdUsuario().getIdUsuario());
            values.put(DbTables.COMENTARIO_ID_POST, comentario.getIdPost().getIdPost());
            rowsAffected = db.update(DbTables.TABLE_COMENTARIO,values,CONDICION_BY_ID,parametrosCondicion);
            db.close();
            return rowsAffected;
        }catch ( Exception ex ) {
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(Comentario comentario) {
        try {
            db = ayudanteBaseDeDatos.getWritableDatabase();
            String[] argumentos = {String.valueOf(comentario.getIdComentario())};
            rowsAffected = db.delete(DbTables.TABLE_COMENTARIO, CONDICION_BY_ID, argumentos);
            db.close();
            return  rowsAffected;
        }catch (Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public Comentario read(Comentario comentario) {
        String[] parametrosCondicion = {String.valueOf(comentario.getIdComentario()), String.valueOf(comentario.getIdUsuario().getIdUsuario())}; // {comentario.getNombreComentario(), comentario.getPasswordComentario()};
        String condicion = DbTables.COMENTARIO_ID + " = ? AND " + DbTables.COMENTARIO_ID_USUARIO + " = ?" ;
        try{
            Cursor cursor = db.query(
                    DbTables.TABLE_COMENTARIO,
                    CAMPOS_RETURN_ALL,
                    condicion,
                    parametrosCondicion,
                    null,
                    null,
                    null
            );
            cursor.moveToFirst();
            comentario = new Comentario();
            return comentario;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Comentario> readAll()  {
        ArrayList<Comentario> comentarios = new ArrayList<>();
        try {
            db = ayudanteBaseDeDatos.getReadableDatabase();
            Cursor cursor = db.query(
                    DbTables.TABLE_COMENTARIO,//from Comentarios
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
            if (!cursor.moveToFirst()) return comentarios;
            while (cursor.moveToNext()) {
                Comentario ComentarioObtenidaDeBD = new Comentario(
                        cursor.getInt(0),
                        new Date(cursor.getString(1).replace("-","/")),
                        cursor.getString(2),
                        new Usuario(cursor.getInt(3)),
                        new Post(cursor.getInt(4))
                );
                comentarios.add(ComentarioObtenidaDeBD);
            }
            // Fin del ciclo. Cerramos cursor y regresamos la lista de Comentarios :)
            cursor.close();
            return comentarios;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public List<Comentario> readAll(Post post)  {
        ArrayList<Comentario> comentarios = new ArrayList<>();
        try {
            db = ayudanteBaseDeDatos.getReadableDatabase();
            String[] argumentos = {String.valueOf(post.getIdPost())};
            Cursor cursor = db.rawQuery(QUERY_BY_ID_POST, argumentos);
            //Hubo un error
            if (cursor == null) return null;
            // Si no hay datos, igualmente regresamos la lista vacía
            if (!cursor.moveToFirst()) return comentarios;
            while (cursor.moveToNext()) {
                Comentario ComentarioObtenidaDeBD = new Comentario(
                        cursor.getInt(0),
                        new Date(cursor.getString(1).replace("-","/")),
                        cursor.getString(2),
                        new Usuario(cursor.getInt(3), cursor.getString(4)),
                        new Post(cursor.getInt(5))
                );
                comentarios.add(ComentarioObtenidaDeBD);
            }
            // Fin del ciclo. Cerramos cursor y regresamos la lista de Comentarios :)
            cursor.close();
            return comentarios;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}