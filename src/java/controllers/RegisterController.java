/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;

/**
 *
 * @author Dusan
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException , IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type_of_user = request.getParameter("type_of_user");
        String form = request.getParameter("from");
        
        if (username.isEmpty() || password.isEmpty() || type_of_user.isEmpty()) {
            request.setAttribute("error", "Sva polja moraju biti popunjena");
            request.getRequestDispatcher(form).forward(request, response);
        }else{
            int xt = Integer.parseInt(type_of_user);
        
            User user = new User(username,password,xt);
            try {
                user.insert();
                request.setAttribute("success", "Uspesno registrovan korisnik");
                request.getRequestDispatcher(form).forward(request, response);
            }catch (SQLException e) {
                request.setAttribute("error", "Taj username je vec zauzet");
                request.getRequestDispatcher(form).forward(request, response);
            }  
            catch (Exception e) {
                e.printStackTrace();
            }   
        }
    }


}
