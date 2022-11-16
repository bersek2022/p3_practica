
<%@page import="com.emergentes.modelo.producto"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%
List<producto> lista=(List<producto>) request.getAttribute("lista");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PRACTICA TRES</title>
    </head>
    <body>
        <h1 style="color: green">=== P R O D U C T O S ===</h1>
        <h2 style="color: blue">LISTA DE LOS PRODUCTOS</h2>
        
        <p> <a href="MainController?op=nuevo">Nuevo</a></p>
        <table border="3">
            <tr>
                <th>Id</th>
                <th>Producto</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th></th>
                <th></th>
            </tr>    
                <c:forEach var="item" items="${lista}">
                <tr>
                    <td>${item.id}</td>
                     <td>${item.producto}</td>
                     <td>${item.precio}</td>
                     <td>${item.cantidad}</td>
                     <td><a href="MainController?op=editar&id=${item.id}">Editar</a></td>
                     <td><a href="MainController?op=eliminar&id=${item.id}"
                           onclick="return(confirm('Esta Seguro de Eliminar el PRODUCTO ???..'))" >Eliminar</a></td> 
                </tr>
                </c:forEach>
        </table>
    </body>
</html>
