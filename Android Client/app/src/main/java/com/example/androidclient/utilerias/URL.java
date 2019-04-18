package com.example.androidclient.utilerias;

import com.example.androidclient.dto.Comentario;
import com.example.androidclient.dto.Post;
import com.example.androidclient.dto.Usuario;

public class URL {
    public static String dominioServidor = "http://10.100.74.159:8080";
    private static final String SERVIDOR = "/JavaServer";
    private static final String USUARIO_SERVLET  = "/UsuarioRest";
    private static final String POST_SERVLET  = "/PostRest";
    private static final String COMENTARIO_SERVLET  = "/ComentarioRest";

    public static String goServletUsuario(String accion, Usuario usuario){
        StringBuilder sb = new StringBuilder(dominioServidor);
        sb.append(SERVIDOR).append(USUARIO_SERVLET)
                .append("?txtAccion=").append(accion)
                .append("&txtIdUsuario=").append(usuario.getIdUsuario())
                .append("&txtUsuario=").append(usuario.getNombreUsuario())
                .append("&txtApellido=").append(usuario.getApellidoUsuario())
                .append("&txtEmail=").append(usuario.getEmailUsuario())
                .append("&txtPassword=").append(usuario.getPasswordUsuario());
        return sb.toString();
    }

    public static String goServletPost(String accion, Usuario usuario, Post post){
        StringBuilder sb = new StringBuilder(dominioServidor);
        sb.append(SERVIDOR).append(POST_SERVLET)
                .append("?txtAccion=").append(accion)
                .append("&txtIdPost=").append(post.getIdPost())
                .append("&txtIdUsuario=").append(usuario.getIdUsuario())
                .append("&txtTitulo=").append(post.getTituloPost())
                .append("&txtCategoria=").append(post.getCategoriaPost())
                .append("&txtResumen=").append(post.getResumenPost())
                .append("&txtContenido=").append(post.getContenidoPost());
        return sb.toString();
    }

    public static String goServletComentario(String accion, Usuario usuario, Post post, Comentario comentario){
        StringBuilder sb = new StringBuilder(dominioServidor);
        sb.append(SERVIDOR).append(COMENTARIO_SERVLET)
                .append("?txtAccion=").append(accion)
                .append("&txtIdComentario=").append(comentario.getIdComentario())
                .append("&txtIdPost=").append(post.getIdPost())
                .append("&txtIdUsuario=").append(usuario.getIdUsuario())
                .append("&txtContenido=").append(comentario.getContenidoComentario());
        return sb.toString();
    }
}
