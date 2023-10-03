/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Metodos.Validaciones;
import Modelo.Colonias;
import Modelo.MCliente;
import Modelo.MNegocio;
import Modelo.Municipios;
import Modelo.VWCliente;
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
public class Elegir_Cliente extends HttpServlet {

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
            Validaciones val = new Validaciones();
            Vector<MCliente> cli = new Vector<MCliente>();
            MCliente aux_cli = new MCliente();
            MCliente clie = new MCliente();
            MNegocio neg = new MNegocio();
            HttpSession sesionneg = request.getSession();
            neg = (MNegocio) sesionneg.getAttribute("Negocio");
            String pagina = "error.html", cliente;
            int longitud = 0;
            VWCliente clien = new VWCliente();
            VWCliente aux_clie = new VWCliente();
            if(neg != null){
                pagina = "error_neg.jsp";
                cliente = request.getParameter("Cli");
                cli = aux_cli.Clientes(neg.getId_mneg());
                if(cli != null){
                    longitud = cli.size();
                    if(val.Validar_Delegacion(cliente, longitud)){
                        clie = cli.elementAt(Integer.parseInt(cliente));
                        clien = aux_clie.Cliente(clie.getId_mcli());
                        sesionneg.setAttribute("Cliente_Orden", clien);
                        pagina = "agregarorden.jsp";
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
            Logger.getLogger(Elegir_Cliente.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Elegir_Cliente.class.getName()).log(Level.SEVERE, null, ex);
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
