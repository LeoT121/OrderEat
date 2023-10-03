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
public class Registro_Negocio extends HttpServlet {

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
            neg = (MNegocio) sesionneg.getAttribute("Negocio");
            MNegocio regneg = new MNegocio();
            Validaciones val = new Validaciones();
            JavaMail mail = new JavaMail();
            AES cif = new AES();
            String nomneg, usu, cor, pass, conf_pass, aviso, cor_enc, pass_enc, cor_des, pass_des, pagina = "ordenes.jsp";
            int estatus = 0;
            if(neg == null){
                neg = new MNegocio();
                nomneg = request.getParameter("Nombre_Negocio");
                usu = request.getParameter("Nombre_Usuario");
                cor = request.getParameter("Correo_Negocio");
                pass = request.getParameter("Password");
                conf_pass = request.getParameter("Confirmar_Password");
                aviso = request.getParameter("Confirmar_Aviso");
                pagina = "error.html";            
                if(val.Validar_Registro_Negocio(nomneg, usu, cor, pass, conf_pass, aviso)){
                    ///System.out.println("Ya pase el primer filtro");
                    cor_enc = cif.getAES(cor);
                    pass_enc = cif.getAES(pass);                    
                    if(neg.Consultar_Disponibilidad(usu, cor_enc)){
                        regneg = new MNegocio(0, usu, pass_enc, nomneg, cor_enc);
                        sesionneg.setAttribute("Previo", regneg);
                        String destinatario =  cor; //A quien le quieres escribir.
                        String asunto = "Autenticacion de correo";
                        String cuerpo = "Este correo es enviado debido a que usted registro este dominio de correo, debe de ingresar el codigo para que su cuenta sea activada.\n"
                                + "El codigo es:\n";
                        String codigo = JWT.createJWT("121" ,usu);                     
                        cuerpo += codigo;
                        cuerpo += "\nEl token tiene una expiracion de 3 minutos.\n"
                                + "Atentamente Ordereat.";
                        try{
                            mail.enviarConGMail(destinatario, asunto, cuerpo);
                            pagina = "verificacion_inicial.jsp";
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
        } catch (AddressException ex) {
            Logger.getLogger(Registro_Negocio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registro_Negocio.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Registro_Negocio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Registro_Negocio.class.getName()).log(Level.SEVERE, null, ex);
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
