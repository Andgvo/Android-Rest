package com.example.androidclient.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidclient.dto.Usuario;
import com.example.androidclient.utilerias.AyudanteBaseDeDatos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements DAO<Usuario> {
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private String NOMBRE_TABLA = "Usuario";

    @Override
    public void create(Usuario usuario) throws SQLException {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombreUsuario", usuario.getNombreUsuario());
        valoresParaInsertar.put("apellidoUsuario", usuario.getApellidoUsuario());
        valoresParaInsertar.put("emailUsuario", usuario.getEmailUsuario());
        valoresParaInsertar.put("passwordUsuario", usuario.getPasswordUsuario());
        baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    @Override
    public void update(Usuario usuario) throws SQLException {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombreUsuario", usuario.getNombreUsuario());
        valoresParaActualizar.put("apellidoUsuario", usuario.getApellidoUsuario());
        valoresParaActualizar.put("emailUsuario", usuario.getEmailUsuario());
        valoresParaActualizar.put("passwordUsuario", usuario.getPasswordUsuario());
        // where id...
        String campoParaActualizar = "id = ?";
        String[] argumentosParaActualizar = {String.valueOf(usuario.getIdUsuario())};
        baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    @Override
    public void delete(Usuario usuario) throws SQLException {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(usuario.getIdUsuario())};
        baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }

    @Override
    public Usuario read(Usuario usuario) throws SQLException {
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
                NOMBRE_TABLA,//from Usuarios
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
}
