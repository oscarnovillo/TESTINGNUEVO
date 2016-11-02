/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peso;

import com.utils.Constantes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author oscar
 */
@WebServlet(name = "Logout", urlPatterns = {"/Logout"})
public class Logout extends HttpServlet {

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
        boolean bError = false;
        String mensajeError = "";
        String nombre = "";
        String peso = "";
        //Errores

        if (request.getSession().getAttribute(Constantes.ATRIBUTO_NOMBRE) == null) {
            mensajeError = Constantes.MENSAJE_ERROR_NOMBRE_NO_EXISTE;
            bError = true;
        } 
        
        if (!bError) {
            nombre = (String)request.getSession().getAttribute(Constantes.ATRIBUTO_NOMBRE) ;
            peso = (String)request.getSession().getAttribute(Constantes.ATRIBUTO_PESO) ;
            //Registrar el usuario
            request.getSession().setAttribute(Constantes.ATRIBUTO_NOMBRE,
                    null);
            request.getSession().setAttribute(Constantes.ATRIBUTO_PESO,
                    null);
            //Bienvenida
            request.setAttribute(Constantes.ATRIBUTO_NOMBRE, nombre);
            request.setAttribute(Constantes.ATRIBUTO_PESO, peso);
            request.getRequestDispatcher(Constantes.PAGINA_LOGOUT).forward(request, response);

        } else {
            request.setAttribute(Constantes.ATRIBUTO_ERROR, mensajeError);
            request.getRequestDispatcher(Constantes.PAGINA_ERROR).forward(request, response);
        }

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
