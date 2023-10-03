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
import Modelo.MNegocio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MARIA DEL REFUGIO
 */
public class Validar_Token_Inicial extends HttpServlet {

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
            throws ServletException, IOException, AddressException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession sesionneg = request.getSession();
            MNegocio neg = new MNegocio();
            MNegocio aux = new MNegocio();
            MNegocio aux2 = new MNegocio();
            MNegocio aux3 = new MNegocio();
            JavaMail mail = new JavaMail();
            AES aes = new AES();
            Validaciones val = new Validaciones();
            String pagina = "error_neg.jsp", token;
            neg = (MNegocio) sesionneg.getAttribute("Negocio");
            int estatus = 0;
            if(neg == null){
                aux2 = (MNegocio) sesionneg.getAttribute("Previo");
                pagina = "error.html";
                if(aux2 != null){
                    token = request.getParameter("Token");
                    if(token != null){
                        if(JWT.decodeJWT(token, "121", aux2.getNomusu_mneg())){
                            if(aux.Consultar_Disponibilidad(aux2.getNomusu_mneg(), aux2.getCor_mneg())){
                                estatus = aux.Registrar_Negocio(aux2);
                                if(estatus > 0){
                                    aux3 = aux.Login_Negocio(aux2.getNomusu_mneg(), aux2.getPass_mneg());
                                    if(aux3 != null){
                                        sesionneg.setAttribute("Negocio", aux3);
                                        String cor = aux3.getCor_mneg();
                                        cor = aes.getAESDecrypt(cor);
                                        String destinatario =  cor; //A quien le quieres escribir.
                                        String asunto = "Bienvenido a Ordereat " + aux3.getNomusu_mneg();
                                        String cuerpo = aux3.getNomneg_mneg() + " ha sido registrado con exito. Ordereat es una pagina para la administracion de ordenes."
                                                + "Atentamente Ordereat";
                                        try{
                                            mail.enviarConGMail(destinatario, asunto, cuerpo);
                                        }                                        
                                        catch(Exception e){
                                            
                                        }                                        
                                        pagina = "ordenes.jsp";
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
        } catch (AddressException ex) {
            Logger.getLogger(Validar_Token_Inicial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Validar_Token_Inicial.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (AddressException ex) {
            Logger.getLogger(Validar_Token_Inicial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Validar_Token_Inicial.class.getName()).log(Level.SEVERE, null, ex);
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
