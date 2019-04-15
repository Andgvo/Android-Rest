<%-- 
    Document   : filtro
    Created on : 4/04/2019, 10:21:16 PM
    Author     : andres
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipn.mx.blog.modelo.dto.Post"%>
<%@page import="java.util.List"%>
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
            List<Post> posts = new ArrayList();
            Post postAux = new Post();
            String accion ="";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String banner = "intro_flower.jpg";
            if (usuario == null) {
                response.sendRedirect("index.jsp?cerrar=true");
            } else {
                accion = request.getParameter("accion");
                if (accion == null) {
                    response.sendRedirect("home.jsp");
                } else if (accion.equals("CATEGORIA")) {
                    String categoria = request.getParameter("categoria");
                    if (categoria != null) {
                        postAux.setCategoriaPost(categoria);
                    }
                    posts = dao.readAllbyCategory(postAux);
                    if(categoria.equals("Java")){
                        banner = "java-banner.jpg";
                    }else if(categoria.equals("Android")){
                        banner = "android-banner.jpg";
                    }
                } else if (accion.equals("MES")) {
                    String mes = request.getParameter("mes");
                    if (mes != null) {
                        postAux.setFechaPost(new Date(mes));
                    }
                    posts = dao.readAllbyMonth(postAux);
                } else if (accion.equals("USUARIO")) {
                    int id = Integer.parseInt(request.getParameter("idUser"));
                    postAux.getIdUsuario().setIdUsuario(id);
                    posts = dao.readAllbyUser(postAux);
                } else {
                    response.sendRedirect("home.jsp");
                }
            }
            // EVITAR QUE SE CARGE Y GENERÉ ERROR
            if(usuario != null){
        %>
        <!-- Navbar -->
        <jsp:include page="shared/navbar.jsp">
            <jsp:param name="userId" value="<%=usuario.getIdUsuario()%>"/>
        </jsp:include>

        <div class="container">
            <div class="jumbotron p-3 p-md-5 text-white rounded bg-dark main-blog-image" style="background-image: url('assets/img/<%=banner%>')">
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
                        <% if(accion.equals("USUARIO")){ %>
                            <div class="row">
                                <div class="col-7 col-sm-6 col-md-6">
                                    Todos los post
                                </div>
                                <div class="col-5 col-sm-6 col-md-6">
                                    <div class="blog-pagination text-right">
                                        <a href="gestionPost.jsp?accion=INSERT" class="btn btn-outline-success">Agregar Post +</a>
                                    </div>
                                </div>
                            </div>
                        <% }else{ %>
                            Todos los post
                        <% } %>
                    </h3>

                    <% if (posts != null)
                            for (Post post : posts) {%>
                    <div class="row mb-2">
                        <div class="col-md-12">
                            <div class="card flex-md-row mb-4 box-shadow h-md-250">
                                <div class="card-body d-flex flex-column align-items-start">
                                    <strong class="d-inline-block mb-2 text-primary">
                                        <%= post.getCategoriaPost()%>
                                    </strong>
                                    <h3 class="mb-0">
                                        <a class="text-dark" href="post.jsp?idPost=<%=post.getIdPost()%>">
                                            <%= post.getTituloPost()%>
                                        </a>
                                    </h3>
                                    <div class="mb-1 text-muted">
                                        El <%= sdf.format(post.getFechaPost())%> por 
                                        <a href="filtro.jsp?accion=USUARIO&idUser=<%=post.getIdUsuario().getIdUsuario()%>">
                                            <%= post.getIdUsuario().getNombreUsuario()%> 
                                        </a>
                                    </div>
                                    <p class="card-text mb-auto"><%= post.getResumenPost()%></p>
                                    <a href="post.jsp?idPost=<%=post.getIdPost()%>"> Continuar leyendo </a>
                                </div>
                                <img class="card-img-right flex-auto d-none d-md-block" data-src="holder.js/200x250?theme=thumb" alt="Thumbnail [200x250]" style="width: 200px; height: 250px;" src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22200%22%20height%3D%22250%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20200%20250%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_169e672edbc%20text%20%7B%20fill%3A%23eceeef%3Bfont-weight%3Abold%3Bfont-family%3AArial%2C%20Helvetica%2C%20Open%20Sans%2C%20sans-serif%2C%20monospace%3Bfont-size%3A13pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_169e672edbc%22%3E%3Crect%20width%3D%22200%22%20height%3D%22250%22%20fill%3D%22%2355595c%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%2255.609375%22%20y%3D%22131%22%3EThumbnail%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E" data-holder-rendered="true">
                            </div>
                            <% if(accion.equals("USUARIO") && post.getIdUsuario().getNombreUsuario().equals(usuario.getNombreUsuario())) { %>
                                <div class="col-12">
                                    <nav class="blog-pagination text-right">
                                        <a href="gestionPost.jsp?accion=UPDATE&idPost=<%=post.getIdPost()%>" class="btn btn-outline-primary">Actualizar</a>
                                        <button type="button" onclick="alertaEliminar('<%=post.getTituloPost()%>','<%=post.getIdPost()%>')" id="btnEliminar" class="btn btn-outline-danger">Eliminar</button>
                                    </nav>
                                </div>
                            <% } %>
                        </div>
                    </div>
                    <% }
                    else { %>
                    <h3>No hay resultados</h3>
                    <% }%>

                </div><!-- /.blog-main -->

                <!-- aside bar-->
                <%@ include file="shared/asidebar.jsp" %>
            </div><!-- /.row -->
        </main><!-- /.container -->

        <!-- footer -->
        <%@ include file="shared/footer.jsp" %>
        <script src="assets/js/sweetalert2.js"></script>
        <script src="assets/js/vue.js"></script>
        <script>
            const swalWithBootstrapButtons = Swal.mixin({
                confirmButtonClass: 'btn btn-outline-success',
                cancelButtonClass: 'btn btn-outline-danger',
                buttonsStyling: false
            });

            function alertaEliminar(titulo, idPost) {
                swalWithBootstrapButtons.fire({
                    title: '¿Estás seguro que deseas elimimar el post "'+titulo+'"?',
                    text: "No podrás recuperar la información después",
                    type: 'warning',
                    showCancelButton: true,
                    confirmButtonText: 'Sí, borrar',
                    cancelButtonText: 'No, cancelar',
                    reverseButtons: true
                }).then((result) => {
                    if (result.value) {
                        window.location.replace("PostServlet?txtAccion=DELETE&txtIdPost="+idPost+"&txtIdUsuario=<%=usuario.getIdUsuario()%>");
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
            $('#btnEliminar').on('click', alertaEliminar);
        </script>
        <% } %>
    </body>
</html>