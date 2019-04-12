package com.example.androidclient.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidclient.dto.Post;
import com.example.androidclient.utilerias.AyudanteBaseDeDatos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDAO implements DAO<Post> {
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private String NOMBRE_TABLA = "Post";

    @Override
    public void create(Post Post) throws SQLException {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("tituloPost", Post.getTituloPost());
        valoresParaInsertar.put("categoriaPost", Post.getCategoriaPost());
        valoresParaInsertar.put("fechaPost", Post.getFechaPost().toString());
        valoresParaInsertar.put("resumenPost", Post.getResumenPost());
        valoresParaInsertar.put("contenidoPost", Post.getContenidoPost());
        valoresParaInsertar.put("urlImagenPost", Post.getUrlImagenPost());
        baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    @Override
    public void update(Post Post) throws SQLException {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("tituloPost", Post.getTituloPost());
        valoresParaActualizar.put("categoriaPost", Post.getCategoriaPost());
        valoresParaActualizar.put("fechaPost", Post.getFechaPost().toString());
        valoresParaActualizar.put("resumenPost", Post.getResumenPost());
        valoresParaActualizar.put("contenidoPost", Post.getContenidoPost());
        valoresParaActualizar.put("urlImagenPost", Post.getUrlImagenPost());
        // where id...
        String campoParaActualizar = "id = ?";
        String[] argumentosParaActualizar = {String.valueOf(Post.getIdPost())};
        baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    @Override
    public void delete(Post Post) throws SQLException {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(Post.getIdPost())};
        baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }

    @Override
    public Post read(Post Post) throws SQLException {
        return null;
    }

    @Override
    public List<Post> readAll() throws SQLException {
        ArrayList<Post> Posts = new ArrayList<>();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();
        // SELECT nombre, edad, id
        String[] columnasAConsultar =
                {"idPost", "tituloPost", "categoriaPost", "resumenPost", "contenidoPost","urlImagenPost"};
        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,//from Posts
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
            return Posts;

        }
        // Si no hay datos, igualmente regresamos la lista vacía
        if (!cursor.moveToFirst()) return Posts;

        // En caso de que sí haya, iteramos y vamos agregando los
        // datos a la lista de Posts
        do {
            // El 0 es el número de la columna, como seleccionamos
            // nombre, edad,id entonces el nombre es 0, edad 1 e id es 2
            int idPost = cursor.getInt(0);
            String tituloPost = cursor.getString(1);
            String categoriaPost = cursor.getString(2);
            String resumenPost = cursor.getString(3);
            String contenidoPost = cursor.getString(4);
            String urlImagenPost = cursor.getString(5);
            Post PostObtenidaDeBD = new Post(
                    idPost, tituloPost, categoriaPost, resumenPost, contenidoPost, urlImagenPost);
            Posts.add(PostObtenidaDeBD);
        } while (cursor.moveToNext());

        // Fin del ciclo. Cerramos cursor y regresamos la lista de Posts :)
        cursor.close();
        return Posts;
    }
}
