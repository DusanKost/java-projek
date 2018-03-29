<%-- 
    Document   : login
    Created on : Mar 14, 2018, 10:29:51 PM
    Author     : Dusan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <%@include file="../_partials/headStyles.jsp"%>
        <title>Login</title>
    </head>
    <body>
        <%@include file="../_partials/navbar.jsp"%>
        <main id="mainC">
            <form action="http://localhost:8084/Projekat/LoginController" method="post">
                <div class="form-group">
                  <label for="exampleInputEmail1">Username</label>
                  <input type="text" name="username" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Username">
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1">Password</label>
                  <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
            <%@include file="../_partials/err_succ.jsp"%>
        </main>
        <%@include file="../_partials/footer.jsp"%>
    </body>
</html>
