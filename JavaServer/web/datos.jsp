<%-- 
    Document   : datos
    Created on : 5/04/2019, 09:57:36 PM
    Author     : andres
--%>

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
            if (usuario == null) {
                response.sendRedirect("index.jsp?cerrar=true");
            }else{
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
                        Mi información
                    </h3>
                    <div class="col-8">
                        <form action="UsuarioServlet" method="post" class="form-signin">                            
                            <input type="hidden" id="txtAccion" name="txtAccion" value="UPDATE" required="" >
                            <input type="hidden" id="txtIdUsuario" name="txtIdUsuario" value="<%=usuario.getIdUsuario()%>" required="" >
                            <div class="form-group">
                                <label for="txtUsuario">Nombre (Usuario): </label>
                                <input type="text" value="<%=usuario.getNombreUsuario()%>" id="txtUsuario" name="txtUsuario" class="form-control" placeholder="Nombre (Usuario)" required="" autofocus="">
                            </div>
                            <div class="form-group">
                                <label for="txtApellido" > Apellido: </label>
                                <input type="text" value="<%=usuario.getApellidoUsuario()%>" id="txtApellido" name="txtApellido" class="form-control" placeholder="Apellido" required="" autofocus="">
                            </div>
                            <div class="form-group">
                                <label for="txtEmail" >Email: </label>
                                <input type="email" value="<%=usuario.getEmailUsuario()%>" id="txtEmail" name="txtEmail" class="form-control" placeholder="Email" required="">
                            </div>
                            <div class="form-group">
                                <label for="txtPassword" >Password: </label>
                                <input type="password" value="<%=usuario.getPasswordUsuario()%>" id="txtPassword" name="txtPassword" class="form-control" placeholder="*****" required="">
                            </div>
                            <nav class="blog-pagination text-right">
                                <button type="submit" class="btn btn-outline-primary">Actualizar</button>
                                <a id="btnEliminar" class="btn btn-outline-danger">Eliminar</a>
                            </nav>
                        </form>
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
                    title: '¿Estás seguro que deseas elimimar tu cuenta?',
                    text: "No podrás recuperar la información después",
                    type: 'warning',
                    showCancelButton: true,
                    confirmButtonText: 'Sí, borrar',
                    cancelButtonText: 'No, cancelar',
                    reverseButtons: true
                }).then((result) => {
                    if (result.value) {
                        window.location.replace("UsuarioServlet?txtAccion=DELETE&txtIdUsuario=<%=usuario.getIdUsuario()%>");
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
