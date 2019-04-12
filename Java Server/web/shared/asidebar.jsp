<%-- 
    Document   : asidebar
    Created on : 4/04/2019, 09:07:29 AM
    Author     : andres
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<aside class="col-md-4 blog-sidebar">
    <div class="p-3">
        <h4 class="font-italic">Categorías</h4>
        <ol class="list-unstyled">
            <li><a href="filtro.jsp?accion=CATEGORIA&categoria=Java">Java</a></li>
            <li><a href="filtro.jsp?accion=CATEGORIA&categoria=Android">Android</a></li>
        </ol>
    </div>

    <div class="p-3">
        <h4 class="font-italic">Archives</h4>
        <ol class="list-unstyled mb-0">
            <li><a href="filtro.jsp?accion=MES&mes=03/01/2019">Marzo 2019</a></li>
            <li><a href="filtro.jsp?accion=MES&mes=04/01/2019">Abril 2019</a></li>
        </ol>
    </div>

    <div class="p-3 mb-3 bg-light rounded">
        <h4 class="font-italic">About</h4>
        <p class="mb-0">Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>
    </div>
</aside><!-- /.blog-sidebar -->