

<%@page import="com.emergentes.modelo.producto"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
producto pro=(producto) request.getAttribute("pro");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PRACTICA TRES</title>
    </head>
    <body>
        <h1 style="color: gold"><c:if test ="${pro.id == 0}">
                N U E V O  ==  R E G I S T R O 
        </c:if>
                <c:if test ="${pro.id != 0}">
                E D I T A R == R E G I S T R O
        </c:if>
            </h1>
        <form action="MainController" method="post">
             <input type="hidden" name="id" value="${pro.id}">
            <table>
                <tr>
                    <td>Producto</td>
                    <td><input type="text" name="producto" value="${pro.producto}"></td>
                </tr>
                <tr>
                    <td>Precio</td>
                    <td><input type="text" name="precio" value="${pro.precio}"></td>
                </tr>
               <tr>
                    <td>Cantidad</td>
                    <td><input type="text" name="cantidad" value="${pro.cantidad}"></td>
                </tr>
                
                <tr>
                    <td></td>
                    <td><input type="submit" value="ENVIAR"></td>
                </tr>
                
            </table>
        </form>
    </body>
</html>
