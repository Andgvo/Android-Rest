<%-- 
    Document   : registro
    Created on : 4/04/2019, 02:32:02 PM
    Author     : andres
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <title>Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Jekyll v3.8.5">
        <!-- Bootstrap core CSS -->
        <link href="assets/css/bootstrap-material.css" rel="stylesheet" >
        <link href="assets/css/blog.css" rel="stylesheet">
        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
            }
            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                    font-size: 3.5rem;
                }
            }
        </style>
        <!-- Custom styles for this template -->
        <link href="assets/css/signin.css" rel="stylesheet">
    </head>
    <body class="text-center">
        <form action="UsuarioServlet" method="post" class="form-signin">
            <img class="mb-4" src="assets/img/ipn-logo.png" alt="" width="90" height="90">
            <h1 class="h3 mb-3 font-weight-normal">Por favor llena los campos</h1>
            <input type="hidden" id="txtAccion" name="txtAccion" value="INSERT" required="" >
            <label for="txtUsuario" class="sr-only">Nombre (Usuario) </label>
            <input type="text" id="txtUsuario" name="txtUsuario" class="form-control" placeholder="Nombre (Usuario)" required="" autofocus="">
            <label for="txtApellido" class="sr-only"> Apellido </label>
            <input type="text" id="txtApellido" name="txtApellido" class="form-control" placeholder="Apellido" required="" autofocus="">
            <label for="txtEmail" class="sr-only">Email</label>
            <input type="email" id="txtEmail" name="txtEmail" class="form-control" placeholder="Email" required="">
            <label for="txtPassword" class="sr-only">Password</label>
            <input type="password" id="txtPassword" name="txtPassword" class="form-control" placeholder="*****" required="">
            
            <button class="btn btn-lg btn-outline-vino btn-block" type="submit">Registrar</button>
            <p class="mt-5 mb-3 text-muted">© 2018-2019</p>
        </form>
    </body>
</html>

