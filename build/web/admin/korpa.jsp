<%-- 
    Document   : korpa
    Created on : Mar 25, 2018, 6:14:12 PM
    Author     : Dusan
--%>

<%@page import="models.KupljenProizvod"%>
<%@page import="models.Proizvod"%>
<%@page import="models.User" %>
<%@page import="models.TypeOfUser" %>
<%@page import="java.util.ArrayList" %>
<%
    /*Provera da li je user ulogovan i ako jeste da li je admin*/
    User x = (User)request.getSession().getAttribute("user");
    session=request.getSession(false);
    if(session.getAttribute("user")==null)
    {
        response.sendRedirect("../auth/login.jsp");
    }else{
    ArrayList<Proizvod> lista = (ArrayList<Proizvod>)session.getAttribute("korpa");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <%@include file="../_partials/headStyles.jsp"%>
        <title>Korpa</title>
    </head>
    <body>
        <%@include file="../_partials/navbar.jsp"%>
        <main id="mainC">
            <%@include file="../_partials/err_succ.jsp"%>
           <%-- Kraj korisnika --%>
           <div class="container text-center">
               
           </div>
           <hr>
           <%-- Ispis korisnika --%>
           <div class="container">
              <%
                  if (lista.isEmpty()) {
                          %>
                          <div class="alert alert-dark" role="alert">
                                Korpa je prazna
                          </div>
                          <%
                      }
              %>
              <table class="table table-hover" style="width: 50%; text-align: center; margin: 0 auto;">
                <thead class="thead-dark">
                  <tr>
                    <th scope="col">#ID</th>
                    <th scope="col">Naziv</th>
                    <th scope="col">Cena</th>
                    <th scope="col">Sladak</th>
                    <th scope="col">Akcije</th>
                    <th scope="col">Kolicina</th>
                  </tr>
                </thead>
                <tbody>
                  <%
                      int cena = 0;
                      for (Proizvod p : lista) 
                      {
                  %>
                      <tr>
                          <td><%= p.getId() %></td>
                          <td><%= p.getNaziv() %></td>
                          <td><%= p.getCena()%></td>
                          <th scope="col"><%= p.getSladak() %></th>
                          <td>
                              <%
                                  if (p.getKup_kolicina() == p.getKolicina()) {
                                     %>
                                        <a href="http://localhost:8084/Projekat/AddRm1Controllerr?param=<%= lista.indexOf(p) %>&add=2" class="text-primary">Oduzmi 1<i class="fas fa-minus"></i></a>
                                     <%
                                  }else{
                                     %>
                                        <a href="http://localhost:8084/Projekat/AddRm1Controllerr?param=<%= lista.indexOf(p) %>&add=1" class="text-primary">Dodaj 1<i class="fas fa-plus"></i></a><br>
                                        <a href="http://localhost:8084/Projekat/AddRm1Controllerr?param=<%= lista.indexOf(p) %>&add=2" class="text-primary">Oduzmi 1<i class="fas fa-minus"></i></a>
                                     <%   
                                 }
                              %>
                              <form action="http://localhost:8084/Projekat/KorpaController" method="Post">
                                  <input type="hidden" name="id" value="<%= lista.indexOf(p) %>">
                                  <input type="submit" value="Obrisi artikal" class="btn btn-danger"></input>
                              </form> </td>
                              <td>
                                  <%= p.getKup_kolicina() %>
                              </td>
                      </tr>
                  <%  cena += p.getKupCena();
                      }
                  %>
                  <tr>
                      <td>#</td>
                      <td>#</td>
                      <td>Cena: <%= cena %></td>
                      <td>
                        <form action="http://localhost:8084/Projekat/KorpaController" method="Post">
                            <input type="hidden" name="_method" value="delete">
                            <input type="submit" value="Isprazni korpu" class="btn btn-danger"></input>
                        </form>
                      </td> 
                      <td>
                        <form action="http://localhost:8084/Projekat/KorpaController" method="Post">
                            <input type="hidden" name="_method" value="put">
                            <input type="submit" value="Plati" class="btn btn-warning"></input>
                        </form>
                      </td>
                  </tr>
                </tbody>
              </table>  
           </div>
           <%-- Kraj ispisa korisnika --%>     
        </main>
        
        <%@include file="../_partials/footer.jsp"%>
        <% } %>
    </body>
</html>

