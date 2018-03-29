<%-- 
    Document   : usersEdit
    Created on : Mar 18, 2018, 10:57:22 PM
    Author     : Dusan
--%>


<%@page import="models.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.TypeOfUser" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <%@include file="../_partials/headStyles.jsp"%>
        <title>Edit user</title>
    </head>
    <body>
        <%@include file="../_partials/navbar.jsp"%>
        <main id="mainC">
            <%@include file="../_partials/err_succ.jsp"%>
            <form action="http://localhost:8084/Projekat/UserController" method="POST">
                <input type="hidden" name="_method" value="put">
                <div class="form-group">
                  <label for="username">Username:</label>
                  <input name="username" value="${user.getUsername()}" type="text" class="form-control" id="username" aria-describedby="emailHelp" placeholder="Username">
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1">Password</label>
                  <input name="password" value="${user.getPassword()}" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                  <select class="custom-select" name="type_of_user">
                    <%
                           for (TypeOfUser t : listaOpcija) 
                           {
                       %>
                       <option value="<%= t.getId() %>"><%= t.getTip() %></option>
                       <%
                           }
                       %>
                  </select>
                  <input type="hidden" name="id" value="${user.getId()}">
                  <input type="hidden" name="from" value="<%= request.getServletPath() %>">
                </div>
                <input type="submit" class="btn btn-primary" value="Save Changes">
             </form>
        </main>
        <%@include file="../_partials/footer.jsp"%>
    </body>
</html>
