package com.example.androidclient.utilerias;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AyudanteBaseDeDatos extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    public AyudanteBaseDeDatos(Context context) {
        super(context, DbTables.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbTables.CREATE_TABLE_USUARIO);
        db.execSQL(DbTables.CREATE_TABLE_POST);
        db.execSQL(DbTables.CREATE_TABLE_COMENTARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}