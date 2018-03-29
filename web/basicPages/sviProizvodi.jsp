<%-- 
    Document   : sviProizvodi
    Created on : Mar 16, 2018, 7:49:12 PM
    Author     : Dusan
--%>

<%@page import="models.Proizvod" %>
<%@page import="java.util.ArrayList" %>
<%
    ArrayList<Proizvod> lista = Proizvod.getAll();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:h="http://java.sun.com/jsf/html" xmlns:f="http://java.sun.com/jsf/core">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <%@include file="../_partials/headStyles.jsp"%>
        <title>Svi proizvodi</title>
    </head>
    <body>
        <%@include file="../_partials/navbar.jsp"%>
        <main id="mainC">
            <%@include file="../_partials/err_succ.jsp"%>
            <%-- Ispisi proizvode --%>
            <div class="container">
                <div class="row">
                    <%
                        for (Proizvod p : lista) {
                    %>
                      <%@include file="../_partials/proizvodPublic.jsp"%>
                    <%
                            }
                    %>
                </div>
            </div>
        </main>
        <%@include file="../_partials/footer.jsp"%>
    </body>
</html>

