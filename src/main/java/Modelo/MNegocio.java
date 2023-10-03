/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Control.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MARIA DEL REFUGIO
 */
public class MNegocio {
    private int id_mneg;
    private String nomusu_mneg, pass_mneg, nomneg_mneg, cor_mneg;
    
    public MNegocio Login_Negocio (String nomusu, String pass) throws SQLException{
        MNegocio neg = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from mnegocio where nomusu_mneg = ? and pass_mneg = ?";
            ps = con.prepareStatement(q);
            ps.setString(1, nomusu);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while(rs.next()){
                neg = new MNegocio();
                neg.setId_mneg(rs.getInt("id_mneg"));
                neg.setCor_mneg(rs.getString("cor_mneg"));
                neg.setNomneg_mneg(rs.getString("nomneg_mneg"));
                neg.setNomusu_mneg(rs.getString("nomusu_mneg"));
                neg.setPass_mneg(rs.getString("pass_mneg"));
                break;
            }
            
        }
        catch (SQLException sq){
            ///System.out.println(sq.getStackTrace());
            neg = null;
        }
        catch (Exception e){
            ///System.out.println(e.getMessage());
            neg = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e){
                neg = null;
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return neg;
    }
    
    public boolean Consultar_Disponibilidad(String nomusu, String cor) throws SQLException{
        boolean bandera = true;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from mnegocio where nomusu_mneg = ? or cor_mneg = ?";
            ps = con.prepareStatement(q);
            ps.setString(1, nomusu);
            ps.setString(2, cor);
            rs = ps.executeQuery();
            while(rs.next()){
                bandera = false;
                break;
            }
            
        }
        catch (SQLException sq){
            ///System.out.println(sq.getStackTrace());
            bandera = false;
        }
        catch (Exception e){
            ///System.out.println(e.getMessage());
            bandera = false;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e){
                bandera = false;
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return bandera;
    }
    
    public int Registrar_Negocio(MNegocio mneg) throws SQLException{
        int estatus = 0;
        PreparedStatement ps = null;
        Connection con = null;
        try{
            con = Conexion.getConexion();
            String q = "insert into mnegocio(nomusu_mneg, pass_mneg, nomneg_mneg, cor_mneg)"
                    + "values(?, ?, ?, ?)";
            ps = con.prepareStatement(q);
            ps.setString(1, mneg.getNomusu_mneg());
            ps.setString(2, mneg.getPass_mneg());
            ps.setString(3, mneg.getNomneg_mneg());
            ps.setString(4, mneg.getCor_mneg());
            estatus = ps.executeUpdate();
        } 
        catch(SQLException sq){
            
        } 
        catch(Exception e){
           
        } 
        finally{
            try{
                ps.close();
                con.close();
            } catch(Exception e){
                
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return estatus;
    }
    
    public MNegocio (){
        
    }
    
    public MNegocio(int id_mneg, String nomusu_mneg, String pass_mneg, String nomneg_mneg, String cor_mneg) {
        this.id_mneg = id_mneg;
        this.nomusu_mneg = nomusu_mneg;
        this.pass_mneg = pass_mneg;
        this.nomneg_mneg = nomneg_mneg;
        this.cor_mneg = cor_mneg;
    }
    
    public int getId_mneg() {
        return id_mneg;
    }

    public void setId_mneg(int id_mneg) {
        this.id_mneg = id_mneg;
    }

    public String getNomusu_mneg() {
        return nomusu_mneg;
    }

    public void setNomusu_mneg(String nomusu_mneg) {
        this.nomusu_mneg = nomusu_mneg;
    }

    public String getPass_mneg() {
        return pass_mneg;
    }

    public void setPass_mneg(String pass_mneg) {
        this.pass_mneg = pass_mneg;
    }

    public String getNomneg_mneg() {
        return nomneg_mneg;
    }

    public void setNomneg_mneg(String nomneg_mneg) {
        this.nomneg_mneg = nomneg_mneg;
    }

    public String getCor_mneg() {
        return cor_mneg;
    }

    public void setCor_mneg(String cor_mneg) {
        this.cor_mneg = cor_mneg;
    }
}
