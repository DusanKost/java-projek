<%-- 
    Document   : index
    Created on : Mar 13, 2018, 7:27:20 PM
    Author     : Dusan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <%@include file="../_partials/headStyles.jsp"%>
        <title>Register</title>
    </head>
    <body>
        <%@include file="../_partials/navbar.jsp"%>
        <main id="mainC">
            <form action="http://localhost:8084/Projekat/RegisterController" method="post">
                <div class="form-group">
                  <label for="exampleInputEmail1">Username</label>
                  <input type="text" name="username" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Username">
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1">Password</label>
                  <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                </div>
                <input type="hidden" value="1" name="type_of_user">
                <input type="hidden" name="from" value="<%= request.getServletPath() %>">
                <button type="submit" class="btn btn-primary">Register</button>
            </form>
            <%@include file="../_partials/err_succ.jsp"%>
        </main>
        <%@include file="../_partials/footer.jsp"%>
    </body>
</html>
