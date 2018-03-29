/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.KupljenProizvod;

/**
 *
 * @author Dusan
 */
public class KupljenProizvodController extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
       String xd = request.getParameter("id");
        if (xd.isEmpty() || xd == null) {
            request.setAttribute("error", "Error");
            request.getRequestDispatcher("/admin/nePoslatiProizvodi.jsp").forward(request, response);
        }else{
            try {
                int id = Integer.parseInt(xd);
                KupljenProizvod.updateNePoslan(id);
                request.setAttribute("success", "Uspesno poslato");
                request.getRequestDispatcher("/admin/nePoslatiProizvodi.jsp").forward(request, response);
            } catch (Exception e) {
            }
        }
    }


}
