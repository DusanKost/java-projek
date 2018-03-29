<%-- 
    Document   : dashboard
    Created on : Mar 28, 2018, 12:08:54 AM
    Author     : Dusan
--%>

<%@page import="models.Proizvod"%>
<%@page import="java.util.ArrayList"%>
<%
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
    ArrayList<Proizvod> lista = Proizvod.getLow();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <%@include file="../_partials/headStyles.jsp"%>
        <title>Prazni Proizvodi</title>
    </head>
    <body>
        <%@include file="../_partials/navbar.jsp"%>
        <main id="mainC">
            <%@include file="../_partials/err_succ.jsp"%>
            <div class="container">
              <table class="table table-hover" style="width: 50%; text-align: center; margin: 0 auto;">
                <thead class="thead-dark">
                  <tr>
                    <th scope="col">#ID</th>
                    <th scope="col">Username</th>
                    <th scope="col">Kolicina</th>
                    <th scope="col">Dodatne funkcije</th>
                  </tr>
                </thead>
                <tbody>
                  <%
                      for (Proizvod p : lista) 
                      {
                  %>
                      <tr>
                          <td><%= p.getId() %></td>
                          <td><%= p.getNaziv() %></td>
                          <th scope="col"><%= p.getKolicina() %></th>
                          <td><a href="http://localhost:8084/Projekat/ProizvodController?naziv=<%= p.getNaziv() %>" class="text-primary">Edit</a> 
                              <form action="http://localhost:8084/Projekat/ProizvodController" method="POST">
                                <input type="hidden" name="_method" value="delete">
                                <input type="hidden" name="id" value="<%= p.getId() %>">
                                <input type="submit" class="btn btn-danger" value="Delete">
                              </form> </td>
                      </tr>
                  <%            
                      }
                  %>
                </tbody>
              </table>  
           </div>
        </main>
        
        <%@include file="../_partials/footer.jsp"%>
    </body>
</html>
