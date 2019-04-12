<%-- 
    Document   : gestionPost
    Created on : 6/04/2019, 05:56:02 AM
    Author     : andres
--%>
<%@page import="com.ipn.mx.blog.modelo.dto.Post"%>
<%@page import="com.ipn.mx.blog.modelo.dao.PostDAO"%>
<%@page import="com.ipn.mx.blog.modelo.dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Inicio</title>
        <%@ include file="shared/header.jsp" %>
        <link href="assets/css/animate.min.css" rel="stylesheet">
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            Usuario usuario = (Usuario) sesion.getAttribute("user");
            PostDAO dao = new PostDAO();
            Post post = new Post();
            String accion ="";
            String titulo = "";
            String categoria = "";
            String resumen = "";
            String contenido = "";
            if (usuario == null) {
                response.sendRedirect("index.jsp?cerrar=true");
            } else {
                accion = request.getParameter("accion");
                if (accion == null) {
                    response.sendRedirect("home.jsp");
                } else if (accion.equals("INSERT")) {
                    
                } else if (accion.equals("UPDATE")) {
                    int id = Integer.parseInt(request.getParameter("idPost"));
                    post.setIdPost(id);
                    post = dao.read(post);
                    titulo = post.getTituloPost();
                    categoria = post.getCategoriaPost();
                    resumen = post.getResumenPost();
                    contenido = post.getContenidoPost();
                } else {
                    response.sendRedirect("home.jsp");
                }
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
                    <div class="block-gris">
                        <h1 class="display-4 font-italic">¿Tienes tantas flores como nosostros?</h1>
                        <p class="lead my-3">Multiple lines of text that form the lede, informing new readers quickly and efficiently about what's most interesting in this post's contents.</p>
                        <!--<p class="lead mb-0"><a href="https://getbootstrap.com/docs/4.0/examples/blog/#" class="text-white font-weight-bold">Continue reading...</a></p>-->
                    </div>
                </div>
            </div>
        </div>

        <main role="main" class="container">
            <div class="row">
                <div class="col-md-8 blog-main">
                    <h3 class="pb-3 mb-4 font-italic border-bottom">
                        Información Post <b class="text-info"><%=titulo%> </b>
                    </h3>
                    <div class="col-12">
                        <% if(accion.equals("INSERT")){ %>
                            <form id="formInsert" action="PostServlet" method="post" class="form-signin">                            
                                <input type="hidden" id="txtAccion" name="txtAccion" value="INSERT" required="" >
                                <input type="hidden" id="txtIdUsuario" name="txtIdUsuario" value="<%=usuario.getIdUsuario()%>" required="" >
                                <div class="form-group">
                                    <label for="txtTitulo">Titulo: </label>
                                    <input type="text" value="<%=titulo%>" id="txtTitulo" name="txtTitulo" class="form-control" placeholder="Titulo" required="" autofocus="">
                                </div>
                                <div class="form-group">
                                    <label for="txtCategoria">Categoria: </label>
                                    <select class="form-control" id="txtCategoria" name="txtCategoria">
                                        <option value="Java">Java</option>
                                        <option value="Android">Android</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="txtResumen" > Resumen: </label>
                                    <textarea id="txtResumen" name="txtResumen" rows="4" class="form-control" placeholder="Resumen" required="" autofocus=""><%=resumen%></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="txtContenido" >Contenido: </label>
                                    <textarea id="txtContenido" name="txtContenido" rows="8" class="form-control" placeholder="Contenido" required=""><%=contenido%></textarea>
                                </div>
                                <nav class="blog-pagination text-right">
                                    <button type="submit" class="btn btn-outline-success">Agregar</button>
                                </nav>
                            </form>
                        <% } else {%>
                            <form id="formUpdate" action="PostServlet" method="post" class="form-signin">                            
                                <input type="hidden" id="txtAccion" name="txtAccion" value="UPDATE" required="" >
                                <input type="hidden" id="txtIdUsuario" name="txtIdUsuario" value="<%=usuario.getIdUsuario()%>" required="" >
                                <input type="hidden" id="txtIdUsuario" name="txtIdPost" value="<%=post.getIdPost()%>" required="" >
                                <div class="form-group">
                                    <label for="txtTitulo">Titulo: </label>
                                    <input type="text" value="<%=titulo%>" id="txtTitulo" name="txtTitulo" class="form-control" placeholder="Titulo" required="" autofocus="">
                                </div>
                                <div class="form-group">
                                    <label for="txtCategoria">Categoria: </label>
                                    <select class="form-control" id="txtCategoria" name="txtCategoria">
                                        <option value="Java">Java</option>
                                        <option value="Android">Android</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="txtResumen" > Resumen: </label>
                                    <textarea id="txtResumen" name="txtResumen" rows="4" class="form-control" placeholder="Resumen" required="" autofocus=""><%=resumen%></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="txtContenido" >Contenido: </label>
                                    <textarea id="txtContenido" name="txtContenido" rows="8" class="form-control" placeholder="Contenido" required=""><%=contenido%></textarea>
                                </div>
                                <nav class="blog-pagination text-right">
                                    <button type="submit" class="btn btn-outline-primary">Actualizar</button>
                                    <a id="btnEliminar" class="btn btn-outline-danger">Eliminar</a>
                                </nav>
                            </form>
                        <% } %>
                    </div>

                </div><!-- /.blog-main -->

                <!-- aside bar-->
                <%@ include file="shared/asidebar.jsp" %>

            </div><!-- /.row -->

        </main><!-- /.container -->

        <!-- footer -->
        <%@ include file="shared/footer.jsp" %>
        <script src="assets/js/sweetalert2.js"></script>
        <script>
            const swalWithBootstrapButtons = Swal.mixin({
                confirmButtonClass: 'btn btn-outline-success',
                cancelButtonClass: 'btn btn-outline-danger',
                buttonsStyling: false
            });

            function alertaEliminar() {
                swalWithBootstrapButtons.fire({
                    title: '¿Estás seguro que deseas elimimar el post "<%=titulo%>"?',
                    text: "No podrás recuperar la información después",
                    type: 'warning',
                    showCancelButton: true,
                    confirmButtonText: 'Sí, borrar',
                    cancelButtonText: 'No, cancelar',
                    reverseButtons: true
                }).then((result) => {
                    if (result.value) {
                        window.location.replace("PostServlet?txtAccion=DELETE&txtIdPost=<%=post.getIdPost()%>&txtIdUsuario=<%=usuario.getIdUsuario()%>");
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
            };
            $('#selCategoria').val('<%=categoria%>');
            $('#btnEliminar').on('click', alertaEliminar);
        </script>
        <% } %>
    </body>
</html>