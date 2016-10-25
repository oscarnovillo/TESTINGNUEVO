/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cajafuerte;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author oscar
 */
@WebServlet(name = "CajaFuerte", urlPatterns = {"/cajaFuerte"})
public class CajaFuerte extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean cajaAbierta = false;
        int combinacion[] = {67, 7, 54};

        Boolean passOK = (Boolean) request.getSession().getAttribute("passOK");
        if (passOK == null) {
            passOK = false;
        }
        if (!passOK) {

            Integer posicion = (Integer) request.getSession().getAttribute("posicion");
            if (posicion == null) {
                posicion = 0;
            }

            int claveActual = combinacion[posicion];

            String num = request.getParameter("num");
            if (num != null) {
                if (num.equals(claveActual + "")) {
                    posicion++;
                } else {
                    posicion = 0;
                }
                request.getSession().setAttribute("posicion", posicion);

                if (posicion == 3) {
                    cajaAbierta = true;
                    request.getSession().setAttribute("cajaAbierta", true);
                    request.getSession().setAttribute("posicion", 0);
                }
                if (!cajaAbierta) {
                    request.getServletContext().getRequestDispatcher("/sigueBuscando.jsp").forward(request, response);
                } else {
                    request.getServletContext().getRequestDispatcher("/ahoraPassword.jsp").forward(request, response);
                }
            } else {
                request.getServletContext().getRequestDispatcher("/errorParametro.jsp").forward(request, response);
            }

        }
        request.getServletContext().getRequestDispatcher("/cajaAbierta.jsp").forward(request, response);

    }

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
            throws ServletException, IOException {
        processRequest(request, response);
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
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
