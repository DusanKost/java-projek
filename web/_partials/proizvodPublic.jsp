<%-- 
    Document   : proizvodPublic
    Created on : Mar 24, 2018, 1:31:20 AM
    Author     : Dusan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="col-sm-4" style="margin-bottom:  50px;">
    <div class="card" style="width: 18rem;">
        <img class="card-img-top" style="height: 200px; width: auto;" src="http://localhost:8084/Projekat/images/<%= p.getSlika() %>" alt="Card image cap">
     <div class="card-body">
       <h5 class="card-title text-center"><%= p.getNaziv() %></h5>
       <p class="card-text text-center"><small class="text-muted"><%= p.sladakToString(p.getSladak()) %></small></p>
       <p class="card-text text-center">Kolicina: <small class="text-muted"><%= p.getKolicina() %></small></p>
       <p class="card-text text-center">Cena: <%= p.getCena() %></p>
       <%
           if (p.getKolicina() == 0) {
                   %>
                   <a style="pointer-events: none;
   cursor: default" href="http://localhost:8084/Projekat/KorpaController?id=<%= p.getId() %>" class="btn btn-primary btn-block">Trenutno nemamo u ponudi</a>
                   <%
               }else{
               %>
               <a href="http://localhost:8084/Projekat/KorpaController?id=<%= p.getId() %>" class="btn btn-primary btn-block">Add To Cart</a>
               <%
               }
       %>
<!--       <a href="http://localhost:8084/Projekat/KorpaController?id=<%= p.getId() %>" class="btn btn-primary btn-block">Add To Cart</a>-->
     </div>
   </div>
 </div>
