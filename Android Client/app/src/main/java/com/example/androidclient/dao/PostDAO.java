package com.example.androidclient.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidclient.dto.Post;
import com.example.androidclient.dto.Usuario;
import com.example.androidclient.utilerias.AyudanteBaseDeDatos;
import com.example.androidclient.utilerias.DbTables;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDAO implements DAO<Post>{

    private static final String CONDICION_BY_ID = DbTables.POST_ID + " = ? ";
    private static final String QUERY_INNER_JOIN =
            "SELECT p.idPost, p.tituloPost, p.categoriaPost, p.fechaPost, p.resumenPost, p.contenidoPost, p.urlImagenPost, u.idUsuario, u.nombreUsuario FROM Post p, Usuario u WHERE  p.idUsuario = u.idUsuario;";
    private static final String[] CAMPOS_RETURN_ALL = {
            DbTables.POST_ID,
            DbTables.POST_TITULO,
            DbTables.POST_CATEGORIA,
            DbTables.POST_FECHA,
            DbTables.POST_RESUMEN,
            DbTables.POST_CONTENIDO,
            DbTables.POST_URL,
            DbTables.POST_ID_USUARIO
    };
    private static final SimpleDateFormat FORMATO = new SimpleDateFormat("yyyy-MM-dd");
    private int rowsAffected = 0;
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private SQLiteDatabase db;


    public PostDAO(Context context){
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(context);
    }

    @Override
    public long create(Post post) {
        long idPostAffected;
        try {
            db = ayudanteBaseDeDatos.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbTables.POST_TITULO, post.getTituloPost());
            values.put(DbTables.POST_CATEGORIA, post.getCategoriaPost());
            values.put(DbTables.POST_FECHA, FORMATO.format(post.getFechaPost()) );
            values.put(DbTables.POST_RESUMEN, post.getResumenPost());
            values.put(DbTables.POST_CONTENIDO, post.getContenidoPost());
            values.put(DbTables.POST_URL, post.getUrlImagenPost());
            values.put(DbTables.POST_ID_USUARIO, post.getIdUsuario().getIdUsuario());
            idPostAffected = db.insert(DbTables.TABLE_POST, null, values);
            db.close();
            return idPostAffected;
        }catch ( Exception ex ) {
            ex.printStackTrace();
            db.close();
            return -1;
        }
    }

    @Override
    public int update(Post post) {
        String[] parametrosCondicion = {String.valueOf(post.getIdPost())};
        try {
            db = ayudanteBaseDeDatos.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbTables.POST_TITULO, post.getTituloPost());
            values.put(DbTables.POST_CATEGORIA, post.getCategoriaPost());
            values.put(DbTables.POST_FECHA, FORMATO.format(post.getFechaPost()) );
            values.put(DbTables.POST_RESUMEN, post.getResumenPost());
            values.put(DbTables.POST_CONTENIDO, post.getContenidoPost());
            values.put(DbTables.POST_URL, post.getUrlImagenPost());
            rowsAffected = db.update(DbTables.TABLE_POST,values,CONDICION_BY_ID,parametrosCondicion);
            db.close();
            return rowsAffected;
        }catch ( Exception ex ) {
            ex.printStackTrace();
            db.close();
            return -1;
        }
    }

    @Override
    public int delete(Post post) {
        try {
            db = ayudanteBaseDeDatos.getWritableDatabase();
            String[] argumentos = {String.valueOf(post.getIdPost())};
            rowsAffected = db.delete(DbTables.TABLE_POST, CONDICION_BY_ID, argumentos);
            db.close();
            return  rowsAffected;
        }catch (Exception ex){
            ex.printStackTrace();
            db.close();
            return -1;
        }
    }

    @Override
    public Post read(Post post) {
        String[] parametrosCondicion = {String.valueOf(post.getIdPost()), String.valueOf(post.getIdUsuario().getIdUsuario())}; // {post.getNombrePost(), post.getPasswordPost()};
        String condicion = DbTables.POST_ID + " = ? AND " + DbTables.POST_ID_USUARIO + " = ?" ;
        try{
            Cursor cursor = db.query(
                    DbTables.TABLE_POST,
                    CAMPOS_RETURN_ALL,
                    condicion,
                    parametrosCondicion,
                    null,
                    null,
                    null
            );
            cursor.moveToFirst();
            post = new Post();
            cursor.close();
            db.close();
            return post;
        }catch (Exception e) {
            e.printStackTrace();
            db.close();
            return null;
        }
    }

    @Override
    public List<Post> readAll()  {
        ArrayList<Post> posts = new ArrayList<>();
        String[] parametros = {};
        try {
            db = ayudanteBaseDeDatos.getReadableDatabase();
            Cursor cursor = db.rawQuery(QUERY_INNER_JOIN, parametros);
            //Hubo un error
            if (cursor == null) return null;
            // Si no hay datos, igualmente regresamos la lista vacía
            if (!cursor.moveToFirst()) return posts;
            do {
                Post PostObtenidaDeBD = new Post(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        new Date(cursor.getString(3).replace("-","/")),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        new Usuario(cursor.getInt(7), cursor.getString(8))
                );
                posts.add(PostObtenidaDeBD);
            } while (cursor.moveToNext());
            // Fin del ciclo. Cerramos cursor y regresamos la lista de Posts :)
            cursor.close();
            db.close();
            return posts;
        }catch (Exception ex){
            ex.printStackTrace();
            db.close();
            return null;
        }
    }

}
