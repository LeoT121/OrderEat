/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Metodos.Validaciones;
import Modelo.DCliente;
import Modelo.DOrden;
import Modelo.MNegocio;
import Modelo.MOrden;
import Modelo.MPagos;
import Modelo.Tokens;
import Modelo.VCliente;
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
public class Eliminar_Pago extends HttpServlet {

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
            String pagina = "error.html";
            Validaciones val = new Validaciones();
            Vector<VCliente> vcli = new Vector<VCliente>();
            Vector<MPagos> pagos = new Vector<MPagos>();
            MPagos mpag = new MPagos();
            MPagos pag = new MPagos();
            int longitud = 0, indice = 0, estatus = 0, id;
            Tokens tok = new Tokens();
            String valor = request.getParameter("Opcion2");
            long now = System.currentTimeMillis();
            MNegocio neg = new MNegocio();
            neg = (MNegocio) sesionneg.getAttribute("Negocio");
            if(neg != null){
                pagina = "error_neg.jsp";
                pagos = pag.Pagos(neg.getId_mneg());                                
                if(pagos != null){
                    longitud = pagos.size();
                    if(val.Validar_Entrega_Orden(valor, longitud)){
                        indice = Integer.parseInt(valor);
                        mpag = pagos.elementAt(indice);
                        id = mpag.getId_mpag();
                        estatus = MPagos.Eliminar_Pago(id);
                        if(estatus > 0){
                            pagina = "reportes.jsp";
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
            Logger.getLogger(Eliminar_Pago.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Eliminar_Pago.class.getName()).log(Level.SEVERE, null, ex);
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
