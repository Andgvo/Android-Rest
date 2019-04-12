package com.example.androidclientapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AyudanteBaseDeDatos extends SQLiteOpenHelper {
    public static final String NOMBRE_BASE_DE_DATOS = "proyecto2";
        private static final String TABLE_USUARIO =
                "create table IF NOT EXISTS Usuario("+
                "    idUsuario integer primary key,"+
                "	nombreUsuario text not null,"+
                "    apellidoUsuario text not null,"+
                "    emailUsuario text not null,"+
                "    passwordUsuario text not null"+
                ");";
    public static final String TABLE_POST = "create table IF NOT EXISTS Post("+
            "	idPost integer not null primary key,"+
            "	tituloPost text not null,"+
            "    categoriaPost text not null,"+
            "	 fechaPost text not null,"+
            "    resumenPost text not null,"+
            "    contenidoPost text not null,"+
            "    urlImagenPost text,"+
            "    idUsuario integer not null,"+
            "    foreign key(idUsuario) references Usuario(idUsuario) on update cascade on delete cascade"+
            ");";
    public static final String TABLE_COMENTARIO = "create table IF NOT EXISTS Comentario("+
            "	idComentario integer not null primary key,"+
            "	fechaComentario text not null,"+
            "	contenidoComentario text not null,"+
            "   idUsuario integer not null,"+
            "   idPost integer not null,"+
            "	foreign key(idUsuario) references Usuario(idUsuario) on update cascade on delete cascade,"+
            "    foreign key(idPost) references Post(idPost) on update cascade on delete cascade"+
            ");";

    private static final int VERSION_BASE_DE_DATOS = 1;

    public AyudanteBaseDeDatos(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USUARIO);
        db.execSQL(TABLE_POST);
        db.execSQL(TABLE_COMENTARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}