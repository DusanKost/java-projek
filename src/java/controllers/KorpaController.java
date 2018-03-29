/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Proizvod;
import models.User;
import models.KupljenProizvod;

/**
 *
 * @author Dusan
 */
public class KorpaController extends HttpServlet {
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
        User x = (User)request.getSession().getAttribute("user");
        HttpSession y = request.getSession(false);
        if(y.getAttribute("user")==null)
        {
            response.sendRedirect("auth/login.jsp");
        }else{
            String sd = request.getParameter("id");
            try {
                int id = Integer.parseInt(sd);
                Proizvod p = Proizvod.getById(id);
                ArrayList<Proizvod> lista = (ArrayList<Proizvod>)request.getSession().getAttribute("korpa");
                lista.add(p);
                request.setAttribute("success", "Artikal dodat u korpu");
                request.getRequestDispatcher("index.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
            }
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
            try {
                if (request.getParameter("_method").equals("put")) {
                    ArrayList<Proizvod> lista = (ArrayList<Proizvod>)request.getSession().getAttribute("korpa");
                    User user = (User)request.getSession().getAttribute("user");
                    String stringLista = new Gson().toJson(lista);
                    KupljenProizvod.insert(user.getId(), stringLista);
                    for (Proizvod p : lista) {
                        p.updateKolicina();
                    }
                    lista.clear();
                    request.setAttribute("success", "Uspesno kupljeno");
                    request.getRequestDispatcher("/admin/korpa.jsp").forward(request, response);
                }else{
                    if (request.getParameter("_method").equals("delete")) {
                        ArrayList<Proizvod> lista = (ArrayList<Proizvod>)request.getSession().getAttribute("korpa");
                        lista.clear();
                        request.setAttribute("success", "Korpa uspesno ispraznjena");
                        request.getRequestDispatcher("/admin/korpa.jsp").forward(request, response);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            String id = request.getParameter("id");
            int xd = Integer.parseInt(id);
            ArrayList<Proizvod> lista = (ArrayList<Proizvod>)request.getSession().getAttribute("korpa");
            lista.remove(xd);
            request.setAttribute("success", "Uspesno obrisan artikal");
            request.getRequestDispatcher("/admin/korpa.jsp").forward(request, response);
        }
    }

}
