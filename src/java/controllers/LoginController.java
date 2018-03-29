/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Proizvod;
import models.User;

/**
 *
 * @author Dusan
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password  = request.getParameter("password");
        
        if (username.isEmpty() || password.isEmpty()) 
        {
            request.setAttribute("error", "Sva polja moraju biti popunnjena");
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
        }else{
            try 
            {
                User user = User.getByUserNmae(username);
                ArrayList<Proizvod> lista = new ArrayList<>();
                if (user.getPassword().equals(password)) {
                    /*begini of session*/
                    HttpSession session = request.getSession();
                    HttpSession korpa = request.getSession();
                    korpa.setAttribute("korpa", lista);
                    session.setAttribute("user", user);
                    //session.setAttribute("typeOfUser", user.getType_of_user());
                    /*end of session*/
                    request.setAttribute("success", "Logovan");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }else{
                    request.setAttribute("error", "Netacna sifra");
                    request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Ne postoji ta kombinacija");
                request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
            }
            catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Generalno nesto ne radi");
                request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
            }
            
        }
    }


}
