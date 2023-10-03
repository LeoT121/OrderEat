/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Metodos.AES;
import Metodos.Validaciones;
import Modelo.Colonias;
import Modelo.DCliente;
import Modelo.DOrden;
import Modelo.MCliente;
import Modelo.MNegocio;
import Modelo.MOrden;
import Modelo.Municipios;
import Modelo.Orden_Platillos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class Agregar_Cliente extends HttpServlet {

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
            HttpSession sesionusu = request.getSession();
            Validaciones val = new Validaciones();
            Municipios mun = new Municipios();
            Colonias aux_col = new Colonias();
            Colonias cols = new Colonias();
            Vector<Colonias> vcol = new Vector<Colonias>();
            MNegocio neg = new MNegocio();
            MCliente mcli = new MCliente();
            MCliente aux = new MCliente();
            neg = (MNegocio) sesionusu.getAttribute("Negocio");
            String pagina = "error.html", cli, tel, cal, n_ext, n_int, col;
            if(neg != null){
                pagina = "error_neg.jsp";
                mun = (Municipios) sesionusu.getAttribute("Delegacion");
                if(mun != null){
                    vcol = aux_col.Col(mun.getId());
                    cli = request.getParameter("Cli");
                    tel = request.getParameter("Tel");
                    cal = request.getParameter("Cal");
                    n_ext = request.getParameter("N_Ext");
                    n_int = request.getParameter("N_Int");
                    col = request.getParameter("Col");
                    boolean bandera = true;
                    int nint = 0, next = 0, indice, longitud, estatus;
                    if(vcol != null){
                        if(vcol.size() > 0){
                            longitud = vcol.size();
                            if(val.Validar_Cliente(cli, tel, cal, n_ext, n_int, col, longitud)){
                                next = Integer.parseInt(n_ext);
                                nint = (n_int.equals("")) ? -1 : Integer.parseInt(n_int);
                                indice = Integer.parseInt(col);
                                cols = vcol.elementAt(indice);
                                mcli = new MCliente(0, neg.getId_mneg(), cols.getId(), next, nint, cli, cal, tel);
                                if(aux.Consultar_Disponibilidad(cli, neg.getId_mneg())){
                                    estatus = MCliente.Registrar_Cliente(mcli);
                                    if(estatus > 0){
                                        pagina = "cliente.jsp";
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
            Logger.getLogger(Agregar_Cliente.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Agregar_Cliente.class.getName()).log(Level.SEVERE, null, ex);
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
