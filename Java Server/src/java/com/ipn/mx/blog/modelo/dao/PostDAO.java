/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.blog.modelo.dao;

import com.ipn.mx.blog.modelo.dto.Post;
import com.ipn.mx.blog.modelo.dto.Usuario;
import com.mysql.jdbc.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andres
 */
public class PostDAO extends Conexion implements DAO<Post>{

    private static final String SP_INSERT = "{ call spInsertPost(?,?,?,?,?,?,?) }";
    private static final String SP_UPDATE = "{ call spUpdatePost(?,?,?,?,?,?,?,?) }";
    private static final String SP_DELETE = "{ call spDeletePost(?) }";
    private static final String SP_SELECT = "{ call spSelectPost(?) }";
    private static final String SP_SELECT_ALL = "{ call spSelectAllPost() }";
    private static final String SP_SELECT_CATEGORIA = "{ call spSelectPostCategoria(?) }";
    private static final String SP_SELECT_MES = "{ call spSelectPostMes(?) }";
    private static final String SP_SELECT_USUARIO = "{ call spSelectPostUsuario(?) }";

    @Override
    public void create(Post post) throws SQLException {       
        CallableStatement cs = null;
        try {
            getConexion();
            cs = (CallableStatement) conexion.prepareCall(SP_INSERT);            
            cs.setString(1, post.getTituloPost());
            cs.setString(2, post.getCategoriaPost());
            cs.setDate(3, new java.sql.Date (post.getFechaPost().getTime()) );
            cs.setString(4, post.getResumenPost());
            cs.setString(5, post.getContenidoPost());
            cs.setString(6, post.getUrlImagenPost());
            cs.setInt(7, post.getIdUsuario().getIdUsuario());
            cs.executeUpdate();
        } catch(SQLException ex){
            System.err.println(ex);
        } finally {
            cerrar(cs);
            cerrar();
        }
    }

    @Override
    public void update(Post post) throws SQLException {
        CallableStatement cs = null;
        int resultado;
        try {
            getConexion();
            cs = (CallableStatement) conexion.prepareCall(SP_UPDATE);
            cs.setInt(1, post.getIdPost());
            cs.setString(2, post.getTituloPost());
            cs.setString(3, post.getCategoriaPost());
            cs.setDate(4, new java.sql.Date (post.getFechaPost().getTime()) );
            cs.setString(5, post.getResumenPost());
            cs.setString(6, post.getContenidoPost());
            cs.setString(7, post.getUrlImagenPost());
            cs.setInt(8, post.getIdUsuario().getIdUsuario());
            resultado = cs.executeUpdate();
        } catch(SQLException ex){
            System.err.println(ex);
        } finally {
            cerrar(cs);
            cerrar();
        }
    }

    @Override
    public void delete(Post post) throws SQLException {
        CallableStatement cs = null;
        try {
            getConexion();
            cs = (CallableStatement) conexion.prepareCall(SP_DELETE);            
            cs.setInt(1, post.getIdPost());
            cs.executeUpdate();
        } finally {
            cerrar(cs);
            cerrar();
        }
    }

    @Override
    public Post read(Post post) throws SQLException {
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            getConexion();
            cs = (CallableStatement) conexion.prepareCall(SP_SELECT);            
            cs.setInt(1, post.getIdPost());
            rs = cs.executeQuery();
            List resultados = obtenerResultados(rs);
            if (resultados.size() > 0) {
                return (Post) resultados.get(0);
            } else {
                return null;
            }
        } finally {
            cerrar(rs);
            cerrar(cs);
            cerrar();
        }
    }

    @Override
    public List<Post> readAll() throws SQLException {
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            getConexion();
            cs = (CallableStatement) conexion.prepareCall(SP_SELECT_ALL);            
            rs = cs.executeQuery();
            List<Post> resultados = obtenerResultados(rs);
            if (resultados.size() > 0) {
                return resultados;
            } else {
                return null;
            }
        } catch(SQLException ex){
            System.err.println(ex);
            return null;
        }finally {
            cerrar(rs);
            cerrar(cs);
            cerrar();
        }
    }
    
    public List<Post> readAllbyCategory(Post post) throws SQLException {
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            getConexion();
            cs = (CallableStatement) conexion.prepareCall(SP_SELECT_CATEGORIA);
            cs.setString(1, post.getCategoriaPost());
            rs = cs.executeQuery();
            List<Post> resultados = obtenerResultados(rs);
            if (resultados.size() > 0) {
                return resultados;
            } else {
                return null;
            }
        } catch(SQLException ex){
            System.err.println(ex);
            return null;
        }finally {
            cerrar(rs);
            cerrar(cs);
            cerrar();
        }
    }
    
    public List<Post> readAllbyMonth(Post post) throws SQLException {
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            getConexion();
            cs = (CallableStatement) conexion.prepareCall(SP_SELECT_MES);
            cs.setDate(1, new Date(post.getFechaPost().getTime()) );
            rs = cs.executeQuery();
            List<Post> resultados = obtenerResultados(rs);
            if (resultados.size() > 0) {
                return resultados;
            } else {
                return null;
            }
        } catch(SQLException ex){
            System.err.println(ex);
            return null;
        }finally {
            cerrar(rs);
            cerrar(cs);
            cerrar();
        }
    }
    
    public List<Post> readAllbyUser(Post post) throws SQLException {
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            getConexion();
            cs = (CallableStatement) conexion.prepareCall(SP_SELECT_USUARIO);
            cs.setInt(1, post.getIdUsuario().getIdUsuario());
            rs = cs.executeQuery();
            List<Post> resultados = obtenerResultados(rs);
            if (resultados.size() > 0) {
                return resultados;
            } else {
                return null;
            }
        } catch(SQLException ex){
            System.err.println(ex);
            return null;
        }finally {
            cerrar(rs);
            cerrar(cs);
            cerrar();
        }
    }
    
    private List obtenerResultados(ResultSet rs) throws SQLException {
        List resultados = new ArrayList();
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("idUsuario"));
            usuario.setNombreUsuario(rs.getString("nombreUsuario"));
            usuario.setApellidoUsuario(rs.getString("apellidoUsuario"));
            usuario.setEmailUsuario(rs.getString("emailUsuario"));
            Post post = new Post(
                    rs.getInt("idPost"),
                    rs.getString("tituloPost"),
                    rs.getString("categoriaPost"),
                    rs.getDate("fechaPost"),
                    rs.getString("resumenPost"),
                    rs.getString("contenidoPost"),
                    rs.getString("urlImagenPost"),
                    usuario
            );
            resultados.add(post);
        }
        return resultados;
    }
}