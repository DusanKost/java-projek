<%-- 
    Document   : users
    Created on : Mar 16, 2018, 7:48:00 PM
    Author     : Dusan
--%>

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
        if (x.getType_of_user() !=2 ) {
                response.sendRedirect("../auth/login.jsp");
            }
    }
    ArrayList<TypeOfUser> listaOpcija = TypeOfUser.getAll();
    ArrayList<User> lista = User.getAll();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <%@include file="../_partials/headStyles.jsp"%>
        <title>Korisnici</title>
    </head>
    <body>
        <%@include file="../_partials/navbar.jsp"%>
        <main id="mainC">
            <%@include file="../_partials/err_succ.jsp"%>
           <%-- Kraj korisnika --%>
           <div class="container text-center">
               <form action="http://localhost:8084/Projekat/RegisterController" method="post">
                   Username: <input type="text" name="username">
                   Password: <input type="password" name="password">
                   Type of user: <select name="type_of_user">
                       <%
                           for (TypeOfUser t : listaOpcija) 
                           {
                       %>
                       <option value="<%= t.getId() %>"><%= t.getTip() %></option>
                       <%
                           }
                       %>
                   </select>
                   <input type="hidden" name="from" value="<%= request.getServletPath() %>">
                   <input class="btn btn-success" type="submit" value="Create">
               </form>
           </div>
           <hr>
           <%-- Ispis korisnika --%>
           <div class="container">
              <table class="table table-hover" style="width: 50%; text-align: center; margin: 0 auto;">
                <thead class="thead-dark">
                  <tr>
                    <th scope="col">#ID</th>
                    <th scope="col">Username</th>
                    <th scope="col">Type Of User</th>
                    <th scope="col">Dodatne funkcije</th>
                  </tr>
                </thead>
                <tbody>
                  <%
                      for (User u : lista) 
                      {
                  %>
                      <tr>
                          <td><%= u.getId() %></td>
                          <td><%= u.getUsername() %></td>
                          <th scope="col"><%= TypeOfUser.getById(u.getType_of_user())%></th>
                          <td><a href="http://localhost:8084/Projekat/UserController?username=<%= u.getUsername() %>" class="text-primary">Edit</a> 
                              <form action="http://localhost:8084/Projekat/UserController" method="Post">
                                  <input type="hidden" name="id" value="<%= u.getId() %>">
                                  <input type="submit" value="Delete" class="btn btn-danger"></input>
                              </form> </td>
                      </tr>
                  <%            
                      }
                  %>
                </tbody>
              </table>  
           </div>
           <%-- Kraj ispisa korisnika --%>     
        </main>
        
        <%@include file="../_partials/footer.jsp"%>
    </body>
</html>
