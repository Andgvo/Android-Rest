package com.example.androidclient.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author andres
 */
public class Comentario implements Serializable {

    private int idComentario;
    private Date fechaComentario;
    private String contenidoComentario;
    private Usuario idUsuario;
    private Post idPost;

    public Comentario() {
    }

    public Comentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public Comentario(String contenidoComentario, Usuario idUsuario, Post idPost) {
        this.fechaComentario = new Date();
        this.contenidoComentario = contenidoComentario;
        this.idUsuario = idUsuario;
        this.idPost = idPost;
    }

    public Comentario(int idComentario, String contenidoComentario, Usuario idUsuario, Post idPost) {
        this.idComentario = idComentario;
        this.fechaComentario = new Date();
        this.contenidoComentario = contenidoComentario;
        this.idUsuario = idUsuario;
        this.idPost = idPost;
    }

    public Comentario(int idComentario, Usuario idUsuario, Post idPost) {
        this.idComentario = idComentario;
        this.idUsuario = idUsuario;
        this.idPost = idPost;
    }

    public Comentario(int idComentario, Date fechaComentario, String contenidoComentario) {
        this.idComentario = idComentario;
        this.fechaComentario = fechaComentario;
        this.contenidoComentario = contenidoComentario;
    }

    public Comentario(int idComentario, Date fechaComentario, String contenidoComentario, Usuario idUsuario, Post idPost) {
        this.idComentario = idComentario;
        this.fechaComentario = fechaComentario;
        this.contenidoComentario = contenidoComentario;
        this.idUsuario = idUsuario;
        this.idPost = idPost;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public Date getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Date fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public String getContenidoComentario() {
        return contenidoComentario;
    }

    public void setContenidoComentario(String contenidoComentario) {
        this.contenidoComentario = contenidoComentario;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Post getIdPost() {
        return idPost;
    }

    public void setIdPost(Post idPost) {
        this.idPost = idPost;
    }

    @Override
    public String toString() {
        return "com.ipn.mx.blog.modelo.dto.Comentario[ idComentario=" + idComentario + " ]";
    }

}