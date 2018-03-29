<%-- 
    Document   : navbar
    Created on : Mar 15, 2018, 8:01:15 PM
    Author     : Dusan
--%>

<%@page import="models.User"%>
<%--<%= request.getServletPath() %>--%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="http://localhost:8084/Projekat/">Projekat</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul id="nav" class="navbar-nav ml-auto">
      <li class="nav-item">
        <a class="nav-link" href="http://localhost:8084/Projekat/">Pocetna <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="http://localhost:8084/Projekat/basicPages/sviProizvodi.jsp">Meni <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="http://localhost:8084/Projekat/basicPages/sladkiProizvodi.jsp">Sladko <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="http://localhost:8084/Projekat/basicPages/slaniProizvodi.jsp">Slano <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="http://localhost:8084/Projekat/basicPages/about.jsp">O Nama <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="http://localhost:8084/Projekat/basicPages/kontakt.jsp">Kontakt <span class="sr-only">(current)</span></a>
      </li>
      <%
            User xz = (User)request.getSession().getAttribute("user");
            session=request.getSession(false);
            if(session.getAttribute("user")==null)
            {
                %>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8084/Projekat/auth/register.jsp">Register</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8084/Projekat/auth/login.jsp">Login</a>
                </li>
                <%
            }else
            {
                if (xz.getType_of_user() == 2) {
                %>
                    <li class="nav-item">
                        <a class="nav-link" href="http://localhost:8084/Projekat/admin/users.jsp">Users</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="http://localhost:8084/Projekat/admin/proizvodi.jsp">Proizvodi</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="http://localhost:8084/Projekat/admin/prazniProizvodi.jsp">Prazni Proizvodi</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="http://localhost:8084/Projekat/admin/nePoslatiProizvodi.jsp">Ne Poslati Proizvodi</a>
                    </li>
                    
                <%
                }
                else{
                    if (xz.getType_of_user() == 3 || xz.getType_of_user() == 5) {
                        %>
                            <li class="nav-item">
                                <a class="nav-link" href="http://localhost:8084/Projekat/admin/proizvodi.jsp">Proizvodi</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="http://localhost:8084/Projekat/admin/prazniProizvodi.jsp">Prazni Proizvodi</a>
                            </li>
                        <%
                    }
                }
                %>
                <li class="nav-item">
                  <a class="nav-link" href="http://localhost:8084/Projekat/admin/korpa.jsp">Korpa</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="http://localhost:8084/Projekat/LogoutController">Logout</a>
                </li>
                <%
            }
      %>
    </ul>
  </div>
</nav>
