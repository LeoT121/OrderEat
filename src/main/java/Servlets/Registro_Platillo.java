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
import Modelo.CTiempo;
import Modelo.MNegocio;
import Modelo.Rel_Plat_Ins;
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
public class Registro_Platillo extends HttpServlet {

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
            CPlatillo cpla = new CPlatillo();
            AES aes = new AES();
            CPlatillo regplat = new CPlatillo();
            Validaciones val = new Validaciones();
            HttpSession sesionneg = request.getSession();
            MNegocio neg = (MNegocio) sesionneg.getAttribute("Negocio");
            String pagina = "error.html", plat, desc, cost, tiempo;
            if(neg != null){
                int id_mneg = neg.getId_mneg();
                pagina = "error_neg.jsp";
                int estatus = 0, costo = 0, id_ctie, indice;
                plat = request.getParameter("Nombre_Platillo");
                desc = request.getParameter("Descripcion_Platillo");
                cost = request.getParameter("Precio_Platillo");    
                tiempo = request.getParameter("Tiempo");
                Vector<CTiempo> tiempos = new Vector<CTiempo>();
                CTiempo aux = new CTiempo();
                tiempos = aux.Tiempos(neg.getId_mneg());
                Vector<Aux_Ins> vains = new Vector<Aux_Ins>();
                Rel_Plat_Ins rpi = new Rel_Plat_Ins();
                Rel_Plat_Ins irpi = new Rel_Plat_Ins();
                vains = (Vector<Aux_Ins>) sesionneg.getAttribute("Vector_Aux_Insumos");
                if(vains != null){
                    if(vains.size() > 0){
                        if(val.Validar_Registro_Platillo(plat, desc, cost, tiempo, tiempos.size())){
                            if(cpla.Consultar_Disponibilidad(plat, neg.getId_mneg())){
                                indice = Integer.parseInt(tiempo);
                                aux = tiempos.elementAt(indice);
                                costo = Integer.parseInt(cost);
                                cost = aes.getAES(Integer.toString(costo));
                                regplat = new CPlatillo(0, id_mneg, aux.getId_ctie(), plat, desc, cost);
                                estatus = CPlatillo.Registrar_Platillo(regplat);
                                System.out.println("Hola como estas");
                                if(estatus > 0){
                                    int id = CPlatillo.Detalle(plat);
                                    if(id > 0){
                                        System.out.println(id);
                                        Boolean saber = true;
                                        for(Aux_Ins i : vains){
                                            rpi = new Rel_Plat_Ins(i.getId_mins(), id, 0, i.getCan_mins());
                                            estatus = irpi.Registrar_Relacion(rpi); 
                                            if(estatus == 0){
                                                saber = false;
                                                break;
                                            }
                                        }
                                        if(saber){
                                            pagina = "platillos.jsp";
                                        }
                                        
                                    }
                                }
                            }                
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
            Logger.getLogger(Registro_Platillo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Registro_Platillo.class.getName()).log(Level.SEVERE, null, ex);
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
