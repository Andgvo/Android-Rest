package com.ipn.mx.blog.servlet;

import com.ipn.mx.blog.modelo.dao.PostDAO;
import com.ipn.mx.blog.modelo.dto.Post;
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

/**
 *
 * @author andres
 */
public class PostServlet extends HttpServlet {
    private final PostDAO dao = new PostDAO();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String accion = request.getParameter("txtAccion");
            switch(accion){
                case "INSERT":
                    insertPost(request, response);
                    break;
                case "UPDATE":
                    updatePost(request, response);
                    break;
                case "DELETE":
                    deletePost(request, response);
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
            Logger.getLogger(PostServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PostServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private void insertPost(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Usuario usuario = new Usuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
        Post post = new Post(
            request.getParameter("txtTitulo"),
            request.getParameter("txtCategoria"),
            request.getParameter("txtResumen"),
            request.getParameter("txtContenido"),
            "url :'v",
            usuario
        );
        dao.create(post);
        response.sendRedirect("filtro.jsp?accion=USUARIO&idUser="+usuario.getIdUsuario());
    }

    private void updatePost(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Usuario usuario = new Usuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
        Post post = new Post(
            Integer.parseInt( request.getParameter("txtIdPost")),
            request.getParameter("txtTitulo"),
            request.getParameter("txtCategoria"),
            request.getParameter("txtResumen"),
            request.getParameter("txtContenido"),
            "url :'v",
            usuario
        );
        dao.update(post);        
        response.sendRedirect("filtro.jsp?accion=USUARIO&idUser="+usuario.getIdUsuario());
    }

    private void deletePost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Usuario usuario = new Usuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
        Post post = new Post(
            Integer.parseInt(request.getParameter("txtIdPost")),
            usuario
        );
        dao.delete(post);
        response.sendRedirect("filtro.jsp?accion=USUARIO&idUser="+usuario.getIdUsuario());
    }
}
