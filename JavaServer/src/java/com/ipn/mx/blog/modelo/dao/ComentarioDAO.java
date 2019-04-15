package com.ipn.mx.blog.modelo.dao;

import com.ipn.mx.blog.modelo.dto.Comentario;
import com.ipn.mx.blog.modelo.dto.Post;
import com.ipn.mx.blog.modelo.dto.Usuario;
import com.mysql.jdbc.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andres
 */
public class ComentarioDAO extends Conexion implements DAO<Comentario>{

    private static final String SP_INSERT = "{ call spInsertComentario(?,?,?,?) }";
    private static final String SP_UPDATE = "{ call spUpdateComentario(?,?,?,?,?) }";
    private static final String SP_DELETE = "{ call spDeleteComentario(?,?,?) }";
    private static final String SP_SELECT = "{ call spSelectComentario(?) }";
    private static final String SP_SELECT_ALL = "{ call spSelectAllComentario(?) }";

    @Override
    public void create(Comentario comentario) throws SQLException {       
        CallableStatement cs = null;
        try {
            getConexion();
            cs = (CallableStatement) conexion.prepareCall(SP_INSERT);            
            //cs.setDate(1, new java.sql.Timestamp (comentario.getFechaComentario().getTime()));
            cs.setTimestamp(1, new Timestamp(comentario.getFechaComentario().getTime()));
            cs.setString(2, comentario.getContenidoComentario());
            cs.setInt(3, comentario.getIdUsuario().getIdUsuario());
            cs.setInt(4, comentario.getIdPost().getIdPost());
            cs.executeUpdate();
        } catch(SQLException ex){
            System.err.println(ex);
        } finally {
            cerrar(cs);
            cerrar();
        }
    }

    @Override
    public void update(Comentario comentario) throws SQLException {
        CallableStatement cs = null;
        try {
            getConexion();
            cs = (CallableStatement) conexion.prepareCall(SP_UPDATE);
            cs.setInt(1, comentario.getIdComentario());
            cs.setTimestamp(2, new Timestamp(comentario.getFechaComentario().getTime()));
            cs.setString(3, comentario.getContenidoComentario());
            cs.setInt(4, comentario.getIdUsuario().getIdUsuario());
            cs.setInt(5, comentario.getIdPost().getIdPost());
            cs.executeUpdate();
        } catch(SQLException ex){
            System.err.println(ex);
        } finally {
            cerrar(cs);
            cerrar();
        }
    }

    @Override
    public void delete(Comentario comentario) throws SQLException {
        CallableStatement cs = null;
        try {
            getConexion();
            cs = (CallableStatement) conexion.prepareCall(SP_DELETE);            
            cs.setInt(1, comentario.getIdComentario());
            cs.setInt(2, comentario.getIdUsuario().getIdUsuario());
            cs.setInt(3, comentario.getIdPost().getIdPost());
            cs.executeUpdate();
        } finally {
            cerrar(cs);
            cerrar();
        }
    }

    @Override
    public Comentario read(Comentario comentario) throws SQLException {
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            getConexion();
            cs = (CallableStatement) conexion.prepareCall(SP_SELECT);            
            cs.setInt(1, comentario.getIdComentario());
            rs = cs.executeQuery();
            List resultados = obtenerResultados(rs);
            if (resultados.size() > 0) {
                return (Comentario) resultados.get(0);
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
    public List<Comentario> readAll() throws SQLException {
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            getConexion();
            cs = (CallableStatement) conexion.prepareCall(SP_SELECT_ALL);
            rs = cs.executeQuery();
            List<Comentario> resultados = obtenerResultados(rs);
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
    
    public List<Comentario> readAll(Post post) throws SQLException {
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            getConexion();
            cs = (CallableStatement) conexion.prepareCall(SP_SELECT_ALL);
            cs.setInt(1, post.getIdPost());
            rs = cs.executeQuery();
            List<Comentario> resultados = obtenerResultados(rs);
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
            Post post = new Post(rs.getInt("idPost") );
            Comentario comentario = new Comentario(
                    rs.getInt("idComentario"),
                    rs.getDate("fechaComentario"),
                    rs.getString("contenidoComentario"),
                    usuario,
                    post
            );
            resultados.add(comentario);
        }
        return resultados;
    }
}