/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Metodos.AES;
import Metodos.JWT;
import Metodos.JavaMail;
import Metodos.Validaciones;
import Modelo.CPlatillo;
import Modelo.MNegocio;
import Modelo.MPagos;
import Modelo.Tokens;
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
public class Registro_Pago extends HttpServlet {

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
            AES aes = new AES();
            MPagos mpag = new MPagos();
            MPagos pag = new MPagos();
            Validaciones val = new Validaciones();
            HttpSession sesionneg = request.getSession();
            MNegocio neg = (MNegocio) sesionneg.getAttribute("Negocio");
            String pagina = "error.html", tit, desc, cost;
            long now = System.currentTimeMillis();
            if(neg != null){
                
                pagina = "error_neg.jsp";
                int id_mneg = neg.getId_mneg();
                int estatus = 0, costo = 0;
                tit = request.getParameter("Titulo");
                desc = request.getParameter("Descripcion");
                cost = request.getParameter("Monto");            
                if(val.Validar_Registro_Platillo(tit, desc, cost, "0", 1)){
                    tit = aes.getAES(tit);
                    desc = aes.getAES(desc);                                                       
                    if(pag.Consultar_Disponibilidad(tit, neg.getId_mneg())){
                        costo = Integer.parseInt(cost);
                        cost = aes.getAES(Integer.toString(costo));
                        mpag = new MPagos(0, neg.getId_mneg(), cost, tit, desc);
                        
                        String token = JWT.createJWT(Integer.toString(neg.getId_mneg()), neg.getNomusu_mneg());
                        String cor = aes.getAESDecrypt(neg.getCor_mneg());
                        String destinatario =  cor; //A quien le quieres escribir.
                        String asunto = "Registro de pagos";
                        String cuerpo = "Este correo es enviado debido a que quiere registrar un pago, para esto necesita ingresar el siguiente codigo:\n"
                                + "El codigo es:\n";
                        cuerpo += token;
                        cuerpo += "\nEl token tiene una expiracion de 3 minutos.\n"
                                + "Atentamente Ordereat.";
                        JavaMail mail = new JavaMail();
                        try{
                            sesionneg.setAttribute("Pagos", mpag);
                            mail.enviarConGMail(destinatario, asunto, cuerpo);
                            pagina = "verificacion_administracion.jsp";
                        }        
                        catch(Exception e){
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
            Logger.getLogger(Registro_Pago.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Registro_Pago.class.getName()).log(Level.SEVERE, null, ex);
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
