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
public class VWIPlat {
    private int id_cpla;
    private String nom_cpla;
    private float can_rpi;
    
    public Vector<VWIPlat> DetallesPlat() throws SQLException{
        Vector<VWIPlat> insumo = new Vector<VWIPlat>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from vwiplat";
            
            ps = con.prepareStatement(q);
            rs = ps.executeQuery();
            
            while(rs.next()){
                VWIPlat ins = new VWIPlat();
                ins.setCan_rpi(rs.getFloat("can_rpi"));
                ins.setNom_cpla(rs.getString("nom_mins"));
                ins.setId_cpla(rs.getInt("id_cpla"));
                insumo.add(ins);
            }
        }
        catch(SQLException sq){
            /**System.out.println("Error al consultar los productos");
            System.out.println(sq.getMessage());*/
            insumo = null;
        }
        catch(Exception e){
            insumo = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
                
            
            }catch(Exception e){
                insumo = null;
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return insumo;
    }

    public VWIPlat() {
    }

    public VWIPlat(int id_cpla, String nom_cpla, float can_rpi) {
        this.id_cpla = id_cpla;
        this.nom_cpla = nom_cpla;
        this.can_rpi = can_rpi;
    }

    public int getId_cpla() {
        return id_cpla;
    }

    public void setId_cpla(int id_cpla) {
        this.id_cpla = id_cpla;
    }

    public String getNom_cpla() {
        return nom_cpla;
    }

    public void setNom_cpla(String nom_cpla) {
        this.nom_cpla = nom_cpla;
    }

    public float getCan_rpi() {
        return can_rpi;
    }

    public void setCan_rpi(float can_rpi) {
        this.can_rpi = can_rpi;
    }
    
}
