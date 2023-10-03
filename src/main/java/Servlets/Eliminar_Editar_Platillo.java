/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Metodos.Validaciones;
import Modelo.CPlatillo;
import Modelo.MNegocio;
import Modelo.VWPlatillo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MARIA DEL REFUGIO
 */
public class Eliminar_Editar_Platillo extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession sesionneg = request.getSession();
            String tipo, valor, pagina = "error.html";
            Validaciones val = new Validaciones();
            VWPlatillo plat = new VWPlatillo();
            VWPlatillo pla = new VWPlatillo();
            MNegocio neg = new MNegocio();
            int longitud = 0, indice = 0;
            neg = (MNegocio) sesionneg.getAttribute("Negocio");
            if(neg != null){
                pagina = "error_neg.jsp";
                valor = request.getParameter("Platillo");
                Vector<VWPlatillo> vplat = (Vector<VWPlatillo>) sesionneg.getAttribute("Vector_Platillos");
                if(plat != null){
                    longitud = vplat.size();
                    if(val.Validar_Edi_Eli_Platillos(valor, longitud)){
                        indice = Integer.parseInt(valor);
                        plat = vplat.elementAt(indice);
                        sesionneg.setAttribute("Modificar_Platillo", plat);
                        pagina = "editarplatillo.jsp";                   
                    }
                }
            }            
            
            response.sendRedirect(pagina);
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
