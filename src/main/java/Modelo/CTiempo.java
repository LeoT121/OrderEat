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
public class CTiempo {
    private int id_ctie, id_mneg;
    private String nom_ctie;
    
    public Vector<CTiempo> Tiempos(int id) throws SQLException{
        Vector<CTiempo> tiempos = new Vector<CTiempo>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from ctiempo where id_mneg = ? ";            
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                CTiempo tiem = new CTiempo();
                tiem.setId_ctie(rs.getInt("id_ctie"));
                tiem.setId_mneg(id);
                tiem.setNom_ctie(rs.getString("nom_ctie"));
                ///System.out.println(tiem.getNom_ctie());
                tiempos.add(tiem);
            }
        }
        catch(SQLException sq){
            ///System.out.println("Error al consultar los productos");
            ///System.out.println(sq.getMessage());
            tiempos = null;
        }
        catch(Exception e){
            tiempos = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
                
            
            }catch(Exception e){
                tiempos = null;
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return tiempos;
    }
    
    public boolean Consultar_Disponibilidad(String nombre, int id) throws SQLException{
        boolean bandera = true;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from ctiempo where id_mneg = ? and nom_ctie = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            ps.setString(2, nombre);
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
    
    public static int Registrar_Tiempo(CTiempo ctie) throws SQLException{
        int estatus = 0;
        PreparedStatement ps = null;
        Connection con = null;
        try{
            con = Conexion.getConexion();
            String q = "insert into ctiempo(id_mneg, nom_ctie)"
                    + "values(?, ?)";
            ps = con.prepareStatement(q);
            ps.setInt(1, ctie.getId_mneg());                 
            ps.setString(2, ctie.getNom_ctie());
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

    public CTiempo() {
    }

    public CTiempo(int id_ctie, int id_mneg, String nom_ctie) {
        this.id_ctie = id_ctie;
        this.id_mneg = id_mneg;
        this.nom_ctie = nom_ctie;
    }

    public int getId_ctie() {
        return id_ctie;
    }

    public void setId_ctie(int id_ctie) {
        this.id_ctie = id_ctie;
    }

    public int getId_mneg() {
        return id_mneg;
    }

    public void setId_mneg(int id_mneg) {
        this.id_mneg = id_mneg;
    }

    public String getNom_ctie() {
        return nom_ctie;
    }

    public void setNom_ctie(String nom_ctie) {
        this.nom_ctie = nom_ctie;
    }
    
}
