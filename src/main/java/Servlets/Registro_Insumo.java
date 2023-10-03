/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Metodos.AES;
import Metodos.JavaMail;
import Metodos.Validaciones;
import Modelo.MInsumos;
import Modelo.MNegocio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MARIA DEL REFUGIO
 */
public class Registro_Insumo extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession sesionneg = request.getSession();
            MNegocio neg = new MNegocio();
            neg = (MNegocio) sesionneg.getAttribute("Negocio");
            MNegocio regneg = new MNegocio();
            Validaciones val = new Validaciones();
            MInsumos minsaux = new MInsumos();
            MInsumos mins;
            String pagina = "error.html", nombre, uni, costo;
            int estatus = 0, id_mneg, cost = 0, un = 0;
            if(neg != null){
                pagina = "error_neg.jsp";
                nombre = request.getParameter("nombre");
                uni = request.getParameter("unidad");
                costo = request.getParameter("precio");
                id_mneg = neg.getId_mneg();
                if(val.Validar_Insumo(nombre, uni, costo)){
                    if(minsaux.Consultar_Disponibilidad(nombre)){
                        cost = Integer.parseInt(costo);
                        un = Integer.parseInt(uni);
                        mins = new MInsumos(0, id_mneg, un, cost, 0, nombre, 0);
                        estatus = minsaux.Registrar_Insumo(mins);
                        if(estatus > 0){
                            pagina = "agregarinsumo_platillo.jsp";
                        }
                    }
                }
            }
            response.sendRedirect(pagina);
            
        } catch (SQLException ex) {
            Logger.getLogger(Registro_Insumo.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Registro_Insumo.class.getName()).log(Level.SEVERE, null, ex);
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
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Registro_Insumo.class.getName()).log(Level.SEVERE, null, ex);
        }
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
