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
public class Rel_Plat_Ins {
    private int id_mins, id_cpla, id_rpi;
    private float can_rpi;
    
    public int Registrar_Relacion(Rel_Plat_Ins mneg) throws SQLException{
        int estatus = 0;
        PreparedStatement ps = null;
        Connection con = null;
        try{
            con = Conexion.getConexion();
            String q = "insert into relplatins(id_mins, id_cpla, can_rpi)"
                    + "values(?, ?, ?)";
            ps = con.prepareStatement(q);
            ps.setInt(1, mneg.getId_mins());
            ps.setInt(2, mneg.getId_cpla());
            ps.setFloat(3, mneg.getCan_rpi());
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
    public Vector<Rel_Plat_Ins> DetallesPlatIns() throws SQLException{
        Vector<Rel_Plat_Ins> insumo = new Vector<Rel_Plat_Ins>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from relplatins";
            
            ps = con.prepareStatement(q);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Rel_Plat_Ins ins = new Rel_Plat_Ins();
                ins.setCan_rpi(rs.getFloat("can_rpi"));
                ins.setId_cpla(rs.getInt("id_cpla"));
                ins.setId_mins(rs.getInt("id_mins"));
                ins.setId_rpi(rs.getInt("id_rpi"));
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
    

    public Rel_Plat_Ins(int id_mins, int id_cpla, int id_rpi, float can_rpi) {
        this.id_mins = id_mins;
        this.id_cpla = id_cpla;
        this.id_rpi = id_rpi;
        this.can_rpi = can_rpi;
    }

    public Rel_Plat_Ins() {
    }

    public int getId_mins() {
        return id_mins;
    }

    public void setId_mins(int id_mins) {
        this.id_mins = id_mins;
    }

    public int getId_cpla() {
        return id_cpla;
    }

    public void setId_cpla(int id_cpla) {
        this.id_cpla = id_cpla;
    }

    public int getId_rpi() {
        return id_rpi;
    }

    public void setId_rpi(int id_rpi) {
        this.id_rpi = id_rpi;
    }

    public float getCan_rpi() {
        return can_rpi;
    }

    public void setCan_rpi(float can_rpi) {
        this.can_rpi = can_rpi;
    }
    
}
