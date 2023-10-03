/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Metodos.AES;
import Metodos.Validaciones;
import Modelo.Aux_Ins;
import Modelo.CPlatillo;
import Modelo.MInsumos;
import Modelo.MNegocio;
import Modelo.Orden_Platillos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Vector;
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
public class Agregar_Insumo_Platillo extends HttpServlet {

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
            String platillo, cantidad, pagina = "error.html"; 
            if(neg != null){
                pagina = "error_neg.jsp";
                platillo = request.getParameter("Insumo");
                cantidad = request.getParameter("cantidad");
                Validaciones val = new Validaciones();
                Vector<MInsumos> plat = new Vector<MInsumos>();
                MInsumos pla = new MInsumos();            
                plat = pla.Insumos(neg.getId_mneg());
                int longitud = 0, indice = 0;
                float cant, costo;
                Vector<Aux_Ins> voplat = new Vector<Aux_Ins>();
                voplat = (Vector<Aux_Ins>)sesionneg.getAttribute("Vector_Aux_Insumos");
                Aux_Ins ins = new Aux_Ins();
                if(plat != null && voplat != null){
                    if(plat.size() > 0){
                        longitud = plat.size();
                        if(val.Validar_Agregar_Insumo_Platillo(platillo, cantidad, longitud)){
                            indice = Integer.parseInt(platillo);
                            pla = plat.elementAt(indice);
                            cant = Float.parseFloat(cantidad);
                            ins = new Aux_Ins(pla.getId_mins(), cant, pla.getNom_mins());
                            voplat.add(ins);
                            sesionneg.setAttribute("Vector_Aux_Insumos", voplat);
                            pagina = "agregarplatillo.jsp";
                        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Agregar_Insumo_Platillo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Agregar_Insumo_Platillo.class.getName()).log(Level.SEVERE, null, ex);
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
