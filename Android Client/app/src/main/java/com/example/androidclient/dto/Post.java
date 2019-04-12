package com.example.androidclient.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author andres
 */
public class Post implements Serializable {

    private int idPost;
    private String tituloPost;
    private String categoriaPost;
    private Date fechaPost;
    private String resumenPost;
    private String contenidoPost;
    private String urlImagenPost;
    private Usuario idUsuario;
    private List<Comentario> comentarioList;

    public Post() {
        idUsuario = new Usuario();
    }

    public Post(Integer idPost) {
        this.idPost = idPost;
    }

    public Post(Integer idPost, Usuario idUsuario) {
        this.idPost = idPost;
        this.idUsuario = idUsuario;
    }

    public Post(String tituloPost, String categoriaPost, String resumenPost, String contenidoPost, String urlImagenPost, Usuario idUsuario) {
        this.tituloPost = tituloPost;
        this.categoriaPost = categoriaPost;
        this.fechaPost = new Date();
        this.resumenPost = resumenPost;
        this.contenidoPost = contenidoPost;
        this.urlImagenPost = urlImagenPost;
        this.idUsuario = idUsuario;
    }

    public Post(int idPost, String tituloPost, String categoriaPost, String resumenPost, String contenidoPost, String urlImagenPost, Usuario idUsuario) {
        this.idPost = idPost;
        this.tituloPost = tituloPost;
        this.categoriaPost = categoriaPost;
        this.fechaPost = new Date();
        this.resumenPost = resumenPost;
        this.contenidoPost = contenidoPost;
        this.urlImagenPost = urlImagenPost;
        this.idUsuario = idUsuario;
    }

    public Post(int idPost, String tituloPost, String categoriaPost, Date fechaPost, String resumenPost, String contenidoPost, String urlImagenPost, Usuario idUsuario) {
        this.idPost = idPost;
        this.tituloPost = tituloPost;
        this.categoriaPost = categoriaPost;
        this.fechaPost = fechaPost;
        this.resumenPost = resumenPost;
        this.contenidoPost = contenidoPost;
        this.urlImagenPost = urlImagenPost;
        this.idUsuario = idUsuario;
    }

    public Post(int idPost, String tituloPost, String categoriaPost, String resumenPost, String contenidoPost, String urlImagenPost) {
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public String getTituloPost() {
        return tituloPost;
    }

    public void setTituloPost(String tituloPost) {
        this.tituloPost = tituloPost;
    }

    public String getCategoriaPost() {
        return categoriaPost;
    }

    public void setCategoriaPost(String categoriaPost) {
        this.categoriaPost = categoriaPost;
    }

    public Date getFechaPost() {
        return fechaPost;
    }

    public void setFechaPost(Date fechaPost) {
        this.fechaPost = fechaPost;
    }

    public String getResumenPost() {
        return resumenPost;
    }

    public void setResumenPost(String resumenPost) {
        this.resumenPost = resumenPost;
    }

    public String getContenidoPost() {
        return contenidoPost;
    }

    public void setContenidoPost(String contenidoPost) {
        this.contenidoPost = contenidoPost;
    }

    public String getUrlImagenPost() {
        return urlImagenPost;
    }

    public void setUrlImagenPost(String urlImagenPost) {
        this.urlImagenPost = urlImagenPost;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("idPost = ").append(idPost)
                .append("\ttituloPost = ").append(tituloPost)
                .append("\t categoriaPost = ").append(categoriaPost)
                .append("\t resumenPost =").append(resumenPost)
                .append("\t contenido = ").append(contenidoPost);
        return sb.toString();
    }

}