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
public class DCliente {
    private int id_dcli, numext_dcli, numint_dcli;
    private String nom_dcli, calle_dcli, tel_dcli, col_dcli, mun_dcli, est_dcli, cp_dcli;
    
    public static int Eliminar_DCliente(int id) throws SQLException{
        int estatus = 0;
        PreparedStatement ps = null;
        Connection con = null;
        try{
            con = Conexion.getConexion();
            String q = "delete from dcliente where id_dcli = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            estatus = ps.executeUpdate();
        } catch(SQLException sq){
            ///System.out.println(sq.getMessage());
        } catch(Exception e){
            ///System.out.println(e.getMessage());
        } finally{
            try{
                ps.close();
                con.close();
            } catch(Exception e){
                ///System.out.println(e.getMessage());
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
    
    public static int Max_Cliente() throws SQLException{
        int id = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select max(id_dcli) from dcliente";
            
            ps = con.prepareStatement(q);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                id = rs.getInt("max(id_dcli)");
                break;
            }
            
        }catch(SQLException sq){
            ///System.out.println("Error al consultar los productos");
            ///System.out.println(sq.getMessage());
        }finally{
            try{
                rs.close();
                ps.close();
                con.close();
                
            
            }catch(Exception e){
                ///System.out.println("No encontro la clase");
                ///System.out.println(e.getMessage());
            
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return id;
    }

    public static int Registrar_Cliente (DCliente cli) throws SQLException{
        int estatus = 0;
        PreparedStatement ps = null;
        Connection con = null;
        try{
            con = Conexion.getConexion();
            String q = "insert into dcliente(nom_dcli, calle_dcli, tel_dcli, numext_dcli, numint_dcli, cp_dcli, col_dcli, mun_dcli, est_dcli)"
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(q);
            ps.setString(1, cli.getNom_dcli());
            ps.setString(2, cli.getCalle_dcli());
            ps.setString(3, cli.getTel_dcli());
            ps.setInt(4, cli.getNumext_dcli());
            ps.setInt(5, cli.getNumint_dcli());
            ps.setString(6, cli.getCp_dcli());
            ps.setString(7, cli.getCol_dcli());
            ps.setString(8, cli.getMun_dcli());
            ps.setString(9, cli.getEst_dcli());
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
    
    public DCliente() {
    }

    public DCliente(int id_dcli, int numext_dcli, int numint_dcli, String cp_dcli, String nom_dcli, String calle_dcli, String tel_dcli, String col_dcli, String mun_dcli, String est_dcli) {
        this.id_dcli = id_dcli;
        this.numext_dcli = numext_dcli;
        this.numint_dcli = numint_dcli;
        this.cp_dcli = cp_dcli;
        this.nom_dcli = nom_dcli;
        this.calle_dcli = calle_dcli;
        this.tel_dcli = tel_dcli;
        this.col_dcli = col_dcli;
        this.mun_dcli = mun_dcli;
        this.est_dcli = est_dcli;
    }

    public int getId_dcli() {
        return id_dcli;
    }

    public void setId_dcli(int id_dcli) {
        this.id_dcli = id_dcli;
    }

    public int getNumext_dcli() {
        return numext_dcli;
    }

    public void setNumext_dcli(int numext_dcli) {
        this.numext_dcli = numext_dcli;
    }

    public int getNumint_dcli() {
        return numint_dcli;
    }

    public void setNumint_dcli(int numint_dcli) {
        this.numint_dcli = numint_dcli;
    }

    public String getCp_dcli() {
        return cp_dcli;
    }

    public void setCp_dcli(String cp_dcli) {
        this.cp_dcli = cp_dcli;
    }

    public String getNom_dcli() {
        return nom_dcli;
    }

    public void setNom_dcli(String nom_dcli) {
        this.nom_dcli = nom_dcli;
    }

    public String getCalle_dcli() {
        return calle_dcli;
    }

    public void setCalle_dcli(String calle_dcli) {
        this.calle_dcli = calle_dcli;
    }

    public String getTel_dcli() {
        return tel_dcli;
    }

    public void setTel_dcli(String tel_dcli) {
        this.tel_dcli = tel_dcli;
    }

    public String getCol_dcli() {
        return col_dcli;
    }

    public void setCol_dcli(String col_dcli) {
        this.col_dcli = col_dcli;
    }

    public String getMun_dcli() {
        return mun_dcli;
    }

    public void setMun_dcli(String mun_dcli) {
        this.mun_dcli = mun_dcli;
    }

    public String getEst_dcli() {
        return est_dcli;
    }

    public void setEst_dcli(String est_dcli) {
        this.est_dcli = est_dcli;
    }
}
