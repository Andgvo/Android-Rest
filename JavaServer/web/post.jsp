<%-- 
    Document   : post
    Created on : 4/04/2019, 09:05:59 AM
    Author     : andres
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.ipn.mx.blog.modelo.dao.ComentarioDAO"%>
<%@page import="com.ipn.mx.blog.modelo.dto.Comentario"%>
<%@page import="com.ipn.mx.blog.modelo.dto.Post"%>
<%@page import="com.ipn.mx.blog.modelo.dao.PostDAO"%>
<%@page import="com.ipn.mx.blog.modelo.dto.Usuario"%>
<%@page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Inicio</title>
        <%@ include file="shared/header.jsp" %>
    </head>

    <body>
        <%
            HttpSession sesion = request.getSession();
            Usuario usuario = (Usuario) sesion.getAttribute("user");
            PostDAO dao = new PostDAO();
            ComentarioDAO daoC = new ComentarioDAO();
            Post post = new Post();
            List<Comentario> comentarios = new ArrayList();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (usuario == null) {
                response.sendRedirect("index.jsp?cerrar=true");
            } else {
                post.setIdPost(Integer.parseInt(request.getParameter("idPost")));
                post = dao.read(post);
                comentarios = daoC.readAll(post);
            }
            if(usuario != null){
        %>
        <!-- Navbar -->
        <jsp:include page="shared/navbar.jsp">
            <jsp:param name="userId" value="<%=usuario.getIdUsuario()%>"/>
        </jsp:include>
        
        <div class="container">
            <div class="jumbotron p-3 p-md-5 text-white rounded bg-dark main-blog-image" style="background-image: url('assets/img/intro_flower.jpg')">
                <div class="col-md-6 px-0">
                    <div class="block-height"></div>
                </div>
            </div>
        </div>

        <main role="main" class="container">
            <div class="row">
                <div class="col-md-8 blog-main">


                    <div class="blog-post">
                        <h2 class="blog-post-title"> <%= post.getTituloPost()%> </h2>
                        <p class="blog-post-meta">
                            El <%= post.getFechaPost()%> 
                            por <a href="filtro.jsp?accion=USUARIO&idUser=<%=post.getIdUsuario().getIdUsuario()%>">
                                <%= post.getIdUsuario().getNombreUsuario()%>
                            </a>.
                        </p>

                        <p><%= post.getResumenPost()%></p>
                        <hr>
                        <p><%= post.getContenidoPost()%> </p>

                    </div><!-- /.blog-post -->

                    <div class="row">
                        <div class="col-12 col-md-9 blog-main">
                            <h3 class="pb-3 mb-4 font-italic border-bottom">
                                Comentarios
                            </h3>
                            <% if (comentarios != null)
                                    for (Comentario comentario : comentarios) {%>
                            <div class="p-3 mb-3 bg-light rounded">
                                <div class="col-12">
                                    <h5 class="font-italic"><%= comentario.getIdUsuario().getNombreUsuario()%> </h5>
                                    <b><%= sdf.format(comentario.getFechaComentario())%></b>
                                    <p class="mb-0"> <%=comentario.getContenidoComentario()%></p>
                                </div>
                                <% if (usuario.getNombreUsuario().equals(comentario.getIdUsuario().getNombreUsuario())) {%>
                                <div class="col-12">
                                    <nav class="blog-pagination text-right">
                                        <button type="button" onclick="abrirModal('<%=comentario.getIdComentario()%>', '<%=comentario.getContenidoComentario()%>' )" class="btn btn-outline-primary">Actualizar</button>
                                        <button type="button" onclick="alertaEliminar('<%=comentario.getIdComentario()%>', '<%=comentario.getIdUsuario().getIdUsuario()%>', '<%= comentario.getIdPost().getIdPost()%>')" id="btnEliminar" class="btn btn-outline-danger">Eliminar</button>
                                    </nav>
                                </div>
                                <% } %>
                            </div>
                            <% }%>
                            <form action="ComentarioServlet" method="post">
                                <div class="form-group">
                                    <input type="hidden" name="txtAccion" value="INSERT">
                                    <input type="hidden" name="txtIdUsuario" value="<%= usuario.getIdUsuario()%>">
                                    <input type="hidden" name="txtIdPost" value="<%= post.getIdPost()%>">
                                    <label for="txtComentario"> Comentario: </label>
                                    <textarea class="form-control" id="txtContenido" name="txtContenido" placeholder="Ingresa aquí tu comentario"></textarea>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-outline-primary">Nuevo Comentario</button>
                                </div>
                            </form>
                        </div>
                    </div>

                </div><!-- /.blog-main -->

                <!-- aside bar-->
                <%@ include file="shared/asidebar.jsp" %>

            </div><!-- /.row -->

        </main><!-- /.container -->

        <div class="modal fade" id="modalEditar" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form id="formUpdateComentario" action="ComentarioServlet" method="post">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="hidden" name="txtAccion" value="UPDATE">
                                <input type="hidden" name="txtIdComentario" id="txtIdComentarioEdit" value="">
                                <input type="hidden" name="txtIdUsuario" value="<%= usuario.getIdUsuario()%>">
                                <input type="hidden" name="txtIdPost" value="<%= post.getIdPost()%>">
                                <label for="txtComentario"> Comentario: </label>
                                <textarea class="form-control" id="txtContenidoEdit" name="txtContenido" placeholder="Ingresa aquí tu comentario"></textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- footer -->
        <%@ include file="shared/footer.jsp" %>
        <script src="assets/js/sweetalert2.js"></script>
        <script>
                                            const swalWithBootstrapButtons = Swal.mixin({
                                                confirmButtonClass: 'btn btn-outline-success',
                                                cancelButtonClass: 'btn btn-outline-danger',
                                                buttonsStyling: false
                                            });

                                            function alertaEliminar(idComentario, idUsuario, idPost) {
                                                swalWithBootstrapButtons.fire({
                                                    title: '¿Estás seguro que deseas elimimar tu comentario"?',
                                                    text: "No podrás recuperar la información después",
                                                    type: 'warning',
                                                    showCancelButton: true,
                                                    confirmButtonText: 'Sí, borrar',
                                                    cancelButtonText: 'No, cancelar',
                                                    reverseButtons: true
                                                }).then((result) => {
                                                    if (result.value) {

                                                        window.location.replace("ComentarioServlet?txtAccion=DELETE&txtIdComentario=" + idComentario + "&txtIdPost=" + idPost + "&txtIdUsuario=" + idUsuario);
                                                    } else if (
                                                            // Read more about handling dismissals
                                                            result.dismiss === Swal.DismissReason.cancel
                                                            ) {
                                                        swalWithBootstrapButtons.fire(
                                                                'Cancelado',
                                                                'No se ha borrado nada',
                                                                'error'
                                                                );
                                                    }
                                                });
                                            }
                                            ;
                                            
        function abrirModal(idComentario, contenidoComentario) {
            $('#txtIdComentarioEdit').val(idComentario);
            $('#txtContenidoEdit').val(contenidoComentario);
            $('#modalEditar').modal();
        }
        </script>
        <% } %>
    </body>
</html>