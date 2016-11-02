/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peso;

import com.utils.Constantes;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author oscar
 */
@WebServlet(name = "Workout", urlPatterns = {"/Workout"})
public class Workout extends HttpServlet {

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
        String workout = "";
        LocalDateTime ultimaLlamada = null;
        LocalDateTime ahora = null;
        LocalDateTime lesionado = null;

        //Errores
        if (request.getSession().getAttribute("nombre") == null) {
            mensajeError = "no hay nombre resgistrado";
            bError = true;
        } else {
            workout = request.getParameter("workout");

            if (workout == null) {
                mensajeError = "falta el parametro workout";
                bError = true;
            } else if (!StringUtils.isNumeric(workout)) {
                mensajeError = "workout debe de ser numérico";
                bError = true;
            }
        }
        if (!bError) {
            //Registrar el usuario
            nombre = (String) request.getSession().getAttribute("nombre");
            peso = (String) request.getSession().getAttribute("peso");
            ahora = LocalDateTime.now();
            ultimaLlamada = (LocalDateTime) request.getSession().getAttribute("ultimaLlamada");
            if (ultimaLlamada == null) {
                ultimaLlamada = LocalDateTime.now();
            }
            //engordar cada 10 segundos, tiempo entre llamadas.
            Duration segundos = Duration.between(ultimaLlamada, ahora);
            int iPeso = NumberUtils.toInt(peso);
            iPeso += (segundos.getSeconds() / 10);

            //mirar si lesion
            lesionado = (LocalDateTime) request.getSession().getAttribute("lesionado");
            Duration segundosLesion = null;

            // si hay lesion esperar tiempo
            if (lesionado != null) {
                segundosLesion = Duration.between(lesionado, ahora);
                if (segundosLesion.getSeconds() >= 30) {
                    // se le quita la lesion
                    request.getSession().setAttribute("lesionado", null);
                }
                request.setAttribute("lesionado", (30 - segundosLesion.getSeconds()) + "");
            } else//si han pasado menos de 5 seg se lesiona
            {
                if (segundos.getSeconds() <= 5) {
                    request.getSession().setAttribute("lesionado",ahora);
                } else {
                    //si no la hay adelgaza el tiempo requerido.
                    int kilosAdelgazar = (NumberUtils.toInt(workout) / 30);
                    kilosAdelgazar *= (int) (0.2 * iPeso);
                    iPeso -= kilosAdelgazar;
                }
            }
            peso = iPeso + "";
            request.getSession().setAttribute("peso", peso);
            request.getSession().setAttribute("ultimaLlamada", ahora);

            request.setAttribute("nombre", nombre);
            request.setAttribute("peso", peso);
            request.getRequestDispatcher("/workout.jsp").forward(request, response);
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
