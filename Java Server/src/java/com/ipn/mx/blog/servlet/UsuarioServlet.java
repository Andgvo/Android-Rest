
package com.ipn.mx.blog.servlet;

import com.ipn.mx.blog.modelo.dao.UsuarioDAO;
import com.ipn.mx.blog.modelo.dto.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author andres
 */
public class UsuarioServlet extends HttpServlet {
    private UsuarioDAO dao = new UsuarioDAO();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String accion = request.getParameter("txtAccion");
            switch(accion){
                case "LOGIN":
                    loginUsuario(request, response);
                    break;
                case "INSERT":
                    insertUsuario(request, response);
                    break;
                case "UPDATE":
                    updateUsuario(request, response);
                    break;
                case "DELETE":
                    deleteUsuario(request, response);
                    break;
                default: break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void insertUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario usuario = new Usuario(
            request.getParameter("txtUsuario"),
            request.getParameter("txtApellido"),
            request.getParameter("txtEmail"),
            request.getParameter("txtPassword") );
        dao.create(usuario);
        response.sendRedirect("index.jsp");
    }

    private void loginUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Usuario usuario = new Usuario(
            request.getParameter("txtUsuario"),
            request.getParameter("txtPassword"));
        usuario = dao.login(usuario);
        if(usuario != null){
            HttpSession sesion = request.getSession();
            sesion.setAttribute("user", usuario);
            response.sendRedirect("home.jsp");
        }else
            response.sendRedirect("index.jsp");
    }

    private void updateUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Usuario usuario = new Usuario(
            Integer.parseInt(request.getParameter("txtIdUsuario")),
            request.getParameter("txtUsuario"),
            request.getParameter("txtApellido"),
            request.getParameter("txtEmail"),
            request.getParameter("txtPassword") );
        dao.update(usuario);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("user", usuario);
        response.sendRedirect("datos.jsp");
    }

    private void deleteUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Usuario usuario = new Usuario(
            Integer.parseInt(request.getParameter("txtIdUsuario"))
        );
        dao.delete(usuario);
        response.sendRedirect("index.jsp?cerrar=true");
    }

}
