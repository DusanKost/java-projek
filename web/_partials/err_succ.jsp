<%-- 
    Document   : err_succ
    Created on : Mar 15, 2018, 9:13:39 PM
    Author     : Dusan
--%>

<%
            if (request.getAttribute("success") != null) 
            {
        %>
            <div class="alert alert-success" role="alert">
                ${success}
            </div>
        <%
            }
        %>
        
        <%
            if (request.getAttribute("error") != null) 
            {
        %>
            <div class="alert alert-danger" role="alert">
                ${error}
            </div>
        <%
            }
        %>