<%-- 
    Document   : navbar
    Created on : 4/04/2019, 08:06:58 AM
    Author     : andres
--%>
<%@page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container" id="top">
    <header class="blog-header py-3">
        <div class="row flex-nowrap justify-content-between align-items-center">
            <div class="col-4 pt-1">
                <a class="text-muted" href="#">IPN</a>
            </div>
            <div class="col-4 text-center">
                <a class="blog-header-logo text-dark" href="home">Blog Flores</a>
            </div>
            <div class="col-4 d-flex justify-content-end align-items-center">
                <a class="btn btn-sm btn-outline-vino" href="index.jsp?cerrar=true">Cerrar Sesión</a>
            </div>
        </div>
    </header>

    <div id="sticker" class="nav-scroller py-1 mb-2">
        <nav class="nav d-flex justify-content-between">
            <a class="p-2 text-muted" href="home.jsp">Blog</a>
            <a class="p-2 text-muted" href="filtro.jsp?accion=CATEGORIA&categoria=Java">Java</a>
            <a class="p-2 text-muted" href="filtro.jsp?accion=CATEGORIA&categoria=Android">Android</a>
            <a class="p-2 text-muted" href="filtro.jsp?accion=USUARIO&idUser=${param.userId}">Mis posts</a>
            <a class="p-2 text-muted" href="datos.jsp">
                Mi Perfil
            </a>
        </nav>
    </div>
</div>