
package com.ipn.mx.blog.servlet;

import com.ipn.mx.blog.modelo.dao.ComentarioDAO;
import com.ipn.mx.blog.modelo.dto.Comentario;
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
public class ComentarioServlet extends HttpServlet {
    private final ComentarioDAO dao = new ComentarioDAO();
    
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
                    insertComentario(request, response);
                    break;
                case "UPDATE":
                    updateComentario(request, response);
                    break;
                case "DELETE":
                    deleteComentario(request, response);
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
            Logger.getLogger(ComentarioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ComentarioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private void insertComentario(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Usuario usuario = new Usuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
        Post post = new Post(Integer.parseInt( request.getParameter("txtIdPost")));
        Comentario comentario = new Comentario(
            request.getParameter("txtContenido"),
            usuario,
            post
        );
        dao.create(comentario);
        response.sendRedirect("post.jsp?idPost="+post.getIdPost());
    }

    private void updateComentario(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Usuario usuario = new Usuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
        Post post = new Post(Integer.parseInt( request.getParameter("txtIdPost")));
        Comentario comentario = new Comentario(
            Integer.parseInt(request.getParameter("txtIdComentario")),
            request.getParameter("txtContenido"),
            usuario,
            post
        );
        dao.update(comentario);
        response.sendRedirect("post.jsp?idPost="+post.getIdPost());
    }

    private void deleteComentario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Usuario usuario = new Usuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
        Post post = new Post(Integer.parseInt(request.getParameter("txtIdPost")));
        Comentario comentario = new Comentario(
            Integer.parseInt(request.getParameter("txtIdComentario")),
            usuario,
            post
        );
        dao.delete(comentario);
        response.sendRedirect("post.jsp?idPost="+post.getIdPost());
    }
}
