<%-- 
    Document   : nePoslatiProizvodi
    Created on : Mar 28, 2018, 10:46:14 PM
    Author     : Dusan
--%>
<%@page import="models.User"%>
<%@page import="models.KupljenProizvod"%>
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
    ArrayList<KupljenProizvod> lista = KupljenProizvod.getNePoslane();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <%@include file="../_partials/headStyles.jsp"%>
        <title>Ne Poslati Proizvodi</title>
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
                    <th scope="col">KupDate</th>
                    <th scope="col">Dodatne funkcije</th>
                  </tr>
                </thead>
                <tbody>
                  <%
                      for (KupljenProizvod kp : lista) 
                      {
                  %>
                      <tr>
                          <td><%= kp.getId() %></td>
                          <th scope="col"><%= User.getUsernameById(kp.getUser_id()) %></th>
                          <th scope="col"><%= kp.getDate() %></th>
                          <td><a href="http://localhost:8084/Projekat/KupljenProizvodController?id=<%= kp.getId() %>" class="text-primary">Posalji Proizvod <i class="fas fa-share-square"></i></a></td>
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

