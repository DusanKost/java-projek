<%-- 
    Document   : editProizvod
    Created on : Mar 24, 2018, 1:44:43 AM
    Author     : Dusan
--%>

<%@page import="models.User"%>
<%@page import="models.Proizvod"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <form action="http://localhost:8084/Projekat/ProizvodController" method="POST" enctype="multipart/form-data">
                <div class="form-group">
                  <label for="naziv">Naziv:</label>
                  <input name="naziv" value="${proizvod.getNaziv()}" type="text" class="form-control" id="naziv" placeholder="Naziv">
                </div>
                <div class="form-group">
                  <select class="custom-select" name="sladak">
                        <option value="0">Slan</option>
                        <option value="1">Sladak</option>
                  </select>
                  <label for="exampleInputPassword1">Cena:</label>
                  <input name="cena" value="${proizvod.getCena()}" type="number" class="form-control" id="cena" placeholder="Cena">
                  <label for="kolicina">Kolicina</label>
                  <input name="kolicina" value="${proizvod.getKolicina()}" type="number" class="form-control" id="kolicina" placeholder="Kolicina">
                  <input accept="image/*" type="file" name="slika" />
                  
                  <input type="hidden" name="_method" value="put">
                  <input type="hidden" name="id" value="${proizvod.getId()}">
                  <input type="hidden" name="oldFileName" value="${proizvod.getSlika()}">
                  <input type="hidden" name="form" value="<%= request.getServletPath() %>">
                </div>
                <input type="submit" class="btn btn-primary" value="Save Changes">
             </form>
        </main>
        <%@include file="../_partials/footer.jsp"%>
    </body>
</html>
