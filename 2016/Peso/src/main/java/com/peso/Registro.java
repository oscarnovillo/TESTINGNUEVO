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
@WebServlet(name = "Registro", urlPatterns = {"/Registro"})
public class Registro extends HttpServlet {

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

        if (request.getSession().getAttribute(Constantes.ATRIBUTO_NOMBRE) != null) {
            mensajeError = Constantes.MENSAJE_ERROR_NOMBRE_EXISTE;
            bError = true;
        } else {
            nombre = request.getParameter(Constantes.ATRIBUTO_NOMBRE);
            peso = request.getParameter(Constantes.ATRIBUTO_PESO);

            if (nombre == null) {
                mensajeError = Constantes.MENSAJE_ERROR_NO_NOMBRE;
                bError = true;
            } else if (peso == null) {
                mensajeError = Constantes.MENSAJE_ERROR_NO_PESO;
                bError = true;
            } else if (!StringUtils.isNumeric(peso)) {
                mensajeError = Constantes.MENSAJE_ERROR_PESO_NO_NUMERO;
                bError = true;
            }
        }
        if (!bError) {
            //Registrar el usuario
            request.getSession().setAttribute(Constantes.ATRIBUTO_NOMBRE,
                    request.getParameter(Constantes.ATRIBUTO_NOMBRE));
            request.getSession().setAttribute(Constantes.ATRIBUTO_PESO,
                    request.getParameter(Constantes.ATRIBUTO_PESO));
            //Bienvenida
            request.setAttribute(Constantes.ATRIBUTO_NOMBRE, nombre);
            request.setAttribute(Constantes.ATRIBUTO_PESO, peso);
            response.getWriter().print("TRUE");
            //request.getRequestDispatcher(Constantes.PAGINA_REGISTRO).forward(request, response);

        } else {
            request.setAttribute(Constantes.ATRIBUTO_ERROR, mensajeError);
            response.getWriter().print("FALSE");
            //request.getRequestDispatcher(Constantes.PAGINA_ERROR).forward(request, response);
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
