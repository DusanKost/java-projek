/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Proizvod;

/**
 *
 * @author Dusan
 */
public class AddRm1Controllerr extends HttpServlet {


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
        String param = request.getParameter("param");
            int par = Integer.parseInt(param);
            if (param != null || !param.isEmpty()) {
                String add = request.getParameter("add");
                if (add != null || !add.isEmpty()) {
                    if (add.equals("1")) {
                        ArrayList<Proizvod> lista = (ArrayList<Proizvod>)request.getSession().getAttribute("korpa");
                        Proizvod p = lista.get(par);
                        p.add1KupKolc();
                        request.setAttribute("success", "Uspesno uvecana kolicina");
                        request.getRequestDispatcher("/admin/korpa.jsp").forward(request, response);
                    }else{
                        ArrayList<Proizvod> lista = (ArrayList<Proizvod>)request.getSession().getAttribute("korpa");
                        Proizvod p = lista.get(par);
                        if (p.getKup_kolicina() == 1) {
                            lista.remove(p);
                            request.setAttribute("success", "Uspesno obrisan artiak kolicina");
                            request.getRequestDispatcher("/admin/korpa.jsp").forward(request, response);
                        }else{
                            p.rm1KupKolc();
                            request.setAttribute("success", "Uspesno umanjena kolicina");
                            request.getRequestDispatcher("/admin/korpa.jsp").forward(request, response);
                        }
                    }
                    
                }else{
                    request.setAttribute("error", "error");
                    request.getRequestDispatcher("/admin/korpa.jsp").forward(request, response);
                }
            }
    }

}
