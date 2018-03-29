<%-- 
    Document   : index
    Created on : Mar 15, 2018, 7:56:37 PM
    Author     : Dusan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <%@include file="_partials/headStyles.jsp"%>
        <link rel="stylesheet" href="css/app.css">
        <title>Pocetna</title>
    </head>
    <body>
        <%@include file="_partials/navbar.jsp"%>
        <main id="mainC">
            <div class="jumbotron">
                <%@include file="_partials/err_succ.jsp"%>
                <h1 class="display-4 text-center">Projekat ketering!</h1>
                <p class="lead text-center">Ovo je primer sajta za ketering radjen u javi.</p>
                <hr class="my-4">
                <p class="text-center">Sluzi da prikaze servlet i jsp tehnologiju.</p>
                <p class="lead text-center">
                    <a class="btn btn-primary btn-lg" href="http://localhost:8084/Projekat/basicPages/sviProizvodi.jsp" role="button">Pogledajte meni</a>
                </p>
            </div>
        </main>
        <%@include file="_partials/footer.jsp"%>
    </body>
</html>

