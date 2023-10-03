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
import java.util.Vector;

/**
 *
 * @author MARIA DEL REFUGIO
 */
public class VOrden {
    private int id_mord, cant_dord, id_mneg;
    private String nom_cpla;
    private boolean est_mord;

    public Vector<VOrden> Detalle_Orden_Actuales(int neg) throws SQLException{
        Vector<VOrden> vord = new Vector<VOrden>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from vwdetorden where id_mneg = ?";
            
            ps = con.prepareStatement(q);
            ps.setInt(1, neg);
            rs = ps.executeQuery();
            
            while(rs.next()){
                VOrden ord = new VOrden();
                ord.setEst_mord(false);
                ord.setId_mord(rs.getInt("id_mord"));
                ord.setId_mneg(neg);
                ord.setCant_dord(rs.getInt("cant_dord"));
                ord.setNom_cpla(rs.getString("nom_cpla"));
                vord.add(ord);
                
            }
        }
        catch(SQLException sq){
            /**System.out.println("Error al consultar los productos");
            System.out.println(sq.getMessage());*/
            vord = null;
        }
        catch(Exception e){
            vord = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
                
            
            }catch(Exception e){
                vord = null;
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return vord;
    }
    
    public Vector<VOrden> Detalle_Orden_Pagos(int neg) throws SQLException{
        Vector<VOrden> vord = new Vector<VOrden>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from vwdetorden where id_mneg = ?";
            
            ps = con.prepareStatement(q);
            ps.setInt(1, neg);
            rs = ps.executeQuery();
            
            while(rs.next()){
                VOrden ord = new VOrden();
                ord.setEst_mord(false);
                ord.setId_mord(rs.getInt("id_mord"));
                ord.setId_mneg(neg);
                ord.setCant_dord(rs.getInt("cant_dord"));
                ord.setNom_cpla(rs.getString("nom_cpla"));
                vord.add(ord);
                
            }
        }
        catch(SQLException sq){
            /**System.out.println("Error al consultar los productos");
            System.out.println(sq.getMessage());*/
            vord = null;
        }
        catch(Exception e){
            vord = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
                
            
            }catch(Exception e){
                vord = null;
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return vord;
    }
    
    public Vector<VOrden> Detalle_Orden_Historial(int neg) throws SQLException{
        Vector<VOrden> vord = new Vector<VOrden>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from vwdetorden where id_mneg = ?";
            
            ps = con.prepareStatement(q);
            ps.setInt(1, neg);
            rs = ps.executeQuery();
            
            while(rs.next()){
                VOrden ord = new VOrden();
                ord.setEst_mord(false);
                ord.setId_mord(rs.getInt("id_mord"));
                ord.setId_mneg(neg);
                ord.setCant_dord(rs.getInt("cant_dord"));
                ord.setNom_cpla(rs.getString("nom_cpla"));
                vord.add(ord);
                
            }
        }
        catch(SQLException sq){
            /**System.out.println("Error al consultar los productos");
            System.out.println(sq.getMessage());*/
            vord = null;
        }
        catch(Exception e){
            vord = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
                
            
            }catch(Exception e){
                vord = null;
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return vord;
    }

    public VOrden(int id_mord, int cant_dord, int id_mneg, String nom_cpla, boolean est_mord) {
        this.id_mord = id_mord;
        this.cant_dord = cant_dord;
        this.id_mneg = id_mneg;
        this.nom_cpla = nom_cpla;
        this.est_mord = est_mord;
    }

    public VOrden() {
    }

    public int getId_mord() {
        return id_mord;
    }

    public void setId_mord(int id_mord) {
        this.id_mord = id_mord;
    }

    public int getCant_dord() {
        return cant_dord;
    }

    public void setCant_dord(int cant_dord) {
        this.cant_dord = cant_dord;
    }

    public int getId_mneg() {
        return id_mneg;
    }

    public void setId_mneg(int id_mneg) {
        this.id_mneg = id_mneg;
    }

    public String getNom_cpla() {
        return nom_cpla;
    }

    public void setNom_cpla(String nom_cpla) {
        this.nom_cpla = nom_cpla;
    }

    public boolean isEst_mord() {
        return est_mord;
    }

    public void setEst_mord(boolean est_mord) {
        this.est_mord = est_mord;
    }
}
