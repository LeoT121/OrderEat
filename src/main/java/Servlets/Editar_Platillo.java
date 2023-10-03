/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Metodos.AES;
import Metodos.Validaciones;
import Modelo.CPlatillo;
import Modelo.MNegocio;
import Modelo.VWPlatillo;
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
public class Editar_Platillo extends HttpServlet {

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
            AES aes = new AES();
            neg = (MNegocio) sesionneg.getAttribute("Negocio");
            String pagina = "error.html";
            if(neg != null){
                VWPlatillo pla = new VWPlatillo();
                VWPlatillo plat = (VWPlatillo)sesionneg.getAttribute("Modificar_Platillo");
                Validaciones val = new Validaciones();
                String tipo, valor;
                pagina = "error_neg.jsp";
                int estatus = 0, costo;
                if(plat != null){                
                    tipo = request.getParameter("Valor_Cambiar");
                    valor = request.getParameter("Nuevo_Valor");
                    if(val.Validar_Editar_Platillo(tipo, valor)){
                        if(tipo.equals("Descripcion")){
                            if(!valor.equals(plat.getDes_cpla())){
                                estatus = CPlatillo.Editar_Platillo_Desc(valor, plat.getId_cpla());
                            }
                        }
                        else{
                            costo = Integer.parseInt(valor);
                            if(!Integer.toString(costo).equals(plat.getCos_cpla())){
                                String cos = Integer.toString(costo);
                                cos = aes.getAES(cos);
                                estatus = CPlatillo.Editar_Platillo_Costo(cos, plat.getId_cpla());
                            }
                        }
                        if(estatus > 0){
                            pagina = "platillos.jsp";
                        }
                    }
                    sesionneg.setAttribute("Modificar_Platillo", null);
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
            Logger.getLogger(Editar_Platillo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Editar_Platillo.class.getName()).log(Level.SEVERE, null, ex);
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
