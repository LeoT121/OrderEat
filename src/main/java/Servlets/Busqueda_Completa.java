/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Metodos.Validaciones;
import Modelo.MCliente;
import Modelo.MNegocio;
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
public class Busqueda_Completa extends HttpServlet {

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
            Vector<MCliente> vcli = new Vector<MCliente>();
            MCliente aux = new MCliente();                    
            MNegocio neg = (MNegocio) sesionneg.getAttribute("Negocio");
            String pagina = "error.html", valor, valor1, valor2;
            Validaciones val = new Validaciones();
            int numero = -1;
            if(neg != null){
                pagina = "error_neg.jsp";
                valor = request.getParameter("Cli");
                valor1 = request.getParameter("Dia");
                valor2 = request.getParameter("Mes");
                vcli = aux.Clientes(neg.getId_mneg());
                if(!(valor == null || valor1 == null || valor2 == null)){
                    if(valor == ""){
                        sesionneg.setAttribute("Busqueda", "-1");
                        pagina = "historial.jsp";
                    }
                    else{
                        if(vcli != null){
                            if(val.Validar_Entrega_Orden(valor, vcli.size())){
                                MCliente act = vcli.elementAt(Integer.parseInt(valor));
                                sesionneg.setAttribute("Busqueda", Integer.toString(act.getId_mcli()));
                                pagina = "historial.jsp";
                            }
                        }
                        else{
                            pagina = "error_neg.jsp";
                            sesionneg.setAttribute("Busqueda", "-1");
                        }
                    }
                    if(pagina != "error_neg.jsp"){
                        if(valor1 == ""){
                            sesionneg.setAttribute("Busqueda2", "-1");
                            pagina = "historial.jsp";
                        }
                        else{
                            if(val.Validar_Entrega_Orden_Dia(valor1)){
                                sesionneg.setAttribute("Busqueda2", valor1);
                            }
                            else{
                                pagina = "error_neg.jsp";
                                sesionneg.setAttribute("Busqueda2", "-1");
                            }
                        }
                    }
                    if(pagina != "error_neg.jsp"){
                        if(valor2 == ""){
                            sesionneg.setAttribute("Busqueda3", "-1");
                            pagina = "historial.jsp";
                        }
                        else{
                            if(val.Validar_Entrega_Orden_Dia(valor2)){
                                sesionneg.setAttribute("Busqueda3", valor2);
                            }
                            else{
                                pagina = "error_neg.jsp";
                                sesionneg.setAttribute("Busqueda3", "-1");
                            }
                        }
                    }
                    if(pagina == "error_neg.jsp"){
                        sesionneg.setAttribute("Busqueda", "-1");
                        sesionneg.setAttribute("Busqueda2", "-1");
                        sesionneg.setAttribute("Busqueda3", "-1");
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
            Logger.getLogger(Busqueda_Completa.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Busqueda_Completa.class.getName()).log(Level.SEVERE, null, ex);
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
