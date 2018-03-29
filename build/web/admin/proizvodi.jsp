<%-- 
    Document   : createSladak
    Created on : Mar 16, 2018, 7:48:16 PM
    Author     : Dusan
--%>
<%@page import="models.User"%>
<%@page import="models.Proizvod" %>
<%@page import="java.util.ArrayList" %>
<%
    /*Provera da li je user ulogovan i ako jeste da li je admin il sef kuhinje
    il glavni menadzer */
    User x = (User)request.getSession().getAttribute("user");
    session=request.getSession(false);
    if(session.getAttribute("user")==null)
    {
        response.sendRedirect("../auth/login.jsp");
    }else{
        if (x.getType_of_user()!=2 && x.getType_of_user()!=3 && x.getType_of_user()!=5) {
                response.sendRedirect("../auth/login.jsp");
            }
    }
    ArrayList<Proizvod> lista = Proizvod.getAll();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <%@include file="../_partials/headStyles.jsp"%>
        <title>Proizvodi</title>
    </head>
    <body>
        <%@include file="../_partials/navbar.jsp"%>
        <main id="mainC">
            <%@include file="../_partials/err_succ.jsp"%>
            <%-- Kreiraj proizvod --%>
            <div class="container text-center" style="margin-bottom: 50px;">
                <form action="http://localhost:8084/Projekat/ProizvodController" method="POST" enctype="multipart/form-data">
                    Naziv: <input style="width: 100px;" type="text" name="naziv"/>
                    Sladak : <select name="sladak">
                        <option selected value="1">Da</option>
                        <option value="0">Ne</option>
                    </select>
                    Cena: <input style="width: 100px;" name="cena" type="number" min="0" value="0">
                    Kolicina: <input style="width: 100px;" name="kolicina" type="number" min="0" value="0">
                    Slika: <input accept="image/*" type="file" name="slika" />
                    <input type="hidden" name="from" value="<%= request.getServletPath() %>">
                    <input type="submit" class="btn btn-success" value="Create Proizvod" />
                </form>
            </div>
            <%-- Ispisi proizvode --%>
            <div class="container">
                <div class="row">
                    <%
                        for (Proizvod p : lista) {
                    %>
                      <div class="col-sm-4" style="margin-bottom:  50px;">
                       <div class="card" style="width: 18rem;">
                           <img class="card-img-top" style="height: 200px; width: auto;" src="http://localhost:8084/Projekat/images/<%= p.getSlika() %>" alt="Card image cap">
                        <div class="card-body">
                          <h5 class="card-title text-center"><%= p.getNaziv() %></h5>
                          <p class="card-text text-center"><small class="text-muted"><%= p.sladakToString(p.getSladak()) %></small></p>
                          <p class="card-text text-center">Kolicina: <small class="text-muted"><%= p.getKolicina() %></small></p>
                          <p class="card-text text-center">Cena: <%= p.getCena() %></p>
                          <a href="http://localhost:8084/Projekat/ProizvodController?naziv=<%= p.getNaziv() %>" class="btn btn-primary" style="float: left;">Edit</a>
                          <form action="http://localhost:8084/Projekat/ProizvodController" method="POST">
                              <input type="hidden" name="_method" value="delete">
                              <input type="hidden" name="id" value="<%= p.getId() %>">
                              <input type="submit" class="btn btn-danger" style="float: right;" value="Delete">
                          </form>
                        </div>
                      </div>
                    </div>
                    <%
                            }
                    %>
                </div>
            </div>
        </main>
        <%@include file="../_partials/footer.jsp"%>
    </body>
</html>
