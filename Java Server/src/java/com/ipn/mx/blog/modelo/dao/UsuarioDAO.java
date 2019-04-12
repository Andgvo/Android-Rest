package com.ipn.mx.blog.modelo.dao;

import com.ipn.mx.blog.modelo.dto.Usuario;
import com.mysql.jdbc.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class UsuarioDAO extends Conexion implements DAO<Usuario>{

    private static final String SP_INSERT = "{ call spInsertUsuario(?,?,?,?) }";
    private static final String SP_UPDATE = "{ call spUpdateUsuario(?,?,?,?,?) }";
    private static final String SP_DELETE = "{ call spDeleteUsuario(?) }";
    private static final String SP_SELECT = "{ call spSelectUsuario(?) }";
    private static final String SP_SELECT_ALL = "{ call spSelectAllUsuario() }";
    private static final String SP_LOGIN = "{ call spLoginUsuario(?,?) }";

    @Override
    public void create(Usuario usuario) {       
        CallableStatement cs = null;
        try {
            getConexion();
            cs = (CallableStatement) conexion.prepareCall(SP_INSERT);            
            cs.setString(1, usuario.getNombreUsuario());
            cs.setString(2, usuario.getApellidoUsuario());
            cs.setString(3, usuario.getEmailUsuario());
            cs.setString(4, usuario.getPasswordUsuario());
            cs.executeUpdate();
        } catch(SQLException ex){
            System.err.println(ex);
        } finally {
            try {
                cerrar(cs);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            cerrar();
        }
    }

    @Override
    public void update(Usuario usuario) throws SQLException {
        CallableStatement cs = null;
        try {
            getConexion();
            cs = (CallableStatement) conexion.prepareCall(SP_UPDATE);
            cs.setInt(1, usuario.getIdUsuario());
            cs.setString(2, usuario.getNombreUsuario());
            cs.setString(3, usuario.getApellidoUsuario());
            cs.setString(4, usuario.getEmailUsuario());
            cs.setString(5, usuario.getPasswordUsuario());
            cs.executeUpdate();
        } catch(SQLException ex){
            System.err.println(ex);
        } finally {
            cerrar(cs);
            cerrar();
        }
    }

    @Override
    public void delete(Usuario usuario) throws SQLException {
        CallableStatement cs = null;
        try {
            getConexion();
            cs = (CallableStatement) conexion.prepareCall(SP_DELETE);            
            cs.setInt(1, usuario.getIdUsuario());
            cs.executeUpdate();
        } finally {
            cerrar(cs);
            cerrar();
        }
    }

    @Override
    public Usuario read(Usuario usuario) throws SQLException {
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            getConexion();
            cs = (CallableStatement) conexion.prepareCall(SP_SELECT);            
            cs.setInt(1, usuario.getIdUsuario());
            rs = cs.executeQuery();
            List resultados = obtenerResultados(rs);
            if (resultados.size() > 0) {
                return (Usuario) resultados.get(0);
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
    public List<Usuario> readAll() throws SQLException {
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            getConexion();
            cs = (CallableStatement) conexion.prepareCall(SP_SELECT_ALL);            
            rs = cs.executeQuery();
            List<Usuario> resultados = obtenerResultados(rs);
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
    
    public Usuario login(Usuario usuario) throws SQLException {
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            getConexion();
            cs = (CallableStatement) conexion.prepareCall(SP_LOGIN);            
            cs.setString(1, usuario.getNombreUsuario());
            cs.setString(2, usuario.getPasswordUsuario());
            rs = cs.executeQuery();
            List resultados = obtenerResultados(rs);
            if (resultados.size() > 0) {
                return (Usuario) resultados.get(0);
            } else {
                return null;
            }
        } finally {
            cerrar(rs);
            cerrar(cs);
            cerrar();
        }
    }

    private List obtenerResultados(ResultSet rs) throws SQLException {
        List resultados = new ArrayList();
        while (rs.next()) {
            Usuario usuario = new Usuario(
                    rs.getInt("idUsuario"),
                    rs.getString("nombreUsuario"),
                    rs.getString("apellidoUsuario"),
                    rs.getString("emailUsuario"),
                    rs.getString("passwordUsuario")
            );
            resultados.add(usuario);
        }
        return resultados;
    }

}
