package com.example.androidclient.utilerias;

public abstract class DbTables {
    public static final String NOMBRE_BASE_DE_DATOS = "proyecto2";
    //ENTIDAD USUSUARIO
    public static final String TABLE_USUARIO = "Usuario";
    public static final String USUARIO_ID = "idUsuario";
    public static final String USUARIO_NOMBRE = "nombreUsuario";
    public static final String USUARIO_APELLIDO = "apellidoUsuario";
    public static final String USUARIO_CORREO = "correoUsuario";
    public static final String USUARIO_PASSWORD = "passwordUsuario";
    //ENTIDAD POST
    public static final String TABLE_POST = "Post";
    public static final String POST_ID = "idPost";
    public static final String POST_TITULO = "tituloPost";
    public static final String POST_CATEGORIA = "categoriaPost";
    public static final String POST_FECHA = "fechaPost";
    public static final String POST_RESUMEN = "resumenPost";
    public static final String POST_CONTENIDO = "contenidoPost";
    public static final String POST_URL = "urlPost";
    public static final String POST_ID_USUARIO =  USUARIO_ID;

    //ENTIDAD COMENTARIO
    public static final String TABLE_COMENTARIO = "Comentario";
    public static final String COMENTARIO_ID = "idComentario";
    public static final String COMENTARIO_FECHA = "fechaComentario";
    public static final String COMENTARIO_CONTENIDO = "contenidoComentario";
    public static final String COMENTARIO_ID_USUARIO = USUARIO_ID;
    public static final String COMENTARIO_ID_POST = POST_ID;

    private static final String CREATE_TABLE_USUARIO =
            "create table IF NOT EXISTS Usuario("+
                "    idUsuario integer primary key,"+
                "	nombreUsuario text not null,"+
                "    apellidoUsuario text not null,"+
                "    emailUsuario text not null,"+
                "    passwordUsuario text not null"+
             ");";
    public static final String CREATE_TABLE_POST =
            "create table IF NOT EXISTS Post("+
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
    public static final String CREATE_TABLE_COMENTARIO =
            "create table IF NOT EXISTS Comentario("+
            "	idComentario integer not null primary key,"+
            "	fechaComentario text not null,"+
            "	contenidoComentario text not null,"+
            "   idUsuario integer not null,"+
            "   idPost integer not null,"+
            "	foreign key(idUsuario) references Usuario(idUsuario) on update cascade on delete cascade,"+
            "    foreign key(idPost) references Post(idPost) on update cascade on delete cascade"+
            ");";


}
