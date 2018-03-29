/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import models.Proizvod;

/**
 *
 * @author Dusan
 */
@MultipartConfig
public class ProizvodController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Salje na edit proizvoda
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
        String naziv = request.getParameter("naziv");
        if (naziv.isEmpty()) {
            request.setAttribute("error", "Mora biti poslan naziv kao param");
            request.getRequestDispatcher("/admin/proizvodi.jsp").forward(request, response);
        }else{
            try {
                Proizvod proizvod = Proizvod.getByNaziv(naziv);
                request.setAttribute("proizvod", proizvod);
                request.getRequestDispatcher("/admin/editProizvod.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /*
    *Brise proizvod
    */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String savePath = "C:\\Users\\Dusan\\Documents\\NetBeansProjects\\Projekat\\web\\images\\";
        
        String id = request.getParameter("id");
        try {
            if (id.isEmpty()) {
            request.setAttribute("error", "ID mora biti poslat");
            request.getRequestDispatcher("/admin/proizvodi.jsp").forward(request, response);
            }else{
                int xd = Integer.parseInt(id);
                Proizvod p = Proizvod.getById(xd);
                if (p == null) {
                    request.setAttribute("error", "Nema proizvod sa tim ID");
                    request.getRequestDispatcher("/admin/proizvodi.jsp").forward(request, response);
                }else{
                    Proizvod.deleteById(xd);
                    File file = new File(savePath + p.getSlika());
                    file.delete();
                }
                request.setAttribute("success", "Uspesno obrisan");
                request.getRequestDispatcher("/admin/proizvodi.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*
    *Cuva edit proizvoda
    */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {   
        String savePath = "C:\\Users\\Dusan\\Documents\\NetBeansProjects\\Projekat\\web\\images\\";
    
        Part part = request.getPart("slika");
        String id = request.getParameter("id");
        String naziv = request.getParameter("naziv");
        String sladak = request.getParameter("sladak");
        String cena = request.getParameter("cena");
        String kolicina = request.getParameter("kolicina");
        String oldFileName = request.getParameter("oldFileName");
        String form = request.getParameter("form");
        String fileName = extractFileName(part);
        
        if (naziv.isEmpty() || sladak.isEmpty() || cena.isEmpty() || kolicina.isEmpty()) {
            request.setAttribute("error", "Polja ne smeju biti prazna");
            request.getRequestDispatcher(form).forward(request, response);
        }else{
            try {
                //Proizvod p = Proizvod.getByNaziv(naziv);
                int xd = Integer.parseInt(id);
                int sladakK = Integer.parseInt(sladak);
                int kolicinaA = Integer.parseInt(kolicina);
                int cenaA = Integer.parseInt(cena);
                if (fileName==null || fileName.isEmpty() || fileName.length() == 0) {
                    Proizvod.updateByIdNoImage(xd, naziv, sladakK, cenaA, kolicinaA);
                    request.setAttribute("success", "Uspesno izmenjen");
                    request.getRequestDispatcher("/admin/proizvodi.jsp").forward(request, response);
                }else{
                    Proizvod.updateById(xd, naziv, fileName, sladakK, cenaA, kolicinaA);
                    part.write(savePath + fileName);
                    if (!oldFileName.isEmpty()) {
                        File file = new File(savePath + oldFileName);
                        file.delete();
                    }
                    request.setAttribute("success", "Uspesno izmenjen");
                    request.getRequestDispatcher("/admin/proizvodi.jsp").forward(request, response);
                }
            }catch (Exception e) {
                request.setAttribute("error", "Nesto ne valja verovatno je to ime za proizvod vec zauzeto");
                request.getRequestDispatcher(form).forward(request, response);
                e.printStackTrace();
            }
        }
    }

    /**
     * Kreira proizvod
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
            }else{
               if (request.getParameter("_method").equals("delete")) {
                    doDelete(request, response);
                } 
            }
        }else{
            String savePath = "C:\\Users\\Dusan\\Documents\\NetBeansProjects\\Projekat\\web\\images\\";
        
            Part part = request.getPart("slika");
            String naziv = request.getParameter("naziv");
            String sladak = request.getParameter("sladak");
            String cena = request.getParameter("cena");
            String kolicina = request.getParameter("kolicina");
            String fileName = extractFileName(part);
            String form = request.getParameter("from");
            if (part.getSize() == 0 || naziv.isEmpty() || sladak.isEmpty() || cena.isEmpty() || cena.equals("0") || kolicina.isEmpty()) {
                request.setAttribute("error", "Sva polja moraju biti popunjena i slika odabrana i cena ne moze biti 0");
                request.getRequestDispatcher(form).forward(request, response);
            }else{
                try {
                    int sladB = Integer.parseInt(sladak);
                    int c = Integer.parseInt(cena);
                    int k = Integer.parseInt(kolicina);
                    Proizvod p = new Proizvod(naziv, fileName, sladB,c,k);
                    p.insert();
                    part.write(savePath + fileName);

                    request.setAttribute("success", "Uspesno Unesen Proizvod");
                    request.getRequestDispatcher(form).forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("error", "Taj naziv proizvoda vec postoji");
                    request.getRequestDispatcher(form).forward(request, response);
                }
            }
        }
    }
    
    public String extractFileName(Part part){
        
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return  s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

}
