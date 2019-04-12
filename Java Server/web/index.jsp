<%-- 
    Document   : index
    Created on : 4/04/2019, 09:49:52 AM
    Author     : andres
--%>
<%@page session="true" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <!-- Bootstrap core CSS -->
        <link href="assets/css/bootstrap-material.css" rel="stylesheet" >
        <link href="assets/css/blog.css" rel="stylesheet">
        
        <!-- Custom styles for this template -->
        <link href="assets/css/signin.css" rel="stylesheet">
    </head>
    <body class="text-center">
        <%
            HttpSession sesion = request.getSession();
            if(request.getParameter("cerrar") != null){
                sesion.invalidate();
            }
        %>
        <form action="UsuarioServlet" method="post" class="form-signin">
            <img class="mb-4" src="assets/img/ipn-logo.png" alt="" width="140" height="140">
            <h1 class="h3 mb-3 font-weight-normal">Por favor inicia sesión</h1>
            <input type="hidden" value="LOGIN" id="txtAccion" name="txtAccion">
            <label for="txtUsuario" class="sr-only">Usuario</label>
            <input type="text" id="txtUsuario" name="txtUsuario" class="form-control" placeholder="Ingresa aquí tu usuario" required="" autofocus="">
            <label for="txtPassword" class="sr-only">Password</label>
            <input type="password" id="txtPassword" name="txtPassword" class="form-control" placeholder="*****" required="">
            <!--<div class="checkbox mb-3">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div> -->
            <button class="btn btn-lg btn-outline-vino btn-block" type="submit">Iniciar</button>
            <hr>
            <a href="registro.jsp">No tienes cuenta, ¡Registrate ahora! </a>
            <p class="mt-5 mb-3 text-muted">© 2018-2019</p>
        </form>
    </body>
</html>
