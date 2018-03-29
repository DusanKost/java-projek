/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;

/**
 *
 * @author Dusan
 */
public class UserController extends HttpServlet {
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String username = request.getParameter("username");
        if (username.isEmpty()) {
            request.setAttribute("error", "Mora biti poslan username kao param");
            request.getRequestDispatcher("/admin/users.jsp").forward(request, response);
        }else{
            try {
                User user = User.getByUserNmae(username);
                request.setAttribute("user", user);
                request.getRequestDispatcher("/admin/editUser.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    protected  void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type_of_user = request.getParameter("type_of_user");
        String form = request.getParameter("form");
        String id = request.getParameter("id");
        
        if (username.isEmpty() || password.isEmpty() || id.isEmpty() || type_of_user.isEmpty()) {
            request.setAttribute("error", "Sva polja moraju biti popunjena");
            request.getRequestDispatcher(form).forward(request, response);
        }else{
            int type = Integer.parseInt(type_of_user);
            int xd = Integer.parseInt(id);
            User.updateById(xd, username, password, type);
            request.setAttribute("success", "Uspesno updejtovaj");
            request.getRequestDispatcher("/admin/users.jsp").forward(request, response);
        }
        
        
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        if (request.getParameter("_method") != null) {
            if (request.getParameter("_method").equals("put")) {
                doPut(request, response);
            }
        }
        else{
            String id = request.getParameter("id");
            if (!id.isEmpty()) {
                int xd = Integer.parseInt(id);
                User.deleteById(xd);
                request.setAttribute("success", "Uspesno obrisan");
                request.getRequestDispatcher("/admin/users.jsp").forward(request, response);
            }else{
                request.setAttribute("error", "Mora biti id pri brisanju");
                request.getRequestDispatcher("/admin/users.jsp").forward(request, response);
            }
        }
    }

}
