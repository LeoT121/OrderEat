/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Metodos.AES;
import Metodos.Validaciones;
import Modelo.DCliente;
import Modelo.DOrden;
import Modelo.MCliente;
import Modelo.MInsumos;
import Modelo.MNegocio;
import Modelo.MOrden;
import Modelo.Orden_Platillos;
import Modelo.Rel_Plat_Ins;
import Modelo.VWCliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class Agregar_Orden extends HttpServlet {

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
            Validaciones val = new Validaciones();
            MNegocio neg = new MNegocio();
            neg = (MNegocio) sesionneg.getAttribute("Negocio");
            String pagina = "error.html", comentario;
            MOrden ord = new MOrden();
            VWCliente clie = new VWCliente();
            Vector<Orden_Platillos> voplat = new Vector<Orden_Platillos>();
            AES aes = new AES();  
            int estatus = 0, id;
            Calendar fecha = new GregorianCalendar();
            int anio = fecha.get(Calendar.YEAR);
            int mes = fecha.get(Calendar.MONTH);
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            if(neg != null){
                pagina = "error_neg.jsp";
                clie = (VWCliente) sesionneg.getAttribute("Cliente_Orden");
                if(clie != null){
                    voplat = (Vector<Orden_Platillos>) sesionneg.getAttribute("Vector_Orden");
                    if(voplat != null){
                        if(voplat.size() != 0){
                            comentario = request.getParameter("Comentario");
                            if(val.Validar_Orden(comentario)){
                                int longitud = voplat.size();
                                int costo = 0;
                                String costo_medida;
                                Orden_Platillos act = new Orden_Platillos();
                                for(int i = 0; i < longitud; ++i){
                                    act = voplat.elementAt(i);
                                    costo += act.getCost();
                                }
                                costo_medida = Integer.toString(costo);
                                ord = new MOrden(0, neg.getId_mneg(), clie.getId_mcli(), 1, comentario, aes.getAES(costo_medida));
                                estatus = MOrden.Registrar_Orden(ord, dia, mes, anio);
                                if(estatus > 0){
                                    id = MOrden.Max_Cliente();
                                    if(id != 0){
                                        boolean bandera = true;
                                        DOrden actual = new DOrden();
                                        Vector<MInsumos> mins = new Vector<MInsumos>();
                                        Vector<Float> auxiliar = new Vector<Float>();
                                        Vector<Rel_Plat_Ins> vrpi = new Vector<Rel_Plat_Ins>();
                                        Rel_Plat_Ins crpi = new Rel_Plat_Ins();
                                        vrpi = crpi.DetallesPlatIns();
                                        MInsumos cmins = new MInsumos();
                                        mins = cmins.Insumos(neg.getId_mneg());
                                        for(MInsumos i : mins){
                                            auxiliar.add(i.getCant_mins());
                                        }
                                        int longitud5 = mins.size();
                                        for(int i = 0; i < longitud; ++i){
                                            act = voplat.elementAt(i);
                                            actual = new DOrden(0, id, act.getId_cpla(), act.getCant());
                                            estatus = DOrden.Registrar_DOrden(actual);                                            
                                            if(estatus == 0){
                                                bandera = false;                                              
                                            }
                                            for(Rel_Plat_Ins j : vrpi){
                                                if(j.getId_cpla() == act.getId_cpla()){
                                                    for(int k = 0; k < longitud5; ++ k){
                                                        MInsumos minsaux = mins.elementAt(k);
                                                        if(minsaux.getId_mins() == j.getId_mins()){
                                                            float a = act.getCant();
                                                            a *= j.getCan_rpi();
                                                            float b = auxiliar.elementAt(k);
                                                            a = b - a;
                                                            auxiliar.set(k, a);
                                                            
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        for(int k = 0; k < longitud5; ++ k){
                                            if(auxiliar.elementAt(k) != mins.elementAt(k).getCant_mins()){
                                                estatus = cmins.Agregar_Cantidad(auxiliar.elementAt(k), mins.elementAt(k).getId_mins());
                                                if(estatus == 0){
                                                    bandera = false;
                                                }
                                            }
                                        }
                                        if(bandera){
                                            pagina = "ordenes.jsp";
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
            Logger.getLogger(Agregar_Orden.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Agregar_Orden.class.getName()).log(Level.SEVERE, null, ex);
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
